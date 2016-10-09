package com.inedo.buildmaster.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Build;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.ReleaseDetails;
import com.inedo.buildmaster.domain.Variable;
import com.inedo.http.HttpEasy;
import com.inedo.jenkins.GlobalConfig;
import com.inedo.utils.MockServer;
import com.inedo.utils.TestConfig;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Tests for the BuildMasterClientApache class
 * 
 * TODO: There is timing issue when running all tests against a live server as tests randomly fail.  Running one at a time works fine.   
 * 
 * @author Andrew Sumner
 */
public class BuildMasterApiTest {
	private static MockServer mockServer = null;
	private static BuildMasterApi buildmaster;
	
	@BeforeClass
    public static void beforeClass() throws IOException {
		BuildMasterConfig config;

		if (TestConfig.useMockServer()) {
			mockServer = new MockServer();
			config = mockServer.getBuildMasterConfig();
		} else {
			config = TestConfig.getProGetConfig();
		}

		GlobalConfig.injectConfiguration(config);
		
		buildmaster = new BuildMasterApi();
	}
		
	@AfterClass
	public static void tearDown() throws Exception {
		if (mockServer != null) {
			mockServer.stop();
		}
		
		GlobalConfig.injectConfiguration(null);
	}

	
	@Test
	public void checkConnection() throws IOException {
		// An exception will be thrown if fails
		buildmaster.checkConnection();
	}
	
	@Test(expected=UnknownHostException.class)
	public void getWithIncorrectHost() throws IOException {
		String origUrl = GlobalConfig.getBuildMasterConfig().url; 
		
		HttpEasy.withDefaults().baseUrl("http://buildmaster1");
						
		try {
			buildmaster.getApplications();
		} finally {
			HttpEasy.withDefaults().baseUrl(origUrl);
		}
	}
		
	@Test
	public void getApplications() throws IOException  {
    	Application[] applications = buildmaster.getApplications();
    	
        assertThat("Expect BuildMaster to have applications created", applications.length, is(greaterThan(0)));
//        System.out.println(applications[0].Application_Name);
	}
	
	@Test
	public void getDeployables() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(TestConfig.getApplicationid());
    	
        assertThat("Expect BuildMaster to have applications created", deployables.length, is(greaterThan(0)));
	}
	
	
	@Test
	public void getDeployable() throws IOException  {
    	Deployable[] deployables = buildmaster.getDeployables(TestConfig.getApplicationid());
    	
        assertThat("Expect BuildMaster to have applications created", deployables.length, is(greaterThan(0)));
	}
	 
	@Test
	public void enableReleaseDeployable() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		
		ReleaseDetails before = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);
		
		buildmaster.enableReleaseDeployable(TestConfig.getApplicationid(), releaseNumber, "2077");
		
		ReleaseDetails after = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);
		
		assertThat(after.Releases_Extended[0].Application_Id, is(before.Releases_Extended[0].Application_Id));
		assertThat(after.Releases_Extended[0].Release_Number, is(before.Releases_Extended[0].Release_Number));
		assertThat(after.Releases_Extended[0].Workflow_Id, is(before.Releases_Extended[0].Workflow_Id));
		
		assertThat(after.Releases_Extended[0].Target_Date, is(before.Releases_Extended[0].Target_Date));
		assertThat(after.Releases_Extended[0].Release_Name, is(before.Releases_Extended[0].Release_Name));
		assertThat(after.Releases_Extended[0].Notes_Text, is(before.Releases_Extended[0].Notes_Text));
		
		if (!TestConfig.useMockServer()) {
			assertThat(after.ReleaseDeployables_Extended.length, is(before.ReleaseDeployables_Extended.length + 1));
		}
	}
	
	@Test
	public void getRelease() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		ReleaseDetails release = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);
				
		assertThat("Expect Test Application to have active release", release.Releases_Extended.length, is(greaterThan(0)));

		String status = release.Releases_Extended[0].ReleaseStatus_Name;
		
		assertThat("Expect Test Application to have active release " + releaseNumber, "Active", is(status));
	}
	
	@Test
	public void getActiveReleases() throws IOException {
		Release[] releases = buildmaster.getActiveReleases(TestConfig.getApplicationid());
		
		assertThat("Expect Test Application to have active release(s)", releases.length, is(greaterThan(0)));
		
//		for (Release release : releases) {
//			System.out.println(release.Release_Number);
//		}
	}

	@Test
	public void getVariableValues() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String testBuildNumber = buildmaster.getPreviousBuildNumber(TestConfig.getApplicationid(), releaseNumber);
		
		Variable[] variables = buildmaster.getVariableValues(TestConfig.getApplicationid(), releaseNumber, testBuildNumber);
		
		assertThat("Expect Test previous build to have variables defined", variables.length, is(greaterThan(0)));
	}
	
	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		
		assertThat("Expect Test Application to have an active release", release.length(), is(greaterThan(0)));
	}
	
	@Test
	public void getNextBuildNumber() throws NumberFormatException, IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		Integer nextBuildNumber = Integer.parseInt(buildmaster.getNextBuildNumber(TestConfig.getApplicationid(), releaseNumber));
		
		assertThat("Expect nextBuildNumber to be greate than zero", nextBuildNumber , is(greaterThan(0)));
		
//		System.out.println("nextBuildNumber: " + nextBuildNumber);
	}
	
	@Test
	public void createBuild() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String buildNumber = buildmaster.getNextBuildNumber(TestConfig.getApplicationid(), releaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
		variablesList.put("cause", "unit test");
		
		String buildMasterBuildNumber = buildmaster.createBuild(TestConfig.getApplicationid(), releaseNumber, buildNumber, variablesList);
		
		assertThat("Expect returned buildNumber to be the same as requested", buildNumber, is(buildMasterBuildNumber));
		
		boolean result = buildmaster.waitForBuildCompletion(TestConfig.getApplicationid(), releaseNumber, buildMasterBuildNumber, true);
		
		assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
		
		
	}
	
	@Test
	public void getBuild() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);
		
		Build build = buildmaster.getBuild(TestConfig.getApplicationid(), releaseNumber, buildNumber);
		
		assertThat("Expect Test Application to have build number " + buildNumber, build.Build_Number.length() , is(greaterThan(0)));
		
//		System.out.println("Current_ExecutionStatus_Name for build " + buildNumber + " is " + build.Current_ExecutionStatus_Name);
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);
		
		boolean result = buildmaster.waitForBuildCompletion(TestConfig.getApplicationid(), releaseNumber, buildNumber, false);
		
		assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
	@Test
	public void printExecutionLog() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getNextBuildNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);
		Build build = buildmaster.getBuild(TestConfig.getApplicationid(), releaseNumber, buildNumber);
		
		PrintStream printSteamOrig = mockServer.getBuildMasterConfig().printStream;
		ByteArrayOutputStream outContent  = new ByteArrayOutputStream();
		
		try {
			PrintStream printSteam = new PrintStream(outContent);
			mockServer.getBuildMasterConfig().printStream = printSteam;
			
			buildmaster.printExecutionLog(build.Current_Execution_Id);
		} finally {
			mockServer.getBuildMasterConfig().printStream = printSteamOrig;
		}
		
		assertThat("Expect Test build " + buildNumber + " to have an execution log", outContent.size(), is(greaterThan(0)));
		
//		System.out.println(outContent.toString());
	}
	
	/*
	 * Checks what would happen if several Jenkins jobs trigger the same application at similar times
	 * 
	 * The job this is calling should have a BUILD STEP with a powershell action with this script:
	 * 	echo "start sleep"
	 *  Start-Sleep -s 60
	 *	echo "end sleep"
	 */
	@Test
	public void queueBuilds() throws IOException, InterruptedException {
		if (TestConfig.useMockServer()) return;
		
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
				
		Map<String, String> variablesList = new HashMap<>();
		int exectiontimes = 1;
		
		for (int i = 0; i < exectiontimes; i++) {
			String testrun = String.valueOf(i + 1);
			
			System.out.println("");
			System.out.println("Test Run: " + testrun);
						
			variablesList.put("hello", "world" + testrun);			
			String buildNumber = buildmaster.createBuild(TestConfig.getApplicationid(), releaseNumber, variablesList);
			System.out.println("BuildNumber=" + buildNumber);

			String prevBuildNumber = buildmaster.getPreviousBuildNumber(TestConfig.getApplicationid(), releaseNumber);
			System.out.println("PreviousBuildNumber=" + prevBuildNumber);

			Variable[] variables = buildmaster.getVariableValues(TestConfig.getApplicationid(), releaseNumber, buildNumber);
			String value = "not found";
			
			for (Variable variable : variables) {
				if (variable.Variable_Name.equalsIgnoreCase("hello")) {
					value = variable.Value_Text;
				}
			}			
			
			System.out.println("Variable HELLO=" + value);
			
			buildmaster.waitForExistingBuildStepToComplete(TestConfig.getApplicationid(), releaseNumber);
			
			//buildmaster.waitForBuildCompletion(TestConfig.getApplicationid(), releaseNumber, buildNumber, false);
		}
	}

}
