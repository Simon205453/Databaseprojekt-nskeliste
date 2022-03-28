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




}
