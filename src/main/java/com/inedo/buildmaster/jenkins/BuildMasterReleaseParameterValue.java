package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.api.BuildMasterApi.BuildNumber;
import com.inedo.buildmaster.domain.Application;
import hudson.EnvVars;
import hudson.model.ParameterValue;
import hudson.model.Run;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

import java.io.IOException;

public class BuildMasterReleaseParameterValue extends ParameterValue {
    private final String applicationId;
    private final String releaseNumber;
    private final boolean showApplicationId;
    private final BuildMasterApi buildmaster = new BuildMasterApi(null);

    @DataBoundConstructor
    public BuildMasterReleaseParameterValue(String name, String applicationId, String releaseNumber, boolean showApplicationId, String description) {
        super(name, description);
        this.applicationId = applicationId;
        this.releaseNumber = releaseNumber;
        this.showApplicationId = showApplicationId;
    }

    @Exported
    public String getApplicationId() {
        return applicationId;
    }

    @Exported
    public String getReleaseNumber() {
        return releaseNumber;
    }

    @Override
    public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        Application application;
        BuildNumber buildNumber;
        String actualApplicationId = applicationId;
        String actualReleaseNumber = releaseNumber;

        if (showApplicationId && actualReleaseNumber.contains("|")) {
            String[] parts = actualReleaseNumber.split("\\|");
            actualApplicationId = parts[0];
            actualReleaseNumber = parts[1];
        }

        try {
            application = buildmaster.getApplication(actualApplicationId);

            if (application == null) {
                throw new IllegalArgumentException("Application " + actualApplicationId + " was not found.");
            }

            actualReleaseNumber = buildmaster.getReleaseNumber(application, actualReleaseNumber);
            buildNumber = buildmaster.getReleaseBuildNumber(application.Application_Id, actualReleaseNumber);

            if (buildNumber == null) {
                throw new IllegalArgumentException("No active release found in BuildMaster for " + application.Application_Name + ", release " + actualReleaseNumber);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        env.put("BUILDMASTER_APPLICATION_ID", String.valueOf(application.Application_Id));
        env.put("BUILDMASTER_APPLICATION_NAME", application.Application_Name);
        env.put("BUILDMASTER_RELEASE_NUMBER", actualReleaseNumber);
        env.put("BUILDMASTER_LATEST_BUILD_NUMBER", buildNumber.latest);
        env.put("BUILDMASTER_NEXT_BUILD_NUMBER", buildNumber.next);
    }

    @Override
    public String toString() {
        return "(BuildMasterReleaseParameterValue) " + getName() + ": Application=" + getApplicationId() + ", ReleaseNumber=" + getReleaseNumber();
    }
}
