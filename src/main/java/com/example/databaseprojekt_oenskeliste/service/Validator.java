package com.example.databaseprojekt_oenskeliste.service;

public class Validator {
    public boolean isEmailValid(String email) {
        if (email.contains("@") && email.contains(".")) {
            return true;
        }else{
            System.out.println("invalid email");
            return false;
        }
    }

    //TODO make validator for - username duplicates - mail - password not empty

}
