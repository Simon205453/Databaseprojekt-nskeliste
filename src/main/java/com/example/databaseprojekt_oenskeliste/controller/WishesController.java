package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.Wishes;

public class WishesController {

    public void setReserved(Wishes wishes) {
        wishes.setReserved(true);
        System.out.println("Wish is now reserved");
    }

    public void removeWishes(Wishes wishes) {
        //TODO implement code to remove wish from wishlist

    }



}
