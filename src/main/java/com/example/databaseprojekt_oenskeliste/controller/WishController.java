package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.WishlistRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;

import static com.example.databaseprojekt_oenskeliste.repository.UserRepo.currentUser;


@Controller
public class WishController {
    @GetMapping("/wishlist")
    public String getWishList(Model model){
        WishlistRepo wishlistRepo = new WishlistRepo();
        User user = currentUser.get(0);
        String email = user.getEmail();
        ArrayList<Wishes> userWish = wishlistRepo.getSingleWishlist(email);
        model.addAttribute("allWishes", userWish);

        return "wishlist";
    }

    @GetMapping("/wishgenerator")
    public String addwish(WebRequest dataFromForm){
        WishlistRepo wishRepo = new WishlistRepo();
        String wishName = dataFromForm.getParameter("name");
        String wishPrice = dataFromForm.getParameter("price");
        User loggedInUser = currentUser.get(0);
        wishRepo.uploadWish(wishName,wishPrice,loggedInUser);
        System.out.println("wish generated");
        return "wishgenerator";
    }

    @GetMapping("/choosewishlist")
    public String chooseWishList(){
        return "choosewishlist";
    }

    @PostMapping("/findwishlist")
    public String testtest(Model model, WebRequest dataFromForm){
        WishlistRepo wishlistRepo = new WishlistRepo();
        String email = dataFromForm.getParameter("email");
        ArrayList<Wishes> singleList = wishlistRepo.getSingleWishlist(email);
        model.addAttribute("singleList", singleList);
        return "findwishlist";
    }

}
