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

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.util.Log;

import org.apache.commons.lang3.ArrayUtils;

import it.matbell.ask.controllers.SensorMonitor;
import it.matbell.ask.model.SensorsStats;

/**
 * Monitors sensors that measure acceleration forces and rotational forces along three axes. This
 * category includes accelerometers, gravity sensors, gyroscopes, and rotational vector sensors.
 * Specifically, this probe monitors the following sensors:
 *
 *      - ACCELEROMETER : Acceleration force along the x,y,z axes in m/s2
 *      - GRAVITY : Force of gravity along the x,y,z axes in m/s2
 *      - GYROSCOPE : Rate of rotation along the x,y,z axes in rad/s
 *      - ACCELERATION : Acceleration force along the x,y,z axes in m/s2
 *      - ROTATION : Rotation vector component along the x,y,z axes
 *
 *  Parameters:
 *
 *      - "maxSamples" : controls the maximum number of samples for each sensor used to calculate
 *      the statistics. The default value is 200 samples.
 *
 */
@SuppressWarnings("unused")
class MotionSensorsProbe extends ContinuousProbe {

    private static final int DEFAULT_MAX_ELEMENTS = 200;

    private static final int[] MOTION_SENSORS = new int[]{
            Sensor.TYPE_ACCELEROMETER,
            Sensor.TYPE_GRAVITY,
            Sensor.TYPE_GYROSCOPE,
            Sensor.TYPE_LINEAR_ACCELERATION,
            Sensor.TYPE_ROTATION_VECTOR
    };

    private SensorMonitor sensorMonitor;
    private int maxSamples = DEFAULT_MAX_ELEMENTS;

    @Override
    public void init() {
        sensorMonitor = new SensorMonitor(getContext(), MOTION_SENSORS.length);

        for (int sensorId : MOTION_SENSORS) {
            sensorMonitor.registerSensor(sensorId, SensorManager.SENSOR_STATUS_ACCURACY_HIGH,
                    3, maxSamples);
        }
    }

    @Override
    public void onFirstRun() {}

    @Override
    void onStop() {
        for (int sensorId : MOTION_SENSORS) sensorMonitor.unRegisterSensor(sensorId);
    }

    @Override
    public void exec() {
        printStatistics();
    }

    private void printStatistics(){

        double stats[] = null;

        for (int sensorId : MOTION_SENSORS) {

            double[] sensorData = sensorMonitor.getStats(sensorId);

            if(stats == null) stats = sensorData;
            else stats = ArrayUtils.addAll(stats, sensorData);

            sensorMonitor.resetSamples(sensorId);
        }

        logOnFile(true, new SensorsStats(stats));
    }
}
