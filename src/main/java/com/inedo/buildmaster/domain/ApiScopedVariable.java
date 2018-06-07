package com.inedo.buildmaster.domain;

import com.google.gson.annotations.SerializedName;

public class ApiScopedVariable {
    /** A string value of the variable name */
    public String name;
    /** A string value of the variable value */
    public String value;
    /** A boolean to indicate a sensitive variable that's obscured from casual viewing in the UI */
    public Boolean sensitive;
    /** A string value of the name of the server the variable is associated with, or null if it's not associated */
    public String server;
    /** A string value of the name of the role the variable is associated with, or null if it's not associated */
    public String role;
    /** A string value of the name of the environment the variable is associated with, or null if it's not associated */
    public String environment;
    /** A string value of the name of the application the variable is associated with, or null if it's not associated */
    public String application;
    /** A string value of the name of the application-group the variable is associated with, or null if it's not associated */
    @SerializedName("application-group")
    public String applicationGroup;
}
