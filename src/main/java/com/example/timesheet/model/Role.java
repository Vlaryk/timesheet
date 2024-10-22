package com.example.timesheet.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

public enum Role {
    ADMIN("admin"),USER("user");
    private final String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
