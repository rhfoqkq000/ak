package com.donga.examples.boomin.retrofit.retrofitGetcircleNotis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getCircleNotis {
    @GET("getCircleNotis")
    Call<Master> getPCircleNotis(@Query("user_id") String user_id);
}