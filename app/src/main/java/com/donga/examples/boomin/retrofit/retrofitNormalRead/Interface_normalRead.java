package com.donga.examples.boomin.retrofit.retrofitNormalRead;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_normalRead {
    @FormUrlEncoded
    @POST("/normal_read")
    Call<Master> readFcm(@Field("notis_id") String notis_id);
}