package com.inedo.buildmaster;

import hudson.Launcher;
import hudson.AbortException;
import hudson.Extension;
import hudson.FilePath;
import hudson.util.FormValidation;
import jenkins.tasks.SimpleBuildStep;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.buildOption.SetBuildVariables;
import com.inedo.buildmaster.buildOption.WaitTillCompleted;

import java.io.IOException;

/**
 * The TriggerBuildPostBuildStep will trigger a build in BuildMaster for a selected application and release
 * as a build step.
 *
 * @author Andrew Sumner
 */
public class TriggerBuildBuilder extends Builder implements SimpleBuildStep, Triggerable {
	private WaitTillCompleted waitTillBuildCompleted = null;
	private SetBuildVariables setBuildVariables = null;
	private EnableReleaseDeployable enableReleaseDeployable = null;
		
	private String applicationId = "${BUILDMASTER_APPLICATION_ID}";
	private String releaseNumber = "${BUILDMASTER_RELEASE_NUMBER}";
	private String buildNumber = "${BUILDMASTER_BUILD_NUMBER}";
	    
    @DataBoundConstructor
	public TriggerBuildBuilder() {
	}

	@DataBoundSetter public final void setWaitTillBuildCompleted(WaitTillCompleted waitTillBuildCompleted) {
		this.waitTillBuildCompleted  = waitTillBuildCompleted;
    }
	
	@DataBoundSetter public final void setSetBuildVariables(SetBuildVariables setBuildVariables) {
		this.setBuildVariables = setBuildVariables;
    }
	
	@DataBoundSetter public final void setEnableReleaseDeployable(EnableReleaseDeployable enableReleaseDeployable) {
		this.enableReleaseDeployable = enableReleaseDeployable;
    }
	
	@DataBoundSetter public final void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
	
	@DataBoundSetter public final void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }
			
	@DataBoundSetter public final void setBuildNumber(String buildNumber) {
        this.buildNumber = buildNumber;
    }
	
	public boolean isWaitTillBuildCompleted() {
		return waitTillBuildCompleted != null;
	}

	public WaitTillCompleted getWaitTillBuildCompleted() {
		return waitTillBuildCompleted;
	}

	public boolean isSetBuildVariables() {
		return setBuildVariables != null;
	}
	
	public SetBuildVariables getSetBuildVariables() {
		return setBuildVariables;
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
	
	public String getBuildNumber() {
		return buildNumber;
	}
		
	@Override
	public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
		if (!BuildHelper.triggerBuild(run, listener, this)) {
			throw new AbortException();
		}
	}
	 
	@Symbol("buildMasterTriggerBuild")
	@Extension
	// This indicates to Jenkins that this is an implementation of an extension
	// point.
	public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
		public DescriptorImpl() {
			super(TriggerBuildBuilder.class);
		}

		@SuppressWarnings("rawtypes")
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
			// Indicates that this builder can be used with all kinds of project types
			return true;
		}

		@Override
		public String getDisplayName() {
			return "BuildMaster: Trigger Build";
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
