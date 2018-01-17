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

import it.matbell.ask.controllers.CellController;
import it.matbell.ask.model.CellsInfo;

/**
 * This probe monitors the phone cells visible by the local device. For each cell, it reports the
 * folloing information:
 *
 *  - type of the cell (i.e., GSM, LTE, CDMA, or WCDMA)
 *  - cell's id, if available
 *  - signal level in dbm
 *
 * Requires:
 *
 *  - "android.permission.ACCESS_COARSE_LOCATION"
 *  - "android.permission.READ_PHONE_STATE"
 *
 */
@SuppressWarnings("unused")
class CellInfoProbe extends ContinuousProbe {

    @Override
    public void init() {}

    @Override
    public void onFirstRun() {}

    @Override
    void onStop() {}

    @Override
    public void exec() {

        logOnFile(true, new CellsInfo(CellController.getVisibleCells(getContext())));
    }
}
