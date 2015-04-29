package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Release
{
	public static final String EXAMPLE =
		"[" + 
		"  { \"Application_Id\": 36, \"Release_Number\": \"1.3\", \"ReleaseStatus_Name\": \"Active\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-04-15T02:21:14.0300000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-04-15T02:21:14.0300000\", \"Release_Name\": \"1.3\", \"Release_Sequence\": 3, \"Application_Name\": \"Test App 3\", \"Workflow_Id\": 62, \"Workflow_Name\": \"Standard\", \"AutoDeployRelease_Indicator\": \"Y\", \"AllowOutOfSequencePromotions_Indicator\": \"N\", \"AutoCancelReleases_Indicator\": \"N\", \"AutoIncrementRelease_Indicator\": \"N\", \"Latest_Step_Sequence\": 1, \"Latest_Environment_Id\": 4, \"Latest_Environment_Name\": \"DEV\", \"Latest_Build_Number\": \"27\", \"Latest_Promoted_Date\": \"2015-04-20T02:01:41.5670000\", \"Latest_Promoted_Build_Number\": \"27\", \"Latest_Promoted_Environment_Id\": 4, \"Latest_Promotion_Status\": \"Completed\", \"Latest_PromotedBy_Name\": \"SYSTEM\", \"NextLatest_Step_Sequence\": 2, \"NextLatest_Environment_Id\": 3, \"NextLatest_Environment_Name\": \"PRD\", \"NextLatest_IsApproved_Indicator\": \"Y\", \"Furthest_Step_Sequence\": 1, \"Furthest_Environment_Id\": 4, \"Furthest_Environment_Name\": \"DEV\", \"Furthest_Build_Number\": \"27\", \"Furthest_Promoted_Date\": \"2015-04-20T02:01:41.5670000\", \"Furthest_PromotedBy_Name\": \"SYSTEM\", \"NextFurthest_Step_Sequence\": 2, \"NextFurthest_Environment_Id\": 3, \"NextFurthest_Environment_Name\": \"PRD\", \"First_Step_Sequence\": 1, \"First_Environment_Id\": 4, \"First_Environment_Name\": \"DEV\", \"Final_Step_Sequence\": 2, \"Final_Environment_Id\": 3, \"Final_Environment_Name\": \"PRD\", \"Builds_Count\": 27}" +
		"]";
			
	public int Application_Id;
	public String Application_Name;
	public String Release_Number;
	public String ReleaseStatus_Name;
	public int Workflow_Id;
	public String Workflow_Name;
	public String AutoDeployRelease_Indicator;
	public String Latest_Build_Number;
	public int First_Environment_Id;
	public String First_Environment_Name;
	public int Final_Environment_Id;
	public String Final_Environment_Name;
	public int Builds_Count;
	
	public String Target_Date;
	public String Release_Name;
	public String Notes_Text;
}

