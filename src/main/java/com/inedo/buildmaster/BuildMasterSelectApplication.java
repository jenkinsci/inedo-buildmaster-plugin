package com.inedo.buildmaster;

public interface BuildMasterSelectApplication {
    public String getApplicationId();

    public String getReleaseNumber();

    public String getPackageNumberSource();

    public String getDeployableId();
}
