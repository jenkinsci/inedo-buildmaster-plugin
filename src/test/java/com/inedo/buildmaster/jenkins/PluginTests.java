package com.inedo.buildmaster.jenkins;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.AdditionalAnswers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import hudson.EnvVars;
import hudson.model.BuildListener;
import hudson.model.AbstractBuild;
import hudson.model.AbstractProject;

import com.inedo.buildmaster.BuildHelper;
import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.buildOption.EnableReleaseDeployable;
import com.inedo.buildmaster.buildOption.SetBuildVariables;
import com.inedo.buildmaster.buildOption.WaitTillCompleted;
import com.inedo.jenkins.GlobalConfig;
import com.inedo.jenkins.JenkinsConsoleLogWriter;
import com.inedo.utils.MockServer;
import com.inedo.utils.TestConfig;

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
	public String buildNumber;
			
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
		
		build = mock(AbstractBuild.class);;
		//launcher = mock(Launcher.class);
		listener = mock(BuildListener.class);
		env = mock(EnvVars.class);
		project = mock(AbstractProject.class);
		
		when(build.getProject()).thenReturn(project);
		when(build.getEnvironment(listener)).thenReturn(env);
		when(env.expand(anyString())).then(returnsFirstArg());
		when(listener.getLogger()).thenReturn(logger);
		
		BuildMasterApi buildmaster = new BuildMasterApi(mockServer.getBuildMasterConfig(), new JenkinsConsoleLogWriter());
		
		this.releaseNumber = buildmaster.getLatestActiveReleaseNumber(TestConfig.getApplicationid());
		this.buildNumber = buildmaster.getNextPackageNumber(TestConfig.getApplicationid(), releaseNumber);
	}
	
	@After
	public void tearDown() throws Exception {
		if (mockServer != null) {
			mockServer.stop();
		}
	}
	
	@Test
	public void perform() throws IOException, InterruptedException {
		TriggerableData data = new TriggerableData(TestConfig.getApplicationid(), releaseNumber, buildNumber);
	
		restLog();
		assertThat("Result should be successful", BuildHelper.triggerBuild(build, listener, data), is(true));
		
		String log[] = extractLogLinesRemovingApiCall();
		//assertThat("Only one action should be performed", log.length, is(1));
		assertThat("Create Build step should be the last actioned performed.", log[log.length - 1], containsString("Create BuildMaster build with BuildNumber="));
	}

	@Test
	public void performWaitTillCompleted() throws IOException, InterruptedException {
		TriggerableData data = new TriggerableData(TestConfig.getApplicationid(), releaseNumber, buildNumber)
			.setWaitTillBuildCompleted(new WaitTillCompleted(true));
		
		restLog();
		assertThat("Result should be successful", BuildHelper.triggerBuild(build, listener, data), is(true));
		
		String log[] = extractLogLines();
		assertThat("Wait step should be the last actioned performed for successful build." , log[log.length - 1], containsString("Execution Status: Succeeded"));
	}
	
	@Test
	public void performSetVariables() throws IOException, InterruptedException {
		TriggerableData data = new TriggerableData(TestConfig.getApplicationid(), releaseNumber, buildNumber)
			.setSetBuildVariables(new SetBuildVariables(false, "hello=performSetVariables"));
		
		restLog();
		assertThat("Result should be successful", BuildHelper.triggerBuild(build, listener, data), is(true));
		
		String log = extractLog();
		assertThat("Variable passed", log, containsString("performSetVariables"));
		assertThat("Variable passed", log, not(containsString("trying")));
		
		data.setSetBuildVariables(new SetBuildVariables(true, "trying=again"));
		
		restLog();
		assertThat("Result should be successful", BuildHelper.triggerBuild(build, listener, data), is(true));
		
		log = extractLog();		
		assertThat("Variable passed", log, containsString("hello"));
		assertThat("Variable passed", log, containsString("trying"));
	}
	
	@Test
	public void performEnableReleaseDeployable() throws IOException, InterruptedException {
		TriggerableData data = new TriggerableData(TestConfig.getApplicationid(), releaseNumber, buildNumber)
			.setEnableReleaseDeployable(new EnableReleaseDeployable("2077"));
		
		restLog();
		assertThat("Result should be successful", BuildHelper.triggerBuild(build, listener, data), is(true));
		
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