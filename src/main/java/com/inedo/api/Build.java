package com.inedo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Build
{
	public static final String EXAMPLE = "";

	public String Build_Number;
	public String Current_ExecutionStatus_Name;
	public String Current_Execution_Id;
}

