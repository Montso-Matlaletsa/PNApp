package com.example.pnapp.LoggedIn.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.pnapp.FoodViews.ShowFood;
import com.example.pnapp.Models.MyFood;
import com.example.pnapp.R;


import com.example.pnapp.services.FatsecretService;
import com.example.pnapp.utils.ServingUtility;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.fatsecret.platform.services.Response;
import com.fatsecret.platform.services.android.Request;
import com.fatsecret.platform.services.android.ResponseListener;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;




public class MealFragment extends Fragment {


    List<MyFood> foodList;
    ListView list;
    SearchView foodSearch;
    ArrayAdapter<MyFood> arrayAdapter;
    public MealFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_meal, container, false);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String key = "b83d887701404c20b962fe958e69421f";
        String secret = "a9ddc7ea23b24fc0b07bc1973a2efc7d";

        Listener listener = new Listener();

        Request request = new Request(key, secret, listener);

        FatsecretService fats = new FatsecretService(key, secret);


        list = view.findViewById(R.id.foods);
        foodSearch = view.findViewById(R.id.searchFood);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MyFood myFood = (MyFood) adapterView.getItemAtPosition(position);
                Intent intent = new Intent(getContext().getApplicationContext(), ShowFood.class);
                intent.putExtra("food", myFood);

                startActivity(intent);

            }
        });


        foodSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                request.searchFoods(requestQueue, s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                request.searchFoods(requestQueue, s);
                return false;
            }
        });


        return  view;
    }

    class Listener implements ResponseListener {

        @Override
        public void onFoodListRespone(Response<CompactFood> response) {
            System.out.println("TOTAL FOOD ITEMS: " + response.getTotalResults());



            List<CompactFood> foods = response.getResults();
            //This list contains summary information about the food items
            foodList = new ArrayList<>();
            MyFood myFood;



            System.out.println("=========FOODS============");
            for (CompactFood food: foods) {
                myFood = new MyFood();
                myFood.setName(food.getName());
                myFood.setId(food.getId());
                myFood.setDescription(food.getDescription());
                myFood.setBrandName(food.getBrandName());
                myFood.setType(food.getType());
                myFood.setUrl(food.getUrl());


                foodList.add(myFood);
            }

            arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, foodList);
            list.setAdapter(arrayAdapter);
        }


        @Override
        public void onRecipeListRespone(Response<CompactRecipe> response) {
            System.out.println("TOTAL RECIPES: " + response.getTotalResults());

            List<CompactRecipe> recipes = response.getResults();
            System.out.println("=========RECIPES==========");
            for (CompactRecipe recipe: recipes) {
                System.out.println(recipe.getName());
            }


        }

        @Override
        public void onFoodResponse(Food food) {
            System.out.println("FOOD NAME: " + food.getName());
        }

        @Override
        public void onRecipeResponse(Recipe recipe) {
            System.out.println("RECIPE NAME: " + recipe.getName());
        }
    }
}