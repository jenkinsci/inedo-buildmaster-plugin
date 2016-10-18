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
	
	public Integer Release_Id;
	public String Release_Number;
	public String Build_Id;
	public String Build_Number;
	public String BuildStatus_Name;
	public String Release_Name;
	public String Target_Date;
	public Integer Build_Sequence;
	public Integer Pipeline_Id;
	public String Pipeline_Name;
	public String ReleaseStatus_Name;
	public Integer Release_Sequence;
//	public String Release_Notes_Text;
//	public String Rejection_Notes;
//	RejectedBy_User_Name
	public String Rejected_Date;
//	CreatedBy_User_Name":"SYSTEM",
	public String CreatedOn_Date;
//	ModfiedBy_User_Name":"SYSTEM",
	public String ModifiedOn_Date;
	public String BuildImporter_Configuration;
	public Integer Application_Id;
	public String Application_Name;
	public Integer Latest_Promotion_Id;
	public String Latest_Promotion_Date;
	public String Latest_Promotion_Status;
	public String Latest_PipelineStage_Name;
	public String Furthest_PipelineStage_Name;
	public Integer Latest_Execution_Id;
	public Integer Latest_Execution_Environment_Id;
	public String Latest_Execution_Environment_Name;
	public String Latest_Execution_RunState_Code;
	public String Latest_Execution_Status_Code;			
}

