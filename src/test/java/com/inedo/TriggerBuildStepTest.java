package com.inedo;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;

import org.junit.Test;

public class TriggerBuildStepTest {
  //@Rule 
  //public JenkinsRule j = new JenkinsRule();

  @Test 
  public void first() throws Exception {
	  	  
//    FreeStyleProject project = j.createFreeStyleProject();
//    project.getBuildersList().add(new Shell("echo hello"));
//    FreeStyleBuild build = project.scheduleBuild2(0).get();
//    System.out.println(build.getDisplayName() + " completed");
//    
//    // TODO: change this to use HtmlUnit
//    String s = FileUtils.readFileToString(build.getLogFile());
//    assertTrue(s.contains("+ echo hello"));
  }
}

//import static org.junit.Assert.*;
//
//import java.io.PrintStream;
//import java.nio.charset.Charset;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.jvnet.hudson.test.JenkinsRule;
//import hudson.EnvVars;
//import hudson.model.BuildListener;
//import hudson.model.FreeStyleBuild;
//import hudson.model.StreamBuildListener;
//import hudson.model.FreeStyleProject;
//import hudson.slaves.EnvironmentVariablesNodeProperty;
//
//public class TriggerBuildStepTest {
//	@Rule public JenkinsRule j = new JenkinsRule();
//	
//	private void setEnvironmentVariable(String variable, String value) {
//	    EnvironmentVariablesNodeProperty prop = new EnvironmentVariablesNodeProperty();
//	    EnvVars envVars = prop.getEnvVars();
//	    envVars.put(variable, value);
//	    j.jenkins.getGlobalNodeProperties().add(prop);
//	}
//
//	/**
//	 * Checks that can expand variable 
//	 * @throws Exception 
//	 */
//	@Test
//	public void buildNumberExpands() throws Exception {
//		
//		setEnvironmentVariable("BUILD_NUMBER", "42");
//		
//		FreeStyleProject project = j.createFreeStyleProject();
//		
//		TriggerBuildBuildStep plugin = new TriggerBuildBuildStep("${BUILD_NUMBER}", true, true, true);
//		
//		String output = "";
//		PrintStream stream = new PrintStream(output);
//		BuildListener listener = new StreamBuildListener(stream, Charset.defaultCharset());
//		 
//		project.getBuildersList().add(plugin);
//		 
//		FreeStyleBuild build = project.scheduleBuild2(0).get();
//		
//		//AbstractBuild<?, ?> build = Mockito.mock(AbstractBuild.class);
//		//Mockito.when(build.getResult()).thenReturn(Result.FAILURE);
//		
//		String buildNumber = plugin.expandBuildNumber(build, listener);
//		
//		assertEquals("Build number returned should be environment variable", "42", buildNumber);
//		 		    
//	}
//}