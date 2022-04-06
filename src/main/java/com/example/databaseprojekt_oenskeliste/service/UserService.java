package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.repository.UserRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    private UserRepo ur = new UserRepo();

    public boolean userExistsInDB(String email, String password) {
        ArrayList<User> userExist = new ArrayList<>();
        ResultSet rs = ur.getUsers();
        try {
            while (rs.next()) {
                User testUser = new User(rs.getString("email"), rs.getString("password"));
                userExist.add(testUser);
            }
            for (User user : userExist) {
                if (user.getEmail().equals(email) && user.getPassword().equals(password)) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.out.println("fejl i userexistsindb userservice");
        }
        return false;
    }

    public int getUserIDFromMail(String mail) {
        int userId = 0;
        ResultSet tempResultSet = ur.selectUserFromMail(mail);
        try {
            tempResultSet.next();
            userId = tempResultSet.getInt("user_id");
            return userId;
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("couldnt get userid from getuserid method");
        }
        return userId;
    }

    public void addUserToDB(String email, String password){
        User newUser = new User(email,password);
        ur.addUserToDB(newUser);
    }

}
