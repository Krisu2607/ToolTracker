package com.example.tooltracker.model.tools;

public enum ToolStatus {
    W_UZYCIU("W użyciu"),
    ZUZYTE("Zużyte"),
    W_OSTRZENIU("W ostrzeniu");

    private final String displayName;

    ToolStatus(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return this.displayName;
    }
}
