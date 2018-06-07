package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Build
{
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

