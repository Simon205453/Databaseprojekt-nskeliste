package com.example.databaseprojekt_oenskeliste.model;

import java.util.ArrayList;

public class User {

    private String email;
    private String password;
    private int userId;
    private ArrayList<Wishes> wishlist;

    public User(String email, String password, int userId, ArrayList<Wishes> wishlist) {
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.wishlist = wishlist;
    }

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
                ", userId=" + userId +
                ", wishlist=" + wishlist +
                '}';
    }
}
