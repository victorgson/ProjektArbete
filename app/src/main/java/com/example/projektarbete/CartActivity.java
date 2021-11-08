package com.example.projektarbete;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.projektarbete.dbclassstructure.RestaurantsDB;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;


public class CartActivity extends AppCompatActivity {


    FirebaseAuth mAuth;
    FirebaseUser currentUser;
    DatabaseReference mDatabase;
    DatabaseReference ref;
    CustomAdapter adapter;
    int totalAmount = 0;
    int deliveryFee = 49;

    TextView totalAmountText;
    TextView deliveryFeeText;
    TextView amountDishes;
    TextView orderButton;

    static List<Dishes> listDish;

    public void orderClick (View view){

        Log.i("info", "Button clicked!");
        startActivity(new Intent(this, TimerActivity.class));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        init();

        listDish = new ArrayList<>();
        ListView listView = (ListView) findViewById(R.id.cart);

        adapter = new CustomAdapter(this, R.layout.list_item, listDish);
        listView.setAdapter(adapter);
        //adapter.add(new Dishes("Asd", "asd", "100",100));

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();}




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

    private void addReceiptEventListener(DatabaseReference mPostReference) {
        // [START post_value_event_listener]
        ValueEventListener postListener = new ValueEventListener() {
            private static final String TAG = "TEST";

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI
                //Post post = dataSnapshot.getValue(Post.class);

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Receipt r = new Receipt();
                    totalAmount += (long) ds.child("dishPrice").getValue();
                    //System.out.println(ds.getValue());
                    adapter.add(new Dishes(ds.child("dishName").getValue().toString(), "asd", ds.child("dishPrice").getValue().toString(),100));
                    //System.out.println(ds.child("dishName").getValue());

                    totalAmountText.setText(String.valueOf(totalAmount + deliveryFee));
                    amountDishes.setText(String.valueOf(totalAmount));
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



    void init(){

        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();
        ref = mDatabase.child("users").child(mAuth.getUid()).child("orders");
        currentUser = mAuth.getCurrentUser();
        addReceiptEventListener(ref);

        totalAmountText = (TextView)findViewById(R.id.totalAmountText);
        deliveryFeeText = (TextView)findViewById(R.id.deliveryFeeText);
        amountDishes = (TextView)findViewById(R.id.amountDishesText);

        deliveryFeeText.setText(String.valueOf(deliveryFee));


    }


}