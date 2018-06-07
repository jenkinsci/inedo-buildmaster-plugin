package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Deployable
{
	public int Deployable_Id;
	public String Deployable_Name;
	public int Dependencies_Count;
	public int Dependants_Count;
	
	// Obsolete?
	public String InclusionType_Code;
	public String Referenced_Release_Number;
	public Integer Referenced_Application_Id;
}

