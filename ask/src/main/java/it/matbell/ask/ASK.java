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

package it.matbell.ask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

@SuppressWarnings("unused")
public class ASK {

    private String jsonConfiguration;
    private Context context;

    /**
     * ASK Contructor.
     *
     * @param context               The application's context.
     * @param jsonConfiguration     The ASK configuration in Json format.
     */
    public ASK(Context context, String jsonConfiguration){
        this.context = context;
        this.jsonConfiguration = jsonConfiguration;
    }

    /**
     * Starts the background service and probes.
     */
    public void start(){

        if(!ASKManager.RUNNING) {
            Log.d("ASK", "Starting ASK");
            Intent intent = new Intent(context, ASKManager.class);
            intent.putExtra(ASKManager.SETUP_KEY, jsonConfiguration);
            context.startService(intent);
        }
    }

    public void stop(){

        if(ASKManager.RUNNING){

            context.stopService(new Intent(context, ASKManager.class));
        }
    }
}
