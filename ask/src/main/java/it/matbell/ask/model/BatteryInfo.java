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

package it.matbell.ask.model;

import it.matbell.ask.logs.FileLogger;

public class BatteryInfo implements Loggable{

    private float batteryPercentage;
    private int plugged;

    public BatteryInfo(float batteryPercentage, int plugged){

        this.plugged = plugged;
        this.batteryPercentage = batteryPercentage;
    }

    @Override
    public String getDataToLog() {
        return batteryPercentage + FileLogger.SEP + plugged;
    }
}
