package com.example.projektarbete.dbclassstructure;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Resturants{


    public String resturantName, resturantDesc;
    public int rating;
    public String imageUri;

    public Resturants() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public Resturants(String resturantName,String resturantDesc, int rating, String imageUri) {
        this.resturantName = resturantName;
        this.resturantDesc = resturantDesc;
        this.rating = rating;
        this.imageUri = imageUri;
    }


}
