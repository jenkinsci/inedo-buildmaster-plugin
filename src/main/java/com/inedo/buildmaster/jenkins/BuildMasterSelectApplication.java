package com.inedo.buildmaster.jenkins;

public interface BuildMasterSelectApplication {
    String getApplicationId();

    public String getReleaseNumber();

    public String getPackageNumberSource();
}
