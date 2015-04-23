package com.inedo.buildmaster;

//import java.io.PrintStream;
//import java.nio.charset.Charset;
//
//import javax.inject.Inject;
//
//import hudson.EnvVars;
//import hudson.model.BuildListener;
//import hudson.model.Describable;
//import hudson.model.FreeStyleBuild;
//import hudson.model.StreamBuildListener;
//import hudson.model.Descriptor;
//import hudson.model.FreeStyleProject;
//import hudson.slaves.EnvironmentVariablesNodeProperty;
//import hudson.tasks.BatchFile;
//import hudson.tasks.Shell;
//import hudson.util.DescribableList;
//
//import org.apache.commons.io.FileUtils;
//import org.apache.commons.lang.SystemUtils;
//import org.junit.Before;
//import org.junit.ClassRule;
//import org.junit.Rule;
//import org.junit.Test;
//import org.jvnet.hudson.test.JenkinsRule;
//import org.jvnet.hudson.test.recipes.WithPlugin;
//
//import com.gargoylesoftware.htmlunit.html.HtmlForm;
//import com.gargoylesoftware.htmlunit.html.HtmlInput;
//import com.gargoylesoftware.htmlunit.html.HtmlPage;
//
//import static org.junit.Assert.*;

public class TriggerBuildBuilderTest {
//	@Rule
//	public JenkinsRule j = new JenkinsRule();
//
//	// TODO This is not working, look at another plugin that is and try again
//	@Test
//	public void configure() throws Exception {
//		// Set global configuration for the plugin
//		HtmlPage p = j.createWebClient().goTo("configure");		
//		HtmlForm form = p.getFormByName("configure");		
//		
//		HtmlInput url = form.getInputByName("_.url");
//		url.setValueAttribute("http://localhost:1234/");
//
//		HtmlInput apiKey = form.getInputByName("_.apiKey");
//		apiKey.setValueAttribute("mykey");
//		
//		form.submit();
//		
//		// Set job configuration
//		FreeStyleProject project = j.createFreeStyleProject();
//		
//		SelectApplicationBuilder before = new SelectApplicationBuilder("36", "LATEST", "NONE");
//		project.getBuildersList().add(before);
//		
//		j.submit(j.createWebClient().getPage(project,"configure").getFormByName("config"));
//		
//		SelectApplicationBuilder after = project.getBuildersList().get(SelectApplicationBuilder.class);
//		
//		j.assertEqualBeans(before, after, "applicationId, releaseNumber, buildNumberSource");
//	}
	
	/* An example test to prove can do a builder */
//	@Test 
//	public void simpletest_build() throws Exception {
//	    FreeStyleProject project = j.createFreeStyleProject();
//	    
//	    if (SystemUtils.IS_OS_WINDOWS) {
//	    	project.getBuildersList().add(new BatchFile("echo hello"));
//	    }
//	    
//	    if (SystemUtils.IS_OS_UNIX) {
//	    	project.getBuildersList().add(new Shell("echo hello"));
//	    }
//	    
//	    FreeStyleBuild build = project.scheduleBuild2(0).get();
//	    
//	    j.assertBuildStatusSuccess(build);
//	    j.assertLogContains("hello", build);
//	}
	
	/* An example test to prove can do a builder */
//	@Test 
//	public void simpletest_configure() throws Exception {
//	    FreeStyleProject project = j.createFreeStyleProject();
//	    
//	    BatchFile before = new BatchFile("echo hello world");
//		project.getBuildersList().add(before);
//		
//		j.submit(j.createWebClient().getPage(project,"configure").getFormByName("config"));
//		
//		BatchFile after = project.getBuildersList().get(BatchFile.class);
//		
//		j.assertEqualBeans(before, after, "command");
//	}
	
//	@Test
//	public void selectApplicationBuilder_configure() throws Exception {
//		
//		HtmlPage p = j.createWebClient().goTo("configure");
//		
//		HtmlForm form = p.getFormByName("configure");
//		
//		HtmlInput url = form.getInputByName("_.url");
//		url.setValueAttribute("http://localhost:1234/");
//		assertTrue(p.getDocumentElement().getTextContent().contains("instead of localhost"));
//		
//		
////		FreeStyleProject project = j.createFreeStyleProject();
////				
////		SelectApplicationBuilder before = new SelectApplicationBuilder("36", "LATEST", "NONE");
////		project.getBuildersList().add(before);
////		
////		j.submit(j.createWebClient().getPage(
////				
////		j.submit(j.createWebClient().getPage(project,"configure").getFormByName("config"));
////		
////		SelectApplicationBuilder after = project.getBuildersList().get(SelectApplicationBuilder.class);
////		
////		j.assertEqualBeans(before, after, "applicationId, releaseNumber, buildNumberSource");
//	}
	
	
//	@Test
//	public void selectApplicationBuilder_build() throws Exception {
//		FreeStyleProject project = j.createFreeStyleProject();
//		
//		
//		//Descriptor descriptor = j.jenkins.getDescriptor(BuildMasterPlugin.class);
//		//String page = descriptor.getGlobalConfigPage();
//		
//		
//		
//		project.getBuildersList().add(new SelectApplicationBuilder("36", "LATEST", "NONE"));
//		
//		FreeStyleBuild build = project.scheduleBuild2(0).get();
//	    
//	    j.assertBuildStatusSuccess(build);
//	    j.assertLogContains("BUILDMASTER_APPLICATION_ID=36", build);
//	    j.assertLogContains("BUILDMASTER_RELEASE_NUMBER=", build);
//	    j.assertLogNotContains("BUILDMASTER_BUILD_NUMBER", build);
//	    
//	}
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
//		
//		setEnvironmentVariable("BUILD_NUMBER", "42");
//		
//		FreeStyleProject project = j.createFreeStyleProject();
//		
//		SelectApplicationBuilder plugin = new SelectApplicationBuilder("a", "b", "c");
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
//		String log = build.getLog();
//		log = log;
//		//assertEquals("Build number returned should be environment variable", "42", buildNumber);
//		 		    
//	}

}