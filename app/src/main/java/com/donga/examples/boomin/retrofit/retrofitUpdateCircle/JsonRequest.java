package com.donga.examples.boomin.retrofit.retrofitUpdateCircle;

import java.util.ArrayList;

/**
 * Created by pmkjkr on 2017. 3. 7..
 */

public class JsonRequest {
    int user_id;
    ArrayList<String> news;

    public JsonRequest(int user_id, ArrayList<String> news){
        this.user_id = user_id;
        this.news = news;
    }
}
