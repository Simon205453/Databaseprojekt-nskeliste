package com.example.databaseprojekt_oenskeliste.service;

import static com.example.databaseprojekt_oenskeliste.model.User.currentUser;

public class Validator {
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
