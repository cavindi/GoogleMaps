package com.shenal.googlemaps;

public class DataPoint {
    int day;
    int temperature;
    int humidity;

    public DataPoint(int day, int temperature, int humidity) {
        this.day = day;
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public DataPoint() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
