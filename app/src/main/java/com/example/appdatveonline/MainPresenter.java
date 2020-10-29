package com.example.appdatveonline;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {
    private MainView view;
    public MainPresenter(MainView view){
        this.view=view;
    }
    void getData(){


        ApiInterface apiInterface=ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Movies>> call=apiInterface.getMovies();
        call.enqueue(new Callback<List<Movies>>() {
            @Override
            public void onResponse(@NotNull Call<List<Movies>> call, @NotNull Response<List<Movies>> response) {

                if(response.isSuccessful() && response.body()!=null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<List<Movies>> call, @NotNull Throwable t) {

                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
