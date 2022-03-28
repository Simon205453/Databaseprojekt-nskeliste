package com.example.databaseprojekt_oenskeliste.model;

import java.util.ArrayList;
import java.util.Arrays;

public class User {

    private String username;
    private String password;
    private String userId;
    private ArrayList<Wishes> wishlist;

    public User(String username, String password, String userId, ArrayList<Wishes> wishlist) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.wishlist = wishlist;
    }

    public String getUsername() {
        return username;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userId='" + userId + '\'' +
                ", wishlist=" + wishlist +
                '}';
    }
}
