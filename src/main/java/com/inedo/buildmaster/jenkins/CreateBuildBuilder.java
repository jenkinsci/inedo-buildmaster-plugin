package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.domain.ApiReleaseBuild;
import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.BuildVariables;
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
    private DeployToFirstStage deployToFirstStage = null;
    private BuildVariables buildVariables = null;
    private String applicationId = "${BUILDMASTER_APPLICATION_ID}";
    private String releaseNumber = "${BUILDMASTER_RELEASE_NUMBER}";
    private String buildNumber = "${BUILDMASTER_BUILD_NUMBER}";

    @DataBoundConstructor
    public CreateBuildBuilder() {
    }

    @DataBoundSetter
    public final void setDeployToFirstStage(DeployToFirstStage deployToFirstStage) {
        this.deployToFirstStage = deployToFirstStage;
    }

    @DataBoundSetter
    public final void setBuildVariables(BuildVariables buildVariables) {
        this.buildVariables = buildVariables;
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

    public boolean isDeployToFirstStage() {
        return deployToFirstStage != null;
    }

    public DeployToFirstStage getDeployToFirstStage() {
        return deployToFirstStage;
    }

    public boolean isBuildVariables() {
        return buildVariables != null;
    }

    public BuildVariables getBuildVariables() {
        return buildVariables;
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

        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
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

        public String getDefaultBuildNumber() {
            return BuildHelper.DEFAULT_BUILD_NUMBER;
        }
    }
}
