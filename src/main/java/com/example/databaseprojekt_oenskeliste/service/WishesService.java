package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.Wishes;

import java.sql.Connection;
import java.sql.Statement;

public class WishesService {
    public static Statement statement;
    public static Connection connection = DBService.connectDB();

    public Wishes createNewWish(String wishName, String price){
        Wishes newWish = new Wishes(wishName, price);
        return newWish;
    }

    public void addWishToDB(Wishes wish, int user_ID) {
        String wishName = wish.getWishname();
        String wishPrice = wish.getPrice();
        try {
            String sqlString = "INSERT INTO wishlist (`user_id`,`wish_name`,`wish_price`) VALUES ('" + user_ID + "', '" + wishName + "', '" + wishPrice + "')";
            statement = connection.createStatement();
            statement.executeUpdate(sqlString);
            System.out.println(sqlString);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error, wish not added");
        }
    }

}
