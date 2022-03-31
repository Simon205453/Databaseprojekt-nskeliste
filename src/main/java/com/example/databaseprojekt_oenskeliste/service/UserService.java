package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static com.example.databaseprojekt_oenskeliste.service.DBService.*;

public class UserService {


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

    public boolean userExistsInDB(String email, String password) {
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
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        System.out.println("something is wrong in userexistsindb method");
        return false;
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
            e.printStackTrace();
        }
        return null;
    }


}
