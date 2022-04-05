package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.repository.DBRepo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserService {
    private Statement statement;
    private Connection connection = DBRepo.connectDB();
    private  String sqlString;
    private  ResultSet rs;

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
        ArrayList<User> userExist = new ArrayList<>();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlString = "SELECT * FROM user";
            rs = statement.executeQuery(sqlString);
            while (rs.next()){
                User testUser = new User(rs.getString("email"),rs.getString("password"));
                userExist.add(testUser);
            }
            for (User user : userExist) {
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
}
