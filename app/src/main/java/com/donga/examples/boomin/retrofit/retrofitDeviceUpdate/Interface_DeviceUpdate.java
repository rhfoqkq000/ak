package com.donga.examples.boomin.retrofit.retrofitDeviceUpdate;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_DeviceUpdate {
    @FormUrlEncoded
    @POST("deviceUpdate")
    Call<Master> deviceUpdate(@Field("device_id") String device_id, @Field("os_enum") String os_enum, @Field("model") String model,
                              @Field("operator") String operator, @Field("api_level") String api_level, @Field("push_service_id") String push_service_id,
                              @Field("normal_user_id") String normal_user_id);
}