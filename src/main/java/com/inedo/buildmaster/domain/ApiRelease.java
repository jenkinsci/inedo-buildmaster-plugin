package com.inedo.buildmaster.domain;

/**
 * Release Object
 * 
 * @see <a href="https://docs.inedo.com/docs/buildmaster/reference/api/release-and-build#data-specification">Data Specification</a>
 */
public class ApiRelease {
    /** An integer representing the system-unique identifier of the release. */
    public Integer id;
    /** A string representing the application-unique identifier of the release. */
    public String number;
    /** A string representing the non-unique name or alias for the release. */
    public String name;
    /** An integer representing the sequence relative to other releases in the application. */
    public Integer sequence;
    /** A string of the template name used for this release, or null if no template is used. */
    @Optional
    public String template;
    /** A string consisting of active, deployed, or canceled. */
    public String status;
    /** A string representing the user who created the release, or SYSTEM if created non-interactively. */
    public String createdBy;
    /** A string representing the UTC date when the release was created, in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
    public String createdOn;
    /** A string representing the user who canceled the release, or SYSTEM if created non-interactively. */
    @Optional
    public String canceledBy;
    /** A string representing the UTC date when the release was created, in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
    @Optional
    public String canceledOn;
    /** A string representing the UTC date of the target release date in ISO 8601 format (yyyy-MM-ddThh:mm:ss). */
    @Optional
    public String targetDate;
    /** An integer representing the system-unique identifier of the application for the release. */
    public Integer applicationId;
    /** A string representing the system-unique name of the application for the release. */
    public String applicationName;
    /** A string representing the name of the pipeline used by the release. */
    public String pipelineName;
    /** An integer representing the system-unique identifier of the active or deployed build that has made it furthest in the pipeline for the release. */
    public Integer furthestBuildId;
    /** A string representing the application-unique number of the active or deployed build that has made it furthest in the pipeline for the release. */
    public String furthestBuildNumber;
    /** An integer representing the system-unique identifier of the active or deployed build that was most recently created. */
    public Integer latestBuildId;
    /** A string representing the application-unique number of the active or deployed package that was most recently created. */
    public String latestBuildNumber;

}