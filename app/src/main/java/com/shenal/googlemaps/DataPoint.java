package com.shenal.googlemaps;

public class DataPoint {
    int day;
    int temperature;

    public DataPoint(int day, int temperature) {
        this.day = day;
        this.temperature = temperature;
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
}
