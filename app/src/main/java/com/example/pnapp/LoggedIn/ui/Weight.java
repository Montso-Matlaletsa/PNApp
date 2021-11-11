package com.example.pnapp.LoggedIn.ui;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pnapp.R;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.card.MaterialCardView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class Weight extends Fragment {


    public Weight() {
        // Required empty public constructor
    }

    TextView today;
    GraphView linegraph;
    LineChart mpGraph;

    LineData lineData;
    LineDataSet lineDataSet;
    ArrayList lineEntries;

    DataPoint dataPoint;
    int point = 0;



    FirebaseAuth auth;
    TextView currentWeight, startWeight;
    MaterialCardView current, start;
    Float weight;
    Double startW;
    DatabaseReference databaseReference;
    Entry entry;
    ArrayList arrayList = new ArrayList<>();

    private LineGraphSeries<DataPoint> series;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_weight, container, false);

        today = view.findViewById(R.id.today);
        linegraph = view.findViewById(R.id.line_graph);
        currentWeight = view.findViewById(R.id.currentWeight);
        current = view.findViewById(R.id.current);
        mpGraph = view.findViewById(R.id.mpGraph);
        start = view.findViewById(R.id.start);
        startWeight = view.findViewById(R.id.startWeight);

        series = new LineGraphSeries<>();
        linegraph.addSeries(series);

        linegraph.setTitle("Weight");

        auth = FirebaseAuth.getInstance();

        final Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("MMM");

        String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));

        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference databaseReference = firebaseDatabase.getReference("user");

        databaseReference.child(auth.getUid())
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        Iterable<DataSnapshot> we = snapshot.child("weight").getChildren();

                        com.example.pnapp.Models.Weight w;
                        List<com.example.pnapp.Models.Weight> list = new ArrayList<>();
                        com.example.pnapp.Models.DataPoint d;

                        int p = 0;

                        for (DataSnapshot s: we) {
                            String id = s.child("id").getValue(String.class);
                            String date = s.child("date").getValue(String.class);
                            Float weight = s.child("weight").getValue(Float.class);
                            Double start = s.child("start").getValue(Double.class);
                            Double goal = s.child("goal").getValue(Double.class);
                            w = new com.example.pnapp.Models.Weight(id, date, weight, start, goal);
                            list.add(w);
                            d = new com.example.pnapp.Models.DataPoint(p , w.getWeight());

                            series.appendData(new DataPoint(p, weight), true, 200);
                            p = p +1;

                        }

                        int currentW = list.size() - 1;


                        com.example.pnapp.Models.Weight a = list.get(currentW);
                        currentWeight.setText(String.valueOf(a.getWeight())+ "Kg");
                        startWeight.setText(String.valueOf(a.getStart())+"Kg");
                        weight = a.getWeight();
                        startW = a.getStart();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        today.setText("Today: "+day+"-"+f.format(new Date()));






        current.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String[] request = {"current", String.valueOf(weight) };
                Intent intent = new Intent(getContext().getApplicationContext(), WeightUpdate.class);
                intent.putExtra("req", request);
                startActivity(intent);
            }
        });

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String[] request = {"start", String.valueOf(startW) };
                Intent intent = new Intent(getContext().getApplicationContext(), WeightUpdate.class);
                intent.putExtra("req", request);
                startActivity(intent);
            }
        });



        return view;
    }



}