package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.api.BuildMasterApi;
import com.inedo.buildmaster.api.BuildMasterApi.BuildNumber;
import com.inedo.buildmaster.domain.Application;
import hudson.EnvVars;
import hudson.model.ParameterValue;
import hudson.model.Run;
import hudson.model.StringParameterValue;
import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.export.Exported;

import java.io.IOException;

public class BuildMasterApplicationParameterValue extends ParameterValue {
    private final String applicationId;
    private final BuildMasterApi buildmaster = new BuildMasterApi(null);

    @DataBoundConstructor
    public BuildMasterApplicationParameterValue(String name, String applicationId, String description) {
        super(name, description);
        this.applicationId = applicationId;
    }

    @Override
    public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        try {
            Application application = buildmaster.getApplication(applicationId);

            if (application == null) {
                throw new IllegalArgumentException("Application " + applicationId + " was not found.");
            }

            env.put("BUILDMASTER_APPLICATION_ID", String.valueOf(application.Application_Id));
            env.put("BUILDMASTER_APPLICATION_NAME", application.Application_Name);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String toString() {
        return "(BuildMasterApplicationParameterValue) " + name + ": Application=" + applicationId;
    }

    @Override
    public String getShortDescription() {
        return name + '=' + applicationId;
    }
}
