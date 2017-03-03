package com.donga.examples.boomin.retrofit.retrofitUpdateToken;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_UpdateToken {
    @FormUrlEncoded
    @POST("deviceUpdate")
    Call<Master> update(@Field("device_id") String device_id, @Field("push_service_id") String push_service_id);
}