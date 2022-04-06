package com.example.databaseprojekt_oenskeliste.service;

import static com.example.databaseprojekt_oenskeliste.model.User.currentUser;

public class Validator {
    public boolean isEmailValid() {
        try {
            String tempMail = currentUser.get(0).getEmail();
            if (tempMail.contains("@") && tempMail.contains(".")) {
                System.out.println("Email is valid");
                return true;
            } else {
                System.out.println("Email is not valid");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
