package com.example.demo.admin.security;

public enum Role {

    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    Role(String key) {
        this.key = key;
    }

    private final String key;

    public String getKey() {
        return key;
    }
}
