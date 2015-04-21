package com.inedo;

public interface TriggerBuild {
	public boolean getWaitTillBuildCompleted();
	public boolean getPrintLogOnFailure();
	public String getVariables();
	public String getApplicationId();
	public String getReleaseNumber();
	public String getBuildNumber();
}
