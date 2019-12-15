package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import com.inedo.buildmaster.jenkins.utils.BuildHelper;
import com.inedo.buildmaster.jenkins.utils.BuildMasterSelector;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

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
import org.kohsuke.stapler.QueryParameter;

import javax.annotation.Nonnull;

/**
 * Deploy a build to the requested stage in BuildMaster.
 *
 * See https://github.com/jenkinsci/pipeline-plugin/blob/master/DEVGUIDE.md#user-content-build-wrappers-1 for tips on 
 * Jenkins pipeline support 
 * 
 * @author Andrew Sumner
 */
public class DeployToStageBuilder extends Builder implements SimpleBuildStep {
    private final String applicationId;
    private final String releaseNumber;
    private final String buildNumber;
    private String stage = "";
    private String variables = null;
    private boolean waitUntilCompleted = true;
    private boolean printLogOnFailure = true;
    private boolean force = false;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public DeployToStageBuilder(String applicationId, String releaseNumber, String buildNumber) {
        this.applicationId = applicationId;
        this.releaseNumber = releaseNumber;
        this.buildNumber = buildNumber;
    }

    @DataBoundSetter
    public final void setStage(String stage) {
        this.stage = stage;
    }

    @DataBoundSetter
    public final void setVariables(String variables) {
        this.variables = variables;
    }

    @DataBoundSetter
    public void setForce(boolean force) {
        this.force = force;
    }

    @DataBoundSetter
    public final void setWaitUntilCompleted(boolean waitUntilCompleted) {
        this.waitUntilCompleted = waitUntilCompleted;
    }

    @DataBoundSetter
    public final void setPrintLogOnFailure(boolean printLogOnFailure) {
        this.printLogOnFailure = printLogOnFailure;
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

    public String getStage() {
        return stage;
    }

    public String getVariables() {
        return variables;
    }

    public boolean isForce() {
        return force;
    }

    public boolean isWaitUntilCompleted() {
        return waitUntilCompleted;
    }

    public boolean isPrintLogOnFailure() {
        return printLogOnFailure;
    }

    @Override
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath workspace, @Nonnull Launcher launcher, @Nonnull TaskListener listener) throws InterruptedException, IOException {
        if (!BuildHelper.deployToStage(run, listener, this)) {
            throw new AbortException("Deployment failed");
        }
    }

    @Symbol("buildMasterDeployBuildToStage")
    @Extension
    // This indicates to Jenkins that this is an implementation of an extension
    // point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        private BuildMasterSelector buildmaster = new BuildMasterSelector();

        public BuildMasterSelector getBuildmaster() {
            return buildmaster;
        }

        public DescriptorImpl() {
            super(DeployToStageBuilder.class);
        }

        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Deploy BuildMaster Build To Stage";
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return buildmaster.doFillApplicationIdItems("$BUILDMASTER_APPLICATION_ID");
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return buildmaster.doCheckApplicationId(value);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return buildmaster.doCheckReleaseNumber(value, applicationId);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return buildmaster.doFillReleaseNumberItems(applicationId, "$BUILDMASTER_RELEASE_NUMBER", true);
        }

        public FormValidation doCheckVariables(@QueryParameter String value) {
            return buildmaster.doCheckVariables(value);
        }

        public String getDefaultBuildNumber() {
            return buildmaster.getDefaultBuildNumber();
        }

    }
}
