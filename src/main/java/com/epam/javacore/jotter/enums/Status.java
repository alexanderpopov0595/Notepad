package com.epam.javacore.jotter.enums;

public enum Status {
    SUBSCRIBED("User is subscribed"),
    WAITING("User is waiting for subscribing");

    private String code;

    Status(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
