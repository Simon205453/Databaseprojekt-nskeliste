package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.service.DBService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index(){
        DBService.connectDB();
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
    public String sucess(WebRequest dataFromForm){
        DBService service = new DBService();
        String email = dataFromForm.getParameter("email");
        String password = dataFromForm.getParameter("password");
        System.out.println(email+" "+password);
        User newUser = new User(email,password);
        service.addUserToDB(newUser);
        return "success";
    }







}
