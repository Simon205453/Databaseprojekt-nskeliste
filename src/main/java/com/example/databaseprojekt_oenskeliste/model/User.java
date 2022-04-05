package com.example.databaseprojekt_oenskeliste.model;

import java.util.ArrayList;

public class User {

    private String email;
    private String password;
    private int userID;
    public static ArrayList<User> currentUser = new ArrayList<>();

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword(){
        return password;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    @Override
    public String toString() {
        return "email = " + email + ", password: " + password + ", user ID: "+userID;
    }
}
