package com.ramonapp.weather.data.model;


/**
 * Created by vahid on 12/11/2017.
 */

public class Geometry {
    Location location;

    public Geometry(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
