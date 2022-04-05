package com.example.databaseprojekt_oenskeliste.model;

public class Wishes {

    private String wishName;
    private String price;
    private int belongingUserId;

    public Wishes(String wishName, String price) {
        this.wishName = wishName;
        this.price = price;
    }

    public Wishes(String wishName, String price, int belongingUserId){
        this.wishName = wishName;
        this.price = price;
        this.belongingUserId = belongingUserId;
    }

    public String getWishName() {
        return wishName;
    }

    public String getPrice() {
        return price;
    }

    public int getBelongingUserId() {
        return belongingUserId;
    }

    @Override
    public String toString() {
        return "Ønske: "+wishName+", pris: "+price;
    }
}
