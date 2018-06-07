package com.inedo.buildmaster.domain;

import com.inedo.buildmaster.Optional;

public class Deployable
{
	public int Deployable_Id;
	public String Deployable_Name;
	public int Dependencies_Count;
	public int Dependants_Count;
	
    // TODO I use these to enable a deployable, but they don't seem to be provided by getDeployable API any more
    // I have marked these as optional to get the tests working, need to come back to this...
    @Optional
    public String InclusionType_Code;
    @Optional
    public String Referenced_Release_Number;
    @Optional
    public Integer Referenced_Application_Id;
}

