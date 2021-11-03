package com.example.projektarbete;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class CartActivity extends AppCompatActivity {


    public void orderClick (View view){

        Log.i("info", "Button clicked!");


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }






    }
}