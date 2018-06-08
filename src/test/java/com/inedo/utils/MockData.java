package com.inedo.utils;

import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.apache.http.entity.InputStreamEntity;

public enum MockData {
    API_DEPLOYMENT("ApiDeployment.json"),
    API_RELEASE_PACKAGE("ApiReleasePackage.json"),
    API_RELEASE_PACKAGES("ApiReleasePackages.json"),
    API_RELEASE("ApiRelease.json"),
    PACKAGE_VARIABLES("PackageVariables.json"), 
    APPLICATION("Application.json"),
    APPLICATIONS("Applications.json"),
    DEPLOYABLE("Deployable.json"),
    DEPLOYABLES("Deployables.json"),
    // remove
    RELEASE("Release.json"),
    RELEASES("Releases.json"), 
    VARIABLE("Variable.json");

    private static final String RESOURCE_PACKAGE = "/com/inedo/buildmaster/mockdata/";
    private String resourceName;

    private MockData(String name) {
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
