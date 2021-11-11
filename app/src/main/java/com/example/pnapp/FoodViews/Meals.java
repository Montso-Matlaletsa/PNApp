package com.example.pnapp.FoodViews;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.hardware.SensorEventListener;
import android.os.Bundle;

import com.example.pnapp.Models.Meal;
import com.example.pnapp.R;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class Meals extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerView;
    mealDisplay adapter;
    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meals);

        auth = FirebaseAuth.getInstance();
        db = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = db.getReference("Meals");

    recyclerView = findViewById(R.id.recyclerView);

    String type = getIntent().getStringExtra("Type");



        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("My Meal");
        toolbar.setSubtitle(type);

        if(getSupportActionBar() != null){
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }



        databaseReference.child(auth.getUid()).
                child(type)
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                List<Meal> list = new ArrayList<>();
                for(DataSnapshot ds : snapshot.getChildren()){
                    Meal meal = new Meal();
                    String name = ds.child("name").getValue(String.class);
                    Long id = ds.child("id").getValue(Long.class);
                    String mealType = ds.child("mealType").getValue(String.class);
                    String type = ds.child("type").getValue(String.class);
                    String userId = ds.child("userId").getValue(String.class);
                    String description = ds.child("description").getValue(String.class);

                    meal.setName(name);
                    meal.setUserId(userId);
                    meal.setId(id);
                    meal.setMealType(mealType);
                    meal.setType(type);
                    meal.setDescription(description);
                    list.add(meal);
                }

                adapter = new mealDisplay(list, getApplicationContext());
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(Meals.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
    private List<Meal> getData()
    {
        List<Meal> list = new ArrayList<>();
        Meal meal1 = new Meal();
        meal1.setName("Fish And Chips");
        meal1.setMealType("Dinner");

        Meal meal2 = new Meal();
        meal2.setName("Makoenya");
        meal2.setMealType("Lunch");
        list.add(meal1);
        list.add(meal2);

        return list;
    }


}