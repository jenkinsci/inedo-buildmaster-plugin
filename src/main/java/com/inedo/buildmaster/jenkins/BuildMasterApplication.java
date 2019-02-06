package com.inedo.buildmaster.jenkins;

public class BuildMasterApplication {
    public int applicationId;
    public String releaseNumber;
    public String packageNumber;
    public String packageNumberSource;
    public Integer deployableId;

    @Override
    public String toString() {
        return String.format("ApplicationId: %s, ReleaseNumber: %s, BuildNumber: %s, DeployableId: %s", applicationId, releaseNumber, packageNumber, deployableId);
    }
}