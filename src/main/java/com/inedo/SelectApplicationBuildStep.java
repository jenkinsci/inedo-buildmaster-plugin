package com.inedo;

import java.io.IOException;

import javax.servlet.ServletException;

import hudson.EnvVars;
import hudson.Launcher;
import hudson.Extension;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.NotImplementedException;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import org.kohsuke.stapler.StaplerRequest;

import com.inedo.BuildMasterPlugin.BuildMasterPluginDescriptor;

import jenkins.model.Jenkins;

/**
 * Sample {@link Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link DescriptorImpl#newInstance(StaplerRequest)} is invoked
 * and a new {@link SelectApplicationBuildStep} is created. The created
 * instance is persisted to the project configuration XML by using
 * XStream, so this allows you to use instance fields (like {@link #name})
 * to remember the configuration.
 *
 * <p>
 * When a build is performed, the {@link #perform(AbstractBuild, Launcher, BuildListener)}
 * method will be invoked. 
 *
 * @author Andrew Sumner
 * 
 * TODO: 
 * Release Number selection: If a specific release number is selected (for example an emergency patch might want to go on an earlier release than the latest one) this will 
 * become invalid once the release is finalised in BuildMaster.  There may be scope for an enhancement here to provide alternative forms of matching on release number - eg by
 * branch name.
 */
public class SelectApplicationBuildStep extends Builder {
	private static final String LATEST_RELEASE = "LATEST"; 
	private static final String LOG_PREFIX = "[BuildMaster] "; 
	
	private final String applicationId;
    private final String releaseNumber;
    private final String buildNumberSource;
    
    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public SelectApplicationBuildStep(String applicationId, String releaseNumber, String buildNumberSource) {
        this.applicationId = applicationId;
        this.releaseNumber = releaseNumber;
        this.buildNumberSource = buildNumberSource;
    }
    	 
    public String getApplicationId() {
        return applicationId;
    }
    
    public String getReleaseNumber() {
        return releaseNumber;
    }
    
    public String getBuildNumberSource() {
    	return buildNumberSource;
    }
    
    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {
    	if( !getSharedDescriptor().validatePluginConfiguration()) {
			listener.getLogger().println(LOG_PREFIX + "Please configure BuildMaster Plugin global settings");
			return false;
		}
    	
    	// Pouplate BUILDMASTER_APPLICATION variable
    	listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_APPLICATION=" + applicationId);
 		build.addAction(new VariableInjectionAction("BUILDMASTER_APPLICATION", applicationId));
        
 		// Populate BUILDMASTER_RELEASE_NUMBER variable
 		String actualReleaseNumber = releaseNumber;
 		
 		if ("LATEST".equals(releaseNumber)) {
			BuildMasterConfig config = getSharedDescriptor().getBuildMasterConfig(listener.getLogger());
			BuildMasterApi buildmaster = new BuildMasterApi(config);
			
			actualReleaseNumber = buildmaster.getLatestActiveReleaseNumber(applicationId);
			
			if (actualReleaseNumber == null || actualReleaseNumber.isEmpty()) {
				listener.getLogger().println(LOG_PREFIX + "Could not get release number from BuildMaster");
				return false;
			}
 		}
 		
        listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_RELEASE_NUMBER=" + actualReleaseNumber);
		build.addAction(new VariableInjectionAction("BUILDMASTER_RELEASE_NUMBER", actualReleaseNumber));
		
		//Populate BUILDMASTER_BUILD_NUMBER variable
		String actualBuildNumber;
		
		if ("BUILDMASTER".equals(buildNumberSource)) {
			//TODO Test Me!
			BuildMasterConfig config = getSharedDescriptor().getBuildMasterConfig(listener.getLogger());
			BuildMasterApi buildmaster = new BuildMasterApi(config);
			
			config.logCalls = true;
			JSONObject release = buildmaster.getRelease(applicationId, actualReleaseNumber);
			
			throw new NotImplementedException();
			
		} else {
			EnvVars envVars;
			try {
				envVars = build.getEnvironment(listener);
			} catch (Exception e) {
				listener.getLogger().println(e.getMessage());
				return false;
			}

			actualBuildNumber = envVars.get("BUILD_NUMBER");
		}
		
		listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_BUILD_NUMBER=" + actualBuildNumber);
		build.addAction(new VariableInjectionAction("BUILDMASTER_BUILD_NUMBER", actualBuildNumber));
		
        return true;
    }
    
    public static BuildMasterPluginDescriptor getSharedDescriptor() {
        return (BuildMasterPluginDescriptor)Jenkins.getInstance().getDescriptorOrDie(BuildMasterPlugin.class);
    }

    /**
     * Descriptor for {@link SelectApplicationBuildStep}. Used as a singleton.
     * The class is marked as public so that it can be accessed from views.
     *
     * <p>
     * See <tt>src/main/resources/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly</tt>
     * for the actual HTML fragment for the configuration screen.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
    	//These fields are required otherwise form data is not loaded correctly
    	@SuppressWarnings("unused") private String applicationId;
    	@SuppressWarnings("unused") private String releaseNumber;
    	@SuppressWarnings("unused") private String buildNumberSource;
    	
    	private BuildMasterApi buildmaster = null;
    	private Boolean isBuildMasterAvailable = null;
    	private String connectionError = "";
        
    	/**
    	 * Check if can connect to BuildMaster - if not prevent any more calls
    	 */
    	public boolean getIsBuildMasterAvailable() {
    		if (isBuildMasterAvailable == null) {
            	isBuildMasterAvailable = true;
                
                try {
                	buildmaster = new BuildMasterApi(getSharedDescriptor().getBuildMasterConfig());            
                	buildmaster.checkConnection();
                } catch (Exception ex) {
                	isBuildMasterAvailable = false;
                	connectionError = ex.getClass().getName() + ": " + ex.getMessage();
                	
                	System.err.println(connectionError);
                }    
    		}
    		
    		return isBuildMasterAvailable;
    	}
    	
    	public String getConnectionError() {
    		return connectionError;
    	}
    	
        /**
         * In order to load the persisted global configuration, you have to 
         * call load() in the constructor.
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
            return "Select BuildMaster Application";
        }
                
        public ListBoxModel doFillApplicationIdItems() {
        	ListBoxModel items = new ListBoxModel();
        	
        	if (!getIsBuildMasterAvailable()) {
        		items.add("", "");
        		
//                items.add("Pax Hold Release", "30");
//                items.add("Something else", "40");

        		return items;
        	}
        	JSONArray applications = buildmaster.getApplications();
            
    		for (int i = 0; i < applications.size(); i++) {
    			JSONObject json = applications.getJSONObject(i);

    			items.add((json.containsKey("ApplicationGroup_Name") ? json.getString("ApplicationGroup_Name") + " > " : "") + json.getString("Application_Name"), json.getString("Application_Id"));
    		}
        	
            return items;
        }
        
        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            if (value.length() == 0)
                return FormValidation.error("Please set a release");
            
            if (!getIsBuildMasterAvailable()) {
            	return FormValidation.ok();
            }
                        
            // Validate release is still active
            if (!LATEST_RELEASE.equals(value)) {
	        	try {
	        		JSONObject json = buildmaster.getRelease(applicationId, value);
	                                    
	            	JSONArray releases = ((JSONArray)json.get("Releases_Extended"));
	            	
	            	if (releases.size() == 0) {
	            		return FormValidation.error("The release " + value + " does not exist for this application");
	            	}
	            	
	        		String status = releases.getJSONObject(0).getString("ReleaseStatus_Name");
	            
		            if (!"Active".equalsIgnoreCase(status)) {
		            	return FormValidation.error("The release status must be Active, the actual status is " + status);
		            }            	
	            } catch (Exception ex) {
	            	return FormValidation.error(ex.getClass().getName() + ": " + ex.getMessage());
	            }    
            }
            
            return FormValidation.ok();
        }
        
        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) {
        	ListBoxModel items = new ListBoxModel();
        	
//        	String releaseNumber="";
//        	if(releaseNumber == null || LATEST_RELEASE.equals(releaseNumber)) {
//        		releaseNumber = "";
//        	}
        	
        	items.add("", "");
        	items.add("Latest Active Release", LATEST_RELEASE);
//        	items.add("0.1." + applicationId, "0.1." + applicationId);
        	
        	if (!getIsBuildMasterAvailable()) {
        		return items;
        	}
        	
        	JSONArray releases = buildmaster.getActiveReleases(applicationId);
        	boolean found = false;
        	
        	for (int i = 0; i < releases.size(); i++) {
    			JSONObject json = releases.getJSONObject(i);

    			items.add(json.getString("Release_Number"), json.getString("Release_Number"));
    			
//    			if (releaseNumber.equals(json.getString("Release_Number"))) {
//    				found = true;
//    			}
    		}
        	
//        	if (!found && !releaseNumber.isEmpty()) {
//        		items.add(releaseNumber, releaseNumber);
//        	}
        	
            return items;
        }
        
        public ListBoxModel doFillBuildNumberSourceItems() {
        	ListBoxModel items = new ListBoxModel();
        	
        	items.add("Jenkins", "JENKINS");
        	items.add("BuildMaster", "BUILDMASTER");
        	
            return items;
        }
    }
}

