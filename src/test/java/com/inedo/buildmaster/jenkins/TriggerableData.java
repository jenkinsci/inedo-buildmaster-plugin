package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.Triggerable;
import com.inedo.buildmaster.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.buildOption.SetBuildVariables;
import com.inedo.buildmaster.buildOption.WaitTillCompleted;

/**
 * Data to inject into TriggerBuildHelper for testing
 * 
 * @author Andrew Sumner
 */
public class TriggerableData implements Triggerable {
	public WaitTillCompleted waitTillBuildCompleted = null;
	public SetBuildVariables setBuildVariables = null;
	public EnableReleaseDeployable enableReleaseDeployable = null;
	public String applicationId;
	public String releaseNumber;
	public String buildNumber;

	public TriggerableData(String applicationId, String releaseNumber, String buildNumber) {
		this.applicationId = applicationId;
		this.releaseNumber = releaseNumber;
		this.buildNumber = buildNumber;
	}
	
	// Getters
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
	
	// Setters
	public TriggerableData setWaitTillBuildCompleted(WaitTillCompleted value) {
		waitTillBuildCompleted = value;
		return this;
	}

	public TriggerableData setSetBuildVariables(SetBuildVariables value) {
		setBuildVariables = value;
		return this;
	}
	
	public TriggerableData setEnableReleaseDeployable(EnableReleaseDeployable value) {
		enableReleaseDeployable = value;
		return this;
	}
	
	public TriggerableData setApplicationId(String value) {
		applicationId = value;
		return this;
	}

	public TriggerableData setReleaseNumber(String value) {
		releaseNumber = value;
		return this;
	}
	
	public TriggerableData setBuildNumber(String value) {
		buildNumber = value;
		return this;
	}
}