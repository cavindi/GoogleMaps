package com.shenal.googlemaps;

public class DODataPoint {
    int day;
    int dissolvedoxygen;

    public DODataPoint(int day, int dissolvedoxygen) {
        this.day = day;
        this.dissolvedoxygen = dissolvedoxygen;
    }

    public DODataPoint() {
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDissolvedoxygen() {
        return dissolvedoxygen;
    }

    public void setDissolvedoxygen(int dissolvedoxygen) {
        this.dissolvedoxygen = dissolvedoxygen;
    }
}
