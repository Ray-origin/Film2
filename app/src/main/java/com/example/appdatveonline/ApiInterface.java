package com.example.appdatveonline;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.POST;

public interface ApiInterface {
   @POST("get_movies.php")
   Call<List<Movies>> getMovies();
}