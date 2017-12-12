package com.ramonapp.weather.data.model;

import java.util.List;

/**
 * Created by vahid on 12/11/2017.
 */

public class GeocodeRsp {
    List<CityGeocode> results;
    String status;

    public GeocodeRsp(List<CityGeocode> results, String status) {
        this.results = results;
        this.status = status;
    }

    public List<CityGeocode> getResults() {
        return results;
    }

    public void setResults(List<CityGeocode> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
