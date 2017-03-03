package com.donga.examples.boomin.retrofit.retrofitSchedule;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_sche {
    @FormUrlEncoded
    @POST("/donga/getTimeTable")
    Call<Master> getTimeTable(@Field("stuId") String stuId, @Field("stuPw") String stuPw);
}