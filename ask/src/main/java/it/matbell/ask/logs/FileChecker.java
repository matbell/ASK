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

package it.matbell.ask.logs;

import android.content.Context;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;

import java.io.File;
import java.io.FilenameFilter;

public class FileChecker {

    private static final int DEFAULT_INTERVAL_MIN = 30;
    private static final long DEFAULT_MAX_LOGS_SIZE = 5242880L; // 5MB

    private int interval = DEFAULT_INTERVAL_MIN;
    private long maxLogsSize = DEFAULT_MAX_LOGS_SIZE;
    private FileSender fileSender;
    private Context context;

    private Handler handler;
    private Runnable fileChecker = new Runnable() {
        @Override
        public void run() {
            checkLogFiles();
            handler.postDelayed(fileChecker, interval);
        }
    };

    public FileChecker(Context context, FileSender fileSender, Integer interval,
                       Integer maxLogsSize){

        this.context = context;
        this.fileSender = fileSender;

        if(interval != null) this.interval = interval;
        if(maxLogsSize != null) this.maxLogsSize = maxLogsSize*1024*1024;

        this.interval *= (60*1000);

        handler = new Handler();
        handler.postDelayed(fileChecker, this.interval);
    }

    public void stop(){
        if(handler != null) handler.removeCallbacks(fileChecker);
    }

    private void checkLogFiles() {

        Log.d("FileChecker", "Checking log files...");

        String path = Environment.getExternalStorageDirectory().toString() + "/" + FileLogger.BASE_DIR;

        File directory = new File(path);

        File[] files = directory.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return !name.toLowerCase().endsWith(".zip");
            }
        });

        long totalFileSize = 0;

        for (File file : files) totalFileSize += file.length();

        if (totalFileSize >= maxLogsSize) {
            String zip = new Zipper(context).zip(files);
            Log.d("FileChecker", "Zip created: " + zip);

            if(fileSender != null) {

                File[] zips = directory.listFiles(new FilenameFilter() {
                    public boolean accept(File dir, String name) {
                        return name.toLowerCase().endsWith(".zip");
                    }
                });

                fileSender.send(context, zips);

            }
        }
    }
}
