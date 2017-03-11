package com.donga.examples.boomin.retrofit.retrofitSetCircle;

import java.util.ArrayList;

/**
 * Created by pmkjkr on 2017. 3. 7..
 */

public class JsonRequest {
    int user_id;
    ArrayList<String> circles;

    public JsonRequest(int user_id, ArrayList<String> circles) {
        this.user_id = user_id;
        this.circles = circles;
    }

    public int getUser_id() {
        return user_id;
    }

    public ArrayList<String> getCircles() {
        return circles;
    }
}
