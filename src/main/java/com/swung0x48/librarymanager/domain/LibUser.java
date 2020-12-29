package com.swung0x48.librarymanager.domain;

public class LibUser {
    int userId;
    String userName;
    String password;
    String role;

    public LibUser() {
    }

    public LibUser(int userId, String userName, String password, String role) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.role = role;
    }

    public LibUser(String username, String password, String role)
    {
        this.userName = username;
        this.password = password;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
