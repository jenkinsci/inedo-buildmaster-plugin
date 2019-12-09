package com.inedo.buildmaster.jenkins;

public class BuildMasterApplication {
    public int applicationId;
    public String releaseNumber;
    public String packageNumber;

    @Override
    public String toString() {
        return String.format("ApplicationId: %s, ReleaseNumber: %s, BuildNumber: %s", applicationId, releaseNumber, packageNumber);
    }
}