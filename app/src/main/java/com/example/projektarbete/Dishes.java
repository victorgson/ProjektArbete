package com.example.projektarbete;

import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Dishes extends android.app.Activity {




    private String name;
    private String info;
    private String price;
    private int prices;


    public Dishes() {
    }

    public Dishes(String name, String info, String price, int prices) {

        this.name = name;
        this.info = info;
        this.price = price;
        this.prices = prices;


    }





    public String getName() {
        return name;
    }

    public String getInfo() {
        return info;
    }

    public String getPrice() {
        return price;
    }

    public int getPrices() {

        return prices;
    }




}
