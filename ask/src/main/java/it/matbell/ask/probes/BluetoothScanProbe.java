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

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import java.util.HashSet;
import java.util.Set;

import it.matbell.ask.model.BTDevice;

/**
 * This probe performs a continuous Bluetooth scan and reports both name and address of the BT
 * devices in proximity.
 *
 * Requires:
 *
 *  - "android.permission.BLUETOOTH"
 *  - "android.permission.BLUETOOTH_ADMIN"
 *
 */
@SuppressWarnings("unused")
class BluetoothScanProbe extends ContinuousProbe {

    private BluetoothAdapter mBtAdapter = BluetoothAdapter.getDefaultAdapter();
    private Set<BTDevice> devices = new HashSet<>();

    private BroadcastReceiver btReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            String action = intent.getAction();

            if (BluetoothDevice.ACTION_FOUND.equals(action)){

                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);

                devices.add(new BTDevice(device));
            }
        }
    };

    @Override
    public void init() {
        getContext().registerReceiver(btReceiver, new IntentFilter(BluetoothDevice.ACTION_FOUND));
    }

    @Override
    public void onFirstRun() {}

    @SuppressWarnings("all")
    @Override
    void onStop() {
        getContext().unregisterReceiver(btReceiver);
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }
    }

    @SuppressWarnings("all")
    @Override
    public void exec() {
        if (mBtAdapter.isDiscovering()) {
            mBtAdapter.cancelDiscovery();
        }

        logOnFile(true, devices);

        devices = new HashSet<>();
        mBtAdapter.startDiscovery();
    }
}
