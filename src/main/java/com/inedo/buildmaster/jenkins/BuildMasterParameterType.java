package com.inedo.buildmaster.jenkins;

public enum BuildMasterParameterType {
    Application("APPLICATION"),
    Release("RELEASE"),
    Build("BUILD");

    private final String value;
    private BuildMasterParameterType(String value) {
        this.value = value;
    }

    public static BuildMasterParameterType fromValue(String value) {
        for (BuildMasterParameterType bmpt: BuildMasterParameterType.values()) {
            if (bmpt.value.equals(value)) return bmpt;
        }

        throw new IllegalArgumentException(value + " is not a valid option");
    }

    public String GetValue() {
        return this.value;
    }

    public String GetLabel() {
        return this.toString();
    }
}
