package com.inedo.buildmaster;

import java.io.IOException;

import jenkins.model.Jenkins;
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
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;
import com.inedo.buildmaster.BuildMasterPlugin.BuildMasterPluginDescriptor;
import com.inedo.buildmaster.api.BuildMasterClientApache;
import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Release;
import com.inedo.buildmaster.domain.ReleaseDetails;

/**
 * The SelectApplicationBuildStep will populate environment variables for the BuildMaster application, release and build numbers 
 * as a build step.
 *
 * @author Andrew Sumner
 * 
 * TODO: Release Number selection: 
 * If a specific release number is selected (for example an emergency patch might want to go on an earlier release than the latest one) this will 
 * become invalid once the release is finalised in BuildMaster.  There may be scope for an enhancement here to provide alternative forms of matching on release number - eg by
 * branch name.
 */
public class SelectApplicationBuilder extends Builder {
	private static final String LATEST_RELEASE = "LATEST"; 
	private static final String LOG_PREFIX = "[BuildMaster] "; 
	
	private final String applicationId;
    private final String releaseNumber;
    private final String buildNumberSource;
    
    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public SelectApplicationBuilder(String applicationId, String releaseNumber, String buildNumberSource) {
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
    
    public static BuildMasterPluginDescriptor getSharedDescriptor() {
		return (BuildMasterPluginDescriptor) Jenkins.getInstance().getDescriptorOrDie(BuildMasterPlugin.class);
	}
    
    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) throws IOException {
    	if( !getSharedDescriptor().validatePluginConfiguration()) {
			listener.getLogger().println(LOG_PREFIX + "Please configure BuildMaster Plugin global settings");
			return false;
		}
    	
    	BuildMasterConfig config = getSharedDescriptor().getBuildMasterConfig(listener.getLogger());
    	BuildMasterClientApache buildmaster = new BuildMasterClientApache(config);
		
    	// Pouplate BUILDMASTER_APPLICATION variable
    	listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_APPLICATION=" + applicationId);
 		build.addAction(new VariableInjectionAction("BUILDMASTER_APPLICATION", applicationId));
        
 		// Populate BUILDMASTER_RELEASE_NUMBER variable
 		String actualReleaseNumber = releaseNumber;
 		
 		if ("LATEST".equals(releaseNumber)) {
			actualReleaseNumber = buildmaster.getLatestActiveReleaseNumber(applicationId);
			
			if (actualReleaseNumber == null || actualReleaseNumber.isEmpty()) {
				listener.getLogger().println(LOG_PREFIX + "No active releases found in BuildMaster for applicationId " + applicationId);
				return false;
			}
 		}
 		
        listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_RELEASE_NUMBER=" + actualReleaseNumber);
		build.addAction(new VariableInjectionAction("BUILDMASTER_RELEASE_NUMBER", actualReleaseNumber));
		
		//Populate BUILDMASTER_BUILD_NUMBER variable
		String actualBuildNumber;
		
		switch (buildNumberSource) {
		case "BUILDMASTER":
			actualBuildNumber = buildmaster.getNextBuildNumber(applicationId, actualReleaseNumber);
			
			listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_BUILD_NUMBER with next BuildMaster build number=" + actualBuildNumber);
			build.addAction(new VariableInjectionAction("BUILDMASTER_BUILD_NUMBER", actualBuildNumber));
			
			break;
		
		case "JENKINS":
			EnvVars envVars;
			try {
				envVars = build.getEnvironment(listener);
			} catch (Exception e) {
				listener.getLogger().println(e.getMessage());
				return false;
			}

			actualBuildNumber = envVars.get("BUILD_NUMBER");
			
			listener.getLogger().println(LOG_PREFIX + "Inject environment variable BUILDMASTER_BUILD_NUMBER with Jenkins build number=" + actualBuildNumber);
			build.addAction(new VariableInjectionAction("BUILDMASTER_BUILD_NUMBER", actualBuildNumber));
			
			break;
			
		case "NOT_REQUIRED":
			// Do nothing
			break;
			
		default:
			listener.getLogger().println(LOG_PREFIX + "Unknown buildNumberSource " + buildNumberSource);
			return false;
		}
		
        return true;
    }
  
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }
    
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
    	private BuildMasterClientApache buildmaster = null;
    	private Boolean isBuildMasterAvailable = null;
    	private String connectionError = "";

        public DescriptorImpl() {
        	super(SelectApplicationBuilder.class);
        }
        
//		@Override
//		public Builder newInstance(StaplerRequest req, JSONObject formData) throws FormException {
//			return req.bindJSON(SelectApplicationBuilder.class, formData);
//		}

        @SuppressWarnings("rawtypes")
		public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }
        
        @Override
        public String getDisplayName() {
            return "Select BuildMaster Application";
        }

		/**
    	 * Check if can connect to BuildMaster - if not prevent any more calls
    	 */
    	public boolean getIsBuildMasterAvailable() {
    		if (isBuildMasterAvailable == null) {
            	isBuildMasterAvailable = true;
                
                try {
                	buildmaster = new BuildMasterClientApache(getSharedDescriptor().getBuildMasterConfig());            
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
    	
        public ListBoxModel doFillApplicationIdItems() throws IOException {
        	ListBoxModel items = new ListBoxModel();
        	
        	if (!getIsBuildMasterAvailable()) {
        		items.add("", "");
        		
        		return items;
        	}
        	
        	Application[] applications = buildmaster.getApplications();
            
        	for (Application application : applications) {
        		items.add((application.ApplicationGroup_Name != null ? application.ApplicationGroup_Name + " > " : "") + application.Application_Name, String.valueOf(application.Application_Id));
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
            if (!LATEST_RELEASE.equals(value) && applicationId != null && !applicationId.isEmpty()) {
	        	try {
	        		ReleaseDetails releaseDetails = buildmaster.getRelease(applicationId, value);
	                                    
	        		if (releaseDetails.Releases_Extended.length == 0) {
	            		return FormValidation.error("The release " + value + " does not exist for this application");
	            	}
	            	
	        		String status = releaseDetails.Releases_Extended[0].ReleaseStatus_Name;
	            
		            if (!"Active".equalsIgnoreCase(status)) {
		            	return FormValidation.error("The release status must be Active, the actual status is " + status);
		            }            	
	            } catch (Exception ex) {
	            	return FormValidation.error(ex.getClass().getName() + ": " + ex.getMessage());
	            }    
            }
            
            return FormValidation.ok();
        }
        
        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
        	ListBoxModel items = new ListBoxModel();
        	
        	items.add("", "");
        	items.add("Latest Active Release", LATEST_RELEASE);
        	
        	if (!getIsBuildMasterAvailable()) {
        		return items;
        	}
        	
        	if(applicationId != null && !applicationId.isEmpty()) {
	        	Release[] releases = buildmaster.getActiveReleases(applicationId);
	        	
	        	for (Release release : releases) {
	        		items.add(release.Release_Number, release.Release_Number);
				}
        	}
        	
            return items;
        }
        
        public ListBoxModel doFillBuildNumberSourceItems() {
        	ListBoxModel items = new ListBoxModel();
        	
        	items.add("BuildMaster", "BUILDMASTER");
        	items.add("Jenkins", "JENKINS");
        	items.add("Not Required", "NOT_REQUIRED");
        	
            return items;
        }
    }
}

