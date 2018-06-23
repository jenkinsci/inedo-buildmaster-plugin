package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.SetBuildVariables;
import com.inedo.buildmaster.jenkins.buildOption.WaitTillCompleted;

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
    public String packageNumber;
    public boolean deployToFirstStage;

    public TriggerableData(String applicationId, String releaseNumber, String packageNumber, boolean deployToFirstStage) {
		this.applicationId = applicationId;
		this.releaseNumber = releaseNumber;
		this.packageNumber = packageNumber;
        this.deployToFirstStage = deployToFirstStage;
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
	
	public String getPackageNumber() {
		return packageNumber;
	}
	
    public boolean getDeployToFirstStage() {
        return deployToFirstStage;
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
	
	public TriggerableData setPackageNumber(String value) {
		packageNumber = value;
		return this;
	}

    public TriggerableData setDeployToFirstStage(boolean value) {
        deployToFirstStage = value;
        return this;
    }

}