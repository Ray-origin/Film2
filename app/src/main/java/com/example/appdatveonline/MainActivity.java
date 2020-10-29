package com.example.appdatveonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;


public class MainActivity extends AppCompatActivity implements MainView {
    ViewFlipper viewFlipper;
    Animation in,out;
    RecyclerView recyclerView;


    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;
    List<Movies> movie;



    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        viewFlipper = findViewById(R.id.quangcao);
        in = AnimationUtils.loadAnimation(this,R.anim.fade_in);
        out = AnimationUtils.loadAnimation(this,R.anim.fade_out);
        viewFlipper.setInAnimation(in);
        viewFlipper.setInAnimation(out);
        int[] ArrayHinh = {R.drawable.abc,R.drawable.a2,R.drawable.a3,R.drawable.a4};
        for (int value : ArrayHinh) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(value);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        recyclerView= findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        presenter=new MainPresenter(this);
        presenter.getData();
        itemClickListener=((view,position)->{
            String title=movie.get(position).getTitle();
            Toast.makeText(this,title,Toast.LENGTH_SHORT).show();
        });
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);

        bottomNav.setSelectedItemId(R.id.nav_home);
        
        bottomNav.setOnNavigationItemSelectedListener(menuItem -> {
            switch (menuItem.getItemId()){
                case R.id.nav_film:
                    startActivity(new Intent(getApplicationContext(),Film.class ));
                    overridePendingTransition(0,0);
                    return true;
                case R.id.nav_home:
                    return true;
                case R.id.nav_account:
                    startActivity(new Intent(getApplicationContext(),Account.class ));
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onGetResult(List<Movies> moviesList) {
        adapter=new MainAdapter(moviesList, this,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
        movie=moviesList;

    }

    @Override
    public void onErrorLoading(String message) {

    }
}