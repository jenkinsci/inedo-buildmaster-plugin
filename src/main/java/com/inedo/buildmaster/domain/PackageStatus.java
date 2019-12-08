package com.inedo.buildmaster.domain;

public enum PackageStatus {

    ACTIVE("active"), DEPLOYED("deployed"), REJECTED("rejected");

    private final String text;

    PackageStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static PackageStatus fromString(String label) {
        for (PackageStatus b : PackageStatus.values()) {
            if (b.text.equalsIgnoreCase(label)) {
                return b;
            }
        }

        throw new IllegalArgumentException(String.format("There is no value with name '%s' in Enum %s", label, PackageStatus.class.getSimpleName()));
    }
}
