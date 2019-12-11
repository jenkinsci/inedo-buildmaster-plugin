package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.utils.ICreateBuild;

/**
 * Data to inject into TriggerBuildHelper for testing
 * 
 * @author Andrew Sumner
 */
public class TriggerableData implements ICreateBuild {
    public DeployToFirstStage deployToFirstStage;
	public String setBuildVariables = null;
	public String applicationId;
	public String releaseNumber;
    public String buildNumber;

    public TriggerableData(String applicationId, String releaseNumber, String buildNumber, DeployToFirstStage deployToFirstStage) {
		this.applicationId = applicationId;
		this.releaseNumber = releaseNumber;
		this.buildNumber = buildNumber;
        this.deployToFirstStage = deployToFirstStage;
	}
	
	// Getters
    public boolean isDeployToFirstStage() {
        return deployToFirstStage != null;
	}
	
    public DeployToFirstStage getDeployToFirstStage() {
        return deployToFirstStage;
	}

	public boolean hasBuildVariables() {
		return setBuildVariables != null;
	}

	public String getBuildVariables() {
		return setBuildVariables;
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
    public TriggerableData setDeployToFirstStage(DeployToFirstStage value) {
        deployToFirstStage = value;
		return this;
	}

	public TriggerableData setSetBuildVariables(String value) {
		setBuildVariables = value;
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