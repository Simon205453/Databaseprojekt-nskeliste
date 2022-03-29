package com.example.databaseprojekt_oenskeliste;

import com.example.databaseprojekt_oenskeliste.controller.WishlistController;
import com.example.databaseprojekt_oenskeliste.service.DBService;
import com.example.databaseprojekt_oenskeliste.service.HomeService;
import com.example.databaseprojekt_oenskeliste.service.Validator;
import com.example.databaseprojekt_oenskeliste.service.WishesService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

import static com.example.databaseprojekt_oenskeliste.service.DBService.connectDB;

@SpringBootApplication
public class DatabaseprojektOenskelisteApplication {

    public static WishesService ws = new WishesService();
    public static WishlistController wc = new WishlistController();
    public static DBService dbs = new DBService();
    public static Validator vali = new Validator();
    public static HomeService hs = new HomeService();

    public static void main(String[] args) {
        SpringApplication.run(DatabaseprojektOenskelisteApplication.class, args);
        connectDB();
    }

}
