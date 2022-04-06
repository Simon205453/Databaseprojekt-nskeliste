package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import static com.example.databaseprojekt_oenskeliste.model.User.currentUser;

@Controller
public class LoginController {

    private UserService us = new UserService();

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
        currentUser.clear();
        System.out.println("current user is now cleared");
        return "redirect:/index";
    }


    @PostMapping("/loginSuccess")
    public String loginSuccess(WebRequest dataFromForm, HttpServletRequest request){
        HttpSession session = request.getSession();
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        User loggedInUser = new User(email, password);
        loggedInUser.setUserID(us.getUserIDFromMail(email));
        if (us.userExistsInDB(email, password)){
            currentUser.add(loggedInUser);
            session.setAttribute("users",loggedInUser);
            return "redirect:/indexlogged";
        } else {
            return "redirect:/index";
        }
    }

    @PostMapping("/success")
    public String success(WebRequest dataFromForm){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        us.addUserToDB(email,password);
        return "redirect:/indexlogged";

    }

}
