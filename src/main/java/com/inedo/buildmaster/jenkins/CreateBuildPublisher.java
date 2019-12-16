package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import com.inedo.buildmaster.jenkins.utils.BuildHelper;
import com.inedo.buildmaster.jenkins.utils.ConfigHelper;
import com.inedo.buildmaster.jenkins.utils.ICreateBuild;
import hudson.util.ListBoxModel;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.domain.ApiReleaseBuild;
import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
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
public class CreateBuildPublisher extends Recorder implements ICreateBuild {
    private final String applicationId;
    private final String releaseNumber;
    private DeployToFirstStage deployToFirstStage = null;
    private String buildVariables = "";

    @DataBoundConstructor
    public CreateBuildPublisher(String applicationId, String releaseNumber) {
        this.applicationId = applicationId;
        this.releaseNumber = releaseNumber;
    }

    @DataBoundSetter
    public final void setDeployToFirstStage(DeployToFirstStage deployToFirstStage) {
        this.deployToFirstStage = deployToFirstStage;
    }

    @DataBoundSetter
    public final void setBuildVariables(String buildVariables) {
        this.buildVariables = buildVariables;
    }

    public boolean isDeployToFirstStage() {
        return deployToFirstStage != null;
    }

    public DeployToFirstStage getDeployToFirstStage() {
        return deployToFirstStage;
    }

    public String getBuildVariables() {
        return buildVariables;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
        ApiReleaseBuild releaseBuild = BuildHelper.createBuild(build, listener, this);

        if (releaseBuild == null) {
            throw new AbortException("Deployment failed");
        }

        JenkinsHelper helper = new JenkinsHelper(build, listener);
        helper.getLogWriter().info("Inject environment variable BUILDMASTER_BUILD_NUMBER=" + releaseBuild.number);
        helper.injectEnvironmentVariable("BUILDMASTER_BUILD_NUMBER", releaseBuild.number);

        return true;
    }

    @Override
    public BuildStepMonitor getRequiredMonitorService() {
        return BuildStepMonitor.NONE;
    }

    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {
        public DescriptorImpl() {
            super(CreateBuildPublisher.class);
        }

        private ConfigHelper buildmaster = new ConfigHelper();

        public ConfigHelper getBuildmaster() {
            return buildmaster;
        }

        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            // Indicates that this builder can be used with all kinds of project types
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Create BuildMaster Build";
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return buildmaster.doFillApplicationIdItems("$BUILDMASTER_APPLICATION_ID");
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return buildmaster.doCheckApplicationId(value);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return buildmaster.doFillReleaseNumberItems(applicationId, "$BUILDMASTER_RELEASE_NUMBER", true);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return buildmaster.doCheckReleaseNumber(value, applicationId);
        }

        public FormValidation doCheckVariables(@QueryParameter String value) {
            return buildmaster.doCheckVariables(value);
        }

        public String getDefaultBuildNumber() {
            return buildmaster.getDefaultBuildNumber();
        }
    }
}
