package com.inedo.buildmaster;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiPackageDeployment;
import com.inedo.buildmaster.domain.ApiVariable;
import com.inedo.jenkins.GlobalConfig;
import com.inedo.jenkins.JenkinsHelper;
import com.inedo.jenkins.VariableInjectionAction;

import hudson.model.Run;
import hudson.model.TaskListener;

/**
 * Does the real work of Trigger a BuildMaster build, has been seperated out from the Builder and Publisher actions
 * so that the code can be shared between them. 
 * 
 * @author Andrew Sumner
 */
public class BuildHelper {
    public static final String DEFAULT_BUILD_NUMBER = "${BUILDMASTER_BUILD_NUMBER}";

    public static boolean triggerBuild(Run<?, ?> run, TaskListener listener, Triggerable trigger) throws IOException, InterruptedException {
        JenkinsHelper helper = new JenkinsHelper(run, listener);
                
        BuildMasterApi buildmaster = new BuildMasterApi(helper.getLogWriter());

        int applicationId = Integer.valueOf(helper.expandVariable(trigger.getApplicationId()));
        String releaseNumber = helper.expandVariable(trigger.getReleaseNumber());
        String buildNumber = helper.expandVariable(trigger.getBuildNumber());

        if (buildmaster.getApplication(applicationId) == null) {
            JenkinsHelper.fail("Unknown application id " + applicationId);
            return false;
        }

        // This is a fail safe step - BuildMaster can tie itself in knots if a new build is created while and existing
        // one is being performed.
        buildmaster.waitForExistingDeploymentsToComplete(applicationId, releaseNumber);

        Map<String, String> variablesList = new HashMap<>();

        if (trigger.isSetBuildVariables() && trigger.getSetBuildVariables().isPreserveVariables()) {
            variablesList = getVariablesListExpanded(run, listener, trigger.getSetBuildVariables().getVariables());

            helper.getLogWriter().info("Gather previous builds build variables");
            String prevBuildNumber = buildmaster.getReleaseCurrentPackageNumber(applicationId, releaseNumber);
            ApiVariable[] variables = buildmaster.getPackageVariables(applicationId, releaseNumber, prevBuildNumber);

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

        if (buildNumber != null && !buildNumber.isEmpty() && !DEFAULT_BUILD_NUMBER.equals(buildNumber)) {
            helper.getLogWriter().info("Create BuildMaster build with BuildNumber=" + buildNumber);
            apiPackage = buildmaster.createPackage(applicationId, releaseNumber, buildNumber, variablesList);

            if (!apiPackage.releasePackage.number.equals(buildNumber)) {
                helper.getLogWriter()
                        .info(String.format("Warning, requested build number '%s' does not match that returned from BuildMaster '%s'.", buildNumber,
                                apiPackage.releasePackage.number));
            }
        } else {
            helper.getLogWriter().info("Create BuildMaster build");
            apiPackage = buildmaster.createPackage(applicationId, releaseNumber, variablesList);

            helper.getLogWriter().info("Inject environment variable BUILDMASTER_BUILD_NUMBER=" + apiPackage.releasePackage.number);
            run.addAction(new VariableInjectionAction("BUILDMASTER_BUILD_NUMBER", apiPackage.releasePackage.number));
        }

        if (trigger.isWaitTillBuildCompleted()) {
            helper.getLogWriter().info("Wait till build completed");
            return buildmaster.waitForDeploymentsToComplete(apiPackage.deployments, trigger.getWaitTillBuildCompleted().isPrintLogOnFailure());
        }

        return true;
    }

    // private static String expandVariable(AbstractBuild<?, ?> build, BuildListener listener, String variable) {
    // if (variable == null || variable.isEmpty()) {
    // return variable;
    // }
    //
    // String expanded = variable;
    //
    // try {
    // expanded = build.getEnvironment(listener).expand(variable);
    // } catch (Exception e) {
    // helper.getLogWriter().info("Exception thrown expanding '" + variable + "' : " + e.getClass().getName() + " " + e.getMessage());
    // }
    //
    // return expanded;
    // }

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
        
        if (!GlobalConfig.validateBuildMasterConfig()) {
            helper.getLogWriter().error("Please configure BuildMaster Plugin global settings");
            return false;
        }
        
        BuildMasterApi buildmaster = new BuildMasterApi(helper.getLogWriter());
        
        int applicationId = Integer.valueOf(helper.expandVariable(builder.getApplicationId()));
        String releaseNumber = helper.expandVariable(builder.getReleaseNumber());
        String buildNumber = helper.expandVariable(builder.getBuildNumber());
        String toStage = helper.expandVariable(builder.getToStage());

        if (buildmaster.getApplication(applicationId) == null) {
            JenkinsHelper.fail("Unknown application id " + applicationId);
            return false;
        }
        
        // This is a fail safe step - BuildMaster can tie itself in knots if a new build is created while and existing
        // one is being performed.
        buildmaster.waitForExistingDeploymentsToComplete(applicationId, releaseNumber);

        if (toStage == null || toStage.isEmpty()) {
            helper.getLogWriter().info("Deploy to next stage");
        } else {
            helper.getLogWriter().info("Deploy to " + toStage + " stage");
        }
        
        ApiDeployment[] deployments = buildmaster.deployPackageToStage(applicationId, releaseNumber, buildNumber, toStage);
                    
//        helper.getLogWriter().info("Inject environment variable BUILDMASTER_BUILD_NUMBER=" + apiPackage.number);
//        build.addAction(new VariableInjectionAction("BUILDMASTER_BUILD_NUMBER", apiPackage.number));
        
        if (builder.isWaitTillBuildCompleted()) {
            helper.getLogWriter().info("Wait till build completed");
            return buildmaster.waitForDeploymentsToComplete(deployments, builder.getWaitTillBuildCompleted().isPrintLogOnFailure());
        }

        return true;
    }
}
