package com.inedo.jenkins;

import com.inedo.buildmaster.api.BuildMasterConfig;
import com.inedo.buildmaster.jenkins.BuildMasterConfiguration;

import jenkins.model.Jenkins;

public class GlobalConfig {
    private static BuildMasterConfig config = null;

    private GlobalConfig() {
    }

    /**
     * Inject configuration for testing purposes
     */
    public static void injectConfiguration(BuildMasterConfig value) {
        config = value;
    }

    public static boolean validateBuildMasterConfig() {
        if (config != null) {
            return true;
        }

        return getSharedDescriptor().validatePluginConfiguration();
    }

    public static BuildMasterConfig getBuildMasterConfig() {
        if (config != null) {
            return config;
        }

        return getSharedDescriptor().getBuildMasterConfig();
    }

    private static BuildMasterConfiguration.DescriptorImpl getSharedDescriptor() {
        return (BuildMasterConfiguration.DescriptorImpl) Jenkins.getInstance().getDescriptorOrDie(BuildMasterConfiguration.class);
    }

}
