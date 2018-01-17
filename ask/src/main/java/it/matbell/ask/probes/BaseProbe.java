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

import android.content.Context;

import java.util.Collection;

import it.matbell.ask.logs.FileLogger;
import it.matbell.ask.model.Loggable;

/**
 * This is the probes' base class.
 *
 * Parameters:
 *  - "logFile" : if present, defines the name of the log file.
 *  - "startDelay" : if present, delays the start of the probe
 *
 */
public abstract class BaseProbe {

    String logFile;
    private Context context;
    private int startDelay;

    public void setLogFile(String logFile){ this.logFile = logFile; }

    public void setContext(Context context){this.context = context;}
    Context getContext(){
        return this.context;
    }

    public void setStartDelay(int startDelay){this.startDelay = startDelay;}
    public int getStartDelay(){return startDelay;}

    public void stop(){ this.onStop(); }

    public abstract void init();
    public abstract void onFirstRun();
    abstract void onStop();

    void logOnFile(boolean withTimeStamp, Collection<? extends Loggable> data){
        if(logFile != null) FileLogger.getInstance().store(logFile, data, withTimeStamp);
    }

    void logOnFile(boolean withTimeStamp, Loggable data){
        if(logFile != null) FileLogger.getInstance().store(logFile, data, withTimeStamp);
    }
}
