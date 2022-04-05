package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;
import static com.example.databaseprojekt_oenskeliste.model.User.currentUser;

@Controller
public class LoginController {

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
    public String loginSuccess(WebRequest dataFromForm,HttpSession session, Model transport){
        UserService userService = new UserService();
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        User loggedInUser = new User(email, password);
        loggedInUser.setUserID(userService.getUserIDFromMail(email));
        session.setAttribute("logged", email);
        transport.addAttribute("session", session.getAttribute("email"));

        if (userService.userExistsInDB(email, password)){
            currentUser.add(loggedInUser);
            session.setAttribute("users",loggedInUser);
            System.out.println(currentUser.toString());
            System.out.println(transport);
            return "redirect:/indexlogged";
        } else {
            System.out.println("");
            return "redirect:/index";
        }
    }

    @PostMapping("/success")
    public String success(WebRequest dataFromForm){
        UserService us = new UserService();
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        us.addUserToDB(email,password);
        return "redirect:/indexlogged";

    }

}
