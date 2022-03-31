package com.example.databaseprojekt_oenskeliste.controller;
import com.example.databaseprojekt_oenskeliste.model.User;
import com.example.databaseprojekt_oenskeliste.model.Wishes;
import com.example.databaseprojekt_oenskeliste.repository.WishlistRepo;
import com.example.databaseprojekt_oenskeliste.service.DBService;
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
        DBService.connectDB();

        ArrayList<User> userSession = (ArrayList<User>) session.getAttribute(String.valueOf(currentUser));
        if (currentUser == null){
            userSession = new ArrayList<>();
        }
        transport.addAttribute("session", userSession);
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
            //Kun email bliver tjekket i database so far.
            System.out.println(transport);
            System.out.println("hej");
            return "redirect:/indexlogged";
        } else {
            System.out.println("");
            return "redirect:/index";
        }

    }

    @GetMapping("/wishlist")
    public String getWishList(Model model){
        WishlistRepo wishlistRepo = new WishlistRepo();
        ArrayList<Wishes> listOfAllWishes = wishlistRepo.getAllWishes();
        User user = currentUser.get(0);
        String email = user.getEmail();
        ArrayList<Wishes> userWish = wishlistRepo.getSingleWishlist(email);
        //model.addAttribute("allWishes", listOfAllWishes);
        model.addAttribute("allWishes", userWish);

        return "wishlist";
    }

    @GetMapping("/wishgenerator")
    public String addwish(WebRequest dataFromForm){
        WishlistController wlc = new WishlistController();
        String wishName = dataFromForm.getParameter("name");
        String wishPrice = dataFromForm.getParameter("price");
        User loggedInUser = currentUser.get(0);
        wlc.uploadWish(wishName,wishPrice,loggedInUser);

        // find logged user, to upload for the specific profile
        // return method to find user.
        //User tempuser =
        //wc.uploadWish(name, price, tempuser);
        //User loggeduser =
        //wc.uploadWish(name, price, loggeduser);
        System.out.println("wish generated");
        return "wishgenerator";
    }


    @GetMapping("/choosewishlist")
    public String chooseList(Model model, WebRequest dataFromForm){
        WishlistRepo wishlistRepo = new WishlistRepo();
        String email = dataFromForm.getParameter("email");
        //ArrayList<Wishes> singleList = wishlistRepo.getSingleWishlist(email);
        //model.addAttribute("singleList", singleList);
        return "choosewishlist";
    }

    @PostMapping("/findwishlist")
    public String testtest(Model model, WebRequest dataFromForm){
        WishlistRepo wishlistRepo = new WishlistRepo();
        String email = dataFromForm.getParameter("email");
        ArrayList<Wishes> singleList = wishlistRepo.getSingleWishlist(email);
        model.addAttribute("singleList", singleList);
        return "findwishlist";
    }
}
