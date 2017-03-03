package com.donga.examples.boomin.retrofit.retrofitGrad;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_grad {
    @FormUrlEncoded
    @POST("/donga/getGraduated")
    Call<Master> getSchedule(@Field("stuId") String stuId, @Field("stuPw") String stuPw);
}