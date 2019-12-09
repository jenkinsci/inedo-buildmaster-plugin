package com.inedo.buildmaster.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.concordion.cubano.driver.http.HttpEasy;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.inedo.buildmaster.domain.ApiDeployment;
import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.domain.ApiReleaseBuild;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.jenkins.GlobalConfig;
import com.inedo.buildmaster.jenkins.utils.JenkinsConsoleLogWriter;
import com.inedo.utils.JsonCompare;
import com.inedo.utils.MockData;
import com.inedo.utils.MockServer;
import com.inedo.utils.TestConfig;

/**
 * Tests for the BuildMasterClientApache class
 * 
 * @author Andrew Sumner
 */
public class BuildMasterApiTest {
	private static MockServer mockServer = null;
	private static BuildMasterApi buildmaster;
    private static boolean compareJson = false;
	
	@BeforeClass
    public static void beforeClass() throws IOException {
		// Set the default log level for SLF4J simple logger to TRACE (defaults to INFO)
		System.setProperty(org.slf4j.impl.SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "TRACE");
		
		BuildMasterConfig config;

		if (TestConfig.useMockServer()) {
			mockServer = new MockServer();
			config = mockServer.getBuildMasterConfig();
		} else {
			config = TestConfig.getProGetConfig();
            compareJson = true;
		}

		GlobalConfig.injectConfiguration(config);
		
		buildmaster = new BuildMasterApi(new JenkinsConsoleLogWriter());
        buildmaster.setRecordJson(compareJson);
	}
		
	@AfterClass
	public static void tearDown() {
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

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.APPLICATIONS.getAsString(), buildmaster.getJsonString(), "[?(@.Application_Name=='TestApplication')]", Application.class);
        }
	}
	
	@Test
	public void getApplication() throws IOException  {
    	Application application = buildmaster.getApplication(TestConfig.getApplicationid());
    	
        assertThat("Expect BuildMaster to have sample application", application.Application_Name, is("TestApplication"));

        if (compareJson) {
            // API returns an array even though asking for a single application
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.APPLICATION.getAsString(), buildmaster.getJsonString(), "Applications_Extended[0]", Application.class);
        }
	}

    @Test
    public void getPipelineStages() throws IOException {
        ApiRelease[] releases = buildmaster.getActiveReleases(TestConfig.getApplicationid());

        List<String> stages = buildmaster.getPipelinesStages(releases[0].pipelineId);

        assertThat("Expect pipeline stages", stages.size(), is(greaterThan(0)));
    }

	@Test
	public void getLatestActiveReleaseNumber() throws IOException {
		String release = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		
		assertThat("Expect Test Application to have an active release", release.length(), is(greaterThan(0)));
	}
	
	@Test
	public void getRelease() throws IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        ApiRelease release = buildmaster.getRelease(TestConfig.getApplicationid(), releaseNumber);

        assertThat("Expect Test Application to have active release", release, is(notNullValue()));

		String status = release.status;
		
		assertThat("Expect Test Application to have active release " + releaseNumber, "active", is(status));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE.getAsString(), buildmaster.getJsonString(), "[0]", ApiRelease.class);
        }
	}
	
	@Test
	public void getActiveReleases() throws IOException {
        ApiRelease[] releases = buildmaster.getActiveReleases(TestConfig.getApplicationid());
		
		assertThat("Expect Test Application to have active release(s)", releases.length, is(greaterThan(0)));

		if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE.getAsString(), buildmaster.getJsonString(), "[0]", ApiRelease.class);
        }
	}
	
	@Test
    public void getNextBuildNumber() throws NumberFormatException, IOException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        Integer nextBuildNumber = Integer.parseInt(buildmaster.getReleaseNextBuildNumber(TestConfig.getApplicationid(), releaseNumber));
		
        assertThat("Expect nextBuildNumber to be greate than zero", nextBuildNumber, is(greaterThan(0)));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE.getAsString(), buildmaster.getJsonString(), "[0]", ApiRelease.class);
        }
	}
	
	@Test
    public void createBuild() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = buildmaster.getReleaseNextBuildNumber(TestConfig.getApplicationid(), releaseNumber);
		Map<String, String> variablesList = new HashMap<>();
		variablesList.put("hello", "world");
        variablesList.put("cause", "unit test");
		
        ApiReleaseBuild releaseBuild = buildmaster.createBuild(TestConfig.getApplicationid(), releaseNumber, buildNumber, variablesList);
        if (compareJson) {
            JsonCompare.assertFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE_BUILD.getAsString(), buildmaster.getJsonString(), ApiReleaseBuild.class);
        }

        assertThat("Expect returned buildNumber to be the same as requested", buildNumber, is(releaseBuild.number));
		
        ApiDeployment[] deployments = buildmaster.deployBuildToStage(TestConfig.getApplicationid(), releaseNumber, releaseBuild.number, null, null);

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_DEPLOYMENT.getAsString(), buildmaster.getJsonString(), "[0]", ApiDeployment.class);
        }

        boolean result = buildmaster.waitForDeploymentToComplete(deployments, true);

        assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}
	
    @Test
    public void deployBuildToStage() throws IOException, InterruptedException {
        String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = String.valueOf(Integer.parseInt(buildmaster.getReleaseNextBuildNumber(TestConfig.getApplicationid(), releaseNumber)) - 1);

        ApiDeployment[] deployments = buildmaster.deployBuildToStage(TestConfig.getApplicationid(), releaseNumber, buildNumber, null, "Integration");

        assertThat("Have a deployment", deployments.length, is(greaterThan(0)));
        assertThat("Envrionment is what was asked for", deployments[0].environmentName, is("Integration"));

        if (compareJson) {
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_DEPLOYMENT_WITH_ENV.getAsString(), buildmaster.getJsonString(), "[0]", ApiDeployment.class);
        }
    }

	@Test
    public void getBuild() throws IOException {
        String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = String.valueOf(buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber));
		
        ApiReleaseBuild releaseBuild = buildmaster.getBuild(TestConfig.getApplicationid(), releaseNumber, buildNumber);
		
        assertThat("Expect Test Application to have build number " + buildNumber, releaseBuild.number.length(), is(greaterThan(0)));

        if (compareJson) {
            // NOTE: returns array even though should only be a single value
            JsonCompare.assertArrayFieldsIdentical("API Structure has not changed",
                    MockData.API_RELEASE_BUILDS.getAsString(), buildmaster.getJsonString(), "[0]", ApiReleaseBuild.class);
        }
	}
	
	@Test
	public void getWaitForBuildCompletion() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber);
		
        boolean result = buildmaster.waitForActiveDeploymentsToComplete(TestConfig.getApplicationid(), releaseNumber);
		
        assertThat("Expect Test build " + buildNumber + " to have built and deployed successfully", result);
	}

	@Test
    public void printExecutionLog() throws IOException, InterruptedException {
		String releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
        String buildNumber = buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber);

        ApiDeployment deployment = buildmaster.getLatestDeployment(TestConfig.getApplicationid(), releaseNumber, buildNumber);
		
        buildmaster.waitForActiveDeploymentsToComplete(deployment.applicationId, deployment.releaseNumber);

        String log = buildmaster.getExecutionLog(deployment.id);
		
        assertThat("Expect Test build " + buildNumber + " to have an execution log", log.length(), is(greaterThan(0)));
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
        int executionTimes = 1;

		for (int i = 0; i < executionTimes; i++) {
			String testRun = String.valueOf(i + 1);

			System.out.println();
			System.out.println("Test Run: " + testRun);
						
			variablesList.put("hello", "world" + testRun);
            ApiReleaseBuild releaseBuild = buildmaster.createBuild(TestConfig.getApplicationid(), releaseNumber, variablesList);
            System.out.println("BuildNumber=" + releaseBuild.number);

            ApiDeployment[] deployments = buildmaster.deployBuildToStage(TestConfig.getApplicationid(), releaseNumber, releaseBuild.number, null, null);

            String currentBuildNumber = buildmaster.getReleaseCurrentBuildNumber(TestConfig.getApplicationid(), releaseNumber);
            System.out.println("CurrentBuildNumber=" + currentBuildNumber);

            buildmaster.waitForDeploymentToComplete(deployments, false);
		}
	}

}
