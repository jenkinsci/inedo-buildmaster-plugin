package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.SetBuildVariables;
import com.inedo.buildmaster.jenkins.buildOption.WaitTillCompleted;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import hudson.util.FormValidation;

/**
 * 
 * The TriggerBuildPostBuildStep will trigger a build in BuildMaster for a selected application and release
 * as a post-build action, after the build has completed.
 * 
 * @author Andrew Sumner 
 */
public class TriggerBuildPublisher extends Recorder implements Triggerable {
    private WaitTillCompleted waitTillBuildCompleted = null;
    private SetBuildVariables setBuildVariables = null;
    private EnableReleaseDeployable enableReleaseDeployable = null;
    private boolean deployToFirstStage = true;
    private String applicationId = "${BUILDMASTER_APPLICATION_ID}";
    private String releaseNumber = "${BUILDMASTER_RELEASE_NUMBER}";
    private String buildNumber = "${BUILDMASTER_BUILD_NUMBER}";

    @DataBoundConstructor
    public TriggerBuildPublisher() {
    }

    @DataBoundSetter
    public final void setWaitTillBuildCompleted(WaitTillCompleted waitTillBuildCompleted) {
        this.waitTillBuildCompleted = waitTillBuildCompleted;
    }

    @DataBoundSetter
    public final void setSetBuildVariables(SetBuildVariables setBuildVariables) {
        this.setBuildVariables = setBuildVariables;
    }

    @DataBoundSetter
    public final void setEnableReleaseDeployable(EnableReleaseDeployable enableReleaseDeployable) {
        this.enableReleaseDeployable = enableReleaseDeployable;
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

    @DataBoundSetter
    public final void setDeployToFirstStage(boolean deployToFirstStage) {
        this.deployToFirstStage = deployToFirstStage;
    }

    public boolean isWaitTillBuildCompleted() {
        return waitTillBuildCompleted != null;
    }

    public WaitTillCompleted getWaitTillBuildCompleted() {
        return waitTillBuildCompleted;
    }

    public boolean isSetBuildVariables() {
        return setBuildVariables != null;
    }

    public SetBuildVariables getSetBuildVariables() {
        return setBuildVariables;
    }

    public boolean isEnableReleaseDeployable() {
        return enableReleaseDeployable != null;
    }

    public EnableReleaseDeployable getEnableReleaseDeployable() {
        return enableReleaseDeployable;
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

    public boolean getDeployToFirstStage() {
        return deployToFirstStage;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
        return BuildHelper.triggerBuild(build, listener, this);
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {
        public DescriptorImpl() {
            super(TriggerBuildPublisher.class);
        }

        @SuppressWarnings("rawtypes")
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        @Override
        public String getDisplayName() {
            return "BuildMaster: Trigger Build";
        }

        // TODO jelly expandableTextbox does not support form validation currently so this does nothing:
        // https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/lib/form/expandableTextbox.jelly
        public FormValidation doCheckVariables(@QueryParameter String value) {
            try {
                BuildHelper.getVariablesList(value);
            } catch (Exception e) {
                return FormValidation.error(e.getMessage());
            }
            
            return FormValidation.ok();
        }

        public String getDefaultBuildNumber() {
            return BuildHelper.DEFAULT_BUILD_NUMBER;
        }
    }
}
