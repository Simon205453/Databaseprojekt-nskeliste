package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.DBRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class WishesService {
    private Statement statement;
    private Connection connection = DBRepo.connectDB();
    private  String sqlString;
    private  ResultSet rs;


    public Wishes createNewWish(String wishName, String price){
        Wishes newWish = new Wishes(wishName, price);
        return newWish;
    }

    public void addWishToDB(Wishes wish, int user_ID) {
        String wishName = wish.getWishName();
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

    public int getUserIDFromMail(String mail){
        int userId = 0;
        try {
            String sqlString = "SELECT `user_id` FROM user WHERE email='"+mail+"';";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sqlString);
            rs.next();
            userId = rs.getInt("user_id");
            System.out.println(userId);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

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

                if(!wishes.getWishName().equals("null")){
                    allWishes.add(wishes);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return allWishes;
    }

    public void uploadWish(String name, String price, User user){
        WishesService ws = new WishesService();
        UserService us = new UserService();
        Wishes newWish = ws.createNewWish(name, price);
        int userId = us.getUserIDFromMail(user.getEmail());
        System.out.println(userId);
        ws.addWishToDB(newWish, userId);
        System.out.println("wish uploaded to database");
    }

    public ArrayList<Wishes> getSingleWishlist(String email){
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
