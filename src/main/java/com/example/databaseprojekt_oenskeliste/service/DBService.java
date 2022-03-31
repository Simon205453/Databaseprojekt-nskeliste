package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBService {

    public static Statement statement;
    public static Connection connection = DBService.connectDB();
    public static String sqlString;
    public static ResultSet rs;

    public static Connection connectDB() {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/database.properties");
            Properties properties = new Properties();
            properties.load(file);

            connection = DriverManager.getConnection(properties.getProperty("connect-string"), properties.getProperty("username"), properties.getProperty("password"));
            System.out.println("Connection established");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
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
