package com.donga.examples.boomin.retrofit.retrofitRemoveCircleNotis;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_removeCircleNotis {
    @POST("/removeCircleNotis")
    Call<Master> removeCircleNotis(@Header("Content-Type") String appjson, @Body JsonRequest body);
}