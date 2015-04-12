package com.inedo;

import static org.junit.Assert.*;

import java.net.UnknownHostException;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Before;
import org.junit.Test;

import com.inedo.BuildMasterConfig;

public class BuildMasterApiTest {
	// TODO These tests are poorly constructed in that they are querying an active BuildMaster instance and therefore these values will 
	// alter over time.  We should either mock the release or create a test application in a known state in BuildMaster.
	
	final String appApplicationId = "30";
	final String appReleaseNumber = "0.1";
	
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
		config.logCalls = true;
			
		buildmaster = new BuildMasterApi(config);
	}

	@Test
	public void getApplications() {
		JSONArray applications = buildmaster.getApplications();
		
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
	public void createBuild() {
		String buildNumber = "23";
		
		String buildMasterBuildNumber = buildmaster.createBuild(appApplicationId, appReleaseNumber, buildNumber, null);
		//String buildMasterBuildNumber = buildmaster.createBuild(appApplicationId, appReleaseNumber);
		
		assertEquals("Expect returned buildNumber to be the same as requested", buildNumber, buildMasterBuildNumber);
	}
	
	@Test
	public void getBuild() {
		String buildNumber = "23";
		
		JSONArray build = buildmaster.getBuild(appApplicationId, appReleaseNumber, buildNumber);
		
		assertTrue("Expect PaxHoldRelease Application to have build number " + buildNumber, build.size() > 0);
		
		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.getJSONObject(0).getString("Current_ExecutionStatus_Name"));
	}
	
	@Test
	public void getWaitForBuildCompletion() {
		String buildNumber = "23";
		
		boolean result = buildmaster.waitForBuildCompletion(appApplicationId, appReleaseNumber, buildNumber, true);
		
		assertTrue("Expect PaxHoldRelease build " + buildNumber + " to have built and deployed successfully", result);
		
		//System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.getJSONObject(0).getString("Current_ExecutionStatus_Name"));
	}
}
