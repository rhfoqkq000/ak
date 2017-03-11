package com.donga.examples.boomin.retrofit.retrofitNormalFcm;

/**
 * Created by pmkjkr on 2017. 3. 7..
 */

public class JsonRequest2 {
    String title, body, contents;

    public JsonRequest2(String title, String body, String contents) {
        this.title = title;
        this.body = body;
        this.contents = contents;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public String getContents() {
        return contents;
    }
}
