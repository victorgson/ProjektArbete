package com.example.projektarbete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.projektarbete.dbclassstructure.Restaurants;
import com.example.projektarbete.dbclassstructure.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RestaurantSignUpActivity extends AppCompatActivity {

    private static final String TAG = "ResturantSignUp";
    //firebase
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;

    TextInputLayout email, password, password2, restaurantName, restaurantDesc;


    Button signUpBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resurant_sign_up);
        init();


        signUpBtn.setOnClickListener(listener);

    }


    public void init(){
        //firebase stuff
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();


        // Layout
        signUpBtn = (Button)findViewById(R.id.resturantSignUpButton);
        email = (TextInputLayout) findViewById(R.id.resturantEmailTextInput);
        password = (TextInputLayout) findViewById(R.id.resturantPasswordTextInput);
        password2 = (TextInputLayout) findViewById(R.id.resturantPassword2TextInput);
        restaurantName = (TextInputLayout) findViewById(R.id.resturantNameTextInputLayout);
        restaurantDesc = (TextInputLayout) findViewById(R.id.resturantDescTextInputLayout);

    }

    private void createAccount(String email, String password, String fName, String lName, Boolean admin) {

        if(email.length() > 0 && password.length() >= 8 ) {
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "createUserWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                writeNewUser(email, fName, lName, mAuth.getUid(), admin);
                                Intent intent = new Intent(RestaurantSignUpActivity.this, LoginActivity.class);
                                startActivity(intent);
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                Toast.makeText(RestaurantSignUpActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });

        } else {
            Toast.makeText(RestaurantSignUpActivity.this, "Please enter a email and password", Toast.LENGTH_SHORT).show();
        }
    }


    public void writeNewUser(String email, String fName, String lName, String uid, Boolean admin){
        User user = new User(email, fName, lName, uid, admin);
        try {
            mDatabase.child("users").child(uid).setValue(user);
        } catch (Exception e) {
            System.out.println(e);
        }


    }
    public void writeNewRestaurant(String restaurantName, String resturantDesc){
        Restaurants restaurant = new Restaurants(restaurantName, resturantDesc, 0, "empty");
        try {
            mDatabase.child("restaurant").child(restaurantName).setValue(restaurant);
        } catch (Exception e) {
            System.out.println(e);
        }


    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String emailString = email.getEditText().getText().toString();
            String passwordString = password.getEditText().getText().toString();
            String resturantNameString = restaurantName.getEditText().getText().toString();
            String resturantDescString = restaurantDesc.getEditText().getText().toString();

            createAccount(emailString, passwordString,"resturant","1",true);
            writeNewRestaurant(resturantNameString, resturantDescString);
        }
    };




}