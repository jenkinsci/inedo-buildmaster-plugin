package com.inedo.buildmaster;

import jenkins.model.Jenkins;

import com.inedo.buildmaster.BuildMasterPlugin.BuildMasterPluginDescriptor;

public interface Triggerable {
	public boolean getWaitTillBuildCompleted();
	public boolean getPrintLogOnFailure();
	public String getVariables();
	public String getApplicationId();
	public String getReleaseNumber();
	public String getBuildNumber();
	public BuildMasterPluginDescriptor getSharedDescriptor();
}
