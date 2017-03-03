package com.donga.examples.boomin.retrofit.retrofitGetMembers;

import java.util.ArrayList;

/**
 * Created by pmk on 17. 2. 8.
 */

public class Master {
    int result_code;
    ArrayList<Member> result_body;

    public ArrayList<Member> getResult_body() {
        return result_body;
    }

    public int getResult_code() {
        return result_code;
    }
}
