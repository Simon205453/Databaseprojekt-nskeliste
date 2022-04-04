package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpSession;

import static com.example.databaseprojekt_oenskeliste.DatabaseprojektOenskelisteApplication.hs;
import static com.example.databaseprojekt_oenskeliste.DatabaseprojektOenskelisteApplication.us;
import static com.example.databaseprojekt_oenskeliste.repository.UserRepo.currentUser;


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

    @PostMapping("/success")
    public String success(WebRequest dataFromForm, HttpSession session, Model transport){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        hs.loginCheckerEmailPassword(email, password);
        return "redirect:/indexlogged";
    }

    @PostMapping("/loginSuccess")
    //Ved ikke om det overhovedet er nødvendigt med sessions.
    public String loginSuccess(WebRequest dataFromForm,HttpSession session, Model transport){
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        User loggedInUser = new User(email, password);
        session.setAttribute("logged", email);
        transport.addAttribute("session",(String) session.getAttribute("email"));

        System.out.println(currentUser.toString());
        if (us.userExistsInDB(email, password)){
            currentUser.add(loggedInUser);
            session.setAttribute("users",loggedInUser);
            System.out.println(transport);
            System.out.println("hej");
            return "redirect:/indexlogged";
        } else {
            System.out.println("");
            return "redirect:/index";
        }
    }

}
