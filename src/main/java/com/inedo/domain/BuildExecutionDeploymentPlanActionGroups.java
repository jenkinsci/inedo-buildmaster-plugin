package com.inedo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecutionDeploymentPlanActionGroups
{
	public int BuildExecution_DeploymentPlan_Id;
	public int Original_DeploymentPlanActionGroup_Sequence;
	public int Original_DeploymentPlanActionGroup_Id;
	public String Original_Deployable_Name;
	public String Original_DeploymentPlanActionGroup_Name;	
	public int Initial_BuildExecution_DeploymentPlan_Id;
	public int Initial_DeploymentPlan_Id;
}

