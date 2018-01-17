package it.matbell.ask.model;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class WiFiScanInfo implements Loggable{

    public List<WiFiAp> wiFiAps = new ArrayList<>();

    public void addAp(String SSID, String BSSID, int signalLevel, int dbmLevel, String capabilities,
                      int frequency, boolean connected, boolean configured){

        this.wiFiAps.add(new WiFiAp(SSID, BSSID, signalLevel, dbmLevel, capabilities, frequency,
                connected, configured));
    }

    @Override
    public String getDataToLog() {

        List<String> apsData = new ArrayList<>();
        for(WiFiAp ap : wiFiAps) apsData.add(ap.getDataToLog());

        return StringUtils.join(apsData, "\t");
    }


    class WiFiAp implements Loggable{

        private String SSID, BSSID, capabilities;
        private int signalLevel, dbmLevel, frequency;
        private boolean connected, configured;

        public WiFiAp(String SSID, String BSSID, int signalLevel, int dbmLevel, String capabilities,
                      int frequency, boolean connected, boolean configured){

            this.SSID = SSID;
            this.BSSID = BSSID;
            this.signalLevel = signalLevel;
            this.dbmLevel = dbmLevel;
            this.capabilities = capabilities;
            this.frequency = frequency;
            this.connected = connected;
            this.configured = configured;
        }

        @Override
        public String getDataToLog() {
            return SSID + "," + BSSID + "," + signalLevel + "," + dbmLevel + "," + capabilities +
                    frequency + "," + connected + "," + configured;
        }
    }

}
