package it.matbell.ask.model;

import it.matbell.ask.logs.FileLogger;

public class HardwareInfo implements Loggable {

    private String androidID, wifiMac, wifiP2pMac, bluetoothMac, phoneBrand, phoneModel, deviceID;
    private String phoneNumber;

    public HardwareInfo(String androidID, String wifiMac, String wifiP2pMac, String bluetoothMac,
                        String phoneBrand, String phoneModel, String deviceID, String phoneNumber){

        this.androidID = androidID;
        this.wifiMac = wifiMac;
        this.wifiP2pMac = wifiP2pMac;
        this.bluetoothMac = bluetoothMac;
        this.phoneBrand = phoneBrand;
        this.phoneModel = phoneModel;
        this.deviceID = deviceID;
        this.phoneNumber = phoneNumber;
    }


    @Override
    public String getDataToLog() {

        return androidID + FileLogger.SEP + wifiMac + FileLogger.SEP + wifiP2pMac + FileLogger.SEP +
                bluetoothMac + FileLogger.SEP + phoneBrand + FileLogger.SEP + phoneModel +
                FileLogger.SEP + deviceID + FileLogger.SEP + phoneNumber;
    }
}
