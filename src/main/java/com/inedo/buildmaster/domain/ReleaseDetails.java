package com.inedo.buildmaster.domain;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ReleaseDetails
{
	public static String getExampleSingle() throws IOException {
		return IOUtils.toString(Application.class.getResourceAsStream("Release.json")).replace(IOUtils.LINE_SEPARATOR_WINDOWS, IOUtils.LINE_SEPARATOR_UNIX);
	}
	
	public Release[] Releases_Extended;
	public Deployable[] ReleaseDeployables_Extended;
	public String[] ReleaseConfigurationFiles;
}

