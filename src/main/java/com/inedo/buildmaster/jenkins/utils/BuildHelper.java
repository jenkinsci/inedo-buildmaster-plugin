package com.inedo.buildmaster.jenkins.utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Strings;
import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiBuild;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.jenkins.DeployToStageBuilder;

import hudson.AbortException;
import hudson.model.Run;
import hudson.model.TaskListener;

/**
 * Does the real work of Trigger a BuildMaster build, has been separated out from the Builder and Publisher actions
 * so that the code can be shared between them. 
 * 
 * @author Andrew Sumner
 */
public class BuildHelper {

    public static ApiBuild createBuild(Run<?, ?> run, TaskListener listener, ICreateBuild trigger) throws IOException, InterruptedException {
        JenkinsHelper helper = new JenkinsHelper(run, listener);

        if (!GlobalConfig.isRequiredFieldsConfigured()) {
            throw new AbortException("Please configure BuildMaster Plugin global settings");
        }

        BuildMasterApi buildmaster = new BuildMasterApi(helper.getLogWriter());

        String applicationIdentifier = helper.expandVariable(trigger.getApplicationId());
        Application application = buildmaster.getApplication(applicationIdentifier);

        if (application == null) {
            throw new AbortException("Unknown application id " + applicationIdentifier);
        }

        String releaseNumber = buildmaster.getReleaseNumber(application, helper.expandVariable(trigger.getReleaseNumber()));
        Map<String, String> variablesList = getVariablesListExpanded(run, listener, trigger.getVariables());

        helper.getLogWriter().info("Create build for {0}, release {1}", application.Application_Name, releaseNumber);
        ApiBuild releaseBuild = buildmaster.createBuild(application.Application_Id, releaseNumber, variablesList);
        helper.getLogWriter().info("Build {0} has been created", releaseBuild.number);

        if (trigger.isDeployToFirstStage()) {
            helper.getLogWriter().info("Deploy build {0} to the first stage", releaseBuild.number);
            ApiDeployment[] deployments = buildmaster.deployBuildToStage(application.Application_Id, releaseNumber, releaseBuild.number, null, null, false);

            if (trigger.getDeployToFirstStage().isWaitUntilCompleted()) {
                if (!buildmaster.waitForDeploymentToComplete(deployments, trigger.getDeployToFirstStage().isPrintLogOnFailure())) {
                    return null;
                }
            }
        }

        return releaseBuild;
    }

    public static Map<String, String> getVariablesListExpanded(Run<?, ?> run, TaskListener listener, String variables) throws AbortException {
        JenkinsHelper helper = new JenkinsHelper(run, listener);
        Map<String, String> variablesList = getVariablesList(variables);

        for (Map.Entry<String, String> variable : variablesList.entrySet()) {
            variable.setValue(helper.expandVariable(variable.getValue()));
        }

        return variablesList;
    }

    public static Map<String, String> getVariablesList(String variables) throws AbortException {
        Map<String, String> variablesList = new HashMap<>();

        if (variables != null && !variables.isEmpty()) {
            String[] variablesArray = variables.split("\n");

            for (String value : variablesArray) {
                value = value.trim();
                if (value.isEmpty())
                    continue;
                if (value.startsWith("#"))
                    continue;

                int pos = value.indexOf("=");

                if (pos < 0) {
                    throw new AbortException(value + " is not in the format 'variable=value'");
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

        String applicationIdentifier = helper.expandVariable(builder.getApplicationId());
        Application application = buildmaster.getApplication(applicationIdentifier);

        if (application == null) {
            throw new AbortException("Unknown application id " + applicationIdentifier);
        }

        String releaseNumber = buildmaster.getReleaseNumber(application, helper.expandVariable(builder.getReleaseNumber()));
        String buildNumber = helper.expandVariable(builder.getBuildNumber());
        String stage = helper.expandVariable(builder.getStage());
        Map<String, String> variablesList = getVariablesListExpanded(run, listener, builder.getVariables());

        if (Strings.isNullOrEmpty(stage)) {
            helper.getLogWriter().info("Deploy build {0} to the next stage for {1}, release {2}", buildNumber, application.Application_Name, releaseNumber);
        } else {
            helper.getLogWriter().info("Deploy build {0} to the {1} stage for {2}, release {3}", buildNumber, stage, application.Application_Name, releaseNumber);
        }
        ApiDeployment[] deployments = buildmaster.deployBuildToStage(application.Application_Id, releaseNumber, buildNumber, variablesList, stage, builder.isForce());

        if (builder.isWaitUntilCompleted()) {
            return buildmaster.waitForDeploymentToComplete(deployments, builder.isPrintLogOnFailure());
        }

        return true;
    }
}
