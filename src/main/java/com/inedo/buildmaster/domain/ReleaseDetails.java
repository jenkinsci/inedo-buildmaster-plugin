package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ReleaseDetails
{
	public static final String EXAMPLE = 
		"{" +
		"\"Releases_Extended\":" + 
		"[" +
		"  { \"Application_Id\": 36, \"Release_Number\": \"1.3\", \"ReleaseStatus_Name\": \"Active\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-04-15T02:21:14.0300000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-04-15T02:21:14.0300000\", \"Release_Name\": \"1.3\", \"Release_Sequence\": 3, \"Application_Name\": \"Test App 3\", \"Workflow_Id\": 62, \"Workflow_Name\": \"Standard\", \"AutoDeployRelease_Indicator\": \"Y\", \"AllowOutOfSequencePromotions_Indicator\": \"N\", \"AutoCancelReleases_Indicator\": \"N\", \"AutoIncrementRelease_Indicator\": \"N\", \"Latest_Step_Sequence\": 1, \"Latest_Environment_Id\": 4, \"Latest_Environment_Name\": \"DEV\", \"Latest_Build_Number\": \"26\", \"Latest_Promoted_Date\": \"2015-04-15T04:06:57.4330000\", \"Latest_Promoted_Build_Number\": \"26\", \"Latest_Promoted_Environment_Id\": 4, \"Latest_Promotion_Status\": \"Completed\", \"Latest_PromotedBy_Name\": \"SYSTEM\", \"NextLatest_Step_Sequence\": 2, \"NextLatest_Environment_Id\": 3, \"NextLatest_Environment_Name\": \"PRD\", \"NextLatest_IsApproved_Indicator\": \"Y\", \"Furthest_Step_Sequence\": 1, \"Furthest_Environment_Id\": 4, \"Furthest_Environment_Name\": \"DEV\", \"Furthest_Build_Number\": \"26\", \"Furthest_Promoted_Date\": \"2015-04-15T04:06:57.4330000\", \"Furthest_PromotedBy_Name\": \"SYSTEM\", \"NextFurthest_Step_Sequence\": 2, \"NextFurthest_Environment_Id\": 3, \"NextFurthest_Environment_Name\": \"PRD\", \"First_Step_Sequence\": 1, \"First_Environment_Id\": 4, \"First_Environment_Name\": \"DEV\", \"Final_Step_Sequence\": 2, \"Final_Environment_Id\": 3, \"Final_Environment_Name\": \"PRD\", \"Builds_Count\": 26}" +
		"]," +
		"\"ReleaseDeployables_Extended\":" +
		"[" +
		"  { \"Deployable_Id\": 2070, \"Deployable_Name\": \"TestDeployable\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-03-22T20:30:37.2530000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-03-22T20:30:37.2530000\", \"Application_Id\": 36, \"Application_Name\": \"Test App 3\", \"Dependencies_Count\": 0, \"Dependants_Count\": 0, \"Release_Number\": \"1.3\", \"InclusionType_Code\": \"I\"}" +
		"]," +
		"\"ReleaseConfigurationFiles\":" + 
		"[" +
		"]" +
		"}";			

	public Release[] Releases_Extended;
	public Deployable[] ReleaseDeployables_Extended;
	public String[] ReleaseConfigurationFiles;
}

