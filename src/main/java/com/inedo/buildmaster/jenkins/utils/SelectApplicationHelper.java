package com.inedo.buildmaster.jenkins.utils;

import java.io.IOException;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.jenkins.utils.BuildMasterApplication;
import com.inedo.buildmaster.jenkins.utils.BuildMasterSelectApplication;
import com.inedo.buildmaster.jenkins.utils.JenkinsHelper;

import hudson.AbortException;
import hudson.model.Run;
import hudson.model.TaskListener;

public class SelectApplicationHelper {
    public static final String LATEST_RELEASE = "LATEST";

    private final JenkinsHelper helper;
    private final BuildMasterApi buildmaster;

    public SelectApplicationHelper(Run<?, ?> run, TaskListener listener) {
        helper = new JenkinsHelper(run, listener);
        buildmaster = new BuildMasterApi(helper.getLogWriter());
    }

    public JenkinsHelper getJenkinsHelper() {
        return helper;
    }

    public BuildMasterApplication selectApplication(BuildMasterSelectApplication source) throws IOException {

        BuildMasterApplication application = new BuildMasterApplication();

        application.applicationId = getApplicationId(source.getApplicationId());

        application.releaseNumber = source.getReleaseNumber();

        if (LATEST_RELEASE.equals(application.releaseNumber)) {
            application.releaseNumber = buildmaster.getLatestActiveReleaseNumber(application.applicationId);

            if (application.releaseNumber == null || application.releaseNumber.isEmpty()) {
                throw new AbortException("No active releases found in BuildMaster for applicationId " + application.applicationId);
            }
        }

        application.buildNumber = buildmaster.getReleaseNextBuildNumber(application.applicationId, application.releaseNumber);

        return application;
    }

    private int getApplicationId(String applicationId) throws AbortException {
        try {
            return buildmaster.getApplicationIdFrom(applicationId);
        } catch (IOException e) {
            throw new AbortException(e.getMessage());
        }
    }
}
