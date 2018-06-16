package com.inedo.buildmaster.domain;

public class Deployable
{
    public int Deployable_Id;
    public String Deployable_Name;
    public int Dependencies_Count;
    public int Dependants_Count;
    public Integer Application_Id;

    @Optional
    public String InclusionType_Code;
    @Optional
    public String Release_Number;
    @Optional
    public Integer Release_Id;
}

