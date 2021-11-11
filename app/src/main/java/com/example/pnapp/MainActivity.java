package com.example.pnapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import com.example.pnapp.LoggedIn.ui.Home;
import com.example.pnapp.auth.ForgotPassword;
import com.example.pnapp.auth.signUp;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    MaterialButton registerBtn, loginBtn;
    TextView forgotPassword;
    TextInputEditText email, password;
    String Email, Password;
    private FirebaseAuth auth;

    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        auth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

        if(auth.getCurrentUser() != null){
            startActivity(new Intent(MainActivity.this, Home.class));
            finish();
        }

        progressBar = findViewById(R.id.progressBar);
        registerBtn = findViewById(R.id.registerBtn);
        loginBtn = findViewById(R.id.loginBtn);
        forgotPassword = findViewById(R.id.forgotPassword);

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ForgotPassword.class);
                startActivity(intent);
                finish();
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Email = email.getText().toString().trim();
                Password = password.getText().toString().trim();

                progressBar.setVisibility(View.VISIBLE);
                auth.signInWithEmailAndPassword(Email, Password)
                        .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if(task.isSuccessful()){

                                    progressBar.setVisibility(View.GONE);

                                    FirebaseUser user = auth.getCurrentUser();

                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    intent.putExtra("user", user);
                                    startActivity(intent);
                                    finish();
                                }else{

                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, signUp.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = auth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }
}