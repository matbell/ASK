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
 *
 */

package it.matbell.ask.probes;

import android.location.Location;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.Arrays;

/**
 * This probe monitors the geographical location of the local device. Specifically, it reports the
 * following information:
 *
 *      - latitude
 *      - longitude
 *      - speed
 *      - the position's accuracy in meters
 *      - altitude
 *      - bearing
 *      - position's timestamp
 *
 * Requires:
 *
 *  - "com.google.android.gms.permission.ACCESS_COARSE_LOCATION"
 *
 */
@SuppressWarnings("unused")
class SKLocationProbe extends SKContinuousProbe {

    private FusedLocationProviderClient locationProviderClient;

    @Override
    public void init() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(getContext());
    }

    @Override
    public void onFirstRun() {}

    @Override
    void onStop() {

    }

    @Override
    public void exec() {
        getLastKnownLocation();
    }

    @SuppressWarnings("all")
    private void getLastKnownLocation(){

        locationProviderClient.getLastLocation()
                .addOnSuccessListener(new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {

                            logOnFile(true, Arrays.asList(location.getLatitude(),
                                    location.getLongitude(), location.getSpeed(),
                                    location.getAccuracy(), location.getAltitude(),
                                    location.getBearing(), location.getTime()).toArray());
                        }
                    }
                });
    }
}
