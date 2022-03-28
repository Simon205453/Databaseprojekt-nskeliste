package com.example.databaseprojekt_oenskeliste.model;

import java.util.ArrayList;

public class User {

    private String username;
    private String password;
    private int userId;
    private ArrayList<Wishes> wishlist;

    public User(String username, String password, int userId, ArrayList<Wishes> wishlist) {
        this.username = username;
        this.password = password;
        this.userId = userId;
        this.wishlist = wishlist;
    }

    public String getUsername() {
        return username;
    }

    public Wishlist createWishlist(){
        Wishlist userwishlist = new Wishlist(this.userId);

        return userwishlist;
    }

    public String addWishToWishlist(String wish){
        String addedWish = wish;

        return addedWish;
    }
}
