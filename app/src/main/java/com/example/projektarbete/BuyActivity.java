package com.example.projektarbete;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class BuyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        getSupportActionBar().hide();


        TextView name = (TextView) findViewById(R.id.txtName);
        TextView info = (TextView) findViewById(R.id.txtInfo);
        TextView price = (TextView) findViewById(R.id.txtPrice);
        Button buyBtn = (Button) findViewById(R.id.buyBtn);

        name.setText(getIntent().getStringExtra("Name"));
        info.setText(getIntent().getStringExtra("Info"));
        price.setText(getIntent().getStringExtra("Price"));

        buyBtn.setText("Köp");
        buyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("Rest1", getIntent().getStringExtra("Rest1"));
               /* intent.putExtra("CartN",getIntent().getStringExtra("Name"));
                intent.putExtra("CartI",getIntent().getStringExtra("Info"));
                intent.putExtra("CartP", getIntent().getStringExtra("Price"));
                intent.putExtra("CartRP", getIntent().getStringExtra("DishPrice"));*/
                intent.setClass(BuyActivity.this, RestMenu.class);

                startActivity(intent);
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
}