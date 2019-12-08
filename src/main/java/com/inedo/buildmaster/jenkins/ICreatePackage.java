package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;

public interface ICreatePackage {
    public boolean isDeployToFirstStage();

    public boolean isPackageVariables();

    public DeployToFirstStage getDeployToFirstStage();

    public PackageVariables getPackageVariables();

    public String getApplicationId();

    public String getReleaseNumber();

    public String getPackageNumber();
}
