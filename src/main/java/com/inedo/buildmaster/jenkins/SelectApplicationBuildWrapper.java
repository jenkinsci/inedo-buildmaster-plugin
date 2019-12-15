package com.inedo.buildmaster.jenkins;

import java.io.IOException;

import com.inedo.buildmaster.jenkins.utils.*;
import org.jenkinsci.Symbol;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.DataBoundSetter;
import org.kohsuke.stapler.QueryParameter;

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

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_APPLICATION_ID=%s", application.applicationId);
        context.env("BUILDMASTER_APPLICATION_ID", String.valueOf(application.applicationId));

        helper.getLogWriter().info("Inject environment variable BUILDMASTER_RELEASE_NUMBER=%s", application.releaseNumber);
        context.env("BUILDMASTER_RELEASE_NUMBER", application.releaseNumber);

        if (application.buildNumber != null) {
            helper.getLogWriter().info(String.format("Inject environment variable BUILDMASTER_BUILD_NUMBER=%s", application.buildNumber));
            context.env("BUILDMASTER_BUILD_NUMBER", application.buildNumber);
        }
    }

    @Extension
    @Symbol("buildMasterWithApplicationRelease")
    public static final class DescriptorImpl extends BuildWrapperDescriptor {
        private BuildMasterSelector buildmaster = new BuildMasterSelector();

        @Override
        public String getDisplayName() {
            return "Inject environment variables with BuildMaster release details";
        }

        @Override
        public boolean isApplicable(AbstractProject<?, ?> item) {
            return true;
        }

        public BuildMasterSelector getBuildmaster() {
            return buildmaster;
        }

        public ListBoxModel doFillApplicationIdItems() throws IOException {
            return buildmaster.doFillApplicationIdItems(null);
        }

        public FormValidation doCheckApplicationId(@QueryParameter String value) {
            return buildmaster.doCheckApplicationId(value);
        }

        public ListBoxModel doFillReleaseNumberItems(@QueryParameter String applicationId) throws IOException {
            return buildmaster.doFillReleaseNumberItems(applicationId, null, true);
        }

        public FormValidation doCheckReleaseNumber(@QueryParameter String value, @QueryParameter String applicationId) {
            return buildmaster.doCheckReleaseNumber(value, applicationId);
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
