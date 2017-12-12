package com.ramonapp.weather.data.network;

import com.ramonapp.weather.data.model.GeocodeRsp;
import com.ramonapp.weather.data.model.WeatherRsp;

import io.reactivex.Single;

/**
 * Created by vahid on 12/9/2017.
 */

public interface NetworkRepository {
    Single<WeatherRsp> getWeather(double lat, double lng, String units, String appId);
    Single<GeocodeRsp> searchCity(String city);
}
