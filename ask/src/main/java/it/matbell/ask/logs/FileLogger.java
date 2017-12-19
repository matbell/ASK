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

import com.snatik.storage.Storage;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FileLogger {

    public static final String BASE_DIR = "AndroidSensingKit";
    private static final String SEP = "\t";

    private static FileLogger instance;
    private Storage storage;
    private String basePath;

    public static FileLogger getInstance(Context context){
        if(instance == null) instance = new FileLogger(context);

        return instance;
    }
    private FileLogger(Context context){

        storage = new Storage(context.getApplicationContext());

        basePath = storage.getExternalStorageDirectory() + File.separator + BASE_DIR;
        storage.createDirectory(basePath);
    }

    public void store(String fileName, double[] data, boolean withTimeStamp){

        List<String> toPrint = new ArrayList<>();
        for(double element : data) toPrint.add(String.valueOf(element));

        store(StringUtils.join(toPrint, SEP), fileName, withTimeStamp);
    }

    public void store(String fileName, int[] data, boolean withTimeStamp){

        List<String> toPrint = new ArrayList<>();
        for(int element : data) toPrint.add(String.valueOf(element));

        store(StringUtils.join(toPrint, SEP), fileName, withTimeStamp);
    }

    public void store(String fileName, boolean withTimeStamp, String...data){

        store(StringUtils.join(data, SEP), fileName, withTimeStamp);
    }

    private void store(String content, String fileName, boolean withTimeStamp){

        String path = basePath+File.separator+fileName;

        Date currentTime = Calendar.getInstance().getTime();

        String toWrite;

        if(withTimeStamp) toWrite = currentTime.getTime() + SEP + content;
        else toWrite = content;

        if(!storage.isFileExist(path))
            storage.createFile(path, toWrite+"\n");
        else
            storage.appendFile(path, toWrite);

    }
}
