package com.learn.enums;

public enum Status {

    OPEN("Open"), IN_PROGRESS("In Progress"), COMPLETED("Completed");
    private final String value;

    public String getValue() {
        return value;
    }

    Status(String value) {
        this.value = value;
    }
}
