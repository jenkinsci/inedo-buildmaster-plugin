package com.inedo.buildmaster;

public interface Triggerable {
	public boolean getWaitTillBuildCompleted();
	public boolean getPrintLogOnFailure();
	public boolean getSetBuildVariables();
	public boolean getPreserveVariables();
	public String getVariables();
	public boolean getEnableDeployable();
	public String getDeployableId();
	public String getApplicationId();
	public String getReleaseNumber();
	public String getBuildNumber();

}
