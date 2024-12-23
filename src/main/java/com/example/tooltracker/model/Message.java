package com.example.tooltracker.model;

import java.sql.Timestamp;

public class Message {
    private int id;
    private String username;
    private String message;
    private Timestamp timestamp;

    // Konstruktor
    public Message(int id, String username, String message, Timestamp timestamp) {
        this.id = id;
        this.username = username;
        this.message = message;
        this.timestamp = timestamp;
    }

    // Gettery i settery
    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getMessage() {
        return message;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}

