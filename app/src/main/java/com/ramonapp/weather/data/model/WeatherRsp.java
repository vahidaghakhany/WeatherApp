package com.ramonapp.weather.data.model;

import java.util.List;

/**
 * Created by vahid on 12/9/2017.
 */

public class WeatherRsp {

    String cod;
    List<Weather> list;
    City city;

    public WeatherRsp(String cod, List<Weather> list, City city) {
        this.cod = cod;
        this.list = list;
        this.city = city;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public List<Weather> getList() {
        return list;
    }

    public void setList(List<Weather> list) {
        this.list = list;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }
}
