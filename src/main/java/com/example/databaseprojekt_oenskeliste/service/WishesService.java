package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.Wishes;

public class WishesService {

    public Wishes createNewWish(String wishName, String price){
        Wishes newWish = new Wishes(wishName, price);
        return newWish;
    }


}
