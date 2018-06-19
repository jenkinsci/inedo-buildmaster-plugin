package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import com.inedo.buildmaster.jenkins.buildOption.WaitTillCompleted;

import hudson.AbortException;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import jenkins.tasks.SimpleBuildStep;

/**
 * Deploy a package to the requested stage in BuildMaster.
 *
 * See https://github.com/jenkinsci/pipeline-plugin/blob/master/DEVGUIDE.md#user-content-build-wrappers-1 for tips on 
 * Jenkins pipeline support 
 * 
 * @author Andrew Sumner
 */
public class DeployToStageBuilder extends Builder implements SimpleBuildStep {
    private String toStage = "";
    private WaitTillCompleted waitTillBuildCompleted = null;
    private String applicationId = DescriptorImpl.defaultApplicationId;
    private String releaseNumber = DescriptorImpl.defaultReleaseNumber;
    private String buildNumber = DescriptorImpl.defaultBuildNumber;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public DeployToStageBuilder() {
    }

    @DataBoundSetter
    public final void setToStage(String toStage) {
        this.toStage = toStage;
    }

    @DataBoundSetter
    public final void setWaitTillBuildCompleted(WaitTillCompleted waitTillBuildCompleted) {
        this.waitTillBuildCompleted = waitTillBuildCompleted;
    }

    @DataBoundSetter
    public final void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    @DataBoundSetter
    public final void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    @DataBoundSetter
    public final void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }

    public String getToStage() {
        return toStage;
    }

    public boolean isWaitTillBuildCompleted() {
        return waitTillBuildCompleted != null;
    }

    public WaitTillCompleted getWaitTillBuildCompleted() {
        return waitTillBuildCompleted;
    }
    
    public String getApplicationId() {
        return applicationId;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    public String getBuildNumber() {
        return buildNumber;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        if (!BuildHelper.deployToStage(run, listener, this)) {
            throw new AbortException("Deployment failed");
        }
    }

    @Symbol("buildMasterDeployToStage")
    @Extension
    // This indicates to Jenkins that this is an implementation of an extension
    // point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        public static final String defaultApplicationId = "${BUILDMASTER_APPLICATION_ID}";
        public static final String defaultReleaseNumber = "${BUILDMASTER_RELEASE_NUMBER}";
        public static final String defaultBuildNumber = BuildHelper.DEFAULT_BUILD_NUMBER;

        public DescriptorImpl() {
            super(DeployToStageBuilder.class);
        }

        @SuppressWarnings("rawtypes")
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        @Override
        public String getDisplayName() {
            return "BuildMaster: Deploy To Stage";
        }
    }
}
