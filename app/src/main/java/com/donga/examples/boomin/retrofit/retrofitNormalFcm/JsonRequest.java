package com.donga.examples.boomin.retrofit.retrofitNormalFcm;

import java.util.ArrayList;

/**
 * Created by pmkjkr on 2017. 3. 7..
 */

public class JsonRequest {
    JsonRequest2 article;

    public JsonRequest(JsonRequest2 article) {
        this.article = article;
    }

    public JsonRequest2 getArticle() {
        return article;
    }
}
