package com.donga.examples.boomin.retrofit.retrofitRoom;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by pmk on 17. 2. 8.
 */

public interface Interface_room {
    @GET("/getWebSeat")
    Call<Master4> getRoom();
}