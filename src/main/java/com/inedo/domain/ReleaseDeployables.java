package com.inedo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ReleaseDeployables
{
	public int Deployable_Id;
	public String Deployable_Name;
}

