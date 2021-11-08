package com.example.projektarbete.dbclassstructure;


import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class RestaurantsDB {


    public String restaurantName, restaurantDesc;
    public int rating;
    public String imageUri;
    public int restaurantID;

    public RestaurantsDB() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    @Override
    public String toString() {
        return "RestaurantsDB{" +
                "restaurantName='" + restaurantName + '\'' +
                ", restaurantDesc='" + restaurantDesc + '\'' +
                ", rating=" + rating +
                ", imageUri='" + imageUri + '\'' +
                '}';
    }

    public RestaurantsDB(String restaurantName, String restaurantDesc, int rating, String imageUri) {
        this.restaurantName = restaurantName;
        this.restaurantDesc = restaurantDesc;
        this.rating = rating;
        this.imageUri = imageUri;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantDesc() {
        return restaurantDesc;
    }

    public void setRestaurantDesc(String restaurantDesc) {
        this.restaurantDesc = restaurantDesc;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getImageUri() {
        return imageUri;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }
}
