package com.donga.examples.boomin.retrofit.retrofitGetCircle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getCircle {
    @GET("donga/getCircle")
    Call<Master> getCircle(@Query("major") String major);
}