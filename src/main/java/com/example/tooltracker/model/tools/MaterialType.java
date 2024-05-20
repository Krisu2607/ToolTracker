package com.example.tooltracker.model.tools;

public enum MaterialType {
    HSS("HSS"),
    VHM("VHM");

    private final String displayName;

    MaterialType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}