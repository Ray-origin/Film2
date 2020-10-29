package com.example.appdatveonline;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Film extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_film);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_film);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_film:
//                        Intent intent = new Intent();
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
            }
        });
    }
}