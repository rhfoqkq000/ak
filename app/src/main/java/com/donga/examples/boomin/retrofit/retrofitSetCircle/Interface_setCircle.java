package com.donga.examples.boomin.retrofit.retrofitSetCircle;

import android.provider.ContactsContract;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

import static android.R.attr.data;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_setCircle {
    @FormUrlEncoded
    @POST("donga/setCircle")
//    Call<Master> setCircle(@Header("Content-Type") String applicationJson, @Field("circles[]") ArrayList<Circles> circles);
    Call<Master> setCircle(@Field("user_id") int user_id, @Field("circle_id") String circle_id);

}