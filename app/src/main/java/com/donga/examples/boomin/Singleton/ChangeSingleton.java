package com.donga.examples.boomin.Singleton;

import java.util.ArrayList;

/**
 * Created by pmkjkr on 2017. 3. 8..
 */

public class ChangeSingleton {
    private static ChangeSingleton mInstance = null;
    private ArrayList<String> mArray = new ArrayList<String>();
    private ArrayList<String> dialogArray = new ArrayList<String>();

    public static ChangeSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new ChangeSingleton();
        }
        return mInstance;
    }

    public ArrayList<String> getmArray() {
        return mArray;
    }

    public void setmArray(ArrayList<String> mArray) {
        this.mArray = mArray;
    }

    public ArrayList<String> getDialogArray() {
        return dialogArray;
    }

    public void setDialogArray(ArrayList<String> dialogArray) {
        this.dialogArray = dialogArray;
    }
}
