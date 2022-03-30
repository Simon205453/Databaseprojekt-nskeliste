package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.UserRepo;
import com.example.databaseprojekt_oenskeliste.repository.WishlistRepo;
import com.example.databaseprojekt_oenskeliste.service.DBService;
import com.example.databaseprojekt_oenskeliste.service.Validator;
import com.example.databaseprojekt_oenskeliste.service.WishesService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.io.PrintWriter;
import java.util.ArrayList;

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

    @GetMapping("/logout")
    public String logout(){
        return "redirect:/index";
    }

    @PostMapping("/success")
    public String success(WebRequest dataFromForm, HttpSession session, Model transport){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        // kigger på det her senere
       // hs.loginCheckerEmailPassword(email, password);
        //transport.addAttribute("session",(String) session.getAttribute("email"));

        return "success";
    }

    @PostMapping("/loginSuccess")
    //Ved ikke om det overhovedet er nødvendigt med sessions.
    public String loginSuccess(WebRequest dataFromForm, HttpServletRequest request,HttpSession session){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        User loggedInUser = new User(email, password);
        session.setAttribute("logged", email);


        System.out.println(currentUser.toString());
        if (dbs.userExistsInDB(email)){
            currentUser.add(loggedInUser);
            session.setAttribute("users",loggedInUser);
            //Kun email bliver tjekket i database so far.
            //TODO implement code to check password matches user email
            System.out.println("hej");
            return "redirect:/wishgenerator";
        } else {
            System.out.println("Hvorfor ender vi her");
            //hvis login ikke er gyldigt bliver dette kørt
            return "redirect:/index";
        }

    }

    @GetMapping("/wishlist")
    public String getWishList(Model model){
        WishlistRepo wishlistRepo = new WishlistRepo();
        ArrayList<Wishes> listOfAllWishes = wishlistRepo.getAllWishes();
        model.addAttribute("allWishes", listOfAllWishes);
        return "wishlist";
    }

    @GetMapping("/wishgenerator")
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
    @GetMapping("/session")
    public String seeUsersSession(){
        return "session";
    }





}
