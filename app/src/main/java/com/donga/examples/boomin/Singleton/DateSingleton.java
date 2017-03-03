package com.donga.examples.boomin.Singleton;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by pmk on 17. 2. 13.
 */
public class DateSingleton {
    private static DateSingleton mInstance = null;
    private String mString;

    public static DateSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new DateSingleton();
        }
        return mInstance;
    }

    private DateSingleton() {
        SimpleDateFormat msimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        mString = msimpleDateFormat.format(currentTime);
    }

    public String getmString() {
        return mString;
    }

    public void setmString(String mString) {
        this.mString = mString;
    }
}
