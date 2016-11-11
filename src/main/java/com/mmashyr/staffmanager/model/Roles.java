package com.mmashyr.staffmanager.model;

/**
 * Created by Mark on 11.11.2016.
 */
public enum Roles {
    USER("user"),
    ADMIN("admin");

    String role;

        Roles(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
