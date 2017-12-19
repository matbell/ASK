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

package it.matbell.ask.model;

import java.util.Arrays;

import it.matbell.ask.commons.Utils;

public class AudioInfo {

    private int ringerMode;
    private float alarmVolume, musicVolume, notificationVolume, ringVolume;
    private boolean bluetoothScoOn, microphoneMute, musicActive, speakerOn, headsetOn;

    public AudioInfo(int ringerMode, float alarmVolume, float musicVolume, float notificationVolume,
                     float ringVolume, boolean bluetoothScoOn, boolean microphoneMute,
                     boolean musicActive, boolean speakerOn, boolean headsetOn){

        this.ringerMode = ringerMode;
        this.alarmVolume = alarmVolume;
        this.musicVolume = musicVolume;
        this.notificationVolume = notificationVolume;
        this.ringVolume = ringVolume;
        this.bluetoothScoOn = bluetoothScoOn;
        this.microphoneMute = microphoneMute;
        this.musicActive = musicActive;
        this.speakerOn = speakerOn;
        this.headsetOn = headsetOn;
    }

    public Object[] getDataToPrint(){

        return Arrays.asList(ringerMode, alarmVolume, musicVolume, notificationVolume, ringVolume,
                bluetoothScoOn, microphoneMute, musicVolume, speakerOn, headsetOn).toArray();
    }

    @Override
    public String toString(){
        return Utils.formatLogOutput(ringerMode, alarmVolume, musicVolume, notificationVolume,
                ringVolume, bluetoothScoOn, microphoneMute, musicActive, speakerOn, headsetOn);
    }
}
