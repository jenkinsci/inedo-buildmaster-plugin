package com.inedo.buildmaster.jenkins;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.jenkinsci.plugins.workflow.steps.Step;
import org.jenkinsci.plugins.workflow.steps.StepContext;
import org.jenkinsci.plugins.workflow.steps.StepDescriptor;
import org.jenkinsci.plugins.workflow.steps.StepExecution;
import org.jenkinsci.plugins.workflow.steps.SynchronousNonBlockingStepExecution;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;

import com.inedo.buildmaster.BuildMasterSelectApplication;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;

/**
 * This duplicates the functionality of {@link SelectApplicationBuilder}. This is required because the only way to return
 * data to the pipeline is via a SynchronousNonBlockingStepExecution.
 * 
 * TODO 'Other' plugin uses ResourceActivity for resource locking, should this?
 * TODO 'Other' plugin should not appear in pipeline syntax dropdown
 * TODO Is this plugin needed? Could enhance other plugins?
 * TODO Do other plugins need to return values, such as actual buildnumber?
 */
public class SelectApplicationPipeline extends Step {

    private String applicationId;
    private String releaseNumber = SelectApplicationHelper.LATEST_RELEASE;
    private String buildNumberSource = "BUILDMASTER";
    private String deployableId = SelectApplicationHelper.NOT_REQUIRED;

    @DataBoundConstructor
    public SelectApplicationPipeline() {
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
    public final void setBuildNumberSource(String buildNumberSource) {
        this.buildNumberSource = buildNumberSource;
    }

    @DataBoundSetter
    public final void setDeployableId(String deployableId) {
        this.deployableId = deployableId;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    public String getBuildNumberSource() {
        return buildNumberSource;
    }

    public String getDeployableId() {
        return deployableId;
    }

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new SelectApplicationPipeline.Execution(context, applicationId, releaseNumber, buildNumberSource, deployableId);
    }

    @Extension
    public static class DescriptorImpl extends StepDescriptor {

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return new HashSet<>(Arrays.asList(Run.class, Launcher.class, TaskListener.class));
        }

        @Override
        public String getFunctionName() {
            return "buildMasterSelectApplication2";
        }

        @Override
        public String getDisplayName() {
            return "BuildMaster: Select Application";
        }

    }

    public static class Execution extends SynchronousNonBlockingStepExecution<BuildMasterApplication> implements BuildMasterSelectApplication {
        private final String applicationId;
        private final String releaseNumber;
        private final String buildNumberSource;
        private final String deployableId;

        public Execution(StepContext context, String applicationId, String releaseNumber, String buildNumberSource, String deployableId) {
            super(context);

            this.applicationId = applicationId;
            this.releaseNumber = releaseNumber;
            this.buildNumberSource = buildNumberSource;
            this.deployableId = deployableId;
        }

        @Override
        protected BuildMasterApplication run() throws Exception {

            SelectApplicationHelper execute = new SelectApplicationHelper(this.getContext().get(Run.class), this.getContext().get(TaskListener.class));
            return execute.selectApplication(this);
        }

        public String getApplicationId() {
            return applicationId;
        }

        public String getReleaseNumber() {
            return releaseNumber;
        }

        public String getBuildNumberSource() {
            return buildNumberSource;
        }

        public String getDeployableId() {
            return deployableId;
        }
    }

}
