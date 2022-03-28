package com.example.databaseprojekt_oenskeliste;

import com.example.databaseprojekt_oenskeliste.service.DBService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

import static com.example.databaseprojekt_oenskeliste.service.DBService.connectDB;

@SpringBootApplication
public class DatabaseprojektOenskelisteApplication {

    public static void main(String[] args) {
        SpringApplication.run(DatabaseprojektOenskelisteApplication.class, args);
        connectDB();
    }

}
