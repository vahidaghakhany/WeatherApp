package com.ramonapp.weather.data.model;

/**
 * Created by vahid on 12/9/2017.
 */

public class Wind {
    float speed;
    float deg;

    public Wind(float speed, float deg) {
        this.speed = speed;
        this.deg = deg;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getDeg() {
        return deg;
    }

    public void setDeg(float deg) {
        this.deg = deg;
    }
}
