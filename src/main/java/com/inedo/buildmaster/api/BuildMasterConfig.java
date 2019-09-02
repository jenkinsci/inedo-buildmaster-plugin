package com.inedo.buildmaster.api;

import java.io.Serializable;

import hudson.util.Secret;

/**
 * Configuration settings required to can the BuildMaster json api
 * 
 * @author Andrew Sumner
 */
public class BuildMasterConfig implements Serializable {
    private static final long serialVersionUID = 1L;

    // Global Configuration Items
    public String url;
    public String apiKey;
    public String user;
    public Secret password;
    public boolean logApiRequests;
    public boolean trustAllCertificates;

}
