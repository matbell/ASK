package it.matbell.ask.model;

import org.apache.commons.lang3.StringUtils;

import java.util.List;

public class WifiP2PData implements Loggable {

    private List<String> addressList;

    public WifiP2PData(List<String> addressList){
        this.addressList = addressList;
    }

    @Override
    public String getDataToLog() {
        return StringUtils.join(addressList, "\t");
    }
}
