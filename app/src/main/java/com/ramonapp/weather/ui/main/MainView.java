package com.ramonapp.weather.ui.main;

import com.ramonapp.weather.data.model.WeatherRsp;

/**
 * Created by vahid on 12/9/2017.
 */

public interface MainView {
    void showLoading();
    void dismissLoading();
    void onError(String message);
    void showMessage(String message);
    void showNowWeatherData(WeatherRsp weatherRsp);
    void show1stDeg(String deg);
    void show1stIcon(String url);
    void show2ndDeg(String deg);
    void show2ndDate(String date);
    void show2ndIcon(String url);
    void show3rdDeg(String deg);
    void show3rdDate(String date);
    void show3rdIcon(String url);

}
