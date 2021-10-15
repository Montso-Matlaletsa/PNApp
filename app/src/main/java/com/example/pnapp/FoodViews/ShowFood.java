package com.example.pnapp.FoodViews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.example.pnapp.LoggedIn.ui.MealFragment;
import com.example.pnapp.Models.MyFood;
import com.example.pnapp.R;
import com.example.pnapp.services.Request;
import com.fatsecret.platform.model.CompactFood;
import com.fatsecret.platform.model.CompactRecipe;
import com.fatsecret.platform.model.Food;
import com.fatsecret.platform.model.Recipe;
import com.fatsecret.platform.model.Serving;
import com.fatsecret.platform.services.Response;
import com.fatsecret.platform.services.android.ResponseListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class ShowFood extends AppCompatActivity {

    TextView foodName, f;
    Long food_id;
    String key;
    String secret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_food);

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        key = "b83d887701404c20b962fe958e69421f";
        secret = "a9ddc7ea23b24fc0b07bc1973a2efc7d";
        foodName = findViewById(R.id.foodName);
        f = findViewById(R.id.f);



       food_id = getIntent().getLongExtra("food_id", 29304L);

        Request req = new Request(key, secret);
        JSONObject json = req.getFood(29304L);

        if(json != null){
            foodName.setText("Okay");
        }else{
            foodName.setText("Dreed");
        }




    }



    private void getFood(final long id) {

        Request req = new Request(key, secret);
        new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... arg0) {
                JSONObject foodGet = req.getFood(id);

                f.setText("We are in");

                try {
                    if (foodGet != null) {
                        String food_name = foodGet.getString("food_name");
                        JSONObject servings = foodGet.getJSONObject("servings");

                        JSONObject serving = servings.getJSONObject("serving");
                        String calories = serving.getString("calories");
                        String carbohydrate = serving.getString("carbohydrate");
                        String protein = serving.getString("protein");
                        String fat = serving.getString("fat");
                        String serving_description = serving.getString("serving_description");
                        Log.e("serving_description", serving_description);
                        /**
                         * Displays results in the LogCat
                         */
                        Log.e("food_name", food_name);
                        Log.e("calories", calories);
                        Log.e("carbohydrate", carbohydrate);
                        Log.e("protein", protein);
                        Log.e("fat", fat);

                        foodName.setText(protein);
                    }

                } catch (JSONException exception) {
                    return exception.getMessage();
                }
                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result.equals("Error"))
                    foodName.setText(result);

            }
        }.execute();
    }

}