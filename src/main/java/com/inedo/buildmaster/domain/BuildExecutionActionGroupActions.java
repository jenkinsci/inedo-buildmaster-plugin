package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecutionActionGroupActions
{
	public String ExecutionStatus_Name;
	public String ExecutionAction_Description;
}

