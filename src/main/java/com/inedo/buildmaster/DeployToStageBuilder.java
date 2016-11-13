package com.inedo.buildmaster;

import hudson.Launcher;
import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import java.io.IOException;

/**
 * Deploy a package to the requested stage in BuildMaster.
 *
 * @author Andrew Sumner
 */
public class DeployToStageBuilder extends Builder {
	private final String toStage;
	private final boolean waitTillBuildCompleted;
    private final boolean printLogOnFailure;
	private final String applicationId;
	private final String releaseNumber;
	private final String buildNumber;

	// Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
	@DataBoundConstructor
	public DeployToStageBuilder(String toStage, JSONObject waitTillBuildCompleted, String applicationId, String releaseNumber, String buildNumber) {
		this.toStage = toStage;
		
		if (waitTillBuildCompleted != null) { 
            this.waitTillBuildCompleted = true; 
            this.printLogOnFailure = "true".equalsIgnoreCase(waitTillBuildCompleted.getString("printLogOnFailure"));
        } else { 
            this.waitTillBuildCompleted = false; 
            this.printLogOnFailure = false;
        }
		
		this.applicationId = applicationId;
		this.releaseNumber = releaseNumber;
		this.buildNumber = buildNumber;
	}

	public String getToStage() {
		return toStage;
	}
	
	public boolean getWaitTillBuildCompleted() {
        return waitTillBuildCompleted;
    }

    public boolean getPrintLogOnFailure() {
        return printLogOnFailure;
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
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException, InterruptedException {
		return BuildHelper.deployToStage(build, listener, this);
	}

	@Extension
	// This indicates to Jenkins that this is an implementation of an extension
	// point.
	public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
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
			return "BuildMaster: Deploy To Stage";
		}
				
		public String getDefaultBuildNumber() {
			return BuildHelper.DEFAULT_BUILD_NUMBER;
		}
	}
}
