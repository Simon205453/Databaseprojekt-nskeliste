package com.example.databaseprojekt_oenskeliste.repository;

import com.example.databaseprojekt_oenskeliste.model.Wishes;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.example.databaseprojekt_oenskeliste.service.DBService.*;
import static com.example.databaseprojekt_oenskeliste.service.DBService.sqlString;

public class WishlistRepo {


    public ArrayList<Wishes> getAllWishes(){
        ArrayList<Wishes> allWishes = new ArrayList<>();
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            sqlString = "SELECT * FROM wishlist ORDER BY `wish_name` ";
            rs = statement.executeQuery(sqlString);

            while (rs.next()){
                Wishes wishes = new Wishes(rs.getString("wish_name"),rs.getString("wish_price"));
                allWishes.add(wishes);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

        return allWishes;
    }

}




