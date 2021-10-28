package com.example.projektarbete.dbclassstructure;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Foods {

    public String foodName;
    public String foodDesc;
    public Resturants resturant;

    public Foods() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Foods(String foodName, String foodDesc, Resturants resturant) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.resturant = resturant;
    }

}
