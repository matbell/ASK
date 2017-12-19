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

import java.util.List;

import it.matbell.ask.controllers.AppsUsageController;

/**
 * This probe monitors the usage statistics of the running applications using the
 * {@link UsageStatsManager}. For this reason, it requires that the minimum API level supported by
 * the app is >= 22.
 *
 * Parameters:
 *      - "lastNMinutes" : controls the size of the time used to identify the running applications.
 *      For example, with "lastNMinutes" : 5, this probe considers as "running" all the apps with
 *      last usage timestamp >= 5 minutes ago. The default value is 5 minutes.
 *
 * Requires:
 *      - "android.permission.PACKAGE_USAGE_STATS"
 */
@SuppressWarnings("all")
class SKRunningApplicationsProbe extends SKContinuousProbe{

    private static final int DEFAULT_LAST_N_MINUTES = 5;

    private int lastNMinutes = DEFAULT_LAST_N_MINUTES;


    @Override
    public void init() {

    }

    @Override
    public void onFirstRun() {

    }

    @Override
    void onStop() {

    }

    @Override
    public void exec() {

        List<String> apps = AppsUsageController.getRecentApplications(getContext(), lastNMinutes);

        if(apps.size() > 0) logOnFile(true, apps);

    }
}
