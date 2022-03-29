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

    public Wishlist createWishlist(){
        Wishlist userwishlist = new Wishlist(this.userId);

        return userwishlist;
    }

    public static String addWishToWishlist(String wish, int userId){
        //String addedWish = wish;

        //user_id skal sammensættes fra user og wishlist tabel fra databasen

        // user_id, wish_id, wish_name (wish_id er AI)

        return "INSERT INTO wishlist (`user_id`,`wish_name`) VALUES ('"+userId+"', '"+wish+"');";
    }
}
