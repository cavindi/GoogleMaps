package com.shenal.googlemaps;

public class HumidityDataPoint {
    int day;
    int humidity;

    public HumidityDataPoint() {
    }

    public HumidityDataPoint(int day, int humidity) {
        this.day = day;
        this.humidity = humidity;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
