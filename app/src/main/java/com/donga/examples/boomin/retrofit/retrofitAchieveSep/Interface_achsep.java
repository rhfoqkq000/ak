package com.donga.examples.boomin.retrofit.retrofitAchieveSep;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_achsep {
    @FormUrlEncoded
    @POST("/donga/getSpeGrade")
    Call<Master> getSepGrade(@Field("stuId") String stuId, @Field("stuPw") String stuPw, @Field("year") String year, @Field("smt") String smt);
}