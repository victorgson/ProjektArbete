package com.example.projektarbete;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth mAuth;
    TextView test;
    FirebaseUser currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    private void init(){
        //firebase
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        //layout
        test = (TextView)findViewById(R.id.HelloWorld);
        Button signOutBtn = (Button)findViewById(R.id.signOutBtn);
        signOutBtn.setOnClickListener(clickListener);

        getSupportActionBar().hide();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // IF USER IS NOT SIGNED IN, SEND TO LOGIN, IF NOT STAY ON MAINACTIVITY

        if(currentUser != null){
            // user signed in
            test.setText(mAuth.getCurrentUser().getEmail());
        } else {
            // user not signed in
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }


    // LISTENER
    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getBaseContext(), LoginActivity.class);
            startActivity(intent);
        }
    };
}