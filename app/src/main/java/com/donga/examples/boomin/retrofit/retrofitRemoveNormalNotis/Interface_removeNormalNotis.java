package com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_removeNormalNotis {
    @POST("/removeNormalNotis")
    Call<Master> removeNormalNotis(@Header("Content-Type") String appjson, @Body JsonRequest body);
}