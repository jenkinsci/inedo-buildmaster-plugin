package com.inedo.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import hidden.jth.org.apache.http.entity.InputStreamEntity;

public enum MockData {
    API_DEPLOYMENT("ApiDeployment.json"),
    API_DEPLOYMENT_WITH_ENV("ApiDeploymentWithEnvironment.json"),
    API_RELEASE_BUILD("ApiReleaseBuild.json"), 
    API_RELEASE_BUILDS("ApiReleaseBuilds.json"),
    API_RELEASE("ApiRelease.json"),
    BUILD_VARIABLES("BuildVariables.json"), 
    APPLICATION("Application.json"),
    APPLICATIONS("Applications.json"),
    RELEASE("Release.json");

    private static final String RESOURCE_PACKAGE = "/com/inedo/buildmaster/mockdata/";
    private final String resourceName;

    MockData(String name) {
        this.resourceName = name;
    }

    public InputStreamEntity getInputSteam() {
        return new InputStreamEntity(this.getClass().getResourceAsStream(RESOURCE_PACKAGE + resourceName));
    }

    public String getAsString() {
        try {
            return IOUtils.toString(this.getClass().getResourceAsStream(RESOURCE_PACKAGE + resourceName));
        } catch (IOException e) {
            throw new IllegalStateException("Unable to load resource " + resourceName, e);
        }
    }
}
