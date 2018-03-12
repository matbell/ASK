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

import android.app.usage.UsageStatsManager;
import android.util.Log;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import it.matbell.ask.commons.Utils;
import it.matbell.ask.controllers.AppsUsageController;
import it.matbell.ask.model.AppUsageData;
import it.matbell.ask.model.AppUsageStats;

/**
 * This probe monitors the apps usage statistics using the {@link UsageStatsManager}. For this
 * reason, it requires that the minimum API level supported by the app is >= 22. In addition, it
 * requires a system-level permission which should be granted by the user through the Settings app,
 * typically under  "Settings" -> "Security" -> "Apps with usage access".
 *
 * Parameters:
 *
 *  - "statsInterval" : defines the beginning of the range of stats to include in the results.
 *  It should be defined in the following format: "<number> <time>". <number> should be a positive
 *  integer, while <time> should be one of the following: "day", "days", "week", "weeks", "month",
 *  "months", "year", or "years". For example, "statsInterval" : "1 day" considers the apps usage
 *  statistics since yesterday. The default value is "1 day".
 *
 * Required permissions:
 *
 * - "android.permission.PACKAGE_USAGE_STATS"
 *
 */
@SuppressWarnings("unused")
class AppsUsageProbe extends ContinuousProbe {

    private static final String DEFAULT_STATS_INTERVAL = "1 day";

    private String statsInterval = DEFAULT_STATS_INTERVAL;
    private boolean valid = false;
    private int time, type;
    private Date startDate;

    @Override
    public void init() {
        checkInterval();

        if(valid){

            Date now = Calendar.getInstance().getTime();

            Calendar cal = Calendar.getInstance();
            cal.setTime(now);
            cal.add(type, -time);

            startDate = cal.getTime();
        }
    }

    @Override
    public void onFirstRun() {}


    @Override
    void onStop() {}

    @Override
    public void exec() {

        if(valid) getRunningApps();
    }

    private void getRunningApps(){

        List<AppUsageStats> apps = AppsUsageController.getAppUsageStats(getContext(), startDate);
        logOnFile(true, new AppUsageData(apps));

    }

    /**
     * Checks the "statsInterval" parameter.
     */
    private void checkInterval(){

        statsInterval = statsInterval.toLowerCase();

        if(statsInterval.split(" ").length != 2){
            invalidInterval();

        } else{

            try {
                time = Integer.parseInt(statsInterval.split(" ")[0]);
            }catch (NumberFormatException e){
                Log.e(Utils.TAG, e.getMessage());
                invalidInterval();
                return;
            }

            if(time < 1){
                invalidInterval();
                return;
            }

            String timeString = statsInterval.split(" ")[1];

            switch(timeString){

                case "minute":
                case "minutes":
                    type = Calendar.MINUTE;
                    break;

                case "hour":
                case "hours":
                    type = Calendar.HOUR;
                    break;

                case "day":
                case "days":
                    type = Calendar.DAY_OF_MONTH;
                    break;

                case "week":
                case "weeks":
                    type = Calendar.WEEK_OF_MONTH;
                    break;

                case "month":
                case "months":
                    type = Calendar.MONTH;
                    break;

                case "year":
                case "years":
                    type = Calendar.YEAR;
                    break;

                default:
                    invalidInterval();
                    break;

            }

            valid = true;
        }

    }

    private void invalidInterval(){

        Log.e(Utils.TAG, "Malformed start interval: "+statsInterval);
    }
}
