package com.donga.examples.boomin.retrofit.retrofitGetPushPermit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by horse on 2017. 8. 30..
 */

public interface Interface_getPushPermit {
    @GET("/getPushPermit")
    Call<Master> getPushPermit(@Query("stuId") String stuId);
}
