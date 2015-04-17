package com.inedo;

import static org.junit.Assert.*;

import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;

import com.inedo.api.BuildMasterConfig;

public class BuildMasterApiTest {
	// TODO These tests are poorly constructed in that they are querying an active BuildMaster instance and therefore these values will 
	// alter over time.  We should either mock the release or create a test application in a known state in BuildMaster.
	
	final String appApplicationId = "36";
	final String appReleaseNumber = "1.3";
	
	private BuildMasterConfig config;
	private BuildMasterApi buildmaster;
	
	@Before
	public void initConfig() {
		config = new BuildMasterConfig();
		
		// Global Config
		config.url = "http://buildmaster";
		config.authentication = "ntlm";
		config.user = "svc_jenkins";
		config.password = "J3nk1ns1";
		config.domain = "customstw";
		config.apiKey = "customs";
		config.logCalls = false;
			
		buildmaster = new BuildMasterApi(config);
	}
	
	@Test
	public void checkConnection() {
		// An exception will be thrown if fails
		buildmaster.checkConnection();
	}
	
	@Test
	public void getApplications() {		
		JSONArray applications = buildmaster.getApplications();

		//[[Active_Releases_Count:2, AllowMultipleActiveBuilds_Indicator:Y, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Deploys Cusmod compents (client database, server and server config files) to the Cook Islands Cusmod environments., Application_Id:5, Application_Name:Cusmod Cook Islands, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2013-06-18T19:44:02.5200000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-01-26T22:17:04.3730000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:A], [Active_Releases_Count:8, AllowMultipleActiveBuilds_Indicator:Y, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Deploys Cusmod compents (client database, server and server config files) to the JBMS cusmod environments., Application_Id:4, Application_Name:Cusmod JBMS, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2013-06-17T20:17:10.9370000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-07-06T21:14:17.0030000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:A], [Active_Releases_Count:3, AllowMultipleActiveBuilds_Indicator:Y, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Deploys Cusmod compents (client database, server and server config files) to the Virtual environment., Application_Id:3, Application_Name:Cusmod Linux, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2013-06-12T18:55:44.3100000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-17T23:29:20.3570000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:A], [Active_Releases_Count:4, AllowMultipleActiveBuilds_Indicator:Y, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Deploys Cusmod compents (client database, server and server config files) to the standard cusmod environments., Application_Id:2, Application_Name:CusMod NZ, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:Admin, CreatedOn_Date:2013-05-30T01:44:24.4870000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-04-10T22:00:28.3500000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Deploy updates PAXWS.TXT or PaxHoldRelease.cfg files to port servers, Application_Id:28, Application_Name:Cusmod NZ Features, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2015-01-13T00:22:47.9370000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2015-02-19T21:45:28.9270000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:N], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, Application_Id:26, Application_Name:Cusmod NZ PRD Test, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-11-04T20:59:16.9400000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-11-04T20:59:16.9400000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:N], [Active_Releases_Count:4, AllowMultipleActiveBuilds_Indicator:Y, AllowMultipleActiveReleases_Indicator:Y, Application_Description:, Application_Id:11, Application_Name:Cusmod PDV Auto Testing, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2013-10-17T22:24:47.8600000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-01-26T22:17:35.3730000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:A], [Active_Releases_Count:17, AllowMultipleActiveBuilds_Indicator:Y, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Deploy Smartgate SQL changes to Cusmod and (later) DotNet and Middleware applications, Application_Id:14, Application_Name:Cusmod SmartGate, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2013-12-13T01:33:53.8130000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-01-27T22:23:45.5300000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, Application_Description:, Application_Id:21, Application_Name:Deployment Scripts CK, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-02-11T02:25:16.3900000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-17T00:18:55.2430000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, Application_Id:22, Application_Name:Deployment Scripts JBMS, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-02-11T02:27:54.8600000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-11T02:27:54.8600000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, Application_Description:, Application_Id:20, Application_Name:Deployment Scripts NZ, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-02-11T01:49:49.2670000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-17T00:19:15.3330000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, Application_Description:, Application_Id:23, Application_Name:Deployment Scripts Other, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-02-11T18:30:23.3500000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-17T00:19:24.4800000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, Application_Description:, Application_Id:7, Application_Name:NZCSExtension, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:AS979c, CreatedOn_Date:2013-07-09T00:29:48.0100000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-17T00:30:53.2100000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, Application_Description:Deployes TSW_GUI ear file to WAS, Application_Id:9, Application_Name:TSW GUI, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:AS979c, CreatedOn_Date:2013-08-19T22:46:43.5870000, ModifiedBy_User_Name:AS979c, ModifiedOn_Date:2013-08-19T23:15:44.4370000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:4, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Verify the Cusmod CK environments are correct., Application_Id:17, Application_Name:Verify Cusmod CK, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-02-04T01:19:32.1330000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-27T21:56:35.3570000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:10, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Verify the Cusmod JBMS environments are correct., Application_Id:18, Application_Name:Verify Cusmod JBMS, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-02-04T01:53:46.5630000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-27T22:02:52.6100000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:11, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:Y, Application_Description:Verify each Cusmod environment is correct., Application_Id:16, Application_Name:Verify Cusmod NZ, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-01-29T20:32:21.2000000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-27T22:11:30.0770000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:3, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:Y, Application_Description:, Application_Id:19, Application_Name:Verify Cusmod Other, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-02-10T20:58:36.1000000, ModifiedBy_User_Name:DC3863, ModifiedOn_Date:2014-02-27T21:59:02.1000000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:A], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, ApplicationGroup_Id:4, ApplicationGroup_Name:Team X, Application_Id:33, Application_Name:Deploy Jenkins Build, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:AS979c, CreatedOn_Date:2015-03-15T21:29:15.0170000, ModifiedBy_User_Name:AS979c, ModifiedOn_Date:2015-03-15T21:29:15.0170000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:N], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:Y, ApplicationGroup_Id:4, ApplicationGroup_Name:Team X, Application_Description:, Application_Id:25, Application_Name:JBMS Middleware, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:DC3863, CreatedOn_Date:2014-09-17T22:41:14.2900000, ModifiedBy_User_Name:AS979c, ModifiedOn_Date:2015-02-24T02:22:01.2070000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:N], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, ApplicationGroup_Id:4, ApplicationGroup_Name:Team X, Application_Id:30, Application_Name:Pax Hold Release, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:CA1097, CreatedOn_Date:2015-02-22T23:15:21.7230000, ModifiedBy_User_Name:CA1097, ModifiedOn_Date:2015-02-22T23:15:21.7230000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:N], [Active_Releases_Count:2, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, ApplicationGroup_Id:4, ApplicationGroup_Name:Team X, Application_Id:36, Application_Name:Pax Hold Release #2, BuildNumber_Scheme_Name:Sequential, CreatedBy_User_Name:AS979c, CreatedOn_Date:2015-03-20T03:19:25.4500000, ModifiedBy_User_Name:AS979c, ModifiedOn_Date:2015-03-20T03:19:25.4500000, ReleaseNumber_Scheme_Name:MajorMinor, VariableSupport_Code:N], [Active_Releases_Count:1, AllowMultipleActiveBuilds_Indicator:N, AllowMultipleActiveReleases_Indicator:N, ApplicationGroup_Id:4, ApplicationGroup_Name:Team X, Application_Description:, Application_Id:27, Application_Name:Smart Viewer, BuildNumber_Scheme_Name:Unique, CreatedBy_User_Name:AF1096, CreatedOn_Date:2014-11-12T23:20:50.6430000, ModifiedBy_User_Name:AS979c, ModifiedOn_Date:2015-02-24T02:23:28.6500000, ReleaseNumber_Scheme_Name:MajorMinorRevision, VariableSupport_Code:N]]
		
		assertTrue("Expect BuildMaster to have applications defined", applications.size() > 0);
		
		for (int i = 0; i < applications.size(); i++) {
			JSONObject json = applications.getJSONObject(i);
			
			System.out.println("[" + json.getString("Application_Id") + "]" + (json.containsKey("ApplicationGroup_Name") ? json.getString("ApplicationGroup_Name") + " > " : "") + json.getString("Application_Name"));
		}
	}
	
	@Test(expected=UnknownHostException.class)
	public void getApplicationsWithError() {
		config.url = "http://buildmaster1";
				
		try {
			buildmaster.getApplications();
		} finally {
			config.url = "http://buildmaster";
		}
	}
	
	@Test
	public void getRelease() {
		JSONObject json = buildmaster.getRelease(appApplicationId, appReleaseNumber);
		JSONArray releaseDetails = ((JSONArray)json.get("Releases_Extended"));
		
		assertTrue("Expect PaxHoldRelease Application to have active release", releaseDetails.size() > 0);

		String status = releaseDetails.getJSONObject(0).getString("ReleaseStatus_Name");
		
		assertEquals("Expect PaxHoldRelease Application to have active release " + appReleaseNumber, "Active", status);
	}
	
	@Test
	public void getActiveReleases() {
		JSONArray releases = buildmaster.getActiveReleases(appApplicationId);
		
		assertTrue("Expect PaxHoldRelease Application to have active release(s)", releases.size() > 0);
		
		for (int i = 0; i < releases.size(); i++) {
			JSONObject json = releases.getJSONObject(i);
			
			System.out.println(json.getString("Release_Number"));
		}
	}

	@Test
	public void getLatestActiveReleaseNumber() {
		String release = buildmaster.getLatestActiveReleaseNumber(appApplicationId);
		
		assertTrue("Expect PaxHoldRelease Application to have an active release", release.length() > 0);
	}
	
	@Test
	public void getNextBuildNumber() {
		Integer nextBuildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(appApplicationId, appReleaseNumber));
		System.out.println("nextBuildNumber: " + nextBuildNumber);
		assertTrue("Expect nextBuildNumber to be greate than zero", nextBuildNumber > 0);
	}
	
	@Test
	public void createBuild() {
		String buildNumber = buildmaster.getNextBuildNumber(appApplicationId, appReleaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
		variablesList.put("cause", "jenkins");
		
		String buildMasterBuildNumber = buildmaster.createBuild(appApplicationId, appReleaseNumber, buildNumber, variablesList);
		//String buildMasterBuildNumber = buildmaster.createBuild(appApplicationId, appReleaseNumber);
		
		assertEquals("Expect returned buildNumber to be the same as requested", buildNumber, buildMasterBuildNumber);
		
		boolean result = buildmaster.waitForBuildCompletion(appApplicationId, appReleaseNumber, buildMasterBuildNumber, true);
		
		assertTrue("Expect PaxHoldRelease build " + buildNumber + " to have built and deployed successfully", result);

	}
	
	@Test
	public void getBuild() {
		Integer buildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(appApplicationId, appReleaseNumber)) - 1;
		
		JSONArray build = buildmaster.getBuild(appApplicationId, appReleaseNumber, buildNumber);
		
		assertTrue("Expect PaxHoldRelease Application to have build number " + buildNumber, build.size() > 0);
		
		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.getJSONObject(0).getString("Current_ExecutionStatus_Name"));
	}
	
	@Test
	public void getWaitForBuildCompletion() {
		Integer buildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(appApplicationId, appReleaseNumber)) - 1;
		
		boolean result = buildmaster.waitForBuildCompletion(appApplicationId, appReleaseNumber, buildNumber, true);
		
		assertTrue("Expect PaxHoldRelease build " + buildNumber + " to have built and deployed successfully", result);
		
		//System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.getJSONObject(0).getString("Current_ExecutionStatus_Name"));
	}
}
