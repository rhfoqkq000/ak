package com.donga.examples.boomin;

import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

/**
 * Created by pmk on 17. 2. 16.
 */

public class AppendLog {

    public void appendLog(String text) {
        Calendar calendar = Calendar.getInstance();
        File logFile = new File("sdcard/BOO_log.txt");
//        File logFile = new File(String.valueOf(context.getFilesDir()));
        if (!logFile.exists()) {
            try {
                logFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            BufferedWriter buf = new BufferedWriter(new FileWriter(logFile, true));
            buf.append(text + ",,," + calendar.getTime().toString());
            buf.newLine();
            buf.close();
            Log.i("appendLog", text);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
