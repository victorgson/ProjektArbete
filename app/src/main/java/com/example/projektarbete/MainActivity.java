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


import com.example.projektarbete.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    FirebaseAuth mAuth;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        init();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        int[] imageId = {R.drawable.mcdonalds, R.drawable.indish_food_pic, R.drawable.thaifood_bild_pic, R.drawable.fried_chicken_pic};

        String[] name = {"McDonald's", "Bombay", "Ching Palace", "KFC"};

        String[] info = {"59.00 SEK i avgift.  Leveranstid: 20-30 min", "79.00 SEK i avgift.  Leveranstid: 40-50 min", "49.00 SEK i avgift.  Leveranstid: 30-40 min",
                "39.00 SEK i avgift.  Leveranstid: 20-30 min"};

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

                Intent i = new Intent(MainActivity.this,MainActivity.class); //ändra till Vincents class
                i.putExtra("name",name[position]);
                i.putExtra("info",info[position]);
                i.putExtra("imageid",imageId[position]);
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
                        startActivity(new Intent(getApplicationContext(), Basket.class));
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

    private void init(){
        //firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        //layout

    }

    @Override
    protected void onStart() {
        super.onStart();

        // IF USER IS NOT SIGNED IN, SEND TO LOGIN, IF NOT STAY ON MAINACTIVITY

        if(currentUser != null){
            // user signed in - do nothing
            System.out.println("dett a då?");
            System.out.println(currentUser.getEmail());
        } else {
            // user not signed in, ask them to sign in
            System.out.println("händer detta?");
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

}
