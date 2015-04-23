package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Build
{
	public static final String EXAMPLE = 
	"[" +
	"  { \"Release_Number\": \"1.3\", \"Build_Number\": \"26\", \"BuildStatus_Name\": \"Active\", \"Release_Name\": \"1.3\", \"Sortable_Build_Number\": \"        26\", \"Build_Sequence\": 26, \"ReleaseStatus_Name\": \"Active\", \"Numeric_Release_Number\": 3, \"CreatedBy_User_Name\": \"SYSTEM\", \"CreatedOn_Date\": \"2015-04-15T04:06:54.2500000\", \"ModifiedBy_User_Name\": \"SYSTEM\", \"ModifiedOn_Date\": \"2015-04-15T04:06:54.2500000\", \"Application_Id\": 36, \"Application_Name\": \"Test App 3\", \"Workflow_Id\": 62, \"Workflow_Name\": \"Standard\", \"AutoDeployRelease_Indicator\": \"Y\", \"AllowOutOfSequencePromotions_Indicator\": \"N\", \"AutoCancelReleases_Indicator\": \"N\", \"First_Step_Sequence\": 1, \"First_Environment_Id\": 4, \"First_Environment_Name\": \"DEV\", \"Current_Step_Sequence\": 1, \"Current_Environment_Id\": 4, \"Current_Environment_Name\": \"DEV\", \"Current_PromotedBy_Name\": \"SYSTEM\", \"Current_Promoted_Date\": \"2015-04-15T04:06:57.4330000\", \"Current_PromotionStatus_Name\": \"Completed\", \"Current_Promotion_Environment_Id\": 4, \"TotalExecutions_Count\": 2, \"Current_Execution_Id\": 92236, \"Current_Execution_Environment_Id\": 4, \"Current_Execution_Environment_Name\": \"DEV\", \"Current_ExecutionStart_Date\": \"2015-04-15T04:06:57.5870000\", \"Current_ExecutionStatus_Name\": \"Succeeded\", \"Next_Step_Sequence\": 2, \"Next_Environment_Id\": 3, \"Next_Environment_Name\": \"PRD\", \"Final_Step_Sequence\": 2, \"Final_Environment_Id\": 3, \"Final_Environment_Name\": \"PRD\", \"CurrentExecution_WarningLogEntry_Indicator\": \"N\"}," +
	"  { \"Release_Number\": \"1.3\", \"Build_Number\": \"25\", \"BuildStatus_Name\": \"Rejected\", \"Release_Name\": \"1.3\", \"Sortable_Build_Number\": \"        25\", \"Build_Sequence\": 25, \"ReleaseStatus_Name\": \"Active\", \"Numeric_Release_Number\": 3, \"Rejection_Notes\": \"Build rejected due to creation of new build.\", \"RejectedBy_User_Name\": \"SYSTEM\", \"Rejected_Date\": \"2015-04-15T04:06:54.2500000\", \"CreatedBy_User_Name\": \"SYSTEM\", \"CreatedOn_Date\": \"2015-04-15T04:05:35.8900000\", \"ModifiedBy_User_Name\": \"SYSTEM\", \"ModifiedOn_Date\": \"2015-04-15T04:05:35.8900000\", \"Application_Id\": 36, \"Application_Name\": \"Test App 3\", \"Workflow_Id\": 62, \"Workflow_Name\": \"Standard\", \"AutoDeployRelease_Indicator\": \"Y\", \"AllowOutOfSequencePromotions_Indicator\": \"N\", \"AutoCancelReleases_Indicator\": \"N\", \"First_Step_Sequence\": 1, \"First_Environment_Id\": 4, \"First_Environment_Name\": \"DEV\", \"TotalExecutions_Count\": 1, \"Final_Step_Sequence\": 2, \"Final_Environment_Id\": 3, \"Final_Environment_Name\": \"PRD\"} " +
	"]";
			
	public String Build_Number;
	public String BuildStatus_Name;
	public String Rejection_Notes;
	public String Current_Execution_Id;
	public int Current_Execution_Environment_Id;
	public String Current_Execution_Environment_Name;
	public String Current_ExecutionStatus_Name;
	public String CurrentExecution_WarningLogEntry_Indicator;
	public int First_Environment_Id;
	public String First_Environment_Name;
	public int Next_Environment_Id;
	public String Next_Environment_Name;
	public int Final_Environment_Id;
	public String Final_Environment_Name;
}

