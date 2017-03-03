package com.donga.examples.boomin.retrofit.retrofitCircleRead;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_circleRead {
    @FormUrlEncoded
    @POST("/circle_read")
    Call<Master> readFcm(@Field("circle_notis_id") String circle_notis_id);
}