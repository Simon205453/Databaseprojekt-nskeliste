package com.example.databaseprojekt_oenskeliste.model;

public class Wishes {

    private String wishname;
    private String wishId;
    private String price;
    private boolean isReserved;

    public Wishes(String wishname, String wishId, String price, boolean isReserved) {
        this.wishname = wishname;
        this.wishId = wishId;
        this.price = price;
        this.isReserved = isReserved;
    }

    public void setReserved(boolean reserved) {
        isReserved = reserved;
        System.out.println("Wish is now reserved");
    }

    public String getWishname() {
        return wishname;
    }

    public String getWishId() {
        return wishId;
    }

    public String getPrice() {
        return price;
    }

    public boolean isReserved() {
        return isReserved;
    }
}
