package com.example.projektarbete;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class RestMenu extends AppCompatActivity {

    static List<Dishes> listDish;
    static ImageView loga;
    static TextView rtInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest_menu);
        getSupportActionBar().hide();

        listDish = new ArrayList<>();
        Dishes dish = new Dishes();
        RestLoga rLoga = new RestLoga();
        RestInfo rInfo = new RestInfo();


        loga = (ImageView) findViewById(R.id.restBild);
        rtInfo = (TextView) findViewById(R.id.restInfo);
        ListView listView = (ListView) findViewById(R.id.menulista);


        if (getIntent().getStringExtra("Rest1").equals("thai")) {
            Dishes.Kt();
            rLoga.setLogaKt(loga);
            rInfo.setRTKt(rtInfo);
        } else if (getIntent().getStringExtra("Rest1").equals("max")) {
            Dishes.Max();
            rLoga.setLogaMacD(loga);
            rInfo.setRTMax(rtInfo);

        } else if (getIntent().getStringExtra("Rest1").equals("bombay")) {
            Dishes.Bombay();
            rLoga.setLogaBombay(loga);
            rInfo.setRTBombay(rtInfo);
        } else if (getIntent().getStringExtra("Rest1").equals("kfc")) {
            Dishes.Kfc();
            rLoga.setLogaKfc(loga);
            rInfo.setRTKfc(rtInfo);
        }


        CustomAdapter adapter = new CustomAdapter(this, R.layout.list_item, listDish);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                Intent intent = new Intent();
                intent.putExtra("Name", listDish.get(position).getName());
                intent.putExtra("Info", listDish.get(position).getInfo());
                intent.putExtra("Price", listDish.get(position).getPrice());
                intent.putExtra("DishPrice", listDish.get(position).getPrices());
                intent.putExtra("Rest1", getIntent().getStringExtra("Rest1"));


                intent.setClass(RestMenu.this, BuyActivity.class);
                startActivity(intent);

            }
        });

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.basket:
                        startActivity(new Intent(getApplicationContext(), CartActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext(), Profile.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.order:
                        startActivity(new Intent(getApplicationContext(), TimerActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;


                }


                return false;
            }
        });
    }


}