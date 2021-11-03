package com.example.projektarbete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;


import com.example.projektarbete.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
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

    }
}
