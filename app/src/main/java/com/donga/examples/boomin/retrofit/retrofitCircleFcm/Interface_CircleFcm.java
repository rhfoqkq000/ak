package com.donga.examples.boomin.retrofit.retrofitCircleFcm;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_CircleFcm {
    @POST("/circle/fcm")
    Call<Master> sendFcm(@Header("Authorization") String token, @Field("title") String title, @Field("body") String body, @Field("contents") String contents);
}