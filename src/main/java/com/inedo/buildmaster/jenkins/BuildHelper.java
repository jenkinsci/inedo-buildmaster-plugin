package com.inedo.buildmaster.jenkins;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiPackageDeployment;
import com.inedo.buildmaster.domain.ApiVariable;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.jenkins.utils.JenkinsHelper;

import hudson.AbortException;
import hudson.model.Run;
import hudson.model.TaskListener;

/**
 * Does the real work of Trigger a BuildMaster build, has been seperated out from the Builder and Publisher actions
 * so that the code can be shared between them. 
 * 
 * @author Andrew Sumner
 */
public class BuildHelper {
    public static final String DEFAULT_PACKAGE_NUMBER = "${BUILDMASTER_PACKAGE_NUMBER}";

    public static ApiPackageDeployment createPackage(Run<?, ?> run, TaskListener listener, ICreatePackage trigger) throws IOException, InterruptedException {
        JenkinsHelper helper = new JenkinsHelper(run, listener);

        if (!GlobalConfig.isRequiredFieldsConfigured()) {
            throw new AbortException("Please configure BuildMaster Plugin global settings");
        }

        BuildMasterApi buildmaster = new BuildMasterApi(helper.getLogWriter());

        int applicationId = Integer.valueOf(helper.expandVariable(trigger.getApplicationId()));
        String releaseNumber = helper.expandVariable(trigger.getReleaseNumber());
        String packageNumber = helper.expandVariable(trigger.getPackageNumber());

        Application application = buildmaster.getApplication(applicationId);

        if (application == null) {
            JenkinsHelper.fail("Unknown application id " + applicationId);
            return null;
        }

        Map<String, String> variablesList = new HashMap<>();

        if (trigger.isSetBuildVariables() && trigger.getSetBuildVariables().isPreserveVariables()) {
            variablesList = getVariablesListExpanded(run, listener, trigger.getSetBuildVariables().getVariables());

            helper.getLogWriter().info("Gather previous builds build variables");
            String currentPackageNumber = buildmaster.getReleaseCurrentPackageNumber(applicationId, releaseNumber);
            ApiVariable[] variables = buildmaster.getPackageVariables(application.Application_Name, releaseNumber, currentPackageNumber);

            for (ApiVariable variable : variables) {
                if (!variablesList.containsKey(variable.name)) {
                    variablesList.put(variable.name, variable.value);
                }
            }
        }

        if (trigger.isEnableReleaseDeployable()) {
            String deployableId = helper.expandVariable(trigger.getEnableReleaseDeployable().getDeployableId());
            helper.getLogWriter().info("Enable release deployable with id=" + deployableId);

            buildmaster.enableReleaseDeployable(applicationId, releaseNumber, Integer.valueOf(deployableId));
        }

        ApiPackageDeployment apiPackage;

        if (packageNumber != null && !packageNumber.equalsIgnoreCase("null") && !packageNumber.isEmpty() && !DEFAULT_PACKAGE_NUMBER.equals(packageNumber)) {
            helper.getLogWriter().info("Create BuildMaster build with PackageNumber=" + packageNumber);
            apiPackage = buildmaster.createPackage(applicationId, releaseNumber, packageNumber, variablesList, trigger.getDeployToFirstStage());

            if (!apiPackage.releasePackage.number.equals(packageNumber)) {
                helper.getLogWriter().info(String.format("Warning, requested build number '%s' does not match that returned from BuildMaster '%s'.",
                        packageNumber,
                        apiPackage.releasePackage.number));
            }
        } else {
            helper.getLogWriter().info("Create BuildMaster package");
            apiPackage = buildmaster.createPackage(applicationId, releaseNumber, variablesList, trigger.getDeployToFirstStage());
        }

        if (trigger.isWaitTillBuildCompleted()) {
            helper.getLogWriter().info("Wait till deployment completed");
            if (!buildmaster.waitForDeploymentsToComplete(apiPackage.deployments, trigger.getWaitTillBuildCompleted().isPrintLogOnFailure())) {
                return null;
            }
        }

        return apiPackage;
    }

    public static Map<String, String> getVariablesListExpanded(Run<?, ?> run, TaskListener listener, String variables) throws IOException {
        JenkinsHelper helper = new JenkinsHelper(run, listener);
        Map<String, String> variablesList = getVariablesList(variables);

        for (Map.Entry<String, String> variable : variablesList.entrySet()) {
            variable.setValue(helper.expandVariable(variable.getValue()));
        }

        return variablesList;
    }

    public static Map<String, String> getVariablesList(String variables) throws IOException {
        Map<String, String> variablesList = new HashMap<>();

        if (variables != null) {
            String[] variablesArray = variables.split("\n");

            for (String value : variablesArray) {
                value = value.trim();
                if (value.isEmpty())
                    continue;
                if (value.startsWith("#"))
                    continue;

                int pos = value.indexOf("=");

                if (pos < 0) {
                    JenkinsHelper.fail(value + " is not in the format 'variable=value'");
                }

                variablesList.put(value.substring(0, pos).trim(), value.substring(pos + 1).trim());
            }
        }

        return variablesList;
    }

    public static boolean deployToStage(Run<?, ?> run, TaskListener listener, DeployToStageBuilder builder) throws IOException, InterruptedException {
        JenkinsHelper helper = new JenkinsHelper(run, listener);
        
        if (!GlobalConfig.isRequiredFieldsConfigured()) {
            throw new AbortException("Please configure BuildMaster Plugin global settings");
        }
        
        BuildMasterApi buildmaster = new BuildMasterApi(helper.getLogWriter());
        
        int applicationId = Integer.valueOf(helper.expandVariable(builder.getApplicationId()));
        String releaseNumber = helper.expandVariable(builder.getReleaseNumber());
        String packageNumber = helper.expandVariable(builder.getPackageNumber());
        String toStage = helper.expandVariable(builder.getToStage());

        if (buildmaster.getApplication(applicationId) == null) {
            throw new AbortException("Unknown application id " + applicationId);
        }
        
        ApiDeployment[] deployments = buildmaster.deployPackageToStage(applicationId, releaseNumber, packageNumber, toStage);

        if (builder.isWaitTillBuildCompleted()) {
            helper.getLogWriter().info("Wait till deployment completed");
            return buildmaster.waitForDeploymentsToComplete(deployments, builder.getWaitTillBuildCompleted().isPrintLogOnFailure());
        }

        return true;
    }
}
