package com.markoproject.model;

/**
 * Created by Marko on 02.12.2016.
 */
public class Location {
    private double Longitude;
    private double Latitude;


    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    @Override
    public String toString() {
        return "Location{" +
                "Longitude=" + Longitude +
                ", Latitude=" + Latitude +
                '}';
    }
}
