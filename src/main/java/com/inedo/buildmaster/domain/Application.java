package com.inedo.buildmaster.domain;

public class Application
{
	public int Application_Id;
	public String Application_Name;
	public int Active_Releases_Count;
	
    // Not really needed, but are showing this in the select application dropdown
    public Integer ApplicationGroup_Id;
    public String ApplicationGroup_Name;
}