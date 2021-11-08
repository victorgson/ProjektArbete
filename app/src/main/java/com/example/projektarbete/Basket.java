package com.example.projektarbete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class Basket extends AppCompatActivity {

    static List<Dishes> basket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

      /*  String name=getIntent().getStringExtra("CartN");
        String info=getIntent().getStringExtra("CartI");
        String price=getIntent().getStringExtra("CartP");



        basket = new ArrayList<>();
        ListView list = (ListView)findViewById(R.id.cart);

        basket.add(new Dishes("bajs","bajs","125 :-",125));
        CustomAdapter adapter = new CustomAdapter(this,R.layout.list_item, basket);
        list.setAdapter(adapter);
        adapter.add(new Dishes(name,info,price));











*/


        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.basket);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.basket:

                        return true;

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), Order.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), HannesClass.class));
                        overridePendingTransition(0,0);
                        return true;



                }




                return false;
            }
        });
    }
}