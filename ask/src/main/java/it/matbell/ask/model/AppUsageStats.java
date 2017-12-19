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

import android.app.usage.UsageStats;

import java.util.Arrays;

import it.matbell.ask.commons.Utils;

public class AppUsageStats {

    private String packageName;
    private long firstTimeStamp, lastTimeStamp, lastTimeUsed, totalTimeInForeground;

    @SuppressWarnings("all")
    public AppUsageStats(UsageStats stats){

        this.packageName = stats.getPackageName();
        this.firstTimeStamp = stats.getFirstTimeStamp();
        this.lastTimeStamp = stats.getLastTimeStamp();
        this.lastTimeUsed = stats.getLastTimeUsed();
        this.totalTimeInForeground = stats.getTotalTimeInForeground();
    }

    public Object[] getDataToPrint(){

        return Arrays.asList(packageName, firstTimeStamp, lastTimeStamp, lastTimeUsed,
                totalTimeInForeground).toArray();
    }

    @Override
    public String toString() {
        return Utils.formatLogOutput(packageName, firstTimeStamp, lastTimeStamp, lastTimeUsed,
                totalTimeInForeground);
    }
}
