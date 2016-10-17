package com.inedo.buildmaster.domain;

public class ApiDeployment {
	/** An integer representing the system-unique identifier of the deployment.*/ 
	public Integer id;
	/** A string representing the name of the plan used for deployment.*/ 
	public String plan;
	/** A string consisting of pending, executing, succeeded, warned, or failed */
	public String status;
	/** A string representing the UTC date when the deployment actually started, in ISO 8601 format (yyyy-MM-ddThh:mm:ss), or null if the execution hasn't started.*/ 
	public String started;
	/** A string representing the UTC date when the deployment completed, in ISO 8601 format (yyyy-MM-ddThh:mm:ss), or null if the execution hasn't completed.*/ 
	public String ended;
	/** A string representing the user who initiated the deployment, or SYSTEM if initiated non-interactively. */
	public String createdBy;
	/** A string representing the UTC date when the deployment was initiated, in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
	public String createdOn;
	/** A string representing the user who canceled the deployment (SYSTEM if canceled non-interactively), or null if the deployment was not canceled. */
	public String canceledBy;
	/** A string representing the UTC date when the deployment was canceled, in ISO 8601 format (yyyy-MM-ddThh:mm:ss), or null if the deployment was not canceled.*/ 
	public String canceledOn;
	/** An integer representing the system-unique identifier of the pipeline used by the deployment.*/ 
	public Integer pipelineId;
	/** A string representing the name of the pipeline used by the deployment. */
	public String pipelineName;
	/** A string representing the name the stage in the pipeline used by the deployment. */
	public String pipelineStageName;
	/** An integer representing the system-unique identifier of the environment used by the deployment, or null if no environment is in context.*/ 
	public Integer environmentId;
	/** A string representing the name of the environment used by the deployment, or null if no environment is in context.*/ 
	public String environmentName;
	/** An integer representing the system-unique identifier of the application for the deployment.*/ 
	public Integer applicationId;
	/** A string representing the system-unique name of the application for the deployment. */
	public String applicationName;
	/** An integer representing the system-unique identifier of the release for the deployment. */
	public Integer releaseId;
	/** A string representing the application-unique number of the release for the deployment.*/ 
	public String releaseNumber;
	/** A string representing the name of the release for the deployment. */
	public String releaseName;
	/** An integer representing the system-unique identifier of the package for the deployment.*/ 
	public Integer packageId;
	/** A string representing the release-unique number of the package for the deployment.*/ 
	public String packageNumber;

}
