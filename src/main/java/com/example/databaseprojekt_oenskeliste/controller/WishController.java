package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.service.WishesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import java.util.ArrayList;
import static com.example.databaseprojekt_oenskeliste.model.User.currentUser;

@Controller
public class WishController {

    private WishesService ws = new WishesService();

    @GetMapping("/wishlist")
    public String getWishList(Model model){
        User user = currentUser.get(0);
        String email = user.getEmail();
        ArrayList<Wishes> userWish = ws.getSingleWishlist(email);
        model.addAttribute("allWishes", userWish);
        return "wishlist";
    }

    @GetMapping("/wishgenerator")
    public String addwish(WebRequest dataFromForm){
        String wishName = dataFromForm.getParameter("name");
        String wishPrice = dataFromForm.getParameter("price");
        User loggedInUser = currentUser.get(0);
        ws.uploadWish(wishName,wishPrice,loggedInUser);
        return "wishgenerator";
    }

    @GetMapping("/choosewishlist")
    public String chooseWishList(){
        return "choosewishlist";
    }

    @PostMapping("/findwishlist")
    public String findWishList(Model model, WebRequest dataFromForm){
        String email = dataFromForm.getParameter("email");
        ArrayList<Wishes> singleList = ws.getSingleWishlist(email);
        model.addAttribute("singleList", singleList);
        return "findwishlist";
    }
}
