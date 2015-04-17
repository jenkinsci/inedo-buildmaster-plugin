package com.inedo.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Release
{
	public static final String EXAMPLE = "";

	public ReleasesExtended[] Releases_Extended;
	
	public class ReleasesExtended
	{
		public String Release_Number;
		public String ReleaseStatus_Name;
	}
}

