package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.api.BuildMasterConfig;

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

    public static boolean isRequiredFieldsConfigured() {
        if (config != null) {
            return true;
        }

        return getSharedDescriptor().isRequiredFieldsConfigured();
    }

    public static BuildMasterConfig getBuildMasterConfig() {
        if (config != null) {
            return config;
        }

        return getSharedDescriptor().getBuildMasterConfig();
    }

    private static BuildMasterConfiguration.DescriptorImpl getSharedDescriptor() {
        return (BuildMasterConfiguration.DescriptorImpl) Jenkins.getInstanceOrNull().getDescriptorOrDie(BuildMasterConfiguration.class);
    }

}
