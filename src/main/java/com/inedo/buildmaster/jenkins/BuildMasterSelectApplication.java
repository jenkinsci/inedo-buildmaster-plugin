package com.inedo.buildmaster.jenkins;

public interface BuildMasterSelectApplication {
    String getApplicationId();

    String getReleaseNumber();

    String getPackageNumberSource();
}
