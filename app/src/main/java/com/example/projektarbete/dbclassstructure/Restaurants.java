package com.example.projektarbete.dbclassstructure;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Restaurants {


    public String restaurantName, restaurantDesc;
    public int rating;
    public String imageUri;

    public Restaurants() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Restaurants(String restaurantName, String restaurantDesc, int rating, String imageUri) {
        this.restaurantName = restaurantName;
        this.restaurantDesc = restaurantDesc;
        this.rating = rating;
        this.imageUri = imageUri;
    }


}
