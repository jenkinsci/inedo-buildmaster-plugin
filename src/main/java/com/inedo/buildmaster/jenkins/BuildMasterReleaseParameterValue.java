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

/**
 * {@link ParameterValue} created from {@link BuildMasterReleaseParameterDefinition}.
 *
 * @author Peter Hayes
 * @since 1.0
 */
public class BuildMasterReleaseParameterValue extends ParameterValue {
    private final String applicationId;
    private final String releaseNumber;
    private final BuildMasterApi buildmaster = new BuildMasterApi(null);

    @DataBoundConstructor
    public BuildMasterReleaseParameterValue(String name, String applicationId, String releaseNumber, String description) {
        super(name, description);
        this.applicationId = applicationId;
        this.releaseNumber = releaseNumber;
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

        try {
            application = buildmaster.getApplication(buildmaster.getApplicationIdFrom(applicationId));
            buildNumber = buildmaster.getReleaseBuildNumber(application.Application_Id, releaseNumber);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        env.put("BUILDMASTER_APPLICATION_ID", String.valueOf(application.Application_Id));
        env.put("BUILDMASTER_APPLICATION_NAME", application.Application_Name);
        env.put("BUILDMASTER_RELEASE_NUMBER", releaseNumber);
        env.put("BUILDMASTER_LATEST_BUILD_NUMBER", buildNumber.latest);
        env.put("BUILDMASTER_NEXT_BUILD_NUMBER", buildNumber.next);
    }

    @Override
    public String toString() {
        return "(BuildMasterReleaseParameterValue) " + getName() + ": Application=" + getApplicationId() + ", ReleaseNumber=" + getReleaseNumber();
    }
}
