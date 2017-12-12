package com.ramonapp.weather.data.network;

import com.ramonapp.weather.data.model.GeocodeRsp;
import com.ramonapp.weather.data.model.WeatherRsp;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by vahid on 12/9/2017.
 */

public interface ApiService {
    @GET("forecast")
    Single<WeatherRsp> getWeather(@Query("lat") double lat, @Query("lon") double lng, @Query("units") String units, @Query("appid") String appId);

    @GET("json")
    Single<GeocodeRsp> searchCity (@Query("address") String city, @Query("sensor") boolean sensor);
}
