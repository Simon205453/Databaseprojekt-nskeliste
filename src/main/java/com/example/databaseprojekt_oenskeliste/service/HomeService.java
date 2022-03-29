package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;

import static com.example.databaseprojekt_oenskeliste.DatabaseprojektOenskelisteApplication.*;
import static com.example.databaseprojekt_oenskeliste.repository.UserRepo.currentUser;

public class HomeService {


    public void loginCheckerEmailPassword(String email, String password){
        System.out.println("email= " + email + ", password= " + password);
        User newUser = new User(email,password);
        currentUser.add(newUser);
        dbs.addUserToDB(newUser);
    }

}
