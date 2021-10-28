package com.example.projektarbete.dbclassstructure;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class User {

    public String email;
    public String fName;
    public String lName;
    public String uid;
    public Boolean admin = false;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String email, String fName, String lName, String uid, Boolean admin) {
        this.email = email;
        this.fName = fName;
        this.lName = lName;
        this.uid = uid;
        this.admin = admin;
    }

}
