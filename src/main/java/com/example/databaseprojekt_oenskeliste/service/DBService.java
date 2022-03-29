package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.*;
import java.util.Properties;

public class DBService {

    public static Statement statement;
    public static Connection connection = DBService.connectDB();
    public static String sqlString;
    public static ResultSet rs;

    public void addUserToDB(User user){
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        try {
            statement = connection.createStatement();
            sqlString = "INSERT INTO user (`email`, `password`)" + "VALUES('"+userEmail+"','"+userPassword+"')";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
