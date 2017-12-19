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

import it.matbell.ask.commons.Utils;

public class CellInfo {

    public static final int TYPE_GSM = 0;
    public static final int TYPE_LTE = 1;
    public static final int TYPE_CDMA = 2;
    public static final int TYPE_WCDMA = 3;

    private int cellType, id, signalLevel;

    public CellInfo(int cellType, int id, int signalLevel){
        this.cellType = cellType;
        this.id = id;
        this.signalLevel = signalLevel;
    }

    @Override
    public String toString() {
        return Utils.formatLogOutput(cellType, id, signalLevel);
    }
}
