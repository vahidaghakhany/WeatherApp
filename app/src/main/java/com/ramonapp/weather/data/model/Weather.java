package com.ramonapp.weather.data.model;

import java.util.List;

/**
 * Created by vahid on 12/9/2017.
 */

public class Weather {

    long dt; //Time in millisecond
    Temp main;
    List<WeatherDetail> weather;
    Wind wind;

    public Weather(long dt, Temp main, List<WeatherDetail> weather, Wind wind) {
        this.dt = dt;
        this.main = main;
        this.weather = weather;
        this.wind = wind;
    }

    public long getDt() {
        return dt;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }

    public Temp getMain() {
        return main;
    }

    public void setMain(Temp main) {
        this.main = main;
    }

    public List<WeatherDetail> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherDetail> weather) {
        this.weather = weather;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }
}
