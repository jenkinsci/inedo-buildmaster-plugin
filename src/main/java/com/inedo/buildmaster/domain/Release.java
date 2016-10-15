package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Release
{
	public static final String EXAMPLE =
		"[" + 
		"  { \"Application_Id\": 36, \"Release_Number\": \"1.3\", \"ReleaseStatus_Name\": \"Active\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-04-15T02:21:14.0300000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-04-15T02:21:14.0300000\", \"Release_Name\": \"1.3\", \"Release_Sequence\": 3, \"Application_Name\": \"Test App 3\", \"Workflow_Id\": 62, \"Workflow_Name\": \"Standard\", \"AutoDeployRelease_Indicator\": \"Y\", \"AllowOutOfSequencePromotions_Indicator\": \"N\", \"AutoCancelReleases_Indicator\": \"N\", \"AutoIncrementRelease_Indicator\": \"N\", \"Latest_Step_Sequence\": 1, \"Latest_Environment_Id\": 4, \"Latest_Environment_Name\": \"DEV\", \"Latest_Build_Number\": \"27\", \"Latest_Promoted_Date\": \"2015-04-20T02:01:41.5670000\", \"Latest_Promoted_Build_Number\": \"27\", \"Latest_Promoted_Environment_Id\": 4, \"Latest_Promotion_Status\": \"Completed\", \"Latest_PromotedBy_Name\": \"SYSTEM\", \"NextLatest_Step_Sequence\": 2, \"NextLatest_Environment_Id\": 3, \"NextLatest_Environment_Name\": \"PRD\", \"NextLatest_IsApproved_Indicator\": \"Y\", \"Furthest_Step_Sequence\": 1, \"Furthest_Environment_Id\": 4, \"Furthest_Environment_Name\": \"DEV\", \"Furthest_Build_Number\": \"27\", \"Furthest_Promoted_Date\": \"2015-04-20T02:01:41.5670000\", \"Furthest_PromotedBy_Name\": \"SYSTEM\", \"NextFurthest_Step_Sequence\": 2, \"NextFurthest_Environment_Id\": 3, \"NextFurthest_Environment_Name\": \"PRD\", \"First_Step_Sequence\": 1, \"First_Environment_Id\": 4, \"First_Environment_Name\": \"DEV\", \"Final_Step_Sequence\": 2, \"Final_Environment_Id\": 3, \"Final_Environment_Name\": \"PRD\", \"Builds_Count\": 27}" +
		"]";
			
	public int Release_Id;
	public int Application_Id;
	public String Release_Number;
	public String ReleaseStatus_Name;
	public String Notes_Text;

	public String CreatedOn_Date;
	public String ModifiedOn_Date;
	public String CancelledReason_Text;
	public String CancelledOn_Date;
	public String Target_Date;

	public String Release_Name;
	public int Release_Sequence;
	public int Pipeline_Id;
	public String Pipeline_Name;
	public String Application_Name;
	
	public String Furthest_Build_Number;
	public String Furthest_PipelineStage_Name;
	public int Furthest_Build_Id;
		
	public int Latest_Build_Id;
	public String Latest_Build_Number;
	public String Latest_Build_CreatedOn_Date;
	public int Latest_Promotion_Id;
	public String Latest_Promoted_Date;
	public String Latest_PromotionStatus_Name;
	public String Latest_Promotion_PipelineStage_Name;
		
	public String ReleaseTemplate_Name;
}

