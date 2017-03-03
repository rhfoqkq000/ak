package com.donga.examples.boomin.retrofit.retrofitCheckCircle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_checkCircle {
    @GET("donga/checkCircle")
    Call<Master> checkCircle(@Query("user_id") String user_id);
}