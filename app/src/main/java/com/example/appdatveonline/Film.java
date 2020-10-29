package com.example.appdatveonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;



public class Film extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_film);

        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.nav_film:
                    return true;
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(),MainActivity.class ));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_account:
                    startActivity(new Intent(getApplicationContext(),Account.class ));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }
}