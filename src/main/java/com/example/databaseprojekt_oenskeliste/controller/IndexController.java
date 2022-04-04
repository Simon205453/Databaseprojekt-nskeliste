package com.example.databaseprojekt_oenskeliste.controller;
import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.DBRepo;
import com.example.databaseprojekt_oenskeliste.repository.WishlistRepo;
import com.example.databaseprojekt_oenskeliste.service.Validator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import static com.example.databaseprojekt_oenskeliste.DatabaseprojektOenskelisteApplication.*;
import static com.example.databaseprojekt_oenskeliste.repository.UserRepo.currentUser;

@Controller
public class IndexController {

    @GetMapping("/index")
    public String index(HttpSession session, Model transport){
        DBRepo.connectDB();
        ArrayList<User> userSession = (ArrayList<User>) session.getAttribute(String.valueOf(currentUser));
        if (currentUser == null){
            userSession = new ArrayList<>();
        }
        transport.addAttribute("session", userSession);
        return "index";
    }

    @GetMapping("/indexlogged")
    public String indexlogged(){
        Validator vali = new Validator();
        if (vali.isUserLoggedIn()){
            System.out.println("indexlogged: user is logged in");
            return "indexlogged";
        } else {
            System.out.println("indexlogged: user is not logged in");
            return "index";
        }

    }

}
