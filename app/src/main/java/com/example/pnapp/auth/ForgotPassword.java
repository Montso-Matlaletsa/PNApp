package com.example.pnapp.auth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pnapp.MainActivity;
import com.example.pnapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {

    ImageButton backToLogin;
    TextInputEditText email;
    MaterialButton forgot;
    private FirebaseAuth auth;
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        backToLogin = findViewById(R.id.backToLogin);
        email = findViewById(R.id.SendToEmail);
        forgot = findViewById(R.id.forgotBtn);
        auth = FirebaseAuth.getInstance();


        forgot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Email = email.getText().toString().trim();

                if(TextUtils.isEmpty(Email)){
                    email.setError("Please enter email address!");
                    return;
                }

                auth.sendPasswordResetEmail(Email)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                email.setText("");
                                Toast.makeText(getApplicationContext(), "Check your Email for Password reset", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        backToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ForgotPassword.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}