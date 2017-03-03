package com.donga.examples.boomin.retrofit.retrofitGetPCircleNotis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getPCircleNotis {
    @GET("getPCircleNotis")
    Call<Master> getPCircleNotis(@Header("Authorization") String token);
}