package com.example.projektarbete;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
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

public class BuyActivity extends AppCompatActivity {


    private DatabaseReference mDatabase;
    private DatabaseReference ref;
    private FirebaseAuth mAuth;
    String userID;
    String orderAmount = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        getSupportActionBar().hide();
        init();


        TextView name = (TextView) findViewById(R.id.txtName);
        TextView info = (TextView) findViewById(R.id.txtInfo);
        TextView price = (TextView) findViewById(R.id.txtPrice);
        TextView buyBtn = (TextView) findViewById(R.id.buyBtn);

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
                orderAmount += 1;
                writeNewOrder(getIntent().getStringExtra("Name"), getIntent().getIntExtra("DishPrice", 0), "1");
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

    public void writeNewOrder(String dishName, int dishPrice, String uid){
        // Creates new object of user
        Receipt r1 = new Receipt(dishName, dishPrice, uid);
        try {
            mDatabase.child("users").child(userID).child("orders").push().setValue(r1);
        } catch (Exception e) {
            System.out.println(e);
        }

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
                    System.out.println(ds.getValue());
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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        ref = mDatabase.child("orders");
        mAuth = FirebaseAuth.getInstance();
        userID = mAuth.getUid();
        addReceiptEventListener(ref);

    }
}