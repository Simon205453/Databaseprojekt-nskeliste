package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;

import java.sql.SQLException;

import static com.example.databaseprojekt_oenskeliste.service.DBService.*;

public class UserService {



    public int getUserIDFromMail(String mail){
        int userId = 0;
        try {
            String sqlString = "SELECT `user_id` FROM `user` WHERE email='"+mail+"'";
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlString);
            rs.next();
            userId = rs.getInt("user_id");
            System.out.println(userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userId;
    }

    public User getUserFromMail(String mail){
        String sqlString = "SELECT `user_id` FROM `user` WHERE email="+mail+"";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlString);
            User returnUser = new User(rs.getString("email"), rs.getString("password"));
            return returnUser;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public User getUserFromID(int user_id){
        String sqlString = "SELECT `email` FROM `user` WHERE user_id="+user_id+"";
        try {
            statement = connection.createStatement();
            rs = statement.executeQuery(sqlString);
            User returnUser = new User(rs.getString("email"), rs.getString("password"));
            return returnUser;
        } catch (Exception e){

        }
        return null;
    }




}
