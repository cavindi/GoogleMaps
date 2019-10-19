package com.shenal.googlemaps;

public class DODataPoint {
    int day;
    int dissolvedOxygen;

    public DODataPoint(int day, int dissolvedOxygen) {
        this.day = day;
        this.dissolvedOxygen = dissolvedOxygen;
    }

    public DODataPoint() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDissolvedOxygen() {
        return dissolvedOxygen;
    }

    public void setDissolvedOxygen(int dissolvedOxygen) {
        this.dissolvedOxygen = dissolvedOxygen;
    }
}
