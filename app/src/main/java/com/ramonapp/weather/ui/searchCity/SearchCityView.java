package com.ramonapp.weather.ui.searchCity;

import com.ramonapp.weather.data.model.GeocodeRsp;

/**
 * Created by vahid on 12/11/2017.
 */

public interface SearchCityView {
    void showLoading();
    void dismissLoading();
    void onError(String message);
    void showCityList(GeocodeRsp geocodeRsp);
}
