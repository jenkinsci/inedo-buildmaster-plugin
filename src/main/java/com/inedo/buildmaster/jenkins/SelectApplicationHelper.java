package com.inedo.buildmaster.jenkins;

import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.domain.Application;
import com.inedo.buildmaster.jenkins.utils.JenkinsHelper;

import hudson.AbortException;
import hudson.model.Run;
import hudson.model.TaskListener;

public class SelectApplicationHelper {
    public static final String LATEST_RELEASE = "LATEST";
    public static final String NOT_REQUIRED = "NOT_REQUIRED";

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

        // Populate BUILDMASTER_PACKAGE_NUMBER variable
        switch (source.getPackageNumberSource()) {
        case "BUILDMASTER":
            application.packageNumber = buildmaster.getReleaseNextBuildNumber(application.applicationId, application.releaseNumber);
            application.packageNumberSource = "BuildMaster";

            break;

        case "JENKINS":
            application.packageNumber = helper.getEnvironmentVariable("BUILD_NUMBER");

            break;

        case "NOT_REQUIRED":
            application.packageNumber = null;

            break;

        default:
            if (source.getPackageNumberSource() == null || source.getPackageNumberSource().isEmpty()) {
                application.packageNumber = null;
            } else {
                throw new AbortException("Unknown packageNumberSource " + source.getPackageNumberSource());
            }
        }

        return application;
    }

    private int getApplicationId(String applicationId) throws AbortException {
        if (applicationId.matches("[0-9]{1,}")) {
            return Integer.parseInt(applicationId);
        }
        
        Optional<Application> application;
        try {
            application = Arrays.stream(buildmaster.getApplications()).filter(a -> a.Application_Name.equalsIgnoreCase(applicationId)).findFirst();

            if (application.isPresent()) {
                return application.get().Application_Id;
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            throw new AbortException(e.getMessage());
        }
        
        throw new AbortException("Application " + applicationId + "was not found");
    }

}
