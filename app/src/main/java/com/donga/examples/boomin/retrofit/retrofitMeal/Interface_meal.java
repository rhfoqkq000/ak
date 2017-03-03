package com.donga.examples.boomin.retrofit.retrofitMeal;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_meal {
    @GET("/donga/meal")
    Call<Master3> getMeal(@Query("date") String date);
}