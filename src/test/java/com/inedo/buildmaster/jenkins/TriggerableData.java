package com.inedo.buildmaster.jenkins;

import com.inedo.buildmaster.jenkins.buildOption.DeployToFirstStage;
import com.inedo.buildmaster.jenkins.buildOption.PackageVariables;

/**
 * Data to inject into TriggerBuildHelper for testing
 * 
 * @author Andrew Sumner
 */
public class TriggerableData implements ICreatePackage {
    public DeployToFirstStage deployToFirstStage;
	public PackageVariables setBuildVariables = null;
	public String applicationId;
	public String releaseNumber;
    public String packageNumber;

    public TriggerableData(String applicationId, String releaseNumber, String packageNumber, DeployToFirstStage deployToFirstStage) {
		this.applicationId = applicationId;
		this.releaseNumber = releaseNumber;
		this.packageNumber = packageNumber;
        this.deployToFirstStage = deployToFirstStage;
	}
	
	// Getters
    public boolean isDeployToFirstStage() {
        return deployToFirstStage != null;
	}
	
    public DeployToFirstStage getDeployToFirstStage() {
        return deployToFirstStage;
	}

	public boolean isPackageVariables() {
		return setBuildVariables != null;
	}

	public PackageVariables getPackageVariables() {
		return setBuildVariables;
	}
	
	public String getApplicationId() {
		return applicationId;
	}

	public String getReleaseNumber() {
		return releaseNumber;
	}
	
	public String getPackageNumber() {
		return packageNumber;
	}

	// Setters
    public TriggerableData setDeployToFirstStage(DeployToFirstStage value) {
        deployToFirstStage = value;
		return this;
	}

	public TriggerableData setSetBuildVariables(PackageVariables value) {
		setBuildVariables = value;
		return this;
	}

	public TriggerableData setApplicationId(String value) {
		applicationId = value;
		return this;
	}

	public TriggerableData setReleaseNumber(String value) {
		releaseNumber = value;
		return this;
	}
	
	public TriggerableData setPackageNumber(String value) {
		packageNumber = value;
		return this;
	}

}