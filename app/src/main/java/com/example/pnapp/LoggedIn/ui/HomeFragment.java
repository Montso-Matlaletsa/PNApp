package com.example.pnapp.LoggedIn.ui;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pnapp.FoodViews.Meals;
import com.example.pnapp.MainActivity;
import com.example.pnapp.Models.Meal;
import com.example.pnapp.Models.User;
import com.example.pnapp.Models.Weight;
import com.example.pnapp.R;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;


public class HomeFragment extends Fragment {

    private FirebaseAuth firebaseAuth;
    TextView user, txtHeight, txtWeight, txtBMI;
    private FirebaseDatabase firebaseDatabase;
    MaterialCardView breakfast, lunch, dinner, snack;

    public HomeFragment() {
        // Required empty public constructor
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        user = view.findViewById(R.id.user);
        breakfast = view.findViewById(R.id.BreakFast);
        lunch = view.findViewById(R.id.lunch);
        dinner = view.findViewById(R.id.dinner);
        snack = view.findViewById(R.id.snack);

        txtBMI = view.findViewById(R.id.txtBMI);
        txtHeight = view.findViewById(R.id.txtHeight);
        txtWeight = view.findViewById(R.id.txtweight);
        FirebaseUser fbuser = firebaseAuth.getCurrentUser();
        user.setText(firebaseAuth.getUid());



        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user");
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);


        databaseReference.child(fbuser.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                    String name = snapshot.child("name").getValue(String.class);
                    String lastname = snapshot.child("lastName").getValue(String.class);
                    Double height = snapshot.child("height").getValue(Double.class);
                    String gender = snapshot.child("gender").getValue(String.class);
                    String goal = snapshot.child("goal").getValue(String.class);
                    Iterable<DataSnapshot> we = snapshot.child("weight").getChildren();

                    Weight w;
                    List<Weight> list = new ArrayList<>();

                for (DataSnapshot s: we) {
                    String id = s.child("id").getValue(String.class);
                    String date = s.child("date").getValue(String.class);
                    Float weight = s.child("weight").getValue(Float.class);
                    w = new Weight(id, date, weight);
                    list.add(w);
                }
                int currentWeight = list.size() - 1;

                Weight a = list.get(currentWeight);
                txtWeight.setText(String.valueOf(a.getWeight()+"Kg"));
                txtHeight.setText(String.valueOf(height/100)+"M");
                user.setText(name+" "+lastname);
                Double h = height/100;
                double bmi = (int) (a.getWeight()/(h * h));
                txtBMI.setText(String.valueOf(bmi));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(), Meals.class);
                intent.putExtra("Type", "Breakfast");
                startActivity(intent);

            }
        });


        lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(), Meals.class);
                intent.putExtra("Type", "Lunch");
                startActivity(intent);
            }
        });

        dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(), Meals.class);
                intent.putExtra("Type", "Dinner");
                startActivity(intent);
            }
        });

        snack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext().getApplicationContext(), Meals.class);
                intent.putExtra("Type", "Snack");
                startActivity(intent);
            }
        });



        return  view;
    }
}