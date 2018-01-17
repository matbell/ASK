package it.matbell.ask.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BTDevices implements Loggable{

    private Collection<BTDevice> devices;

    public BTDevices(Collection<BTDevice> devices){ this.devices = devices; }


    @Override
    public String getDataToLog() {

        List<String> data = new ArrayList<>();

        for(BTDevice device : devices) data.add(device.getDataToLog());

        return StringUtils.join(data, "\t");
    }
}
