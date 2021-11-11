package com.example.pnapp.LoggedIn.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pnapp.MainActivity;
import com.example.pnapp.Models.Weight;
import com.example.pnapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {

    Button logOutg;
    FirebaseAuth auth;
    TextView user_name, user_email, userGender,
            userHeight, userGoal, userWeight, userName, userEmail;
    private FirebaseDatabase firebaseDatabase;


    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account, container, false);

        logOutg = view.findViewById(R.id.logoutBtn);
        user_email = view.findViewById(R.id.user_email);
        user_name = view.findViewById(R.id.user_name);

        userGender = view.findViewById(R.id.userGender);
        userGoal = view.findViewById(R.id.userGoal);
        userHeight = view.findViewById(R.id.userheight);
        userWeight = view.findViewById(R.id.userWeight);
        userName = view.findViewById(R.id.userName);
        userEmail = view.findViewById(R.id.userEmail);

        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user");

        databaseReference.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String name = snapshot.child("name").getValue(String.class);
                String lastname = snapshot.child("lastName").getValue(String.class);
                int height = snapshot.child("height").getValue(Integer.class);
                String gender = snapshot.child("gender").getValue(String.class);
                String goal = snapshot.child("goal").getValue(String.class);
                Iterable<DataSnapshot> we = snapshot.child("weight").getChildren();


                com.example.pnapp.Models.Weight w;
                List<com.example.pnapp.Models.Weight> list = new ArrayList<>();

                for (DataSnapshot s: we) {
                    String id = s.child("id").getValue(String.class);
                    String date = s.child("date").getValue(String.class);
                    Float weight = s.child("weight").getValue(Float.class);
                    w = new com.example.pnapp.Models.Weight(id, date, weight);
                    list.add(w);
                }
                int currentWeight = list.size() - 1;

                Weight a = list.get(currentWeight);

                user_name.setText(name+" "+lastname);
                user_email.setText(auth.getCurrentUser().getEmail());

                userName.setText(name+" "+lastname);
                userEmail.setText(auth.getCurrentUser().getEmail());

                userHeight.setText(String.valueOf(height/100));
                userWeight.setText(String.valueOf(a.getWeight())+"Kg");

                userGoal.setText(goal);
                userGender.setText(gender);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        logOutg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                auth.signOut();
                startActivity(new Intent(getContext().getApplicationContext(), MainActivity.class));

            }
        });

        return view;
    }
}