package com.inedo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Application
{
	public static final String EXAMPLE = "[ " +
	"{ \"Application_Id\": 5, \"Application_Name\": \"Cusmod Cook Islands\", \"Application_Description\": \"Deploys Cusmod compents (client database, server and server config files) to the Cook Islands Cusmod environments.\", \"ReleaseNumber_Scheme_Name\": \"MajorMinorRevision\", \"BuildNumber_Scheme_Name\": \"Sequential\", \"AllowMultipleActiveReleases_Indicator\": \"Y\", \"AllowMultipleActiveBuilds_Indicator\": \"Y\", \"CreatedBy_User_Name\": \"DC3863\", \"CreatedOn_Date\": \"2013-06-18T19:44:02.5200000\", \"ModifiedBy_User_Name\": \"DC3863\", \"ModifiedOn_Date\": \"2014-01-26T22:17:04.3730000\", \"Active_Releases_Count\": 2, \"VariableSupport_Code\": \"A\"}, " +
	"{ \"Application_Id\": 4, \"Application_Name\": \"Cusmod JBMS\", \"Application_Description\": \"Deploys Cusmod compents (client database, server and server config files) to the JBMS cusmod environments.\", \"ReleaseNumber_Scheme_Name\": \"MajorMinorRevision\", \"BuildNumber_Scheme_Name\": \"Sequential\", \"AllowMultipleActiveReleases_Indicator\": \"Y\", \"AllowMultipleActiveBuilds_Indicator\": \"Y\", \"CreatedBy_User_Name\": \"DC3863\", \"CreatedOn_Date\": \"2013-06-17T20:17:10.9370000\", \"ModifiedBy_User_Name\": \"DC3863\", \"ModifiedOn_Date\": \"2014-07-06T21:14:17.0030000\", \"Active_Releases_Count\": 8, \"VariableSupport_Code\": \"A\"}, " +
	"{ \"Application_Id\": 36, \"Application_Name\": \"Pax Hold Release #2\", \"ReleaseNumber_Scheme_Name\": \"MajorMinor\", \"BuildNumber_Scheme_Name\": \"Sequential\", \"AllowMultipleActiveReleases_Indicator\": \"N\", \"AllowMultipleActiveBuilds_Indicator\": \"N\", \"CreatedBy_User_Name\": \"AS979c\", \"CreatedOn_Date\": \"2015-03-20T03:19:25.4500000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-03-20T03:19:25.4500000\", \"ApplicationGroup_Id\": 4, \"ApplicationGroup_Name\": \"Team X\", \"Active_Releases_Count\": 1, \"VariableSupport_Code\": \"N\"}, " +
	"{ \"Application_Id\": 27, \"Application_Name\": \"Smart Viewer\", \"Application_Description\": \"\", \"ReleaseNumber_Scheme_Name\": \"MajorMinorRevision\", \"BuildNumber_Scheme_Name\": \"Unique\", \"AllowMultipleActiveReleases_Indicator\": \"N\", \"AllowMultipleActiveBuilds_Indicator\": \"N\", \"CreatedBy_User_Name\": \"AF1096\", \"CreatedOn_Date\": \"2014-11-12T23:20:50.6430000\", \"ModifiedBy_User_Name\": \"AS979c\", \"ModifiedOn_Date\": \"2015-02-24T02:23:28.6500000\", \"ApplicationGroup_Id\": 4, \"ApplicationGroup_Name\": \"Team X\", \"Active_Releases_Count\": 1, \"VariableSupport_Code\": \"N\"} " +
	"]";


	public int Application_Id;
	public String Application_Name;
	public int Active_Releases_Count;
	public Integer ApplicationGroup_Id;
	public String ApplicationGroup_Name;
}