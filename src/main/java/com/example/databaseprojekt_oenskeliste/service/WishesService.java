package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.WishlistRepo;
import java.sql.ResultSet;
import java.util.ArrayList;

public class WishesService {

    private WishlistRepo wlRepo = new WishlistRepo();

    public Wishes createNewWish(String wishName, String price) {
        return new Wishes(wishName, price);
    }


    public ArrayList<Wishes> getAllWishes() {
        ArrayList<Wishes> allWishes = new ArrayList<>();
        ResultSet thisrs = wlRepo.getWishesFromWishlist();
        try {
            while (thisrs.next()) {
                String wishName = thisrs.getString("wish_name");
                String wishPrice = thisrs.getString("wish_price");
                int user_id = thisrs.getInt("user_id");

                Wishes wishes = new Wishes(wishName, wishPrice, user_id);

                if (!wishes.getWishName().equals("null")) {
                    allWishes.add(wishes);
                }
            }
            return allWishes;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Fejl i wishesservice getallwishes");
        }
        System.out.println("couldnt get wishes from wishes service getallwishes");
        return allWishes;
    }

    public void uploadWish(String name, String price, User user) {
        Wishes newWish = createNewWish(name, price);
        int userId = user.getUserID();
        if(!(name==null && price==null)){
            wlRepo.addWishToDB(newWish, userId);
            System.out.println("wish uploaded to database");
        }else{
            System.out.println("Wish not valid and not uploaded");
        }

    }

    public ArrayList<Wishes> getSingleWishlist(String email) {
        UserService us = new UserService();
        ArrayList<Wishes> allWishes = getAllWishes();
        ArrayList<Wishes> singleWish = new ArrayList<>();
        int userID = us.getUserIDFromMail(email);

        for (Wishes allWish : allWishes) {
            if (allWish.getUserID() == userID) {
                singleWish.add(allWish);
            }
        }
        return singleWish;
    }

}
