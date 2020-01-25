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

public class BuildMasterBuildParameterValue extends ParameterValue {
    private final String buildNumber;
    private final BuildMasterApi buildmaster = new BuildMasterApi(null);

    @DataBoundConstructor
    public BuildMasterBuildParameterValue(String name, String buildNumber, String description) {
        super(name, description);
        this.buildNumber = buildNumber;
    }

    @Override
    public void buildEnvironment(Run<?, ?> build, EnvVars env) {
        env.put("BUILDMASTER_BUILD_NUMBER", buildNumber);
    }

    @Override
    public String toString() {
        return "(BuildMasterBuildParameterValue) " + name + ": BuildNumber=" + buildNumber;
    }

    @Override
    public String getShortDescription() {
        return name + '=' + buildNumber;
    }
}
