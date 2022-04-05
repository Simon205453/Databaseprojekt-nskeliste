package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.UserRepo;
import com.example.databaseprojekt_oenskeliste.repository.WishlistRepo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WishesService {

    public Wishes createNewWish(String wishName, String price) {
        Wishes newWish = new Wishes(wishName, price);
        return newWish;
    }

    public int getUserIDFromMail(String mail) {
        int userId = 0;
        UserRepo ur = new UserRepo();
        ResultSet tempResultSet = ur.selectUserFromMail(mail);
        try {
            tempResultSet.next();
            userId = tempResultSet.getInt("user_id");
            System.out.println(userId);
            return userId;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("couldnt get userid from getuserid method");
        }
        return userId;
    }

    public ArrayList<Wishes> getAllWishes() {
        ArrayList<Wishes> allWishes = new ArrayList<>();
        WishlistRepo wlRepo = new WishlistRepo();
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
        WishesService ws = new WishesService();
        WishlistRepo wlRepo = new WishlistRepo();
        UserService us = new UserService();
        Wishes newWish = ws.createNewWish(name, price);
        System.out.println(newWish.toString());
        int userId = us.getUserIDFromMail(user.getEmail());
        System.out.println(userId);
        wlRepo.addWishToDB(newWish, userId);
        System.out.println("wish uploaded to database");
    }

    public ArrayList<Wishes> getSingleWishlist(String email) {
        ArrayList<Wishes> allWishes = getAllWishes();
        ArrayList<Wishes> singleWish = new ArrayList<>();
        int userID = getUserIDFromMail(email);

        for (Wishes allWish : allWishes) {
            if (allWish.getBelongingUserId() == userID) {
                singleWish.add(allWish);
            }
        }
        return singleWish;
    }

}
