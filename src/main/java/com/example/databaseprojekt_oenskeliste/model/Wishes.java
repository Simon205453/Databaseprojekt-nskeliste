package com.example.databaseprojekt_oenskeliste.model;

public class Wishes {

    private String wishName;
    private String price;
    private int userID;

    public Wishes(String wishName, String price) {
        this.wishName = wishName;
        this.price = price;
    }

    public Wishes(String wishName, String price, int userID){
        this.wishName = wishName;
        this.price = price;
        this.userID = userID;
    }

    public String getWishName() {
        return wishName;
    }

    public String getPrice() {
        return price;
    }

    public int getUserID() {
        return userID;
    }

    @Override
    public String toString() {
        return "Ønske: "+wishName+", pris: "+price;
    }
}
