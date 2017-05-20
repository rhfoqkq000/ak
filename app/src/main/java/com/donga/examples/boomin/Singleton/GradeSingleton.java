package com.donga.examples.boomin.Singleton;

import com.donga.examples.boomin.retrofit.retrofitAchieveAll.Result_body;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pmk on 17. 2. 17.
 */
public class GradeSingleton {
    private static GradeSingleton mInstance = null;
    String partGrade, partAvg;
    ArrayList<Integer> position;
    ArrayList<String> year;
    ArrayList<ArrayList<String>> detail2;

    HashMap<String, HashMap<String, String>> bottom;
    Result_body AllResultBody;

    public String getPartAvg() {
        return partAvg;
    }

    public void setPartAvg(String partAvg) {
        this.partAvg = partAvg;
    }

    public String getPartGrade() {
        return partGrade;
    }

    public void setPartGrade(String partGrade) {
        this.partGrade = partGrade;
    }

    public static GradeSingleton getInstance() {
        if(mInstance == null)
        {
            mInstance = new GradeSingleton();
        }
        return mInstance;
    }

    public ArrayList<Integer> getPosition() {
        return position;
    }

    public void setPosition(ArrayList<Integer> position) {
        this.position = position;
    }

    public ArrayList<String> getYear() {
        return year;
    }

    public void setYear(ArrayList<String> year) {
        this.year = year;
    }

    public ArrayList<ArrayList<String>> getDetail2() {
        return detail2;
    }

    public void setDetail2(ArrayList<ArrayList<String>> detail2) {
        this.detail2 = detail2;
    }

    public HashMap<String, HashMap<String, String>> getBottom() {
        return bottom;
    }

    public void setBottom(HashMap<String, HashMap<String, String>> bottom) {
        this.bottom = bottom;
    }

    public Result_body getAllResultBody() {
        return AllResultBody;
    }

    public void setAllResultBody(Result_body allResultBody) {
        AllResultBody = allResultBody;
    }
}
