package com.example.projektarbete;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

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


    }
}