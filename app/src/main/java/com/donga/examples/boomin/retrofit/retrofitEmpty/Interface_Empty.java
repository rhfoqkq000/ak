package com.donga.examples.boomin.retrofit.retrofitEmpty;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_Empty {
    @GET("donga/empty/room")
    Call<Master> getEmpty(@Query("day") String day, @Query("from") String from, @Query("to") String to);
}