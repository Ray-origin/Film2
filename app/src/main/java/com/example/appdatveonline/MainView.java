package com.example.appdatveonline;

import java.util.List;

public interface MainView {

    void onGetResult(List<Movies> moviesList);
    void onErrorLoading(String message);
}
