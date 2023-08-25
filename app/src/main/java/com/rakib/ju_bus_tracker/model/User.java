package com.rakib.ju_bus_tracker.model;

public class User {
    private String id;
    private String password;
    private UserType userType;

    public User(String id, String password, UserType userType) {
        this.id = id;
        this.password = password;
        this.userType = userType;
    }

    // Getters and setters for id, password, and userType

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    // Enum for user types (e.g., Student, Driver, Admin)
    public enum UserType {
        STUDENT, DRIVER, ADMIN
    }
}
