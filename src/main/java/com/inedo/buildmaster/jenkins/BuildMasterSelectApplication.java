package com.inedo.buildmaster.jenkins;

public interface BuildMasterSelectApplication {
    public String getApplicationId();

    public String getReleaseNumber();

    public String getPackageNumberSource();

    public String getDeployableId();
}
