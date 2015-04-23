package com.inedo.buildmaster;

public interface Triggerable {
	public boolean getWaitTillBuildCompleted();
	public boolean getPrintLogOnFailure();
	public String getVariables();
	public String getApplicationId();
	public String getReleaseNumber();
	public String getBuildNumber();
}
