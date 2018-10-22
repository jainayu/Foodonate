package com.example.root.foodonate;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
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

public class NewAccountActivity extends AppCompatActivity {

    // Variables to access elements
    TextView tandcTV;
    Button registerBtn;
    EditText emailField, passwordField, confirmPasswordField;
    EditText nameField, addressField, callPhoneField, whatsappPhoneField;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_account);

        mAuth = FirebaseAuth.getInstance();

        emailField = findViewById(R.id.emailRegisEditText);
        passwordField = findViewById(R.id.passwordRegisEditText);
        confirmPasswordField = findViewById(R.id.confirmRegisEditText);

        nameField = findViewById(R.id.nameRegisEditText);
        addressField = findViewById(R.id.addressRegisEditText);
        callPhoneField = findViewById(R.id.callPhoneRegisEditText);
        whatsappPhoneField = findViewById(R.id.whatsappPhoneEditText);

        tandcTV = findViewById(R.id.tandcTextView);
        tandcTV.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String loremIpsum = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam";
                // change this to fragments instead of dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(NewAccountActivity.this);
                builder.setTitle("My Title");
                builder.setMessage(loremIpsum);

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // Do something
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
                builder.show();
            }
        });

        registerBtn = findViewById(R.id.registerButton);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailField.getText().toString(), password = passwordField.getText().toString(), confirmPassword = confirmPasswordField.getText().toString();

                if(TextUtils.isEmpty(email) || TextUtils.isEmpty(password)){
                    Toast.makeText(NewAccountActivity.this, "Email and password must be non-empty", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(confirmPassword)){
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(NewAccountActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        //Log.d(TAG, "createUserWithEmail:success");

                                        Toast.makeText(NewAccountActivity.this, "Account created successfully", Toast.LENGTH_SHORT).show();
                                        startActivity(new Intent(NewAccountActivity.this, MainActivity.class));
                                    } else {
                                        // If sign in fails, display a message to the user.
                                        //Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(NewAccountActivity.this, "Account creation failed", Toast.LENGTH_SHORT).show();
                                    }

                                    // ...
                                }
                            });
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password and confirm password differ", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
