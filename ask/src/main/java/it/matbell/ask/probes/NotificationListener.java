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

import android.content.Intent;
import android.os.IBinder;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import it.matbell.ask.commons.Utils;

class NotificationListener extends NotificationListenerService {

    @Override
    public IBinder onBind(Intent intent) {
        return super.onBind(intent);
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn){

        Log.d(Utils.TAG, sbn.getPackageName() +"," +sbn.getTag() + ","
                + sbn.getNotification().category +", "+sbn.getNotification().toString());

    }

    @Override
    public void onNotificationRemoved(StatusBarNotification sbn){

        StatusBarNotification[] activeNotifications = this.getActiveNotifications();

        if(activeNotifications != null && activeNotifications.length > 0) {
            for (int i = 0; i < activeNotifications.length; i++) {

                Log.d(Utils.TAG, activeNotifications[i].getPackageName() +","
                        +activeNotifications[i].getTag() + "," + activeNotifications[i].getNotification().category
                +", "+activeNotifications[i].getNotification().toString());

            }
        }
    }
}
