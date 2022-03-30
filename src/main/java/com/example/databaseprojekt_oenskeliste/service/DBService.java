package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DBService {

    public static Statement statement;
    public static Connection connection = DBService.connectDB();
    public static String sqlString;
    public static ResultSet rs;

    public void addUserToDB(User user) {
        String userEmail = user.getEmail();
        String userPassword = user.getPassword();
        try {
            statement = connection.createStatement();
            sqlString = "INSERT INTO user (`email`, `password`)" + "VALUES('" + userEmail + "','" + userPassword + "')";
            statement.executeUpdate(sqlString);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean userExistsInDB(String email) {
        ArrayList<User> testListe = new ArrayList<>();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlString = "SELECT * FROM user";
            rs = statement.executeQuery(sqlString);
            while (rs.next()){
                User testUser = new User(rs.getString("email"),rs.getString("password"));
                testListe.add(testUser);

            }
            for (User user : testListe) {
                if (user.getEmail().equals(email)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("something is wrong in userexistsindb method");
        return false;
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
