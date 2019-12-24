package com.inedo.buildmaster.domain;

/**
 * Package Object
 * 
 * @see <a href="https://docs.inedo.com/docs/buildmaster/reference/api/release-and-build#data-specification">Data Specification</a>
 */
public class ApiReleaseBuild {
    /** An integer representing the system-unique identifier of the build. */
    public Integer id;
    /** A string representing the release-unique identifier of the build. */
    public String number;
    /** A string consisting of active, deployed, or rejected. */
    public String status;
    /** A string representing the user who created the build, or SYSTEM if created non-interactively. */
    public String createdBy;
    /** A string representing the UTC date when the build was created, in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
    public String createdOn;

    /** A string representing the user who rejected the build, or SYSTEM if rejected non-interactively. */
    @Optional
    public String rejectedBy;

    /** A string representing the UTC date when the build was rejected, in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
    @Optional
    public String rejectedOn;

    /** An integer representing the system-unique identifier of the pipeline used by the build. */
    public Integer pipelineId;
    /** A string representing the name of the pipeline used by the build. */
    public String pipelineName;

    /** A string representing the name the furthest stage the build has advanced to in the pipeline. */
    @Optional
    public String furthestStage;
    /** An integer representing the system-unique identifier of the application for the build. */
    public Integer applicationId;
    /** A string representing the system-unique name of the application for the build. */
    public String applicationName;
    /** An integer representing the system-unique identifier of the release for the build. */
    public Integer releaseId;
    /** A string representing the application-unique number of the release for the build. */
    public String releaseNumber;
    /** A string representing the name of the release for the build. */
    public String releaseName;
}
