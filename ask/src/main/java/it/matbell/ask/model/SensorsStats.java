package it.matbell.ask.model;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

public class SensorsStats implements Loggable {

    private double stats[];

    public SensorsStats(double stats[]){
        this.stats = stats;
    }

    @Override
    public String getDataToLog() {
        return StringUtils.join(ArrayUtils.toObject(stats), "\t");
    }
}
