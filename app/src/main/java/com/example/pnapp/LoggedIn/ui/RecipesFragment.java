package com.example.pnapp.LoggedIn.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.pnapp.Models.MyFood;
import com.example.pnapp.R;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.fatsecret.platform.services.Response;
import com.fatsecret.platform.services.android.Request;
import com.fatsecret.platform.services.android.ResponseListener;

import java.util.ArrayList;
import java.util.List;

public class RecipesFragment extends Fragment {

    List<com.example.pnapp.Models.CompactRecipe> recipeList;
    ArrayAdapter<com.example.pnapp.Models.CompactRecipe> arrayAdapter;
    ListView list;
    SearchView Search;

    public RecipesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recipes, container, false);

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        String key = "b83d887701404c20b962fe958e69421f";
        String secret = "a9ddc7ea23b24fc0b07bc1973a2efc7d";

        RecipesFragment.Listener listener = new RecipesFragment.Listener();

        Request request = new Request(key, secret, listener);

        list = view.findViewById(R.id.recipes);
        Search = view.findViewById(R.id.searchRecipes);

        Search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                request.searchRecipes(requestQueue, s);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                request.searchRecipes(requestQueue, s);
                return false;
            }
        });

        return view;
    }

    class Listener implements ResponseListener {


        @Override
        public void onFoodListRespone(Response<CompactFood> response) {
            System.out.println("TOTAL FOOD ITEMS: " + response.getTotalResults());



            List<CompactFood> foods = response.getResults();
            //This list contains summary information about the food items

            System.out.println("=========FOODS============");
            for (CompactFood food: foods) {

            }


        }


        @Override
        public void onRecipeListRespone(Response<CompactRecipe> response) {
            System.out.println("TOTAL RECIPES: " + response.getTotalResults());

            List<CompactRecipe> recipes = response.getResults();

            recipeList = new ArrayList<>();

            com.example.pnapp.Models.CompactRecipe myRecipe;

            System.out.println("=========RECIPES==========");
            for (CompactRecipe recipe: recipes) {
                myRecipe = new com.example.pnapp.Models.CompactRecipe();

                myRecipe.setName(recipe.getName());
                myRecipe.setDescription(recipe.getDescription());
                myRecipe.setImages(recipe.getImages());
            }
            arrayAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, recipeList);
            list.setAdapter(arrayAdapter);

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