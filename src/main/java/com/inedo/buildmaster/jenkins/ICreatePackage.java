package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;

public interface ICreatePackage {
    boolean isDeployToFirstStage();

    boolean isPackageVariables();

    DeployToFirstStage getDeployToFirstStage();

    PackageVariables getPackageVariables();

    String getApplicationId();

    String getReleaseNumber();

    String getPackageNumber();
}
