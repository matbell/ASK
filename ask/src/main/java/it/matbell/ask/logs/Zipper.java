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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import it.matbell.ask.controllers.HardwareInfoController;

class Zipper {

    private String baseDir;
    private Context context;

    public Zipper(Context context){

        this.context = context;
        Storage storage = new Storage(context.getApplicationContext());
        baseDir = storage.getExternalStorageDirectory() + File.separator + FileLogger.BASE_DIR;

    }

    public String zip(File[] files){

        List<String> fileList = new ArrayList<>();
        String zipFile = null;

        for(File file : files)
            if(file.isFile())
                fileList.add(generateZipEntry(file.getAbsoluteFile().toString()));

        if(fileList.size() > 0) {

            zipFile = baseDir + File.separator + HardwareInfoController.getDeviceID(context)
            + "_" + (System.currentTimeMillis()/1000) + ".zip";

            createZip(zipFile, fileList);
        }

        return zipFile;
    }

    /**
     * Zip it
     * @param zipFile output ZIP file location
     */
    private void createZip(String zipFile, List<String> inputFiles){

        byte[] buffer = new byte[1024];

        try{

            FileOutputStream fos = new FileOutputStream(zipFile);
            ZipOutputStream zos = new ZipOutputStream(fos);

            System.out.println("Output to Zip : " + zipFile);

            for(String file : inputFiles){

                System.out.println("File Added : " + file);
                ZipEntry ze = new ZipEntry(file);
                zos.putNextEntry(ze);

                FileInputStream in = new FileInputStream(baseDir + File.separator + file);

                int len;
                while ((len = in.read(buffer)) > 0) {
                    zos.write(buffer, 0, len);
                }

                in.close();
            }

            zos.closeEntry();
            //remember close it
            zos.close();

            System.out.println("Done");
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }

    private static String generateZipEntry(String file){
        return file.substring(file.lastIndexOf(File.separator) + 1, file.length());
    }
}
