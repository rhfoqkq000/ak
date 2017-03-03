package com.donga.examples.boomin.retrofit.retrofitPNormalNotis;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_getPNormalNotis {
    @GET("getPNormalNotis")
    Call<Master> getPNormalNotis(@Header("Authorization") String token);
}