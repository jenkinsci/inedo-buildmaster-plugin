package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.api.BuildMasterApi.BuildNumber;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.jenkins.utils.*;
import hudson.*;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

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

public class SelectApplicationBuildWrapper extends SimpleBuildWrapper implements ResourceActivity
{
    private final String applicationId;
    private String releaseNumber = ConfigHelper.LATEST_RELEASE;

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
        JenkinsHelper helper = new JenkinsHelper(build, listener);
        BuildMasterApi buildmaster = new BuildMasterApi(helper.getLogWriter());

        // Application Id
        Application application = buildmaster.getApplication(buildmaster.getApplicationIdFrom(applicationId));

        if (application == null) {
            throw new AbortException(String.format("Application not found for identifier '%s'", applicationId));
        }

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_APPLICATION_ID={0}", application.Application_Id);
        context.env("BUILDMASTER_APPLICATION_ID", String.valueOf(application.Application_Id));

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_APPLICATION_NAME={0}", application.Application_Name);
        context.env("BUILDMASTER_APPLICATION_NAME", application.Application_Name);

        // Release Number
        String actualReleaseNumber = releaseNumber;

        if (ConfigHelper.LATEST_RELEASE.equals(releaseNumber)) {
            actualReleaseNumber = buildmaster.getLatestActiveReleaseNumber(application.Application_Id);

            if (actualReleaseNumber == null || actualReleaseNumber.isEmpty()) {
                throw new AbortException("No active releases found in BuildMaster for applicationId " + application.Application_Id);
            }
        }

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_RELEASE_NUMBER={0}", actualReleaseNumber);
        context.env("BUILDMASTER_RELEASE_NUMBER", actualReleaseNumber);

        // Build Number
        BuildNumber buildNumber = buildmaster.getReleaseBuildNumber(application.Application_Id, actualReleaseNumber);

        helper.getLogWriter().info(String.format("Inject environment variable BUILDMASTER_LATEST_BUILD_NUMBER=%s", buildNumber.latest));
        context.env("BUILDMASTER_LATEST_BUILD_NUMBER", buildNumber.latest);

        helper.getLogWriter().info(String.format("Inject environment variable BUILDMASTER_NEXT_BUILD_NUMBER=%s", buildNumber.next));
        context.env("BUILDMASTER_NEXT_BUILD_NUMBER", buildNumber.next);
    }

    @Extension
    @Symbol("buildMasterWithApplicationRelease")
    public static final class DescriptorImpl extends BuildWrapperDescriptor {
        private ConfigHelper configHelper = new ConfigHelper();

        @Override
        public String getDisplayName() {
            return "Inject BuildMaster release details as environment variables";
        }

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

        public ConfigHelper getConfigHelper() {
            return configHelper;
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return configHelper.doFillApplicationIdItems(null);
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return configHelper.doCheckApplicationId(value);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return configHelper.doFillReleaseNumberItems(applicationId, null, true);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return configHelper.doCheckReleaseNumber(value, applicationId);
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
