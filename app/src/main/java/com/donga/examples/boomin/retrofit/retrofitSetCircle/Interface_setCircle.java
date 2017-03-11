package com.donga.examples.boomin.retrofit.retrofitSetCircle;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_setCircle {
    @POST("donga/setCircle")
    Call<Master> setCircle(@Header("Content-Type") String applicationJson, @Body com.donga.examples.boomin.retrofit.retrofitSetCircle.JsonRequest body);
//    Call<Master> setCircle(@Field("user_id") int user_id, @Field("circle_id") String circle_id);

}