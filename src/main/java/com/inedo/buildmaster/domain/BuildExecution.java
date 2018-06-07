package com.inedo.buildmaster.domain;

import com.inedo.buildmaster.Optional;

public class BuildExecution
{
	public int Execution_Id;
	public int Application_Id;
	public String Release_Number;
	public String Release_Name;
	public String Build_Number;
	public String ReleaseStatus_Name;
	public String BuildStatus_Name;
    // public int Workflow_Id;
    // public String Workflow_Name;
    @Optional
    public Integer Environment_Id;
    @Optional
    public String Environment_Name;
	public String ExecutionStart_Date;
	public String ExecutionEnded_Date;
	public String ExecutionStatus_Name;
	public String PromotionStatus_Name;
	public String WarningLogEntry_Indicator;
    // public int Initial_BuildExecution_DeploymentPlan_Id;
    // public int Initial_DeploymentPlan_Id;
    // TODO Is this set any more?
    @Optional
	public String Build_AutoPromote_Indicator;
}
