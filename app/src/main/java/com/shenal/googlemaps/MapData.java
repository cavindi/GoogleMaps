package com.shenal.googlemaps;

public class MapData {
    double latitude;
    double longitude;

    public MapData(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public MapData() {
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
