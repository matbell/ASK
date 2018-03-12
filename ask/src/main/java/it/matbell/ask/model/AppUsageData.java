package it.matbell.ask.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import it.matbell.ask.logs.FileLogger;

public class AppUsageData implements Loggable {

    private List<AppUsageStats> data;

    public AppUsageData(List<AppUsageStats> data){
        this.data = data;
    }

    @Override
    public String getDataToLog() {

        if(data != null) {
            List<String> dataString = new ArrayList<>();
            for (AppUsageStats stats : data) dataString.add(stats.getDataToLog());

            return StringUtils.join(dataString, FileLogger.SEP);
        }

        return "";
    }
}
