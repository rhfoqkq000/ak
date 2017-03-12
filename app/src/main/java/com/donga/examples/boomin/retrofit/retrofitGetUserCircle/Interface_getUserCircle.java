package com.donga.examples.boomin.retrofit.retrofitGetUserCircle;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getUserCircle {
    @FormUrlEncoded
    @POST("/donga/getUserCircle")
    Call<Master> getUserCircle(@Field("user_id") String user_id);
}