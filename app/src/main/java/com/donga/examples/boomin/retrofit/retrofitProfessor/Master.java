package com.donga.examples.boomin.retrofit.retrofitProfessor;

import java.util.ArrayList;

/**
 * Created by pmk on 17. 2. 9.
 */

public class Master {
    int result_code;
    ArrayList<Professor> result_body;

    public ArrayList<Professor> getResult_body() {
        return result_body;
    }

    public int getResult_code() {
        return result_code;
    }
}
