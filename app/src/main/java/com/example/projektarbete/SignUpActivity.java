package com.example.projektarbete;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    EditText email;
    EditText password;
    EditText password2;
    private boolean identicalPasswords;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        Button signUpBtn = (Button)findViewById(R.id.button3);
         email = (EditText)findViewById(R.id.editTextEmail);
         password = (EditText)findViewById(R.id.editTextPassword);
         password2 = (EditText)findViewById(R.id.editTextPassword2);
            signUpBtn.setOnClickListener(onClickListener);

            password.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(password.getText().length() < 8){
                        Toast.makeText(SignUpActivity.this, "Password needs to be minimum 8 letters.", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        password2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(password2.getText() != password.getText()){
                    System.out.println("Passwords need to be identical!");
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }


    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            String emailString;
            String passwordString;
            emailString = String.valueOf(email.getText());
            passwordString = String.valueOf(password.getText());
            createAccount(emailString, passwordString);


        }
    };

    private void createAccount(String email, String password) {

        if(email.length() > 0 && password.length() >= 8 ) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            writeNewUser(mAuth.getUid(), "nametest", "testemail");
                            Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }
                    }
                });

        } else {
            Toast.makeText(SignUpActivity.this, "Please enter a email and password", Toast.LENGTH_SHORT).show();
        }
    }


    public void writeNewUser(String username, String email, String uid){
        User user = new User(username,email,uid);
        try {
            mDatabase.child("users").setValue(user);
        } catch (Exception e) {
            System.out.println(e);
        }


    }


}