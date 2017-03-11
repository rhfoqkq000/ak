package com.donga.examples.boomin.retrofit.retrofitNormalFcm;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_normalFcm {
    @POST("/normal/fcm")
    Call<Master> sendFcm(@Header("Authorization") String token, @Header("Content-Type") String appJson, @Body JsonRequest body);
}