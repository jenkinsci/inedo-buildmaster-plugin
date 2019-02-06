package com.inedo.buildmaster.jenkins;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiReleaseBuild;
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

    public static ApiReleaseBuild createPackage(Run<?, ?> run, TaskListener listener, ICreatePackage trigger) throws IOException, InterruptedException {
        JenkinsHelper helper = new JenkinsHelper(run, listener);

        if (!GlobalConfig.isRequiredFieldsConfigured()) {
            throw new AbortException("Please configure BuildMaster Plugin global settings");
        }

        BuildMasterApi buildmaster = new BuildMasterApi(helper.getLogWriter());

        int applicationId = Integer.valueOf(helper.expandVariable(trigger.getApplicationId()));
        String releaseNumber = helper.expandVariable(trigger.getReleaseNumber());
        String buildNumber = helper.expandVariable(trigger.getPackageNumber());

        Application application = buildmaster.getApplication(applicationId);

        if (application == null) {
            JenkinsHelper.fail("Unknown application id " + applicationId);
            return null;
        }

        Map<String, String> variablesList = new HashMap<>();

        if (trigger.isPackageVariables()) {
            variablesList = getVariablesListExpanded(run, listener, trigger.getPackageVariables().getVariables());

            if (trigger.getPackageVariables().isPreserveVariables()) {
                helper.getLogWriter().info("Gather previous builds build variables");
                String currentBuildNumber = buildmaster.getReleaseCurrentBuildNumber(applicationId, releaseNumber);
                ApiVariable[] variables = buildmaster.getBuildVariables(application.Application_Name, releaseNumber, currentBuildNumber);

                for (ApiVariable variable : variables) {
                    if (!variablesList.containsKey(variable.name)) {
                        variablesList.put(variable.name, variable.value);
                    }
                }
            }
        }

        if (trigger.isEnableReleaseDeployable()) {
            String deployableId = helper.expandVariable(trigger.getEnableReleaseDeployable().getDeployableId());
            helper.getLogWriter().info("Enable release deployable with id=" + deployableId);

            buildmaster.enableReleaseDeployable(applicationId, releaseNumber, Integer.valueOf(deployableId));
        }

        ApiReleaseBuild releasePackage;

        if (buildNumber != null && !buildNumber.equalsIgnoreCase("null") && !buildNumber.isEmpty() && !DEFAULT_PACKAGE_NUMBER.equals(buildNumber)) {
            helper.getLogWriter().info("Create build %s for the %s application, release %s", buildNumber, application.Application_Name, releaseNumber);
            releasePackage = buildmaster.createBuild(applicationId, releaseNumber, buildNumber, variablesList);

            if (!releasePackage.number.equals(buildNumber)) {
                helper.getLogWriter().info("Warning, requested build number '%s' does not match that returned from BuildMaster '%s'.",
                        buildNumber,
                        releasePackage.number);
            }
        } else {
            helper.getLogWriter().info("Create build for the %s application, release %s", application.Application_Name, releaseNumber);
            releasePackage = buildmaster.createBuild(applicationId, releaseNumber, variablesList);
        }

        helper.getLogWriter().info("Build %s has been created", releasePackage.number);

        if (trigger.isDeployToFirstStage()) {
            helper.getLogWriter().info("Deploy build %s to the first stage", releasePackage.number);
            ApiDeployment[] deployments = buildmaster.deployBuildToStage(applicationId, releaseNumber, releasePackage.number, null, null);

            if (trigger.getDeployToFirstStage().isWaitUntilDeploymentCompleted()) {
                if (!buildmaster.waitForDeploymentToComplete(deployments, trigger.getDeployToFirstStage().isPrintLogOnFailure())) {
                    return null;
                }
            }
        }

        return releasePackage;
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
        String buildNumber = helper.expandVariable(builder.getPackageNumber());
        String stage = helper.expandVariable(builder.getStage());

        if (buildmaster.getApplication(applicationId) == null) {
            throw new AbortException("Unknown application id " + applicationId);
        }
        
        Map<String, String> variablesList = new HashMap<>();

        if (builder.isDeployVariables()) {
            variablesList = getVariablesListExpanded(run, listener, builder.getDeployVariables().getVariables());
        }

        Application application = buildmaster.getApplication(applicationId);

        if (application == null) {
            JenkinsHelper.fail("Unknown application id " + applicationId);
            return false;
        }

        if (Strings.isNullOrEmpty(stage)) {
            helper.getLogWriter().info("Deploy build %s to the next stage for the %s application, release %s", buildNumber, application.Application_Name, releaseNumber);
        } else {
            helper.getLogWriter().info("Deploy build %s to the '" + stage + "' stage for the %s application, release %s", buildNumber, application.Application_Name,
                    releaseNumber);
        }
        ApiDeployment[] deployments = buildmaster.deployBuildToStage(applicationId, releaseNumber, buildNumber, variablesList, stage);

        if (builder.isWaitUntilDeploymentCompleted()) {
            return buildmaster.waitForDeploymentToComplete(deployments, builder.isPrintLogOnFailure());
        }

        return true;
    }
}
