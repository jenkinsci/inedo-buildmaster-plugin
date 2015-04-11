package com.inedo;

import hudson.EnvVars;
import hudson.Launcher;
import hudson.Extension;
import hudson.util.FormValidation;
import hudson.util.Secret;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.model.AbstractProject;
import hudson.tasks.BuildStepMonitor;
import hudson.tasks.Builder;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Publisher;
import hudson.tasks.Recorder;
import net.sf.json.JSONObject;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.StaplerRequest;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.BuildMasterPlugin.BuildMasterPluginDescriptor;

import javax.servlet.ServletException;

import java.io.IOException;

import jenkins.model.Jenkins;

/**
 * Sample {@link Builder}.
 *
 * <p>
 * When the user configures the project and enables this builder,
 * {@link DescriptorImpl#newInstance(StaplerRequest)} is invoked
 * and a new {@link TriggerBuildPostBuildStep} is created. The created
 * instance is persisted to the project configuration XML by using
 * XStream, so this allows you to use instance fields (like {@link #name})
 * to remember the configuration.
 *
 * <p>
 * When a build is performed, the {@link #perform(AbstractBuild, Launcher, BuildListener)}
 * method will be invoked. 
 *
 * @author Kohsuke Kawaguchi
 */
public class TriggerBuildPostBuildStep extends Recorder {

	private final boolean useJenkinsBuildNumber;
    private final boolean promoteBuild;
    private final boolean waitTillBuildCompleted;
    private final boolean printLogOnFailure;
    
    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public TriggerBuildPostBuildStep(boolean useJenkinsBuildNumber, boolean promoteBuild, boolean waitTillBuildCompleted, boolean printLogOnFailure) {
    	this.useJenkinsBuildNumber = useJenkinsBuildNumber;
    	this.promoteBuild = promoteBuild;
        this.waitTillBuildCompleted = waitTillBuildCompleted;
        this.printLogOnFailure = printLogOnFailure;
    }

    public boolean getUseJenkinsBuildNumber() {
        return useJenkinsBuildNumber;
    }

    public boolean getPromoteBuild() {
        return promoteBuild;
    }
    
    public boolean getWaitTillBuildCompleted() {
        return waitTillBuildCompleted;
    }
    
    public boolean getPrintLogOnFailure() {
        return printLogOnFailure;
    }
        
    @Override
    public boolean perform(AbstractBuild<?, ?> build, Launcher launcher, BuildListener listener) {
    	if( !getSharedDescriptor().validatePluginConfiguration()) {
			listener.getLogger().println("Please configure BuildMaster Plugin global settings");
			return false;
		}
    	
    	EnvVars envVars;
		try {
			envVars = build.getEnvironment(listener);
		} catch (Exception e) {
			listener.getLogger().println(e.getMessage());
			return false;
		}
    	
    	String applicationId = envVars.get("BUILDMASTER_APPLICATION");
    	String releaseNumber = envVars.get("BUILDMASTER_RELEASE");
    	
    	listener.getLogger().println("[BuildMaster Plugin] Triggering build for BUILDMASTER_APPLICATION: " + applicationId + ", BUILDMASTER_RELEASE: " + releaseNumber);
 		    	
    	return true;
    }
    
    public BuildMasterPluginDescriptor getSharedDescriptor() {
        return (BuildMasterPluginDescriptor)Jenkins.getInstance().getDescriptorOrDie(BuildMasterPlugin.class);
    }

    @Override
	public BuildStepMonitor getRequiredMonitorService() {
		return BuildStepMonitor.NONE;
	}
    
    /**
     * Descriptor for {@link TriggerBuildPostBuildStep}. Used as a singleton.
     * The class is marked as public so that it can be accessed from views.
     *
     * <p>
     * See <tt>src/main/resources/hudson/plugins/hello_world/HelloWorldBuilder/*.jelly</tt>
     * for the actual HTML fragment for the configuration screen.
     */
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Publisher> {
    	//These fields are required otherwise form data is not loaded correctly
    	@SuppressWarnings("unused") private boolean useJenkinsBuildNumber;
    	@SuppressWarnings("unused") private boolean promoteBuild;
    	@SuppressWarnings("unused") private boolean waitTillBuildCompleted;
    	@SuppressWarnings("unused") private boolean printLogOnFailure;
    	
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
            return "Trigger BuildMaster Build";
        }
    }
}

