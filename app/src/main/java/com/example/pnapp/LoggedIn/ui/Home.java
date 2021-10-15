package com.example.pnapp.LoggedIn.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.os.Bundle;
import android.view.MenuItem;

import com.example.pnapp.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class Home extends AppCompatActivity{

    BottomNavigationView bottomNavigationView;


    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.navHostFragment, fragment)
                    .commit();
            return true;
        }
        return false;
    }

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        loadFragment(new HomeFragment());


            BottomNavigationView navigation = findViewById(R.id.bottomNav_view);


            navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment fragment = null;

                    switch (item.getItemId()) {
                        case R.id.home:
                            fragment = new HomeFragment();
                            break;

                        case R.id.meal:
                            fragment = new MealFragment();
                            break;

                        case R.id.recipes:
                            fragment =new RecipesFragment();
                            break;

                        case R.id.account:
                            fragment =new AccountFragment();
                            break;
                    }

                    return loadFragment(fragment);
                }
            });


    }


}