package com.example.pnapp.auth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.pnapp.LoggedIn.ui.Home;
import com.example.pnapp.Models.User;
import com.example.pnapp.Models.Weight;
import com.example.pnapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class CreateProfile extends AppCompatActivity  implements  View.OnClickListener{

    AutoCompleteTextView gender, goal;
    LinearLayout personal, health;
    MaterialButton pBtn, healthBtn;
    TextInputEditText name, lastname, height, weight;
    String Name, LastName, Goal, Gender;
    int Height;
    Float Weight;

    private FirebaseAuth auth;
    private DatabaseReference databaseReference;




    String[] list = {"Male", "Female", "Not Specific"};
    String[] list2 = {"Weight Loss", "Bodybuilding", "Lean Fit", "body Maintain"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);

        gender = findViewById(R.id.gender);
        goal = findViewById(R.id.goal);
        health = findViewById(R.id.health);
        personal = findViewById(R.id.personal);
        pBtn = findViewById(R.id.pBtn);
        healthBtn = findViewById(R.id.healthBtn);

        name = findViewById(R.id.name);
        lastname = findViewById(R.id.lastname);
        height = findViewById(R.id.height);
        weight = findViewById(R.id.weight);

        auth = FirebaseAuth.getInstance();

        if(auth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(), signUp.class));
        }

        databaseReference = FirebaseDatabase.getInstance().getReference();

        FirebaseUser firebaseUser = auth.getCurrentUser();

        pBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Name = name.getText().toString().trim();
                LastName = lastname.getText().toString().trim();
                Gender = gender.getText().toString().trim();

                if(TextUtils.isEmpty(Name)){
                    name.setError("Enter Name");
                    return;
                }

                if(TextUtils.isEmpty(LastName)){
                    name.setError("Enter Last Name");
                    return;
                }

                if(TextUtils.isEmpty(Gender)){
                    name.setError("Select Gender");
                    return;
                }

                personal.setVisibility(View.GONE);
                health.setVisibility(View.VISIBLE);

            }
        });

        healthBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userInformation();

                startActivity(new Intent(CreateProfile.this, Home.class));
                finish();
            }
        });



        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,list);

        ArrayAdapter adapter2 = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,list2);

        gender.setAdapter(adapter);
        goal.setAdapter(adapter2);
    }

    @Override
    public void onClick(View view) {

    }

    private void userInformation(){

        Height = Integer.parseInt(height.getText().toString().trim());
        Weight = Float.parseFloat(weight.getText().toString().trim());
        Goal = goal.getText().toString().trim();

        Random rand = new Random();
        int r1 = rand.nextInt(10);
        int r2 = rand.nextInt(10);
        int r3 = rand.nextInt(10);
        int r4 = rand.nextInt(10);

        String id = "wei"+r1+r2+r3+r4;

        com.example.pnapp.Models.Weight w = new Weight(id, getDate()  , Weight, 0.0, 0.0);
        List<Weight> weights = new ArrayList<>();
        weights.add(w);

        User user = new User(Name, LastName,Gender,Height, weights,Goal);

        FirebaseUser firebaseUser = auth.getCurrentUser();
        databaseReference.child("user").child(firebaseUser.getUid()).setValue(user);
        Toast.makeText(getApplicationContext(), "Account Successfully Created", Toast.LENGTH_LONG).show();

    }

    private String getDate(){
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("MMM");

        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(c.get(Calendar.YEAR));

        return day+"-"+f.format(new Date());
    }
}