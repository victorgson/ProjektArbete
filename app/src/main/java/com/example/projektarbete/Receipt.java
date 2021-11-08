package com.example.projektarbete;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Receipt {

    public String dishName;
    public int dishPrice;
    public String uid;


    public Receipt() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Receipt(String dishName, int dishPrice, String uid) {
        this.dishName = dishName;
        this.dishPrice = dishPrice;
        this.uid = uid;
    }

}
