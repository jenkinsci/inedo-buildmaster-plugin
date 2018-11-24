package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import com.inedo.buildmaster.jenkins.buildOption.DeployVariables;

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
    private String stage = "";
    private boolean waitUntilDeploymentCompleted = true;
    private boolean printLogOnFailure = true;
    private DeployVariables deployVariables = null;
    private String applicationId = DescriptorImpl.defaultApplicationId;
    private String releaseNumber = DescriptorImpl.defaultReleaseNumber;
    private String packageNumber = DescriptorImpl.defaultPackageNumber;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public DeployToStageBuilder() {
    }

    @DataBoundSetter
    public final void setStage(String stage) {
        this.stage = stage;
    }

    @DataBoundSetter
    public final void setWaitUntilDeploymentCompleted(boolean waitUntilDeploymentCompleted) {
        this.waitUntilDeploymentCompleted = waitUntilDeploymentCompleted;
    }

    @DataBoundSetter
    public final void setPrintLogOnFailure(boolean printLogOnFailure) {
        this.printLogOnFailure = printLogOnFailure;
    }

    @DataBoundSetter
    public final void setDeployVariables(DeployVariables deployVariables) {
        this.deployVariables = deployVariables;
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
    public final void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
    }

    public String getStage() {
        return stage;
    }

    public boolean isWaitUntilDeploymentCompleted() {
        return waitUntilDeploymentCompleted;
    }

    public boolean isPrintLogOnFailure() {
        return printLogOnFailure;
    }

    public boolean isDeployVariables() {
        return deployVariables != null;
    }

    public DeployVariables getDeployVariables() {
        return deployVariables;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    public String getPackageNumber() {
        return packageNumber;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        if (!BuildHelper.deployToStage(run, listener, this)) {
            throw new AbortException("Deployment failed");
        }
    }

    @Symbol("buildMasterDeployPackageToStage")
    @Extension
    // This indicates to Jenkins that this is an implementation of an extension
    // point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        public static final String defaultApplicationId = "${BUILDMASTER_APPLICATION_ID}";
        public static final String defaultReleaseNumber = "${BUILDMASTER_RELEASE_NUMBER}";
        public static final String defaultPackageNumber = BuildHelper.DEFAULT_PACKAGE_NUMBER;

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
            return "Deploy BuildMaster Package To Stage";
        }
    }
}
