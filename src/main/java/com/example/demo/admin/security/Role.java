package com.example.demo.admin.security;

public enum Role {

    ADMIN("ROLE_ADMIN"),
    BUYER("ROLE_BUYER"),
    SELLER("ROLE_SELLER");

    Role(String key) {
        this.key = key;
    }

    private final String key;

    public String getKey() {
        return key;
    }
}
