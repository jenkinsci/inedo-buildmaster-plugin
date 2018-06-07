package com.inedo.buildmaster.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ReleaseDetails
{
	public Release[] Releases_Extended;
	public Deployable[] ReleaseDeployables_Extended;
	public String[] ReleaseConfigurationFiles;
}

