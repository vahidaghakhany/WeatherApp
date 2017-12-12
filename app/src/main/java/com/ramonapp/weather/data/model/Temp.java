package com.ramonapp.weather.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vahid on 12/9/2017.
 */

public class Temp {

    float temp;
    @SerializedName("temp_min")
    float tempMin;
    @SerializedName("temp_max")
    float tempMax;
    int humidity;

    public Temp(float temp, float tempMin, float tempMax, int humidity) {
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.humidity = humidity;
    }

    public float getTemp() {
        return temp;
    }

    public void setTemp(float temp) {
        this.temp = temp;
    }

    public float getTempMin() {
        return tempMin;
    }

    public void setTempMin(float tempMin) {
        this.tempMin = tempMin;
    }

    public float getTempMax() {
        return tempMax;
    }

    public void setTempMax(float tempMax) {
        this.tempMax = tempMax;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
