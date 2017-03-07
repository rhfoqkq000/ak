package com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis;

import java.util.ArrayList;

/**
 * Created by pmkjkr on 2017. 3. 7..
 */

public class JsonRequest {
    ArrayList<String> targets;

    public JsonRequest(ArrayList<String> requestIds){
        this.targets = requestIds;
    }
}
