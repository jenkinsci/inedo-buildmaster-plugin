package com.inedo.buildmaster;

import hudson.model.AbstractBuild;
import hudson.model.BuildListener;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import jenkins.model.Jenkins;

import com.inedo.buildmaster.api.BuildMasterClientApache;
import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.domain.Variable;

/**
 * Does the real work of Trigger a BuildMaster build, has been seperated out from the Builder and Publisher actions
 * so that the code can be shared between them. 
 * 
 * @author Andrew Sumner
 */
public class TriggerBuildHelper {
	public static final String LOG_PREFIX = "[BuildMaster] "; 
	public static final String DEFAULT_BUILD_NUMBER = "${BUILDMASTER_BUILD_NUMBER}"; 
	private static BuildMasterConfig config = null;
	
	/**
	 * TODO: As I haven't been able to successfully mock a static class this is my work around for testing purposes
	 */
	public static void injectConfiguration(BuildMasterConfig value) {
		config = value;
	}
	
	public static boolean validateBuildMasterConfig() {
		if (config != null) {
			return true;
		}
		
		return getSharedDescriptor().validatePluginConfiguration();
	}

	public static BuildMasterConfig getBuildMasterConfig() {
		if (config != null) {
			return config;
		}
		
		return getSharedDescriptor().getBuildMasterConfig();
	}
	
	private static BuildMasterConfiguration.DescriptorImpl getSharedDescriptor() {
		return (BuildMasterConfiguration.DescriptorImpl) Jenkins.getInstance().getDescriptorOrDie(BuildMasterConfiguration.class);
	}
	
	public static boolean triggerBuild(AbstractBuild<?, ?> build, BuildListener listener, Triggerable trigger) throws IOException, InterruptedException {
		if (!validateBuildMasterConfig()) {
			listener.getLogger().println("Please configure BuildMaster Plugin global settings");
			return false;
		}
		
		BuildMasterConfig config = getBuildMasterConfig();
		config.printStream = listener.getLogger();
		BuildMasterClientApache buildmaster = new BuildMasterClientApache(config);		
		String applicationId = expandVariable(build, listener, trigger.getApplicationId());
		String releaseNumber = expandVariable(build, listener, trigger.getReleaseNumber());
		String buildNumber = expandVariable(build, listener, trigger.getBuildNumber());
		
		Map<String, String> variablesList = getVariablesList(trigger.getVariables());
		
		if (trigger.getPreserveVariables()) {
			listener.getLogger().println(LOG_PREFIX + "Gather previous builds build variables");
			
			String prevBuildNumber = buildmaster.getPreviousBuildNumber(applicationId, releaseNumber);			
			Variable[] variables = buildmaster.getVariableValues(applicationId, releaseNumber, prevBuildNumber);
			
			for (Variable variable : variables) {
				if (!variablesList.containsKey(variable.Variable_Name)) {
					variablesList.put(variable.Variable_Name, variable.Value_Text);
				}
			}
		}
		
		if (trigger.getEnableReleaseDeployable()) {
			String deployableId = expandVariable(build, listener, trigger.getDeployableId());
			listener.getLogger().println(LOG_PREFIX + "Enable release deployable with id=" + deployableId);
			
			buildmaster.enableReleaseDeployable(applicationId, releaseNumber, deployableId);			
		}
		
		String buildMasterBuildNumber;
		
		if (buildNumber != null && !buildNumber.isEmpty() && !DEFAULT_BUILD_NUMBER.equals(buildNumber)) {
			listener.getLogger().println(LOG_PREFIX + "Create BuildMaster build with BuildNumber=" + buildNumber);
			buildMasterBuildNumber = buildmaster.createBuild(applicationId, releaseNumber, buildNumber, variablesList);
			
			if (!buildMasterBuildNumber.equals(buildNumber)) {
				listener.getLogger().println(LOG_PREFIX + String.format("Warning, requested build number '%s' does not match that returned from BuildMaster '%s'.", buildNumber, buildMasterBuildNumber));
			}
		} else {
			listener.getLogger().println(LOG_PREFIX + "Create BuildMaster build");
			buildMasterBuildNumber = buildmaster.createBuild(applicationId, releaseNumber, variablesList);
			
			listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_BUILD_NUMBER=" + buildMasterBuildNumber);
			build.addAction(new VariableInjectionAction("BUILDMASTER_BUILD_NUMBER", buildMasterBuildNumber));
		}
		
		if (trigger.getWaitTillBuildCompleted()) {
			listener.getLogger().println(LOG_PREFIX + "Wait till build completed");
			return buildmaster.waitForBuildCompletion(applicationId, releaseNumber, buildMasterBuildNumber, trigger.getPrintLogOnFailure());
		}

		return true;
	}
	
	private static String expandVariable(AbstractBuild<?, ?> build, BuildListener listener, String variable) {
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

	public static Map<String, String> getVariablesList(String variables) throws IOException {
		Map<String, String> variablesList = new HashMap<>();
		
		if (variables != null) {
			String[] variablesArray = variables.split("\n");
			
			for (String value : variablesArray) {
				value = value.trim();
				if (value.isEmpty()) continue;
				if (value.startsWith("#")) continue;
				
				int pos = value.indexOf("=");
				
				if (pos < 0) throw new RuntimeException(LOG_PREFIX + value + " is not in the format 'variable=value'");
				
				variablesList.put(value.substring(0, pos).trim(), value.substring(pos + 1).trim());
			}
		}
		
		return variablesList;
	}
}
