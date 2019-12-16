package com.inedo.buildmaster.jenkins;

import hudson.EnvVars;
import hudson.model.ParameterValue;
import hudson.model.Run;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

import java.util.logging.Logger;

/**
 * {@link ParameterValue} created from {@link BuildMasterReleaseParameterDefinition}.
 *
 * @author Peter Hayes
 * @since 1.0
 */
public class BuildMasterReleaseParameterValue extends ParameterValue {
    private final String applicationId;
    private final String applicationName;
    private final String releaseNumber;

    private static final Logger LOGGER = Logger.getLogger(BuildMasterReleaseParameterValue.class.getName());

    @DataBoundConstructor
    public BuildMasterReleaseParameterValue(String name, String applicationId, String applicationName, String releaseNumber, String description) {
        super(name, description);
        this.applicationId = applicationId;
        this.applicationName  = applicationName;
        this.releaseNumber = releaseNumber;
    }

    @Exported
    public String getApplicationId() {
        return applicationId;
    }

    @Exported
    public String getApplicationName() {
        return applicationName;
    }

    @Exported
    public String getReleaseNumber() {
        return releaseNumber;
    }

    @Override
    public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        env.put("BUILDMASTER_APPLICATION_ID", getApplicationId());
        env.put("BUILDMASTER_APPLICATION_NAME", getApplicationName());
        env.put("BUILDMASTER_RELEASE_NUMBER", getReleaseNumber());
    }

    @Override
    public String toString() {
        return "(BuildMasterReleaseParameterValue) " + getName() + ": Application=#" + getApplicationId() + " - '" + getApplicationName() + "', ReleaseNumber=" + getReleaseNumber();
    }
}
