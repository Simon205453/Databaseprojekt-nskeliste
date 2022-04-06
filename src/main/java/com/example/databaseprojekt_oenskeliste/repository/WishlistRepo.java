package com.example.databaseprojekt_oenskeliste.repository;

import com.example.databaseprojekt_oenskeliste.model.Wishes;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class WishlistRepo {
    private Statement statement;
    private Connection connection = DBRepo.connectDB();

    public void addWishToDB(Wishes wish, int user_ID) {
        String wishName = wish.getWishName();
        String wishPrice = wish.getPrice();
        try {
            String sqlString = "INSERT INTO wishlist (`user_id`,`wish_name`,`wish_price`) VALUES ('" + user_ID + "', '" + wishName + "', '" + wishPrice + "')";
            statement = connection.createStatement();
            statement.executeUpdate(sqlString);
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("Error, wish not added");
        }
    }

    public ResultSet getWishesFromWishlist(){
        try {
        statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        String sqlString = "SELECT * FROM wishlist ORDER BY `wish_name`";
            return statement.executeQuery(sqlString);
        } catch (Exception e){
            e.printStackTrace();
            System.out.println("fejl i getWishesFromWishlist");
        }
        return null;
    }
}




