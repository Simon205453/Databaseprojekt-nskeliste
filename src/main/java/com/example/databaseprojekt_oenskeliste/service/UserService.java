package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.UserRepo;
import com.example.databaseprojekt_oenskeliste.repository.WishlistRepo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserService {

    public boolean userExistsInDB(String email, String password) {
        ArrayList<User> userExist = new ArrayList<>();
        UserRepo ur = new UserRepo();
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
        //UserRepo ur = new UserRepo();
        WishesService ws = new WishesService();
        int userId = ws.getUserIDFromMail(mail);
        /*
        try {
            //userId = ws.getUserIDFromMail(mail);
            userId = ur.selectUserFromMail(mail).getInt("user_id");
            return userId;
        } catch (SQLException e) {
            e.printStackTrace();
        }
         */
        //System.out.println("fejl i getUserIDFromMail userservice");
        return userId;
    }
}
