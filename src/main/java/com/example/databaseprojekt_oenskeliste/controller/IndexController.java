package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.service.DBService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

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








}
