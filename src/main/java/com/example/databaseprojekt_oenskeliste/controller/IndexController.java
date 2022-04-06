package com.example.databaseprojekt_oenskeliste.controller;

import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.repository.DBRepo;
import com.example.databaseprojekt_oenskeliste.service.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import static com.example.databaseprojekt_oenskeliste.model.User.currentUser;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(HttpSession session, Model transport) {
        DBRepo.connectDB();
        currentUser.clear();
        ArrayList<User> userSession = (ArrayList<User>) session.getAttribute(String.valueOf(currentUser));
        if (currentUser == null) {
            userSession = new ArrayList<>();
        }
        transport.addAttribute("session", userSession);
        return "index";
    }


    @GetMapping("/indexlogged")
    public String indexlogged() {
        Validator vali = new Validator();
        if (vali.isEmailValid()) {
            System.out.println("indexlogged: user is logged in");
            return "indexlogged";
        } else {
            System.out.println("indexlogged: user is not logged in");
            return "redirect:/index";
        }

    }
}
