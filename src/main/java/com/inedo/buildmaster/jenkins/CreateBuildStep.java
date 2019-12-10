package com.inedo.buildmaster.jenkins;

import java.io.IOException;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import hudson.util.ListBoxModel;
import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.domain.ApiReleaseBuild;
import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;

import hudson.AbortException;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.util.FormValidation;

import javax.annotation.Nonnull;

/**
 * Create a build in BuildMaster for the specified application and release and optionally deploy it to the first stage.
 *
 * <p>
 * <i>This duplicates the functionality of {@link CreateBuildBuilder}. This is required because the only way to return
 * data to pipeline script is via a SynchronousNonBlockingStepExecution because updating environment variables is not supported.</i>
 * </p>
 */
public class CreateBuildStep extends Step implements ICreateBuild, Serializable {
    private static final long serialVersionUID = 1L;

    private final String applicationId;
    private final String releaseNumber;
    private DeployToFirstStage deployToFirstStage = null;
    private String buildVariables = "";

    @DataBoundConstructor
    public CreateBuildStep(String applicationId, String releaseNumber) {
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

    public String getApplicationId() {
        return applicationId;
    }

    public String getReleaseNumber() {
        return releaseNumber;
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

    public String getBuildVariables() {
        return buildVariables;
    }

    @Override
    public StepExecution start(StepContext context) {
        return new Execution(context, this);
    }

    @Extension
    public static class DescriptorImpl extends StepDescriptor {
        private BuildMasterSelector buildmaster = new BuildMasterSelector();

        public BuildMasterSelector getBuildmaster() {
            return buildmaster;
        }

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return new HashSet<>(Arrays.asList(Run.class, Launcher.class, TaskListener.class));
        }

        @Override
        public String getFunctionName() {
            return "buildMasterCreateBuild";
        }

        @Nonnull
        @Override
        public String getDisplayName() {
            return "Create BuildMaster Build";
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return buildmaster.doFillApplicationIdItems("BUILDMASTER_APPLICATION_ID");
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return buildmaster.doCheckApplicationId(value);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return buildmaster.doCheckReleaseNumber(value, applicationId);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return buildmaster.doFillReleaseNumberItems(applicationId, "BUILDMASTER_RELEASE_NUMBER");
        }

        public FormValidation doCheckVariables(@QueryParameter String value) {
            return buildmaster.doCheckVariables(value);
        }

        public String getDefaultBuildNumber() {
            return buildmaster.getDefaultBuildNumber();
        }
    }

    private static class Execution extends SynchronousNonBlockingStepExecution<String> {
        private static final long serialVersionUID = 1L;

        private final ICreateBuild buildConfig;

        public Execution(StepContext context, ICreateBuild buildConfig) {
            super(context);

            this.buildConfig = buildConfig;
        }

        @Override
        protected String run() throws Exception {
            ApiReleaseBuild releaseBuild = BuildHelper.createBuild(this.getContext().get(Run.class), this.getContext().get(TaskListener.class), buildConfig);

            if (releaseBuild == null) {
                throw new AbortException("Deployment failed");
            }
            
            return releaseBuild.number;
        }
    }
}
