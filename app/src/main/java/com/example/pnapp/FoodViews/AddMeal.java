package com.example.pnapp.FoodViews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pnapp.LoggedIn.ui.Home;
import com.example.pnapp.Models.Meal;
import com.example.pnapp.Models.MyFood;
import com.example.pnapp.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddMeal extends AppCompatActivity {

    String[] list = {"Breakfast", "Lunch", "Dinner", "Snack"};
    AutoCompleteTextView type;
    TextInputEditText foodName;
    ImageButton getBack;
    DatabaseReference database;
    MaterialButton btn;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        type = findViewById(R.id.FoodType);
        getBack = findViewById(R.id.getBack);
        foodName = findViewById(R.id.regFoodName);
        btn = findViewById(R.id.add);

        auth = FirebaseAuth.getInstance();

        MyFood myFood = (MyFood) getIntent().getSerializableExtra("food");

        foodName.setText(myFood.getName());


        database = FirebaseDatabase.getInstance().getReference();
        Meal meal = new Meal();
        meal.setName(myFood.getName());
        meal.setDescription(myFood.getDescription());
        meal.setId(myFood.getId());
        meal.setBrandName(myFood.getBrandName());
        meal.setType(myFood.getType());

        meal.setUserId(auth.getUid());



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                meal.setMealType(type.getText().toString().trim());
                database.child("Meals").child(meal.getUserId())
                        .child(meal.getMealType()).child(String.valueOf(meal.getId()))
                        .setValue(meal);

                foodName.setText("");

                Toast.makeText(AddMeal.this, "Food Added to your Plan", Toast.LENGTH_LONG).show();
            }
        });

        ArrayAdapter adapter = new
                ArrayAdapter(this,android.R.layout.simple_list_item_1,list);
        type.setAdapter(adapter);

        getBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AddMeal.this, Home.class));
                finish();
            }
        });
    }
}