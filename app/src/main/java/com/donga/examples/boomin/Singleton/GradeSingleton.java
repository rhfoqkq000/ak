package com.donga.examples.boomin.Singleton;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by pmk on 17. 2. 17.
 */
public class GradeSingleton {
    private static GradeSingleton mInstance = null;
    String allGrade, avgGrade, partGrade, partAvg;
    ArrayList<ArrayList<String>> detail;
    ArrayList<Integer> position;
    ArrayList<String> year;
    ArrayList<ArrayList<String>> detail2;
    HashMap<String, HashMap<String, String>> bottom;

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

    public String getAllGrade() {
        return allGrade;
    }

    public void setAllGrade(String allGrade) {
        this.allGrade = allGrade;
    }

    public String getAvgGrade() {
        return avgGrade;
    }

    public void setAvgGrade(String avgGrade) {
        this.avgGrade = avgGrade;
    }

    public ArrayList<ArrayList<String>> getDetail() {
        return detail;
    }

    public void setDetail(ArrayList<ArrayList<String>> detail) {
        this.detail = detail;
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
}
