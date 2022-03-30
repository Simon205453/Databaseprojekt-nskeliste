package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.UserRepo;
import com.example.databaseprojekt_oenskeliste.service.DBService;
import com.example.databaseprojekt_oenskeliste.service.UserService;
import com.example.databaseprojekt_oenskeliste.service.WishesService;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

public class WishlistController {

    WishesService ws = new WishesService();
    DBService dbs = new DBService();
    UserRepo userRepo = new UserRepo();
    //ArrayList<User> userList = userRepo.getUsers();
    UserService us = new UserService();

    public void uploadWish(String name, String price, User user){
        Wishes newWish = ws.createNewWish(name, price);
        int userId = us.getUserIDFromMail(user.getEmail());

        //us.getUserFromMail();
        dbs.addWishToDB(newWish, userId);
        System.out.println("wish uploaded to database");
    }




}
