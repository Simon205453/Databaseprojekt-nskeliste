package com.example.databaseprojekt_oenskeliste.repository;

import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.service.UserService;


import java.sql.ResultSet;
import java.util.ArrayList;


import static com.example.databaseprojekt_oenskeliste.service.DBService.*;
import static com.example.databaseprojekt_oenskeliste.service.DBService.sqlString;

public class WishlistRepo {

    UserService us = new UserService();

    public ArrayList<Wishes> getAllWishes(){
        ArrayList<Wishes> allWishes = new ArrayList<>();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlString = "SELECT * FROM wishlist ORDER BY `wish_name`";
            rs = statement.executeQuery(sqlString);

            while (rs.next()){
                String wishName = rs.getString("wish_name");
                String wishPrice = rs.getString("wish_price");
                int user_id = rs.getInt("user_id");

                Wishes wishes = new Wishes(wishName,wishPrice,user_id);

                if(!wishes.getWishname().equals("null")){
                    allWishes.add(wishes);
                }
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return allWishes;
    }

    public ArrayList<Wishes> getSingleWishlist(String email){
        ArrayList<Wishes> allWishes = getAllWishes();
        ArrayList<Wishes> singleWish = new ArrayList<>();
        int userID = us.getUserIDFromMail(email);

        for (Wishes allWish : allWishes) {
            if (allWish.getBelongingUserId() == userID) {
                singleWish.add(allWish);
            }
        }

        return singleWish;

    }


}




