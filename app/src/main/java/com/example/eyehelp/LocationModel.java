package com.example.eyehelp;
import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class LocationModel {
    private double latitude;
    private double longitude;
    private String name;

    public LocationModel() {
        // Default constructor required for Firebase
    }

    public LocationModel(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getName() {
        return name;
    }
}
