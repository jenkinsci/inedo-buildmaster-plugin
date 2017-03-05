package com.inedo.buildmaster.domain;

import java.io.IOException;

import org.apache.commons.io.IOUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class Release
{
	public static String getExampleArray() throws IOException {
		return IOUtils.toString(Application.class.getResourceAsStream("Releases.json")).replace(IOUtils.LINE_SEPARATOR_WINDOWS, IOUtils.LINE_SEPARATOR_UNIX);
	}

	public int Release_Id;
	public int Application_Id;
	public String Release_Number;
	public String ReleaseStatus_Name;
	public String Notes_Text; // Obsolete?

	public String CreatedOn_Date;
	public String ModifiedOn_Date;
	public String CancelledReason_Text;	// Obsolete?
	public String CancelledOn_Date;		// Obsolete?
	public String Target_Date;			// Obsolete?

	public String Release_Name;
	public int Release_Sequence;
	public int Pipeline_Id;
	public String Pipeline_Name;
	public String Application_Name;
	
	public String Furthest_Build_Number;
	public String Furthest_PipelineStage_Name;
	public int Furthest_Build_Id;
		
	public int Latest_Build_Id;
	public String Latest_Build_Number;
	public String Latest_Build_CreatedOn_Date;
	public int Latest_Promotion_Id;
	public String Latest_Promoted_Date;
	public String Latest_PromotionStatus_Name;
	public String Latest_Promotion_PipelineStage_Name;
		
	public String ReleaseTemplate_Name;		// Obsolete?
}

