package com.example.databaseprojekt_oenskeliste.service;

public class Validator {
    public boolean isEmailValid(String email){
        if(email.contains("@")){
            if (email.contains(".")){  //-----   kan ikke få && til at virke..?
                return true;
            }
        } else {
            System.out.println("invalid email");
            return false;
        }
        return false;
    }
}
