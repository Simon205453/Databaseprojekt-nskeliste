package com.example.databaseprojekt_oenskeliste.model;

public class Wishes {

    private String wishname;
    private String price;
    private boolean isReserved;
    private int belongingUserId;


    public Wishes(String wishname){
        this.wishname = wishname;
    }

    public Wishes(String wishname, String price) {
        this.wishname = wishname;
        this.price = price;
    }

    public Wishes(String wishname, String price, int belongingUserId){
        this.wishname = wishname;
        this.price = price;
        this.belongingUserId = belongingUserId;
    }


    public void setReserved(boolean reserved) {
        isReserved = reserved;
        System.out.println("Wish is now reserved");
    }

    public String getWishname() {
        return wishname;
    }

    public String getPrice() {
        return price;
    }

    public int getBelongingUserId() {
        return belongingUserId;
    }

    @Override
    public String toString() {
        return "Ønske: "+wishname+", pris: "+price;
    }
}
