package com.inedo.buildmaster.jenkins;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.notNullValue;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.domain.ApiReleasePackage;
import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;
import com.inedo.buildmaster.jenkins.utils.JenkinsConsoleLogWriter;
import com.inedo.utils.MockServer;
import com.inedo.utils.TestConfig;

import hudson.EnvVars;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;
import hudson.model.BuildListener;

/**
 * Tests for the TriggerBuildHelper class
 * 
 * @author Andrew Sumner
 */
public class PluginTests {
	public MockServer mockServer;
		
	@SuppressWarnings("rawtypes")
	public AbstractBuild build;
	@SuppressWarnings("rawtypes")	
	public AbstractProject project;
	//public Launcher launcher;
	public BuildListener listener;
	public EnvVars env;
	public ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	public PrintStream logger = new PrintStream(outContent);
	
	public String releaseNumber;
	public String packageNumber;
    public DeployToFirstStage deployToFirstStage = null;
			
	@Before
	public void before() throws IOException, InterruptedException {
		BuildMasterConfig config;

		if (TestConfig.useMockServer()) {
			mockServer = new MockServer();
			config = mockServer.getBuildMasterConfig();
		} else {
			config = TestConfig.getProGetConfig();
		}

		GlobalConfig.injectConfiguration(config);
		
        build = mock(AbstractBuild.class);
		//launcher = mock(Launcher.class);
		listener = mock(BuildListener.class);
		env = mock(EnvVars.class);
		project = mock(AbstractProject.class);
		
		when(build.getProject()).thenReturn(project);
		when(build.getEnvironment(listener)).thenReturn(env);
		when(env.expand(anyString())).then(returnsFirstArg());
		when(listener.getLogger()).thenReturn(logger);
		
        BuildMasterApi buildmaster = new BuildMasterApi(config, new JenkinsConsoleLogWriter());
		
		this.releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		this.packageNumber = buildmaster.getReleaseNextPackageNumber(TestConfig.getApplicationid(), releaseNumber);
        this.deployToFirstStage = new DeployToFirstStage(true);
	}
	
	@After
	public void tearDown() throws Exception {
		if (mockServer != null) {
			mockServer.stop();
		}
	}
	
	@Test
	public void perform() throws IOException, InterruptedException {
        TriggerableData data = new TriggerableData(String.valueOf(TestConfig.getApplicationid()), releaseNumber, packageNumber, deployToFirstStage);
	
		restLog();
		
        ApiReleasePackage releasePackage = BuildHelper.createPackage(build, listener, data);

        assertThat("Result should be successful", releasePackage, is(notNullValue()));

		String log[] = extractLogLinesRemovingApiCall();
		//assertThat("Only one action should be performed", log.length, is(1));
        assertThat("Create Build step should be the last actioned performed.", log[log.length - 1], containsString("Create BuildMaster build with PackageNumber="));
	}

	@Test
	public void performWaitTillCompleted() throws IOException, InterruptedException {
        TriggerableData data = new TriggerableData(String.valueOf(TestConfig.getApplicationid()), releaseNumber, packageNumber, deployToFirstStage);
		
		restLog();
		assertThat("Result should be successful", BuildHelper.createPackage(build, listener, data), is(true));
		
		String log[] = extractLogLines();
		assertThat("Wait step should be the last actioned performed for successful build." , log[log.length - 1], containsString("Execution Status: Succeeded"));
	}
	
	@Test
	public void performSetVariables() throws IOException, InterruptedException {
        TriggerableData data = new TriggerableData(String.valueOf(TestConfig.getApplicationid()), releaseNumber, packageNumber, new DeployToFirstStage(true))
                .setSetBuildVariables(new PackageVariables("hello=performSetVariables"));
		
		restLog();
		assertThat("Result should be successful", BuildHelper.createPackage(build, listener, data), is(true));
		
		String log = extractLog();
		assertThat("Variable passed", log, containsString("performSetVariables"));
		assertThat("Variable passed", log, not(containsString("trying")));
		
		
        PackageVariables vars = new PackageVariables("trying=again");
        vars.setPreserveVariables(true);

        data.setSetBuildVariables(vars);

		restLog();
		assertThat("Result should be successful", BuildHelper.createPackage(build, listener, data), is(true));
		
		log = extractLog();		
		assertThat("Variable passed", log, containsString("hello"));
		assertThat("Variable passed", log, containsString("trying"));
	}
	
	@Test
	public void performEnableReleaseDeployable() throws IOException, InterruptedException {
        TriggerableData data = new TriggerableData(String.valueOf(TestConfig.getApplicationid()), releaseNumber, packageNumber, deployToFirstStage)
			.setEnableReleaseDeployable(new EnableReleaseDeployable("2077"));
		
		restLog();
		assertThat("Result should be successful", BuildHelper.createPackage(build, listener, data), is(true));
		
		String log = extractLog();
		assertThat("Has requested updated", log, containsString("Releases_CreateOrUpdateRelease"));
		assertThat("Has passed deployable id", log, containsString("Deployable_Id=\"2077\""));
	}
	
	// Mocking of Server
	private void restLog() {
		logger.flush();
		outContent.reset();
	}
	
	private String extractLog() throws UnsupportedEncodingException {
		return URLDecoder.decode(outContent.toString(), "UTF-8");
	}
	
	private String[] extractLogLines() {
		return outContent.toString().split("[\\r\\n]+");
	}
	
	private String[] extractLogLinesRemovingApiCall() {
		ArrayList<String> out = new ArrayList<String>(Arrays.asList(extractLogLines()));
		
		for (Iterator<String> iterator = out.iterator(); iterator.hasNext();) {
		    String string = iterator.next();
		    if (string.contains("Executing request")) {
		        iterator.remove();
		    }
		}
		
		return out.toArray(new String[0]);
	}
}