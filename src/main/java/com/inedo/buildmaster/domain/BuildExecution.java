package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecution
{
	public int Execution_Id;
	public String ExecutionStatus_Name;
	public String PromotionStatus_Name;
	public String WarningLogEntry_Indicator;
	public int Initial_BuildExecution_DeploymentPlan_Id;
	public int Initial_DeploymentPlan_Id;
}

