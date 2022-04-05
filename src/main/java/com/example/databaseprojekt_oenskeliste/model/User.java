package com.example.databaseprojekt_oenskeliste.model;

import java.util.ArrayList;

public class User {

    private String email;
    private String password;
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

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
