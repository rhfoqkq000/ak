package com.donga.examples.boomin.retrofit.retrofitNormalNotis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getNormalNotis {
    @GET("getNormalNotis")
    Call<Master> getNormalNotis(@Query("user_id") String user_id);
}