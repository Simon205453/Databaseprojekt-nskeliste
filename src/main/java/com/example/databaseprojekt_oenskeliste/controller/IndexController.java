package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.repository.UserRepo;
import com.example.databaseprojekt_oenskeliste.service.DBService;
import com.example.databaseprojekt_oenskeliste.service.Validator;
import com.example.databaseprojekt_oenskeliste.service.WishesService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import static com.example.databaseprojekt_oenskeliste.DatabaseprojektOenskelisteApplication.*;
import static com.example.databaseprojekt_oenskeliste.repository.UserRepo.currentUser;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(){
        DBService.connectDB();
        currentUser.clear();
        return "index";
    }

    @GetMapping("/opretnybruger")
    public String createNewUser(){

        return "createNewUser";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/success")
    public String success(WebRequest dataFromForm){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        hs.loginCheckerEmailPassword(email, password);
        return "success";
    }

    @PostMapping("/loginSuccess")
    public String loginSuccess(WebRequest dataFromForm){
        //UserRepo allUsers = new UserRepo();
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        User loggedInUser = new User(email, password);
        currentUser.add(loggedInUser);
        System.out.println(currentUser.toString());
        if (dbs.userExistsInDB(email)){
            //Kun email bliver tjekket i database so far.
            //TODO implement code to check password matches user email
            System.out.println("hej");
            return "/loginSuccess";
        } else {
            System.out.println("Hvorfor ender vi her");
            //hvis login ikke er gyldigt bliver dette kørt
            return "redirect:/index";
        }

    }

    @GetMapping("/wishlist")
    public String wishlist(){
        return "wishlist";
    }

    @PostMapping("/wishgenerator")
    public String addwish(WebRequest dataFromForm){

        String wishName = dataFromForm.getParameter("name");
        String wishPrice = dataFromForm.getParameter("price");



        // find logged user, to upload for the specific profile
        // return method to find user.
        //User tempuser =
        //wc.uploadWish(name, price, tempuser);
        //User loggeduser =
        //wc.uploadWish(name, price, loggeduser);
        System.out.println("wish generated");
        return "wishgenerator";
    }





}
