package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecution
{
	public static final String EXAMPLE =
			"[" +
			"  { \"Execution_Id\": 92236, \"Application_Id\": 36, \"Release_Number\": \"1.3\", \"Build_Number\": \"26\", \"BuildStatus_Name\": \"Active\", \"Workflow_Id\": 62, \"Workflow_Name\": \"Standard\", \"Environment_Id\": 4, \"Environment_Name\": \"DEV\", \"ExecutionStart_Date\": \"2015-04-15T04:06:57.5870000\", \"ExecutionEnded_Date\": \"2015-04-15T04:07:05.7600000\", \"ExecutionStatus_Name\": \"Succeeded\", \"CreatedBy_User_Name\": \"SYSTEM\", \"CreatedOn_Date\": \"2015-04-15T04:06:57.4330000\", \"Application_Name\": \"Test App 3\", \"PromotionStatus_Name\": \"Completed\", \"Promoted_Date\": \"2015-04-15T04:06:57.4330000\", \"PromotedBy_Name\": \"SYSTEM\", \"WarningLogEntry_Indicator\": \"N\", \"Release_Name\": \"1.3\", \"ReleaseStatus_Name\": \"Active\", \"Initial_BuildExecution_DeploymentPlan_Id\": 142077, \"VariableSupport_Code\": \"N\", \"ApplicationGroup_Id\": 4, \"Release_Sequence\": 3, \"Initial_DeploymentPlan_Id\": 214, \"Build_AutoPromote_Indicator\": \"Y\"}" + 
			"]";
			
	public int Execution_Id;
	public int Application_Id;
	public String Release_Number;
	public String Release_Name;
	public String Build_Number;
	public String ReleaseStatus_Name;
	public String BuildStatus_Name;
	public int Workflow_Id;
	public String Workflow_Name;
	public Integer Environment_Id;
	public String Environment_Name;
	public String ExecutionStart_Date;
	public String ExecutionEnded_Date;
	public String ExecutionStatus_Name;
	public String PromotionStatus_Name;
	public String WarningLogEntry_Indicator;
	public int Initial_BuildExecution_DeploymentPlan_Id;
	public int Initial_DeploymentPlan_Id;
	public String Build_AutoPromote_Indicator;
}
