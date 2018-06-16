package com.inedo.buildmaster.domain;

/**
 * Package Object
 * 
 * @see <a href="https://inedo.com/support/documentation/buildmaster/reference/api/release-and-package#data-specification">Data Specification</a>
 */
public class ApiReleasePackage {
    /** An integer representing the system-unique identifier of the package. */
    public Integer id;
    /** A string representing the release-unique identifier of the package. */
    public String number;
    /** A string consisting of active, deployed, or rejected. */
    public String status;
    /** A string representing the user who created the package, or SYSTEM if created non-interactively. */
    public String createdBy;
    /** A string representing the UTC date when the package was created, in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
    public String createdOn;

    /** A string representing the user who rejected the package, or SYSTEM if rejected non-interactively. */
    @Optional
    public String rejectedBy;

    /** A string representing the UTC date when the package was rejected, in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
    @Optional
    public String rejectedOn;

    /** An integer representing the system-unique identifier of the pipeline used by the package. */
    public Integer pipelineId;
    /** A string representing the name of the pipeline used by the package. */
    public String pipelineName;

    /** A string representing the name the furthest stage the package has advanced to in the pipeline. */
    @Optional
    public String furthestStage;
    /** An integer representing the system-unique identifier of the application for the package. */
    public Integer applicationId;
    /** A string representing the system-unique name of the application for the package. */
    public String applicationName;
    /** An integer representing the system-unique identifier of the release for the package. */
    public Integer releaseId;
    /** A string representing the application-unique number of the release for the package. */
    public String releaseNumber;
    /** A string representing the name of the release for the package. */
    public String releaseName;
}
