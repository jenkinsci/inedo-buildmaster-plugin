package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.BuildMasterSelectApplication;
import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.Deployable;
import com.inedo.buildmaster.domain.ReleaseStatus;
import com.inedo.buildmaster.jenkins.utils.JenkinsConsoleLogWriter;
import com.inedo.buildmaster.jenkins.utils.JenkinsHelper;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Resource;
import hudson.model.ResourceActivity;
import hudson.model.ResourceList;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildStepDescriptor;
import hudson.tasks.Builder;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import jenkins.tasks.SimpleBuildStep;

/**
 * The SelectApplicationBuildStep will populate environment variables for the BuildMaster application, release and build numbers 
 * as a build step.
 * 
 * This Action also locks any other Jenkins jobs from running FOR THE SAME BUILDMASTER APPLICATION until this job has completed.  
 *
 * See https://github.com/jenkinsci/pipeline-plugin/blob/master/DEVGUIDE.md#user-content-build-wrappers-1 for tips on 
 * Jenkins pipeline support 
 * 
 * @author Andrew Sumner
 *
 * TODO: Now that I am locking resources this possibly should be a JobConfiguration item and not a build step, although it
 * doesn't seem to matter for resource locking purposes.
 * 
 * TODO: Release Number selection: 
 * If a specific release number is selected (for example an emergency patch might want to go on an earlier release than the latest one) this will 
 * become invalid once the release is finalised in BuildMaster.  There may be scope for an enhancement here to provide alternative forms of matching on release number - eg by
 * branch name.
 */
 
public class SelectApplicationBuilder extends Builder implements SimpleBuildStep, ResourceActivity, BuildMasterSelectApplication {


    private String applicationId;
    private String releaseNumber = SelectApplicationHelper.LATEST_RELEASE;
    private String buildNumberSource = "BUILDMASTER";
    private String deployableId = SelectApplicationHelper.NOT_REQUIRED;

    // Fields in config.jelly must match the parameter names in the "DataBoundConstructor"
    @DataBoundConstructor
    public SelectApplicationBuilder() {
    }

    @DataBoundSetter 
    public final void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }
    
    @DataBoundSetter
    public final void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }
    
    @DataBoundSetter
    public final void setBuildNumberSource(String buildNumberSource) {
        this.buildNumberSource = buildNumberSource;
    }
    
    @DataBoundSetter
    public final void setDeployableId(String deployableId) {
        this.deployableId = deployableId;
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
    
    public String getDeployableId() {
        return deployableId;
    }
    
    @Override
    public void perform(Run<?, ?> run, FilePath workspace, Launcher launcher, TaskListener listener) throws InterruptedException, IOException {
        SelectApplicationHelper execute = new SelectApplicationHelper(run, listener);

        BuildMasterApplication application = execute.selectApplication(this);

        JenkinsHelper helper = execute.getJenkinsHelper();

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_APPLICATION_ID=" + application.applicationId);
        helper.injectEnvrionmentVariable("BUILDMASTER_APPLICATION_ID", String.valueOf(application.applicationId));

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_RELEASE_NUMBER=" + application.releaseNumber);
        helper.injectEnvrionmentVariable("BUILDMASTER_RELEASE_NUMBER", application.releaseNumber);

        if (application.buildNumber != null) {
            helper.getLogWriter()
                    .info(String.format("Inject environment variable BUILDMASTER_BUILD_NUMBER with %s build number = %s", application.buildNumberSource, application.buildNumber));
            helper.injectEnvrionmentVariable("BUILDMASTER_BUILD_NUMBER", application.buildNumber);
        }

        if (application.deployableId != null) {
            helper.getLogWriter().info("Inject environment variable BUILDMASTER_DEPLOYABLE_ID=" + application.deployableId);
            helper.injectEnvrionmentVariable("BUILDMASTER_DEPLOYABLE_ID", String.valueOf(application.deployableId));
        }
    }
  
    @Override
    public DescriptorImpl getDescriptor() {
        return (DescriptorImpl)super.getDescriptor();
    }
    
    @Symbol("buildMasterSelectApplication")
    @Extension // This indicates to Jenkins that this is an implementation of an extension point.
    public static final class DescriptorImpl extends BuildStepDescriptor<Builder> {
        private BuildMasterApi buildmaster = null;
        private Boolean isBuildMasterAvailable = null;
        private String connectionError = "";

        public DescriptorImpl() {
            super(SelectApplicationBuilder.class);
        }
        
        // @Override
        // public Builder newInstance(StaplerRequest req, JSONObject formData) throws FormException {
        // return req.bindJSON(SelectApplicationBuilder.class, formData);
        // }

        @SuppressWarnings("rawtypes")
        public boolean isApplicable(Class<? extends AbstractProject> jobType) {
            // Indicates that this builder can be used with all kinds of project types 
            return true;
        }
        
        @Override
        public String getDisplayName() {
            return "BuildMaster: Select Application";
        }

        /**
         * Check if can connect to BuildMaster - if not prevent any more calls
         */
        public boolean getIsBuildMasterAvailable() {
            if (isBuildMasterAvailable == null) {
                isBuildMasterAvailable = true;
                
                try {
                    buildmaster = new BuildMasterApi(new JenkinsConsoleLogWriter());
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
                items.add((application.ApplicationGroup_Name != null ? application.ApplicationGroup_Name + " > " : "") + application.Application_Name,
                        String.valueOf(application.Application_Id));
            }
            
            return items;
        }
        
        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            if (value.length() == 0)
                return FormValidation.error("Please set an application id");
            
            return FormValidation.ok();
        }
        
        
        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            if (value.length() == 0)
                return FormValidation.error("Please set a release");
            
            if (!getIsBuildMasterAvailable()) {
                return FormValidation.ok();
            }
                        
            // Validate release is still active
            if (!SelectApplicationHelper.LATEST_RELEASE.equals(value) && applicationId != null && !applicationId.isEmpty()) {
                try {
                    ApiRelease releaseDetails = buildmaster.getRelease(Integer.valueOf(applicationId), value);

                    if (releaseDetails == null) {
                        return FormValidation.error("The release " + value + " does not exist for this application");
                    }

                    String status = releaseDetails.status;

                    if (!ReleaseStatus.ACTIVE.getText().equalsIgnoreCase(status)) {
                        return FormValidation.error(String.format("The release status for release %s must be Active, the actual status is %s", value, status));
                    }
                } catch (Exception ex) {
                    return FormValidation.error(ex.getClass().getName() + ": " + ex.getMessage());
                }
            }
            
            return FormValidation.ok();
        }
        
        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            ListBoxModel items = new ListBoxModel();

            // items.add("", "");
            items.add("Latest Active Release", SelectApplicationHelper.LATEST_RELEASE);

            if (!getIsBuildMasterAvailable()) {
                return items;
            }

            if (applicationId != null && !applicationId.isEmpty()) {
                ApiRelease[] releases = buildmaster.getActiveReleases(Integer.valueOf(applicationId));

                for (ApiRelease release : releases) {
                    items.add(release.number);
                }
            }

            return items;
        }
        
        public ListBoxModel doFillDeployableIdItems(@QueryParameter String applicationId) throws IOException {
            ListBoxModel items = new ListBoxModel();

            // items.add("", "");
            items.add("Not Required", SelectApplicationHelper.NOT_REQUIRED);

            if (!getIsBuildMasterAvailable()) {
                return items;
            }

            if (applicationId != null && !applicationId.isEmpty()) {
                Deployable[] deployables = buildmaster.getApplicationDeployables(Integer.valueOf(applicationId));

                for (Deployable deployable : deployables) {
                    items.add(deployable.Deployable_Name, String.valueOf(deployable.Deployable_Id));
                }
            }

            return items;
        }
        
        public FormValidation doCheckDeployableId(@QueryParameter String value) {
            if (value.length() == 0)
                return FormValidation.error("Please set a deployable");
            
            if (!getIsBuildMasterAvailable()) {
                return FormValidation.ok();
            }
                        
            // Validate release is still active
            if (!SelectApplicationHelper.NOT_REQUIRED.equals(value)) {
                try {
                    Deployable deployable = buildmaster.getDeployable(Integer.valueOf(value));

                    if (deployable == null) {
                        return FormValidation.error("The deployable " + value + " does not exist for this application");
                    }
                } catch (Exception ex) {
                    return FormValidation.error(ex.getClass().getName() + ": " + ex.getMessage());
                }
            }
            
            return FormValidation.ok();
        }
        
        public ListBoxModel doFillBuildNumberSourceItems() {
            ListBoxModel items = new ListBoxModel();

            items.add("BuildMaster", "BUILDMASTER");
            items.add("Jenkins", "JENKINS");
            items.add("Not Required", "NOT_REQUIRED");

            return items;
        }
    }
    
 // ResourceActivity
    @Override
    public ResourceList getResourceList() {
        ResourceList list = new ResourceList();
        Resource r = new Resource("BuildMaster Application " + this.applicationId);
        list.w(r);

        return list;
    }

    // ResourceActivity
    @Override
    public String getDisplayName() {
        return "BuildMaster Application Resource";
    }
}

