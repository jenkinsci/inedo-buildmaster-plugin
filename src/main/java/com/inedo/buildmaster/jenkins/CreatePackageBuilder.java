package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.domain.ApiPackageDeployment;
import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;
import com.inedo.buildmaster.jenkins.buildOption.WaitTillCompleted;
import com.inedo.buildmaster.jenkins.utils.JenkinsHelper;

import hudson.AbortException;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import jenkins.tasks.SimpleBuildStep;

/**
 * Create a package in BuildMaster for the specified application and release and optionally deploy it to the first stage.
 *
 * <p>
 * <i>This has been duplicated by {@link CreatePackageStep} to support Jenkins pipeline script.</i>
 * </p>
 * 
 * @author Andrew Sumner
 */
public class CreatePackageBuilder extends Builder implements SimpleBuildStep, ICreatePackage {
    private WaitTillCompleted waitTillBuildCompleted = null;
    private PackageVariables packageVariables = null;
    private EnableReleaseDeployable enableReleaseDeployable = null;
    private boolean deployToFirstStage = true;
    private String applicationId = "${BUILDMASTER_APPLICATION_ID}";
    private String releaseNumber = "${BUILDMASTER_RELEASE_NUMBER}";
    private String packageNumber = "${BUILDMASTER_PACKAGE_NUMBER}";

    @DataBoundConstructor
    public CreatePackageBuilder() {
    }

    @DataBoundSetter
    public final void setWaitTillBuildCompleted(WaitTillCompleted waitTillBuildCompleted) {
        this.waitTillBuildCompleted = waitTillBuildCompleted;
    }

    @DataBoundSetter
    public final void setPackageVariables(PackageVariables packageVariables) {
        this.packageVariables = packageVariables;
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
    public final void setPackageNumber(String packageNumber) {
        this.packageNumber = packageNumber;
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

    public boolean isPackageVariables() {
        return packageVariables != null;
    }

    public PackageVariables getPackageVariables() {
        return packageVariables;
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

    public String getPackageNumber() {
        return packageNumber;
    }

    public boolean getDeployToFirstStage() {
        return deployToFirstStage;
    }

    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        ApiPackageDeployment apiPackage = BuildHelper.createPackage(run, listener, this);

        if (apiPackage == null) {
            throw new AbortException("Deployment failed");
        }

        JenkinsHelper helper = new JenkinsHelper(run, listener);
        helper.getLogWriter().info("Inject environment variable BUILDMASTER_PACKAGE_NUMBER=" + apiPackage.releasePackage.number);
        helper.injectEnvrionmentVariable("BUILDMASTER_PACKAGE_NUMBER", apiPackage.releasePackage.number);
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        public DescriptorImpl() {
            super(CreatePackageBuilder.class);
        }

        @SuppressWarnings("rawtypes")
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Create BuildMaster Package";
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

        public String getDefaultPackageNumber() {
            return BuildHelper.DEFAULT_PACKAGE_NUMBER;
        }
    }
}
