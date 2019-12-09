package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.ApiRelease;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.domain.ReleaseStatus;
import com.inedo.buildmaster.jenkins.utils.JenkinsConsoleLogWriter;
import com.inedo.buildmaster.jenkins.utils.JenkinsHelper;

import hudson.EnvVars;
import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.AbstractProject;
import hudson.model.Resource;
import hudson.model.ResourceActivity;
import hudson.model.ResourceList;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.BuildWrapperDescriptor;
import hudson.util.FormValidation;
import hudson.util.ListBoxModel;
import jenkins.tasks.SimpleBuildWrapper;

public class SelectApplicationBuildWrapper extends SimpleBuildWrapper implements ResourceActivity, BuildMasterSelectApplication
{
    private final String applicationId;
    private String releaseNumber = SelectApplicationHelper.LATEST_RELEASE;

    @DataBoundConstructor
    public SelectApplicationBuildWrapper(String applicationId) {
        this.applicationId = applicationId;
    }

    @DataBoundSetter
    public final void setReleaseNumber(String releaseNumber) {
        this.releaseNumber = releaseNumber;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public String getReleaseNumber() {
        return releaseNumber;
    }

    @Override
    public void setUp(Context context, Run<?, ?> build, FilePath workspace, Launcher launcher, TaskListener listener, EnvVars initialEnvironment) throws IOException {
        SelectApplicationHelper execute = new SelectApplicationHelper(build, listener);

        BuildMasterApplication application = execute.selectApplication(this);

        JenkinsHelper helper = execute.getJenkinsHelper();

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_APPLICATION_ID=" + application.applicationId);
        context.env("BUILDMASTER_APPLICATION_ID", String.valueOf(application.applicationId));

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_RELEASE_NUMBER=" + application.releaseNumber);
        context.env("BUILDMASTER_RELEASE_NUMBER", application.releaseNumber);

        if (application.buildNumber != null) {
            helper.getLogWriter()
                    .info(String.format("Inject environment variable BUILDMASTER_BUILD_NUMBER=%s", application.buildNumber));
            context.env("BUILDMASTER_BUILD_NUMBER", application.buildNumber);
        }
    }

    @Extension
    @Symbol("buildMasterWithApplicationRelease")
    public static final class DescriptorImpl extends BuildWrapperDescriptor {
        private BuildMasterApi buildmaster = null;
        private Boolean isBuildMasterAvailable = null;
        private String connectionError = "";

        public DescriptorImpl() {
        }

        @Override
        public String getDisplayName() {
            return "Select BuildMaster Application";
        }

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

        /**
         * Check if can connect to BuildMaster - if not prevent any more calls
         */
        public boolean getIsBuildMasterAvailable() {
            if (isBuildMasterAvailable == null) {
                try {
                    buildmaster = new BuildMasterApi(new JenkinsConsoleLogWriter());
                    buildmaster.checkConnection();
                    isBuildMasterAvailable = true;
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

            items.add("", "");

            if (!getIsBuildMasterAvailable()) {
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
                    ApiRelease releaseDetails = buildmaster.getRelease(Integer.parseInt(applicationId), value);

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
                ApiRelease[] releases = buildmaster.getActiveReleases(Integer.parseInt(applicationId));

                for (ApiRelease release : releases) {
                    items.add(release.number);
                }
            }

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
