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
        // if / else med forbindelse til DB for at tjekke om emailen allerede findes.
    }

    public boolean isUserLoggedIn(){
        String tempMail = currentUser.get(0).getEmail();
        if (tempMail.contains("@") && tempMail.contains(".")){
            System.out.println("user is logged in");
            return true;
        } else {
            System.out.println("user is NOT logged in");
            return false;
        }


    }

    public boolean isUserAlreadyAdded(User user){
        UserRepo ur = new UserRepo();
        if (ur.getUsers().contains(user.getEmail())){
            return true;
        } else {
            return false;
        }
    }

    public boolean isWishReserved(Wishes wishes){
        if (wishes.isReserved()){
            return true;
        } else {
            return false;
        }
    }

    public boolean isWishAlreadyAdded(Wishes wishes, Wishlist wishlist){
        if (wishlist.getWishes().contains(wishes.getWishname())){
            return true;
        } else {
            return false;
        }
    }

    //TODO make validator for - username duplicates - mail - password not empty

}
