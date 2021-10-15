package com.example.pnapp.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pnapp.MainActivity;
import com.example.pnapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class signUp extends AppCompatActivity {

    ImageButton backBtn;
    MaterialButton register;
    private FirebaseAuth auth;
    TextInputEditText regEmail, regPassword, username, ComfirmPassword;

    String password;
    String email;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(signUp.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        backBtn = findViewById(R.id.backBtn);
        register = findViewById(R.id.Register);

        regPassword = findViewById(R.id.RegPassword);
        regEmail = findViewById(R.id.RegEmail);
        username = findViewById(R.id.userName);
        ComfirmPassword = findViewById(R.id.ConfirmPassword);

        auth = FirebaseAuth.getInstance();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                email = regEmail.getText().toString().trim();
                String Username = username.getText().toString().trim();
                password= regPassword.getText().toString().trim();
                String comfirmPassword = ComfirmPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(), "Enter email address!", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!password.equals(comfirmPassword)){
                    Toast.makeText(getApplicationContext(), "Passwords Should match", Toast.LENGTH_SHORT).show();
                    return;
                }


                if (password.length() < 6) {
                    Toast.makeText(getApplicationContext(), "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show();
                    return;
                }

                //create User

                auth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(signUp.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(signUp.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();

                                if(!task.isSuccessful()){
                                    Toast.makeText(signUp.this, "Authentication failed." + task.getException(),
                                            Toast.LENGTH_SHORT).show();
                                }else{

                                    Toast.makeText(signUp.this, "Account Created successfully",
                                            Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signUp.this, MainActivity.class));
                                    finish();
                                }
                            }
                        });

            }
        });

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(signUp.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }



}