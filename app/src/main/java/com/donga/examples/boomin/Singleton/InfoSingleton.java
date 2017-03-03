package com.donga.examples.boomin.Singleton;

/**
 * Created by pmk on 17. 2. 16.
 */
public class InfoSingleton {
    private static InfoSingleton mInstance = null;
    private String stuId, stuPw, year, id, fromGetCircleNoticeID;

    public static InfoSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new InfoSingleton();
        }
        return mInstance;
    }

    public String getStuId() {
        return stuId;
    }

    public void setStuId(String stuId) {
        this.stuId = stuId;
    }

    public String getStuPw() {
        return stuPw;
    }

    public void setStuPw(String stuPw) {
        this.stuPw = stuPw;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFromGetCircleNoticeID() {
        return fromGetCircleNoticeID;
    }

    public void setFromGetCircleNoticeID(String fromGetCircleNoticeID) {
        this.fromGetCircleNoticeID = fromGetCircleNoticeID;
    }
}
