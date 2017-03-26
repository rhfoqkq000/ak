package com.donga.examples.boomin.Singleton;

import java.util.ArrayList;

/**
 * Created by pmkjkr on 2017. 3. 8..
 */

public class ScheduleSingleton {
    private static ScheduleSingleton mInstance = null;
    private int currentMinTime;
    private int currentMaxTime;

    public static ScheduleSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new ScheduleSingleton();
        }
        return mInstance;
    }

    public int getCurrentMinTime() {
        return currentMinTime;
    }

    public void setCurrentMinTime(int currentMinTime) {
        this.currentMinTime = currentMinTime;
    }

    public int getCurrentMaxTime() {
        return currentMaxTime;
    }

    public void setCurrentMaxTime(int currentMaxTime) {
        this.currentMaxTime = currentMaxTime;
    }
}
