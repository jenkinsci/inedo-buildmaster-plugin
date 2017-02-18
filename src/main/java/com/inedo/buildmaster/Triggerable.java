package com.inedo.buildmaster;

import com.inedo.buildmaster.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.buildOption.SetBuildVariables;
import com.inedo.buildmaster.buildOption.WaitTillCompleted;

public interface Triggerable {
	public boolean isWaitTillBuildCompleted();
	public boolean isSetBuildVariables();
	public boolean isEnableReleaseDeployable();
	public WaitTillCompleted getWaitTillBuildCompleted();
	public SetBuildVariables getSetBuildVariables();
	public EnableReleaseDeployable getEnableReleaseDeployable();
	public String getApplicationId();
	public String getReleaseNumber();
	public String getBuildNumber();

}
