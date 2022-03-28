package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;

import java.sql.*;

public class DBService {

    public static Statement statement;
    public static Connection connection = DBService.connectDB();
    public static String sqlString;
    public static ResultSet rs;

    public void addUserToDB(User user){
        String selectedUser = user.getUsername();
        try {
            statement = connection.createStatement();
            sqlString = "INSERT INTO user (`username`)" + "VALUES('"+selectedUser+"')";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection connectDB() {
        try {
            String url = "jdbc:mysql://localhost:3306/wishlist_proj";
            connection = DriverManager.getConnection(url, "root", "Need2breed#");
            System.out.println("Connection established");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void newWish(String wish, int user_ID){
        // String sqlString = "INSERT INTO wishlist (`user_id`,`wish_name`) VALUES ('"+userId+"', '"+wish+"');";
        String test = User.addWishToWishlist(wish,user_ID);
        try {
            statement = connection.createStatement();
            statement.executeUpdate(test);
            System.out.println(test);
        }catch (Exception e){
            System.out.println("e");
            System.out.println("fejl ved oprettelse af nyt ønske");
        }

    }




}
