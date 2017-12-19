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

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.List;

import it.matbell.ask.logs.FileLogger;

/**
 * This IntentService has been used by the {@link ActivityRecognitionProbe} to receive the intents
 * sent by the Android Activity Recognition.
 * Each activity is associated with a confidence level, which is an int between 0 and 100.
 *
 */
public class ActivityRecognitionReceiver extends IntentService {

    private static final String TAG = ActivityRecognitionReceiver.class.getName();

    public ActivityRecognitionReceiver(){
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);

        if(result != null) {

            List<DetectedActivity> activities = result.getProbableActivities();

            int[] act = new int[8];
            for (DetectedActivity da : activities) {

                int pos = -1;

                switch (da.getType()){
                    case DetectedActivity.IN_VEHICLE:
                        pos = 0;
                        break;

                    case DetectedActivity.ON_BICYCLE:
                        pos = 1;
                        break;

                    case DetectedActivity.ON_FOOT:
                        pos = 2;
                        break;

                    case DetectedActivity.RUNNING:
                        pos = 3;
                        break;

                    case DetectedActivity.STILL:
                        pos = 4;
                        break;

                    case DetectedActivity.TILTING:
                        pos = 5;
                        break;

                    case DetectedActivity.WALKING:
                        pos = 6;
                        break;

                    case DetectedActivity.UNKNOWN:
                        pos = 7;
                        break;
                }

                if(pos != -1) act[pos] = da.getConfidence();
            }

            if(intent != null && intent.hasExtra(ActivityRecognitionProbe.LOG_INTENT_FIELD) &&
                    intent.getStringExtra(ActivityRecognitionProbe.LOG_INTENT_FIELD) != null) {

                FileLogger.getInstance(getApplicationContext()).store(intent.getStringExtra(
                        ActivityRecognitionProbe.LOG_INTENT_FIELD), act, true);
            }
        }

    }
}
