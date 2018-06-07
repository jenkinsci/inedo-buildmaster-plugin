package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Variable
{
	public String Variable_Name;
	public String Value_Text;
	public String Scope_Code;
	public String Sensitive_Indicator;
}

