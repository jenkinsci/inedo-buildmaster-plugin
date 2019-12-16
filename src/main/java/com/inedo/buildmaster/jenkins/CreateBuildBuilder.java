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
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import jenkins.tasks.SimpleBuildStep;

import javax.annotation.Nonnull;

/**
 * Create a build in BuildMaster for the specified application and release and optionally deploy it to the first stage.
 *
 * <p>
 * <i>This has been duplicated by {@link CreateBuildStep} to support Jenkins pipeline script.</i>
 * </p>
 * 
 * @author Andrew Sumner
 */
public class CreateBuildBuilder extends Builder implements SimpleBuildStep, ICreateBuild {
    private final String applicationId;
    private final String releaseNumber;
    private DeployToFirstStage deployToFirstStage = null;
    private String buildVariables = "";

    @DataBoundConstructor
    public CreateBuildBuilder(String applicationId, String releaseNumber) {
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
    public void perform(@Nonnull Run<?, ?> run, @Nonnull FilePath workspace, @Nonnull Launcher launcher, @Nonnull TaskListener listener) throws InterruptedException, IOException {
        ApiReleaseBuild releaseBuild = BuildHelper.createBuild(run, listener, this);

        if (releaseBuild == null) {
            throw new AbortException("Deployment failed");
        }

        JenkinsHelper helper = new JenkinsHelper(run, listener);
        helper.getLogWriter().info("Inject environment variable BUILDMASTER_BUILD_NUMBER=" + releaseBuild.number);
        helper.injectEnvironmentVariable("BUILDMASTER_BUILD_NUMBER", releaseBuild.number);
    }

    @Extension
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        public DescriptorImpl() {
            super(CreateBuildBuilder.class);
        }

        private final ConfigHelper configHelper = new ConfigHelper();

        public ConfigHelper getConfigHelper() {
            return configHelper;
        }

        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            return true;
        }

        @Override
        public String getDisplayName() {
            return "Create BuildMaster Build";
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return configHelper.doFillApplicationIdItems("$BUILDMASTER_APPLICATION_ID");
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return configHelper.doCheckApplicationId(value);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return configHelper.doCheckReleaseNumber(value, applicationId);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return configHelper.doFillReleaseNumberItems(applicationId, "$BUILDMASTER_RELEASE_NUMBER", true);
        }

        public FormValidation doCheckVariables(@QueryParameter String value) {
            return configHelper.doCheckVariables(value);
        }

        public String getDefaultBuildNumber() {
            return configHelper.getDefaultBuildNumber();
        }
    }
}
