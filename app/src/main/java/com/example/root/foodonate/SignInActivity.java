package com.example.root.foodonate;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class SignInActivity extends AppCompatActivity {

    // For using firebase authentication
    private FirebaseAuth mAuth;
    // For accessing layout elements
    Button signInBtn, newAccountBtn;
    TextView forgotPasswordTV;
    EditText emailField, passwordField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        // Initialize variables declared above
        mAuth = FirebaseAuth.getInstance();
        signInBtn = findViewById(R.id.signInButton);
        newAccountBtn = findViewById(R.id.createNewAccountButton);
        forgotPasswordTV = findViewById(R.id.forgotPasswordTitleTextView);
        emailField = findViewById(R.id.emailSignIneditText);
        passwordField = findViewById(R.id.passwordSignIneditText);
    }

    @Override
    public void onStart(){
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void signIn(String email, String password) {
        if (!validateForm(emailField, passwordField)) {
            return;
        }

        // progress dialog will be implemented later
        //showProgressDialog();

        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(SignInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // progress dialog will be implemented later
                        // hideProgressDialog();
                    }
                });
        // [END sign_in_with_email]
    }

    public static boolean validateForm(EditText emailField, EditText passwordField) {
        boolean valid = true;

        String email = emailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            emailField.setError("Required.");
            valid = false;
        } else {
            emailField.setError(null);
        }

        String password = passwordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            passwordField.setError("Required.");
            valid = false;
        } else {
            passwordField.setError(null);
        }

        return valid;
    }

    private void updateUI(FirebaseUser user) {
        if (user != null) {
            startActivity(new Intent(SignInActivity.this, MainActivity.class));
        } else {
            signInBtn.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    //startActivity(new Intent(MainActivity.this, Home.class));
                    signIn(emailField.getText().toString(), passwordField.getText().toString());
                }
            });

            newAccountBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    startActivity(new Intent(SignInActivity.this, NewAccountActivity.class));
                }
            });


            forgotPasswordTV.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    startActivity(new Intent(SignInActivity.this, ForgotPasswordActivity.class));
                }
            });
        }
    }
}
