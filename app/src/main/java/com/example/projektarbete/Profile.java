package com.example.projektarbete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.projektarbete.dbclassstructure.RestaurantsDB;
import com.example.projektarbete.dbclassstructure.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    TextView logoutText;
    FirebaseAuth mAuth;
    BottomNavigationView bottomNavigationView;
    DatabaseReference mDatabase;
    DatabaseReference ref;
    String userID;
    TextView emailTextView, fNameTextView, lNameTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_info);
        init();
        getSupportActionBar().hide();



        logoutText.setOnClickListener(view -> logout());


        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.profile:

                        return true;

                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;



                }




                return false;
            }
        });
    }


    private void init(){
        // layout
        logoutText = (TextView)findViewById(R.id.logout);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        emailTextView = (TextView)findViewById(R.id.email_profile);
        fNameTextView = (TextView)findViewById(R.id.phone_profile);
        lNameTextView = (TextView)findViewById(R.id.country_profile);

        //firebase

        //hämtar instancen för användaren om nu finns
        mAuth = FirebaseAuth.getInstance();
        // hämtar användarid från instancen
        userID = mAuth.getUid();
        //hämtar referensen till databasen
        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        // hämtar en annan referens till /users/
        ref = mDatabase.child("users");

        // skapar en lyssnare på referensen ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // loopar igenom alla entires på referenspunkten, så alla användare i detta fallet.
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User r = new User();
                    // kollar om nuvarande nyckeln är lika med nuvarande användarens userid, isåfall uppdatera profil sidan.
                    // finns nog väldigt mycket bättre och effektivare sätt att göra detta på
                    if(ds.getKey().equals(userID))
                    {
                    String fName = ds.child("fName").getValue(String.class);
                    String lName = ds.child("lName").getValue(String.class);
                    String uid = ds.child("uid").getValue(String.class);
                    String email = ds.child("email").getValue((String.class));
                    Boolean admin = ds.child("admin").getValue(Boolean.class);
                    r.setfName(fName);
                    r.setlName(lName);
                    r.setEmail(email);
                    r.setUid(uid);
                    r.setAdmin(admin);
                    updateUI(r.getEmail(), r.getfName(), r.getlName()); // uppdaterar UI med användardata
                    }

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.w("TAG", "loadPost:onCancelled", error.toException());

            }
        });
    };




    void updateUI(String email, String fName, String lName){
    emailTextView.setText(email);
    fNameTextView.setText(fName);
    lNameTextView.setText(lName);
    }

    void logout(){
        // Signs out, then sends to MainActivity where we check if the user is signed in.
        // In this case, it's false > sending to login
        mAuth.getInstance().signOut();
        startActivity(new Intent(this, MainActivity.class));
    }

}