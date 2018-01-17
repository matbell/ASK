package it.matbell.ask.model;

import android.location.Location;

import it.matbell.ask.logs.FileLogger;

public class LocationInfo implements Loggable{

    private double latitude, longitude, altitude, bearing;
    private float speed, accuracy;
    private long time;

    public LocationInfo(Location location){
        this.latitude = location.getLatitude();
        this.longitude = location.getLongitude();
        this.speed = location.getSpeed();
        this.accuracy = location.getAccuracy();
        this.altitude = location.getAltitude();
        this.bearing = location.getBearing();
        this.time = location.getTime();
    }

    @Override
    public String getDataToLog() {

        return latitude + FileLogger.SEP + longitude + FileLogger.SEP + speed + FileLogger.SEP +
                accuracy + FileLogger.SEP + altitude + FileLogger.SEP + bearing + FileLogger.SEP +
                time;
    }
}
