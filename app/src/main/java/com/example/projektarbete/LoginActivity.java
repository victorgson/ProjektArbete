package com.example.projektarbete;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract;
import com.firebase.ui.auth.IdpResponse;
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private static final int RC_SIGN_IN = 9001;
    private static final String TAG = "LoginActivity";
    FirebaseAuth mAuth;
    TextView signUpText, resturantSignUpText;
    Button signInBtn;
    TextInputLayout email, password;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

    }

    private void init(){
        // firebase
        mAuth = FirebaseAuth.getInstance();

        // layout
        email = (TextInputLayout) findViewById(R.id.emailSignIn);
        password = (TextInputLayout) findViewById(R.id.passwordSignIn);
        signInBtn = (Button)findViewById(R.id.signInButton);
        signInBtn.setOnClickListener(signInEmailListener);
        signUpText = (TextView)findViewById(R.id.textView);
        resturantSignUpText = (TextView)findViewById(R.id.resturantText);
        resturantSignUpText.setOnClickListener(resturantTextListener);
        signUpText.setOnClickListener(signUpTextListener);

        getSupportActionBar().hide();

    }

    // Sign in with email method
    private void signInEmail(String email, String password) {

        if(email.length() > 0 && password.length() > 0) {

            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information
                                Log.d(TAG, "signInWithEmail:success");
                                FirebaseUser user = mAuth.getCurrentUser();
                                startActivity(new Intent(getBaseContext(), MainActivity.class));
                            } else {
                                // If sign in fails, display a message to the user.
                                Log.w(TAG, "signInWithEmail:failure", task.getException());
                                Toast.makeText(LoginActivity.this, "Authentication failed.",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }
                        }
                    });
        } else {
            Toast.makeText(LoginActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
        }
    }


    // LISTENERS

    // Listens to sign up text
    View.OnClickListener signUpTextListener = new View.OnClickListener() {
        @Override
        // Runs once the sign up text is clicked
        public void onClick(View view) {
                    startActivity(new Intent(getBaseContext(), SignUpActivity.class));
            }
        };

    // Listens to resturant signup text
    View.OnClickListener resturantTextListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            startActivity(new Intent(getBaseContext(), ResurantSignUpActivity.class));
        }

    };

    // Listens to sign in button
    View.OnClickListener signInEmailListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String emailString = email.getEditText().getText().toString().trim();
            String passwordString = password.getEditText().getText().toString().trim();
            signInEmail(emailString, passwordString);


        }
    };



}


