package com.example.appdatveonline;

import java.util.List;

public interface MainView {
    void showLoading();
    void hideLoading();
    void onGetResult(List<Movies> moviesList);
    void onErrorLoading(String message);
}
