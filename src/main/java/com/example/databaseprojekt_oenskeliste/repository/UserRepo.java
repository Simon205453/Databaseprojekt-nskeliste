package com.example.databaseprojekt_oenskeliste.repository;

import com.example.databaseprojekt_oenskeliste.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static com.example.databaseprojekt_oenskeliste.repository.DBRepo.*;;

public class UserRepo {

    public static ArrayList<User> currentUser = new ArrayList<>();


    public ArrayList<User> getUsers(){
        ArrayList<User> allUsers = new ArrayList<>();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            //sqlString = "SELECT `user_emails`" + "FROM `user_emails`" + "WHERE user_emails.user_emails";
            sqlString = "SELECT * FROM user ORDER BY `userid` ";
            rs = statement.executeQuery(sqlString);

            while(rs.next()){
                 User tempUser = new User(rs.getString("email"), rs.getString("password")); // fix senere
                allUsers.add(tempUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allUsers;
    }

    //is login present in database
    public boolean isLoginValid(){
        boolean loginValid = false;
        try{
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            rs = statement.executeQuery(sqlString);
            sqlString = "SELECT * FROM `user` ";

            if (rs.next()){

            }


        }catch (Exception e){
            e.printStackTrace();
        }

        return true;
    }




}
