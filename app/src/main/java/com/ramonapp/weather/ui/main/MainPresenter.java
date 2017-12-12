package com.ramonapp.weather.ui.main;

/**
 * Created by vahid on 12/9/2017.
 */

public interface MainPresenter {
    void getWeatherData(double lat, double lng);
    void onDestroy();
    void unSubscribe();
}
