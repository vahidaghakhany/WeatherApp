package com.ramonapp.weather.data.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by vahid on 12/11/2017.
 */

public class CityGeocode {
    @SerializedName("formatted_address")
    String name;
    Geometry geometry;

    public CityGeocode(String name, Geometry geometry) {
        this.name = name;
        this.geometry = geometry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }
}
