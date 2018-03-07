package it.matbell.ask.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import it.matbell.ask.logs.FileLogger;

public class LoggableElements implements Loggable{

    private Collection<? extends Loggable> elements;

    public LoggableElements(Collection<? extends Loggable> elements){
        this.elements = elements;
    }

    @Override
    public String getDataToLog() {

        if(elements != null && elements.size() != 0) {
            List<String> data = new ArrayList<>();

            for (Loggable loggable : elements) data.add(loggable.getDataToLog());

            return StringUtils.join(data, FileLogger.SEP);
        }

        return "";
    }
}
