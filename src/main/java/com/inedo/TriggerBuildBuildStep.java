package com.inedo;

import hudson.EnvVars;
import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.util.Secret;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.BuildMasterPlugin.BuildMasterPluginDescriptor;

import javax.servlet.ServletException;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jenkins.model.Jenkins;

/**
 * Sample {@link Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link DescriptorImpl#newInstance(StaplerRequest)} is invoked and a new
 * {@link TriggerBuildBuildStep} is created. The created instance is persisted
 * to the project configuration XML by using XStream, so this allows you to use
 * instance fields (like {@link #name}) to remember the configuration.
 *
 * <p>
 * When a build is performed, the
 * {@link #perform(AbstractBuild, Launcher, BuildListener)} method will be
 * invoked.
 *
 * @author Andrew Sumner
 * 
 *         TODO Variables: Could look at passing variables to BuildMaster
 */
public class TriggerBuildBuildStep extends Builder {
	private static final String LOG_PREFIX = "[BuildMaster] "; 
	private static final String DEFAULT_BUILD_NUMBER = "${BUILDMASTER_BUILD_NUMBER}"; 

	private final boolean waitTillBuildCompleted;
	private final boolean printLogOnFailure;
	private final String variables;
	private final String applicationId;
	private final String releaseNumber;
	private final String buildNumber;

	// Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
	@DataBoundConstructor
	public TriggerBuildBuildStep(JSONObject waitTillBuildCompleted, String variables, String applicationId, String releaseNumber, String buildNumber) {
		if (waitTillBuildCompleted != null) { 
			this.waitTillBuildCompleted = true; 
			this.printLogOnFailure = "true".equalsIgnoreCase(waitTillBuildCompleted.getString("printLogOnFailure"));
		} else { 
			this.waitTillBuildCompleted = false; 
			this.printLogOnFailure = false;
		}
				
		this.variables = variables;
		this.applicationId = applicationId;
		this.releaseNumber = releaseNumber;
		this.buildNumber = buildNumber;
	}

	public boolean getWaitTillBuildCompleted() {
		return waitTillBuildCompleted;
	}

	public boolean getPrintLogOnFailure() {
		return printLogOnFailure;
	}
	
	public String getVariables() {
		return variables;
	}

	public String getApplicationId() {
		return applicationId;
	}

	public String getReleaseNumber() {
		return releaseNumber;
	}
	
	public String getBuildNumber() {
		return buildNumber;
	}
		
	@Override
	public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {
		if (!getSharedDescriptor().validatePluginConfiguration()) {
			listener.getLogger().println("Please configure BuildMaster Plugin global settings");
			return false;
		}
		
		BuildMasterConfig config = getSharedDescriptor().getBuildMasterConfig(listener.getLogger());
		BuildMasterApi buildmaster = new BuildMasterApi(config);

		String applicationId = expandVariable(build, listener, this.applicationId);
		String releaseNumber = expandVariable(build, listener, this.releaseNumber);
		String buildNumber = expandVariable(build, listener, this.buildNumber);
		
		Map<String, String> variablesList = getVariablesList(this.variables);
		
		String buildMasterBuildNumber;
		
		if (buildNumber != null && !buildNumber.isEmpty() && !DEFAULT_BUILD_NUMBER.equals(buildNumber)) {
			listener.getLogger().println(LOG_PREFIX + "Create BuildMaster build with BuildNumber=" + buildNumber);
			buildMasterBuildNumber = buildmaster.createBuild(applicationId, releaseNumber, buildNumber, variablesList);
			
			if (!buildMasterBuildNumber.equals(buildNumber)) {
				listener.getLogger().println(LOG_PREFIX + "Warning, requested build number does not match that returned from BuildMaster.");
			}
		} else {
			listener.getLogger().println(LOG_PREFIX + "Create BuildMaster build");
			buildMasterBuildNumber = buildmaster.createBuild(applicationId, releaseNumber, variablesList);
			
			listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_BUILD_NUMBER=" + buildMasterBuildNumber);
			build.addAction(new VariableInjectionAction("BUILDMASTER_BUILD_NUMBER", buildMasterBuildNumber));
		}
		
		if (waitTillBuildCompleted) {
			listener.getLogger().println(LOG_PREFIX + "Wait till build completed");
			return buildmaster.waitForBuildCompletion(applicationId, releaseNumber, buildMasterBuildNumber, printLogOnFailure);
		}

		return true;
	}

	private static Map<String, String> getVariablesList(String variables) {
		Map<String, String> variablesList = new HashMap<>();
		
		String[] variablesArray = variables.split("\n");
		
		for (String value : variablesArray) {
			value = value.trim();
			if (value.isEmpty()) continue;
			if (value.startsWith("#")) continue;
			
			int pos = value.indexOf("=");
			
			if (pos < 0) throw new RuntimeException(value + " is not in the format 'variable=value'");
			
			variablesList.put(value.substring(0, pos - 1), value.substring(pos + 1));
		}
		
		return variablesList;
	}

	protected String expandVariable(AbstractBuild<?, ?> build, BuildListener listener, String variable) {
		if (variable == null || variable.isEmpty()) {
			return variable;
		}
		
		String expanded = variable;
		
		try {
			expanded = build.getEnvironment(listener).expand(variable);
		} catch (Exception e) {
			listener.getLogger().println(LOG_PREFIX + "Exception thrown expanding '" + variable + "' : " + e.getClass().getName() + " " + e.getMessage());
		}
		
		return expanded;
	}

	public BuildMasterPluginDescriptor getSharedDescriptor() {
		return (BuildMasterPluginDescriptor) Jenkins.getInstance().getDescriptorOrDie(BuildMasterPlugin.class);
	}

	/**
	 * Descriptor for {@link TriggerBuildBuildStep}. Used as a singleton. The
	 * class is marked as public so that it can be accessed from views.
	 *
	 * <p>
	 * See
	 * <tt>src/main/resources/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly</tt>
	 * for the actual HTML fragment for the configuration screen.
	 */
	@Extension
	// This indicates to Jenkins that this is an implementation of an extension
	// point.
	public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
		// These fields are required otherwise form data is not loaded correctly
		@SuppressWarnings("unused") private boolean waitTillBuildCompleted;
		@SuppressWarnings("unused") private boolean printLogOnFailure;
		@SuppressWarnings("unused") private String variables;
		@SuppressWarnings("unused") private String app;
		@SuppressWarnings("unused") private String applicationId;
		@SuppressWarnings("unused") private String releaseNumber;
		@SuppressWarnings("unused") private String buildNumber;
		
		/**
		 * In order to load the persisted global configuration, you have to call
		 * load() in the constructor.
		 */
		public DescriptorImpl() {
			load();
		}

		public boolean isApplicable(Class<? extends AbstractProject> aClass) {
			// Indicates that this builder can be used with all kinds of project types
			return true;
		}

		@Override
		public boolean configure(StaplerRequest req, JSONObject formData) throws FormException {
			req.bindJSON(this, formData);
			save();
			return true;
		}

		@Override
		public String getDisplayName() {
			return "Trigger BuildMaster Build";
		}
		
		// TODO jelly expandableTextbox does not support form validation currently so this does nothing: 
		// https://github.com/jenkinsci/jenkins/blob/master/core/src/main/resources/lib/form/expandableTextbox.jelly
		public FormValidation doCheckVariables(@QueryParameter String value) {
			try {
				getVariablesList(value);
			} catch (Exception e) {
                return FormValidation.error(e.getMessage());
            }
            
            return FormValidation.ok();
		}
		
		public String getDefaultBuildNumber() {
			return DEFAULT_BUILD_NUMBER;
		}
	}
}
