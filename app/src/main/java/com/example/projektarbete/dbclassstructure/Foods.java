package com.example.projektarbete.dbclassstructure;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Foods {

    public String foodName;
    public String foodDesc;
    public RestaurantsDB restaurant;

    public Foods() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Foods(String foodName, String foodDesc, RestaurantsDB restaurant) {
        this.foodName = foodName;
        this.foodDesc = foodDesc;
        this.restaurant = restaurant;
    }

}
