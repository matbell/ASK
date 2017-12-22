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

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import it.matbell.ask.logs.FileChecker;
import it.matbell.ask.logs.FileSender;
import it.matbell.ask.probes.BaseProbe;
import it.matbell.ask.probes.ContinuousProbe;
import it.matbell.ask.probes.OnEventProbe;
import it.matbell.ask.workers.SimpleWorker;
import it.matbell.ask.workers.ThreadWorker;
import it.matbell.ask.workers.Worker;

public class ASKManager extends Service {

    public static boolean RUNNING = false;

    // Intent used to start the service
    public static final String SETUP_INTENT = "it.cnr.iit.mattia.ask.SETUP_INTENT";
    // Intent's action that contains the Json configuration string
    public static final String SETUP_KEY = "SKSetup";

    // Shared preferences
    private static final String PREFS = "it.cnr.iit.mattia.aka";
    private static final String PREF_LAST_CONFIG_KEY = "lastConfig";
    private static final String PREF_FIRST_RUN_KEY = "firstRun";

    private List<Worker> workers = new ArrayList<>();
    private FileChecker fileChecker;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        for(Worker worker : workers) worker.stop();
        if(fileChecker != null) fileChecker.stop();
        RUNNING = false;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        if(!RUNNING) {
            final String configuration = getConfiguration(intent);

            parseConfiguration(configuration);

            RUNNING = true;
        }

        return Service.START_STICKY;
    }

    /**
     * Creates a {@link Worker} object for each Probe specified in the configuration.
     *
     * @param jsonConf      The Json configuration
     */
    private void parseConfiguration(String jsonConf){
        new ParseTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, jsonConf);
    }

    void startProbes(ASKSetup setup ){

        FileSender fileSender = null;
        if(setup.remoteLogger != null) fileSender = new FileSender(setup.remoteLogger);

        if(setup.zipperInterval != null)
            fileChecker = new FileChecker(getApplicationContext(), fileSender,
                    setup.zipperInterval, setup.maxLogSizeMb);

        for(BaseProbe probe : setup.probes) {

            Worker worker = null;

            if(probe instanceof OnEventProbe)
                worker = new SimpleWorker(probe, isFirstRun());

            else if(probe instanceof ContinuousProbe)
                worker = new ThreadWorker((ContinuousProbe) probe, true);


            if(worker != null){

                worker.start();
                workers.add(worker);
            }
        }

        firstRunDone();
    }

    /**
     * Checks if this is the first time that the service has been executed.
     *
     * @return      True if this is the first execution, False otherwise.
     */
    private boolean isFirstRun(){
        return getSharedPreferences(PREFS, Context.MODE_PRIVATE).getBoolean(
                PREF_FIRST_RUN_KEY, true);
    }

    /**
     * Sets a flag in the shared preferences to remember if the first run has been executed or not.
     */
    private void firstRunDone(){
        getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putBoolean(
                PREF_FIRST_RUN_KEY, false).apply();
    }

    /**
     * Reads the Json configuration. During the first execution, the configuration should be in the
     * Intent's extras. If the system kills and restart the service, the configuration can be find
     * in the shared preferences.
     *
     * @param intent    The Intent object received in the
     *                  {@link Service#onStartCommand(Intent, int, int)} method.
     *
     * @return          The Json configuration.
     */
    private String getConfiguration(Intent intent){

        String configuration;

        if(intent != null && intent.getAction()!= null && intent.getAction().equals(SETUP_INTENT)
                && intent.hasExtra(SETUP_KEY)){
            configuration = intent.getStringExtra(SETUP_KEY);

        }else{

            configuration = getSharedPreferences(PREFS, Context.MODE_PRIVATE).getString(
                    PREF_LAST_CONFIG_KEY, null);

        }

        if(configuration != null)
            getSharedPreferences(PREFS, Context.MODE_PRIVATE).edit().putString(
                    PREF_LAST_CONFIG_KEY, configuration).apply();

        return configuration;
    }

    private class ParseTask extends AsyncTask<String, Void, ASKSetup>{

        @Override
        protected ASKSetup doInBackground(String... conf) {
            return ASKSetup.parse(getApplicationContext(), conf[0]);
        }

        @Override
        protected void onPostExecute(ASKSetup setup) {
            startProbes(setup);
        }
    }
}
