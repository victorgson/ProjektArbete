package com.example.projektarbete;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projektarbete.dbclassstructure.Foods;
import com.example.projektarbete.dbclassstructure.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    TextInputLayout email, password, password2, fName, lName;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_sign_up);
        init();


        if (Build.VERSION.SDK_INT >= 21) {
            Window window = this.getWindow();
            ((Window) window).addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.support_bar));
        }


    }




    private void init(){
        // firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance("https://projektarbete-b5f1f-default-rtdb.europe-west1.firebasedatabase.app").getReference();

        // layout
        TextView signUpBtn = (TextView) findViewById(R.id.signUpButton);
        email = (TextInputLayout) findViewById(R.id.editTextEmail);
        password = (TextInputLayout) findViewById(R.id.editTextPassword);
        password2 = (TextInputLayout) findViewById(R.id.editTextPassword2);
        fName = (TextInputLayout)findViewById(R.id.firstName);
        lName = (TextInputLayout)findViewById(R.id.lastName);
        signUpBtn.setOnClickListener(onClickListener);
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
                            writeNewUser(email,fName, lName, mAuth.getUid(), admin);
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


    public void writeNewUser(String email, String fName, String lName, String uid, Boolean admin){
        // Creates new object of user
        User user = new User(email, fName, lName, uid, admin);
        try {
      /*      // writes user object sorted after uid under users in firebase
            users:
                uid:
                    user:*/
            mDatabase.child("users").child(uid).setValue(user);
        } catch (Exception e) {
            System.out.println(e);
        }

    }


    // LISTENER

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String firstNameString = fName.getEditText().getText().toString();
            String lastNameString = lName.getEditText().getText().toString();
            String emailString = String.valueOf(email.getEditText().getText().toString());
            String passwordString = String.valueOf(password.getEditText().getText().toString());
            createAccount(emailString, passwordString, firstNameString, lastNameString, false);


        }
    };

}
