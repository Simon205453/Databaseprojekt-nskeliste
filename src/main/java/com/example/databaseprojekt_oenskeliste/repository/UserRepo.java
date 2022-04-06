package com.example.databaseprojekt_oenskeliste.repository;

import com.example.databaseprojekt_oenskeliste.model.User;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UserRepo {
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
            System.out.println("User added to DB");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ResultSet getUsers() {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlString = "SELECT * FROM user;";
            rs = statement.executeQuery(sqlString);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("something is wrong in userrepo method");
        }
        return rs;
    }

    public ResultSet selectUserFromMail(String mail){
        try {
            String sqlString = "SELECT `user_id` FROM user WHERE email='" + mail + "';";
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

            return statement.executeQuery(sqlString);
        } catch (Exception e){
            System.out.println("Fejl i select user from mail");
            e.printStackTrace();
        }
        return null;

    }
}
