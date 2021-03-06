package com.example.projektarbete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
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

        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            ((Window) window).addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.support_bar));
        }



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

        //h??mtar instancen f??r anv??ndaren om nu finns
        mAuth = FirebaseAuth.getInstance();
        // h??mtar anv??ndarid fr??n instancen
        userID = mAuth.getUid();
        //h??mtar referensen till databasen
        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        // h??mtar en annan referens till /users/
        ref = mDatabase.child("users");

        // skapar en lyssnare p?? referensen ref
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // loopar igenom alla entires p?? referenspunkten, s?? alla anv??ndare i detta fallet.
                for (DataSnapshot ds : snapshot.getChildren()) {
                    User r = new User();
                    // kollar om nuvarande nyckeln ??r lika med nuvarande anv??ndarens userid, is??fall uppdatera profil sidan.
                    // finns nog v??ldigt mycket b??ttre och effektivare s??tt att g??ra detta p??
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
                    updateUI(r.getEmail(), r.getfName(), r.getlName()); // uppdaterar UI med anv??ndardata
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