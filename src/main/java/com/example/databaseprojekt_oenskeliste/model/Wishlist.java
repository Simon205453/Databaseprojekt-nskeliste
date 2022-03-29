package com.example.databaseprojekt_oenskeliste.model;

import java.util.ArrayList;

public class Wishlist {

    private ArrayList<Wishes> wishes;
    private int amountOfWishes;
    private int assignedUserId;

    public Wishlist(ArrayList<Wishes> wishes, int amountOfWishes, int assignedUserId) {
        this.wishes = wishes;
        this.amountOfWishes = amountOfWishes;
        this.assignedUserId = assignedUserId;
    }

    public Wishlist(int assignedUserId) {
        this.assignedUserId = assignedUserId;
    }

    public ArrayList<Wishes> getWishes() {
        return wishes;
    }

    public int getAmountOfWishes() {
        return amountOfWishes;
    }

    public int getAssignedUserId() {
        return assignedUserId;
    }
}
