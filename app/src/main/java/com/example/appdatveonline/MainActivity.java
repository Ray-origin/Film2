package com.example.appdatveonline;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {
    ViewFlipper viewFlipper;
    Animation in,out;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ApiInterface apiInterface;
    List<Movies> moviesList;
    MainAdapter adapter;
    MainAdapter.ItemClickListener listener;



    @SuppressLint("NonConstantResourceId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiInterface=ApiClient.getApiClient().create(ApiInterface.class);


        viewFlipper = (ViewFlipper) findViewById(R.id.quangcao);
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

        recyclerView=(RecyclerView) findViewById(R.id.main_recycler_view);
        recyclerView.setLayoutManager(layoutManager);

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

    public void getMovies(){
        Call<List<Movies>> call=apiInterface.getMovies();
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(@NotNull Call<List<Movies>> call, @NotNull Response<List<Movies>> response) {
                moviesList=response.body();
                Log.i(MainActivity.class.getSimpleName(),response.body().toString());
                adapter=new MainAdapter(moviesList,MainActivity.this, listener);
                recyclerView.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull Call<List<Movies>> call, @NotNull Throwable t) {
                Toast.makeText(MainActivity.this, "rp :"+
                                t.getMessage(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getMovies();
    }

}