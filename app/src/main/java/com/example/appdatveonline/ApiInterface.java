package com.example.appdatveonline;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {
   @FormUrlEncoded
   @GET("get_movies.php")
   Call<List<Movies>> getMovies();
}