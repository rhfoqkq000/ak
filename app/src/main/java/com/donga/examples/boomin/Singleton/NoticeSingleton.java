package com.donga.examples.boomin.Singleton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by pmk on 17. 2. 13.
 */
public class NoticeSingleton {
    private static NoticeSingleton mInstance = null;
    private ArrayList<String> noticeIdArray = new ArrayList<String>();
    private ArrayList<String> ok_noticeIdArray = new ArrayList<String>();

    public static NoticeSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new NoticeSingleton();
        }
        return mInstance;
    }

    public ArrayList<String> getNoticeIdArray() {
        return noticeIdArray;
    }

    public void setNoticeIdArray(ArrayList<String> noticeIdArray) {
        this.noticeIdArray = noticeIdArray;
    }

    public ArrayList<String> getOk_noticeIdArray() {
        return ok_noticeIdArray;
    }

    public void setOk_noticeIdArray(ArrayList<String> ok_noticeIdArray) {
        this.ok_noticeIdArray = ok_noticeIdArray;
    }
}
