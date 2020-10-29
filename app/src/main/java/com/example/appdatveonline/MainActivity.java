package com.example.appdatveonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {
    Toolbar toolbar;
    ViewFlipper viewFlipper;
    Animation in,out;

    RecyclerView recyclerView;
    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;
    List<Movies> movie;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewFlipper = (ViewFlipper) findViewById(R.id.quangcao);
        in = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setInAnimation(out);
        recyclerView=findViewById((R.id.main_recyclerView));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter= new MainPresenter( this);
        presenter.getData();

        itemClickListener=((view, position) ->{
            String title=movie.get(position).getTitle();
            Toast.makeText(this,title,Toast.LENGTH_SHORT).show();
        });

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_home);

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            private Object LoginActivity;

            @SuppressLint("NonConstantResourceId")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.nav_film:
//                        Intent intent = new Intent();
                        startActivity(new Intent(getApplicationContext(),Film.class ));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.nav_home:

                        return true;
                    case R.id.nav_account:

                     return true;
                }
                return false;
            }

        });
        int[] ArrayHinh = {R.drawable.abc,R.drawable.a2,R.drawable.a3,R.drawable.a4};
        for (int value : ArrayHinh) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(value);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
    }


    @Override
    public void onGetResult(List<Movies> moviesList) {
        adapter=new MainAdapter(moviesList,this,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        movie=moviesList;
    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
}