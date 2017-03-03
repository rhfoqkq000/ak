package com.donga.examples.boomin.retrofit.retrofitChange_att;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_change_att {
    @FormUrlEncoded
    @POST("change_att")
    Call<Master> change_att(@Field("circle_notis_id") String circle_notis_id, @Field("att") int att);
}