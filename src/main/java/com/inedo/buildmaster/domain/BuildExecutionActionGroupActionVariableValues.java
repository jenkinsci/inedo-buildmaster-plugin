package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BuildExecutionActionGroupActionVariableValues
{
	public int BuildExecution_ActionGroupAction_Id;
	public String Variable_Name;
	public String Value_Text;
}

