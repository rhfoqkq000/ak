package com.donga.examples.boomin.retrofit.retrofitAchieveAll;

import java.util.ArrayList;

/**
 * Created by pmk on 17. 2. 16.
 */

public class Result_body {
    String allGrade, avgGrade;
    ArrayList<ArrayList<String>> detail;

    public String getAllGrade() {
        return allGrade;
    }

    public String getAvgGrade() {
        return avgGrade;
    }

    public ArrayList<ArrayList<String>> getDetail() {
        return detail;
    }
}
