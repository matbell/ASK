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

import android.view.Display;
import android.view.Surface;

import it.matbell.ask.logs.FileLogger;

/**
 * {@link DisplayInfo#state} can be:
 *      - {@link Display#STATE_ON}
 *      - {@link Display#STATE_OFF}
 *      - {@link Display#STATE_DOZE}
 *      - {@link Display#STATE_DOZE_SUSPEND}
 *      - {@link Display#STATE_UNKNOWN}
 *
 *
 * {@link DisplayInfo#rotation} can be:
 *      - {@link Surface#ROTATION_0}
 *      - {@link Surface#ROTATION_90}
 *      - {@link Surface#ROTATION_180}
 *      - {@link Surface#ROTATION_270}
 */
public class DisplayInfo implements Loggable{

    private int state, rotation;

    public DisplayInfo(Display display){
        this.state = display.getState();
        this.rotation = display.getRotation();
    }

    @Override
    public String getDataToLog() {
        return state + FileLogger.SEP + rotation;
    }
}
