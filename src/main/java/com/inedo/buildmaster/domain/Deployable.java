package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Deployable
{
	public static final String EXAMPLE = 
			"[" + 
			"  { \"Deployable_Id\": 2073, \"Deployable_Name\": \"Application\", \"DeployableType_Code\": \"OTHR\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-04-23T23:01:28.7830000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-04-28T01:49:15.5400000\", \"Application_Id\": 36, \"Application_Name\": \"Jenkins Plugin Test Application\", \"Dependencies_Count\": 1, \"Dependants_Count\": 0}," +
			"  { \"Deployable_Id\": 2077, \"Deployable_Name\": \"Configuration\", \"DeployableType_Code\": \"OTHR\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-04-28T01:15:27.9630000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-04-28T01:15:27.9630000\", \"Application_Id\": 36, \"Application_Name\": \"Jenkins Plugin Test Application\", \"Dependencies_Count\": 0, \"Dependants_Count\": 0}, " +
			"  { \"Deployable_Id\": 2078, \"Deployable_Name\": \"Baseline\", \"DeployableType_Code\": \"OTHR\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-04-28T01:16:02.7970000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-04-28T01:16:02.7970000\", \"Application_Id\": 36, \"Application_Name\": \"Jenkins Plugin Test Application\", \"Dependencies_Count\": 0, \"Dependants_Count\": 1}" +
			"]";
					
	public int Deployable_Id;
	public String Deployable_Name;
	public int Dependencies_Count;
	public int Dependants_Count;
	public String InclusionType_Code;
	public String Referenced_Release_Number;
	public int Referenced_Application_Id;
}

