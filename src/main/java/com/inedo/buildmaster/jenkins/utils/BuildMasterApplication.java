package com.inedo.buildmaster.jenkins.utils;

public class BuildMasterApplication {
    public int applicationId;
    public String releaseNumber;
    public String buildNumber;

    @Override
    public String toString() {
        return String.format("ApplicationId: %s, ReleaseNumber: %s, BuildNumber: %s", applicationId, releaseNumber, buildNumber);
    }
}