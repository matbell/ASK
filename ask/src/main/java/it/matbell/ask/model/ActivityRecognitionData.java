package it.matbell.ask.model;

import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class ActivityRecognitionData implements Loggable {

    private int[] act = new int[8];

    public ActivityRecognitionData(ActivityRecognitionResult result){

        List<DetectedActivity> activities = result.getProbableActivities();

        for (DetectedActivity da : activities) {

            int pos = -1;

            switch (da.getType()) {
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

            if (pos != -1) act[pos] = da.getConfidence();
        }

    }

    @Override
    public String getDataToLog() {
        return StringUtils.join(ArrayUtils.toObject(act), "\t");
    }
}
