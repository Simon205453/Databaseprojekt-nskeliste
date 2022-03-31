package com.example.databaseprojekt_oenskeliste.service;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.model.Wishlist;
import com.example.databaseprojekt_oenskeliste.repository.UserRepo;

import static com.example.databaseprojekt_oenskeliste.repository.UserRepo.currentUser;

public class Validator {
    public boolean isEmailValid(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        }else{
            System.out.println("invalid email");
            return false;
        }
    }

    public boolean isUserLoggedIn(){
        try {
            String tempMail = currentUser.get(0).getEmail();


        if (tempMail.contains("@") && tempMail.contains(".")){
            System.out.println("user is logged in");
            return true;
        } else {
            System.out.println("user is NOT logged in");
            return false;
        }
        } catch (Exception e){
            System.out.println("no user is logged in");
        }
        return false;
    }
}
