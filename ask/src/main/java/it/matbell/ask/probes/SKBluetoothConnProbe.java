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

import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.HashSet;
import java.util.Set;

import it.matbell.ask.model.BTDevice;

/**
 * This probe monitors the Bluetooth connections. Reports both the name and address of each
 * connected device.
 *
 * Requires:
 *
 *  - "android.permission.BLUETOOTH"
 */
@SuppressWarnings("unused")
class SKBluetoothConnProbe extends SKOnEventProbe {

    private Set<BTDevice> connectedDevices = new HashSet<>();
    private BTEventsReceiver receiver;

    @Override
    public void init() {

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_CONNECTED);
        intentFilter.addAction(BluetoothDevice.ACTION_ACL_DISCONNECTED);

        receiver = new BTEventsReceiver();
        getContext().registerReceiver(receiver, intentFilter);
    }

    @Override
    public void onFirstRun() {}

    @Override
    void onStop() {

        getContext().unregisterReceiver(receiver);
    }

    private void printData(){
        logOnFile(true, connectedDevices);
    }

    class BTEventsReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if(action != null) {

                BTDevice device;

                switch (action) {

                    case BluetoothDevice.ACTION_ACL_CONNECTED:

                        device = new BTDevice((BluetoothDevice) intent.getParcelableExtra(
                                BluetoothDevice.EXTRA_DEVICE));

                        if(!connectedDevices.contains(device)) {
                            connectedDevices.add(device);
                            printData();
                        }

                        break;

                    case BluetoothDevice.ACTION_ACL_DISCONNECTED:

                        device = new BTDevice((BluetoothDevice) intent.getParcelableExtra(
                                BluetoothDevice.EXTRA_DEVICE));

                        if(connectedDevices.contains(device)) {
                            connectedDevices.remove(device);
                            printData();
                        }
                        break;
                }
            }

        }
    }
}
