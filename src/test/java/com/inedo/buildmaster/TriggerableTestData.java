package com.inedo.buildmaster;

public class TriggerableTestData implements Triggerable {
	public boolean waitTillBuildCompleted = false;
	public boolean printLogOnFailure = false;
	public boolean setBuildVariables = false;
	public boolean preserveVariables = false;
	public String variables = null;
	public boolean enableDeployable = false;
	public String deployableId = null;
	public String applicationId;
	public String releaseNumber;
	public String buildNumber;

	public TriggerableTestData(String applicationId, String releaseNumber, String buildNumber) {
		this.applicationId = applicationId;
		this.releaseNumber = releaseNumber;
		this.buildNumber = buildNumber;
	}
	
	// Getters
	public boolean getWaitTillBuildCompleted() {
		return waitTillBuildCompleted;
	}

	public boolean getPrintLogOnFailure() {
		return printLogOnFailure;
	}
	
	public boolean getSetBuildVariables() {
		return setBuildVariables;
	}
	
	public boolean getPreserveVariables() {
		return preserveVariables;
	}
	
	public String getVariables() {
		return variables;
	}

	public boolean getEnableDeployable() {
		return enableDeployable;
	}
	
	public String getDeployableId() {
		return deployableId;
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
	public TriggerableTestData setWaitTillBuildCompleted(boolean value) {
		waitTillBuildCompleted = value;
		return this;
	}

	public TriggerableTestData setPrintLogOnFailure(boolean value) {
		printLogOnFailure = value;
		return this;
	}
	
	public TriggerableTestData setSetBuildVariables(boolean value) {
		setBuildVariables = value;
		return this;
	}
	
	public TriggerableTestData setPreserveVariables(boolean value) {
		preserveVariables = value;
		return this;
	}
	
	public TriggerableTestData setVariables(String value) {
		variables = value;
		return this;
	}

	public TriggerableTestData setEnableDeployable(boolean value) {
		enableDeployable = value;
		return this;
	}
	
	public TriggerableTestData setDeployableId(String value) {
		deployableId = value;
		return this;
	}
	
	public TriggerableTestData setApplicationId(String value) {
		applicationId = value;
		return this;
	}

	public TriggerableTestData setReleaseNumber(String value) {
		releaseNumber = value;
		return this;
	}
	
	public TriggerableTestData setBuildNumber(String value) {
		buildNumber = value;
		return this;
	}
}