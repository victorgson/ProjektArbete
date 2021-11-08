package com.example.projektarbete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;


import com.google.firebase.database.IgnoreExtraProperties;
import com.example.projektarbete.databinding.ActivityMainBinding;
import com.example.projektarbete.dbclassstructure.RestaurantsDB;
import com.example.projektarbete.dbclassstructure.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

public class
MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mDatabase;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        init();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.mcdonalds, R.drawable.indish_food_pic, R.drawable.thaifood_bild_pic, R.drawable.fried_chicken_pic, R.drawable.fried_chicken_pic};

        String[] name = {"Max", "Bombay", "Kanyas Thai", "KFC", "TEST",  };

        String[] info = {"59.00 SEK i avgift.  Leveranstid: 20-30 min", "79.00 SEK i avgift.  Leveranstid: 40-50 min", "49.00 SEK i avgift.  Leveranstid: 30-40 min",
                "39.00 SEK i avgift.  Leveranstid: 20-30 min", "TEST"};

        String[] restaurantsID = {"max", "bombay", "thai", "kfc", "TEST" };

        ArrayList<Restaurants> restaurantsArrayList = new ArrayList<>();

        for (int i = 0; i < imageId.length; i++) {

            Restaurants restaurants = new Restaurants(name[i], info[i], imageId[i]);
            restaurantsArrayList.add(restaurants);
        }

        ListAdapter listAdapter = new ListAdapter(MainActivity.this, restaurantsArrayList);

        binding.listView.setAdapter(listAdapter);
        binding.listView.setClickable(true);
        binding.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i = new Intent(); //ändra till Vincents class
                i.putExtra("name",name[position]);
                i.putExtra("info",info[position]);
                i.putExtra("imageid",imageId[position]);
                i.putExtra("Rest1", restaurantsID[position]);

                i.setClass(MainActivity.this, RestMenu.class);
                startActivity(i);
                Log.i("info","Du tryckte på "+name[position]);

            }
        });



        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0,0);
                        return true;


                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
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

    private void addRestaurantEventListener(DatabaseReference mPostReference) {
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            private static final String TAG = "TEST";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //Post post = dataSnapshot.getValue(Post.class);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    RestaurantsDB r = new RestaurantsDB();
                    String name = ds.child("resturantName").getValue(String.class);
                    String desc = ds.child("resturantDesc").getValue(String.class);
                    r.setRestaurantName(name);
                    r.setRestaurantDesc(desc);

                    r.getRestaurantName();

                    // lägga till mcdonalds i listan
                    System.out.println(r.getRestaurantDesc());



                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
                Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
            }
        };
        mPostReference.addValueEventListener(postListener);
        // [END post_value_event_listener]
    }

    private void init(){
        //firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        ref = mDatabase.child("resturants");
        currentUser = mAuth.getCurrentUser();
        //layout

    }

    @Override
    protected void onStart() {
        super.onStart();
        addRestaurantEventListener(ref);

        // IF USER IS NOT SIGNED IN, SEND TO LOGIN, IF NOT STAY ON MAINACTIVITY

        if(currentUser != null){
            // user signed in - do nothing
        } else {
            // user not signed in, ask them to sign in
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

}
