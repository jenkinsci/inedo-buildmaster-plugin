package com.inedo.buildmaster.jenkins;

import java.io.Serializable;
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
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.domain.ApiReleasePackage;
import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;

import hudson.AbortException;
import hudson.Extension;
import hudson.Launcher;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.util.FormValidation;

/**
 * Create a package in BuildMaster for the specified application and release and optionally deploy it to the first stage.
 *
 * <p>
 * <i>This duplicates the functionality of {@link CreatePackageBuilder}. This is required because the only way to return
 * data to pipeline script is via a SynchronousNonBlockingStepExecution because updating environment variables is not supported.</i>
 * </p>
 */
public class CreatePackageStep extends Step implements ICreatePackage, Serializable {
    private static final long serialVersionUID = 1L;

    private DeployToFirstStage deployToFirstStage = null;
    private PackageVariables packageVariables = null;
    private EnableReleaseDeployable enableReleaseDeployable = null;
    private String applicationId;
    private String releaseNumber;
    private String packageNumber;

    @DataBoundConstructor
    public CreatePackageStep() {
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

    @Override
    public StepExecution start(StepContext context) throws Exception {
        return new Execution(context, this);
    }

    @Extension
    public static class DescriptorImpl extends StepDescriptor {

        @Override
        public Set<? extends Class<?>> getRequiredContext() {
            return new HashSet<>(Arrays.asList(Run.class, Launcher.class, TaskListener.class));
        }

        @Override
        public String getFunctionName() {
            return "buildMasterCreatePackage";
        }

        @Override
        public String getDisplayName() {
            return "Create BuildMaster Package";
        }

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

    private static class Execution extends SynchronousNonBlockingStepExecution<String> {
        private static final long serialVersionUID = 1L;

        private final ICreatePackage packageConfig;

        public Execution(StepContext context, ICreatePackage packageConfig) {
            super(context);

            this.packageConfig = packageConfig;
        }

        @Override
        protected String run() throws Exception {
            ApiReleasePackage releasePackage = BuildHelper.createPackage(this.getContext().get(Run.class), this.getContext().get(TaskListener.class), packageConfig);

            if (releasePackage == null) {
                throw new AbortException("Deployment failed");
            }
            
            return releasePackage.number;
        }
    }
}
