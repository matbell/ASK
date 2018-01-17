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

import it.matbell.ask.logs.FileLogger;
import it.matbell.ask.model.ActivityRecognitionData;

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

            ActivityRecognitionData data = new ActivityRecognitionData(result);

            if(intent != null && intent.hasExtra(ActivityRecognitionProbe.LOG_INTENT_FIELD) &&
                    intent.getStringExtra(ActivityRecognitionProbe.LOG_INTENT_FIELD) != null) {

                FileLogger.getInstance().store(intent.getStringExtra(
                        ActivityRecognitionProbe.LOG_INTENT_FIELD), data, true);
            }
        }

    }
}
