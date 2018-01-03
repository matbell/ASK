/*
 * Copyright (c) 2017. Mattia Campana, m.campana@iit.cnr.it, campana.mattia@gmail.com
 *
 * This file is part of Android Sensing Kit (ASK).
 *
 * Android Sensing Kit (ASK) is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Android Sensing Kit (ASK) is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Android Sensing Kit (ASK).  If not, see <http://www.gnu.org/licenses/>.
 */

package it.matbell.ask.probes;

import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import it.matbell.ask.commons.Utils;
import it.matbell.ask.controllers.WeatherController;

/**
 * This probe monitors the current weather conditions (e.g., temperature, humidity, etc.) at the
 * current device location. Weather information comes from the API service of
 * http://www.openweathermap.com
 *
 * Returns the following information:
 *
 *  - the weather conditions id, according to http://www.openweathermap.com/weather-conditions
 *  - temperature
 *  - minimum temperature
 *  - maximum temperature
 *  - humidity
 *  - pressure
 *  - wind speed
 *  - wind direction in degrees
 *  - percentage of cloudiness
 *  - rain volume for the last 3 hours
 *  - snow volume for the last 3 hour
 *  - sunrise time (unix UTC timestamp)
 *  - sunset time (unix UTC timestamp)
 *
 * Requires:
 *
 *  - "com.google.android.gms.permission.ACCESS_FINE_LOCATION"
 *  - "android.permission.INTERNET"
 *
 */
public class WeatherProbe extends ContinuousProbe {

    private FusedLocationProviderClient locationProviderClient;
    private int unit;
    private String appId;

    private WeatherController.WeatherListener listener = new WeatherController.WeatherListener(){

        @Override
        public void onWeatherAvailable(Object[] data) {
            logOnFile(true, data);
        }

        @Override
        public void onWeatherFailed(String reason) {
            Log.d(Utils.TAG, WeatherProbe.class.getName() + " failure: "+reason);
        }
    };

    @Override
    public void init() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
    }

    @Override
    public void onFirstRun() {

    }

    @Override
    void onStop() {

    }

    @Override
    @SuppressWarnings("all")
    public void exec() {

        locationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {

                            WeatherController.getWeatherByCoordinates(getContext(), listener,
                                    location.getLatitude(), location.getLongitude(), unit, appId);
                        }
                    }
                });
    }
}
