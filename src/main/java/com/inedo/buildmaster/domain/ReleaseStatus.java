package com.inedo.buildmaster.domain;

public enum ReleaseStatus {

    ACTIVE("active"), DEPLOYED("deployed"), CANCELED("canceled");

    private String text;

    private ReleaseStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static ReleaseStatus fromString(String label) {
        for (ReleaseStatus b : ReleaseStatus.values()) {
            if (b.text.equalsIgnoreCase(label)) {
                return b;
            }
        }

        throw new IllegalArgumentException(String.format("There is no value with name '%s' in Enum %s", label, ReleaseStatus.class.getSimpleName()));
    }
}
