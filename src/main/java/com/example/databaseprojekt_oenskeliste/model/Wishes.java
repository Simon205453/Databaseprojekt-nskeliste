package com.example.databaseprojekt_oenskeliste.model;

public class Wishes {

    private String wishname;
    private String wishId;
    private String price;
    private boolean isReserved;
    private int belongingUserId;

    public Wishes(String wishname, String price, int belongingUserId) {
        this.wishname = wishname;
        this.price = price;
        this.isReserved = false;
        this.belongingUserId = belongingUserId;
    }

    public Wishes(String wishname){
        this.wishname = wishname;
    }

    public Wishes(String wishname, String price) {
        this.wishname = wishname;
        this.price = price;
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

    public int getBelongingUserId() {
        return belongingUserId;
    }
    @Override
    public String toString() {
        return "Wishes:  Name= " + wishname + ",  Price= " + price + ",  is reserved= " + isReserved;
    }
    @Override
    public String toString() {
        return "Ønske: "+wishname+", pris: "+price;
    }
}
