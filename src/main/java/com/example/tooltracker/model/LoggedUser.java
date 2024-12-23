package com.example.tooltracker.model;

public class LoggedUser {
    private static Users user;

    public static void setUser(Users user) {
        LoggedUser.user = user;
    }

    public static Users getUser() {
        return user;
    }
}
