package com.example.pnapp.FoodViews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnapp.Models.Meal;
import com.example.pnapp.R;

import java.util.Collections;
import java.util.List;

public class mealDisplay extends RecyclerView.Adapter<meal_card_holder> {

    List<Meal> list = Collections.emptyList();
    Context context;

    public mealDisplay(List<Meal> list, Context context){
        this.list = list;
        this.context = context;

    }

    @NonNull
    @Override
    public meal_card_holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View photoView = inflater.inflate(R.layout.meal_card, parent, false);

        meal_card_holder viewHolder = new meal_card_holder(photoView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final meal_card_holder holder,final int position) {
        final int index = holder.getAdapterPosition();
        holder.mealName.setText(list.get(position).getName());
        holder.mealType.setText(list.get(position).getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context.getApplicationContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
