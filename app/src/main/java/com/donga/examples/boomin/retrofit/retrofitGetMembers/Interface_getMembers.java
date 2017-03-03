package com.donga.examples.boomin.retrofit.retrofitGetMembers;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getMembers {
    @GET("getMembers")
    Call<Master> getMembers(@Header("Authorization") String token);
}