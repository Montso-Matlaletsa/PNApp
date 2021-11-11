package com.example.pnapp.LoggedIn.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pnapp.Models.Weight;
import com.example.pnapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class UpdateStartWeight extends AppCompatActivity {

    TextInputEditText NewWeight;
    FloatingActionButton addWeight;
    Double weight;
    ImageButton updateWeight;

    DatabaseReference databaseReference;
    FirebaseAuth auth;
    FirebaseDatabase firebaseDatabase;
    String[] req = new String[2];
    com.example.pnapp.Models.Weight w;
    List<Weight> list;
    String action = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_start_weight);

        NewWeight = findViewById(R.id.NewStartWeight);
        addWeight = findViewById(R.id.addStartWeight);
        updateWeight = findViewById(R.id.updateStartWeight);
        auth = FirebaseAuth.getInstance();

        req = getIntent().getStringArrayExtra("req");


        weight = Double.parseDouble(req[1]);
        DecimalFormat df = new DecimalFormat();
        df.setMinimumFractionDigits(2);

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user");

        list = new ArrayList<>();

        databaseReference.child(auth.getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Iterable<DataSnapshot> we = snapshot.child("weight").getChildren();
                Weight wei;
                for (DataSnapshot s: we) {
                    String id = s.child("id").getValue(String.class);
                    String date = s.child("date").getValue(String.class);
                    Float weight = s.child("weight").getValue(Float.class);
                    Double start = s.child("start").getValue(Double.class);
                    Double goal = s.child("goal").getValue(Double.class);
                    wei = new Weight(id, date, weight, start, goal);
                    list.add(wei);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        w = list.get(list.size() -1);


        updateWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    w.setStart(Double.parseDouble(NewWeight.getText().toString().trim()));

                    databaseReference.child(auth.getUid())
                            .child("weight").child(getDate()).setValue(w);

                    Toast.makeText(UpdateStartWeight.this, "Weight is updated", Toast.LENGTH_SHORT).show();





            }

        });



        NewWeight.setText(req[1]);


        addWeight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                weight = weight+0.01;
                NewWeight.setText(String.valueOf(df.format(weight)));
            }
        });
    }


    private String getDate(){
        final Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("MMM");

        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
        String year = String.valueOf(c.get(Calendar.YEAR));

        return day+"-"+f.format(new Date());
    }
}