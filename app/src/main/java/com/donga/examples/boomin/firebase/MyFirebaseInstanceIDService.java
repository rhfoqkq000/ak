/**
 * Copyright 2016 Google Inc. All Rights Reserved.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.donga.examples.boomin.firebase;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.retrofit.retrofitUpdateToken.Interface_UpdateToken;
import com.donga.examples.boomin.retrofit.retrofitUpdateToken.Master;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    final String SFLAG = "LOGIN";
    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // TODO: Implement this method to send token to your app server.
        SharedPreferences sharedPreferences = getSharedPreferences(SFLAG, Context.MODE_PRIVATE);

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_UpdateToken update = client.create(Interface_UpdateToken.class);
        Log.i("sendReg", String.valueOf(sharedPreferences.getString("UUID", "")));
        Log.i("sendReg", token);

        Call<Master> call = update.update(sharedPreferences.getString("UUID", ""), token);
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                Log.i("IDService", String.valueOf(response.body().getResult_code()));
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
