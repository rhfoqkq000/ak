package com.donga.examples.boomin.Singleton;

import java.util.Map;

/**
 * Created by pmk on 17. 2. 14.
 */
public class PushSingleton {
    private static PushSingleton mInstance = null;
    private String mString;
    private String mStringSend;
    private String mStringTitle;
    private Map mMap;

    public static PushSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new PushSingleton();
        }
        return mInstance;
    }

    private PushSingleton() {

    }

    public String getmString() {
        return mString;
    }

    public void setmString(String mString) {
        this.mString = mString;
    }

    public Map getmMap() {
        return mMap;
    }

    public void setmMap(Map mMap) {
        this.mMap = mMap;
    }

    public String getmStringSend() {
        return mStringSend;
    }

    public void setmStringSend(String mStringSend) {
        this.mStringSend = mStringSend;
    }

    public String getmStringTitle() {
        return mStringTitle;
    }

    public void setmStringTitle(String mStringTitle) {
        this.mStringTitle = mStringTitle;
    }
}
