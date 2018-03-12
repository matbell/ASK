package it.matbell.ask.model;

import android.content.pm.ApplicationInfo;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

import it.matbell.ask.logs.FileLogger;

public class PackagesData implements Loggable{

    public List<String> packages = new ArrayList<>();

    public PackagesData(List<ApplicationInfo> info){
        for (ApplicationInfo packageInfo : info) packages.add(packageInfo.packageName);
    }

    public PackagesData(){}

    @Override
    public String getDataToLog() {

        return StringUtils.join(packages, FileLogger.SEP);
    }
}
