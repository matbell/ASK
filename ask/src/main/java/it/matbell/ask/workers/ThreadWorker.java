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

package it.matbell.ask.workers;

import android.util.Log;

import it.matbell.ask.commons.Utils;
import it.matbell.ask.probes.ContinuousProbe;

public class ThreadWorker extends Worker {

    private RunThread thread;
    private boolean active;

    public ThreadWorker(ContinuousProbe probe, boolean firstRun){
        super(probe, firstRun);
    }

    @Override
    public void start() {

        if(!active){

            active = true;
            thread = new RunThread();
            thread.start();
        }
    }

    @Override
    public void stop() {

        active = false;
        thread = null;

    }

    class RunThread extends Thread{

        @Override
        public void run() {
            super.run();

            try {

                sleep(getProbe().getStartDelay() * 1000);

                if(isFirstRun()) getProbe().onFirstRun();

                while (active) {

                    ContinuousProbe continuousProbe = (ContinuousProbe) getProbe();
                    continuousProbe.exec();
                    sleep(continuousProbe.getInterval() * 1000);
                }

                getProbe().stop();

            }catch (InterruptedException e){
                Log.e(Utils.TAG, e.getMessage());
            }
        }

    }
}
