package com.inedo.buildmaster.jenkins;

import hudson.AbortException;
import hudson.EnvVars;
import hudson.Launcher;
import hudson.Util;
import hudson.model.*;
import hudson.tasks.BuildWrapper;
import java.io.IOException;
import java.util.Locale;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

import jenkins.model.Jenkins;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

/**
 * {@link ParameterValue} created from {@link BuildMasterReleaseParameterDefinition}.
 *
 * @author Peter Hayes
 * @since 1.0
 */
public class BuildMasterReleaseParameterValue extends ParameterValue {
    private String applicationId;
    private String releaseNumber;

    private static final Logger LOGGER = Logger.getLogger(BuildMasterReleaseParameterValue.class.getName());

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
        env.put("BUILDMASTER_APPLICATION_ID", getApplicationId());
        env.put("BUILDMASTER_RELEASE_NUMBER", getReleaseNumber());
    }

    @Override
    public String toString() {
        return "(BuildMasterReleaseParameterValue) " + getName() + ": ApplicationId='" + getApplicationId() + "', ReleaseNumber=" + getReleaseNumber();
    }
}
