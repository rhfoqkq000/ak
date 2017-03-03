package com.donga.examples.boomin.retrofit.retrofitProfessor;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_professor {
    @GET("/donga/getPro")
    Call<Master> getPro(@Query("major") String major);
}