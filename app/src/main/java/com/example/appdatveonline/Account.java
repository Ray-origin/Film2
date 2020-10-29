package com.example.appdatveonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Account extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_account);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
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
            }
        });
    }
}