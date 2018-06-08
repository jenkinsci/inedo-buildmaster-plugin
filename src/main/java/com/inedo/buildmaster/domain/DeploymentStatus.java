package com.inedo.buildmaster.domain;

public enum DeploymentStatus {

    PENDING("pending"), EXECUTING("executing"), SUCCEEDED("succeeded"), WARNED("warned"), FAILED("failed");

    private String text;

    private DeploymentStatus(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static DeploymentStatus fromString(String label) {
        for (DeploymentStatus b : DeploymentStatus.values()) {
            if (b.text.equalsIgnoreCase(label)) {
                return b;
            }
        }

        throw new IllegalArgumentException(String.format("There is no value with name '%s' in Enum %s", label, DeploymentStatus.class.getSimpleName()));
    }
}
