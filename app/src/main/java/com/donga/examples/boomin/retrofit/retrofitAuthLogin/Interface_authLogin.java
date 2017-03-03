package com.donga.examples.boomin.retrofit.retrofitAuthLogin;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_authLogin {
    @FormUrlEncoded
    @POST("/auth/login")
    Call<Master> authLogin(@Field("email") String email, @Field("password") String password);
}