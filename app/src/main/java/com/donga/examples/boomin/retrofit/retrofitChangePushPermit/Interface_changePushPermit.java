package com.donga.examples.boomin.retrofit.retrofitChangePushPermit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_changePushPermit {
    @GET("/change_push_permit")
    Call<Master> changePushPermit(@Query("user_id") String user_id);
}