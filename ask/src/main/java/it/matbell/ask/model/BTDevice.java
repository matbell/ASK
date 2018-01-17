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

import android.bluetooth.BluetoothDevice;
import android.support.annotation.NonNull;

import java.util.Comparator;

public class BTDevice implements Comparable<BTDevice>, Comparator<BTDevice>, Loggable{

    private String name, address;

    public BTDevice(BluetoothDevice device){
        this.name = device.getName();
        this.address = device.getAddress();
    }

    @Override
    public int compareTo(@NonNull BTDevice o) {
        return this.address.compareTo(o.address);
    }

    @Override
    public int compare(BTDevice o1, BTDevice o2) {
        return o1.address.compareTo(o2.address);
    }

    @Override
    public int hashCode() {
        return address.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BTDevice && ((BTDevice)obj).address.equals(address);
    }

    @Override
    public String getDataToLog() {
        return name + "," + address;
    }
}
