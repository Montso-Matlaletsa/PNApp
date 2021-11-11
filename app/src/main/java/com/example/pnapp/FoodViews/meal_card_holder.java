package com.example.pnapp.FoodViews;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pnapp.R;

public class meal_card_holder extends RecyclerView.ViewHolder {

    TextView mealName, mealType;

    public meal_card_holder(@NonNull View itemView) {
        super(itemView);

        mealName = itemView.findViewById(R.id.mealNa);
        mealType = itemView.findViewById(R.id.mealType);
    }
}
