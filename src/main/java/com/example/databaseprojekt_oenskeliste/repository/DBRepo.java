package com.example.databaseprojekt_oenskeliste.repository;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;

public class DBRepo {

    public  static Statement statement;
    public static Connection connection = DBRepo.connectDB();
    public static String sqlString;
    public static ResultSet rs;

    public static Connection connectDB() {
        try {
            FileInputStream file = new FileInputStream("src/main/resources/database.properties");
            Properties properties = new Properties();
            properties.load(file);

            connection = DriverManager.getConnection(properties.getProperty("connect-string"), properties.getProperty("username"), properties.getProperty("password"));
            System.out.println("Connection established");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}