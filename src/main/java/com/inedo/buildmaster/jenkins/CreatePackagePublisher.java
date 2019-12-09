package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.domain.ApiReleaseBuild;
import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;
import com.inedo.buildmaster.jenkins.utils.JenkinsHelper;

import hudson.AbortException;
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
public class CreatePackagePublisher extends Recorder implements ICreatePackage {
    private DeployToFirstStage deployToFirstStage = null;
    private PackageVariables packageVariables = null;
    private String applicationId = "${BUILDMASTER_APPLICATION_ID}";
    private String releaseNumber = "${BUILDMASTER_RELEASE_NUMBER}";
    private String packageNumber = "${BUILDMASTER_PACKAGE_NUMBER}";

    @DataBoundConstructor
    public CreatePackagePublisher() {
    }

    @DataBoundSetter
    public final void setDeployToFirstStage(DeployToFirstStage deployToFirstStage) {
        this.deployToFirstStage = deployToFirstStage;
    }

    @DataBoundSetter
    public final void setPackageVariables(PackageVariables packageVariables) {
        this.packageVariables = packageVariables;
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

    public boolean isDeployToFirstStage() {
        return deployToFirstStage != null;
    }

    public DeployToFirstStage getDeployToFirstStage() {
        return deployToFirstStage;
    }

    public boolean isPackageVariables() {
        return packageVariables != null;
    }

    public PackageVariables getPackageVariables() {
        return packageVariables;
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
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
        ApiReleaseBuild releasePackage = BuildHelper.createPackage(build, listener, this);

        if (releasePackage == null) {
            throw new AbortException("Deployment failed");
        }

        JenkinsHelper helper = new JenkinsHelper(build, listener);
        helper.getLogWriter().info("Inject environment variable BUILDMASTER_PACKAGE_NUMBER=" + releasePackage.number);
        helper.injectEnvironmentVariable("BUILDMASTER_PACKAGE_NUMBER", releasePackage.number);

        return true;
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {
        public DescriptorImpl() {
            super(CreatePackagePublisher.class);
        }

        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Create BuildMaster Build";
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
