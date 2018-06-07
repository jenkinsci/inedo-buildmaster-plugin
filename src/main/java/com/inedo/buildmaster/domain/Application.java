package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Application
{
	public int Application_Id;
	public String Application_Name;
	public int Active_Releases_Count;
	
	// Obsolete?
	public Integer ApplicationGroup_Id;
	public String ApplicationGroup_Name;
}