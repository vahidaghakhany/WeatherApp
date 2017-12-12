package com.ramonapp.weather.ui.searchCity;

/**
 * Created by vahid on 12/11/2017.
 */

public interface SearchCityPresenter {
    void doSearch(String s);
    void onDestroy();
    void unSubscribe();
}
