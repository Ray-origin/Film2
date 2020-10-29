package com.example.appdatveonline;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Account extends AppCompatActivity {

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_account);

        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.nav_film:
//                        Intent intent = new Intent();
                    startActivity(new Intent(getApplicationContext(),Film.class ));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_home:
                    startActivity(new Intent(getApplicationContext(), Activity.class ));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_account:
                    return true;
            }
            return false;
        });
    }
}