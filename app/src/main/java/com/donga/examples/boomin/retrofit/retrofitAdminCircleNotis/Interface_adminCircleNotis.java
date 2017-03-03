package com.donga.examples.boomin.retrofit.retrofitAdminCircleNotis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_adminCircleNotis {
    @GET("adminCircleNotis")
    Call<Master> adminCircleNotis(@Header("Authorization") String token, @Query("pcnotis_id") String pcnotis_id);
}