package com.donga.examples.boomin;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.donga.examples.boomin.Singleton.ManageSingleton;
import com.donga.examples.boomin.retrofit.retrofitAuthLogin.Interface_authLogin;
import com.donga.examples.boomin.retrofit.retrofitAuthLogin.Master;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MinServiceClass extends Service {
    Thread th = null;
    AppendLog log = new AppendLog();

    public MinServiceClass() {
    }

    @Override
    public void onCreate() {

        th = new Thread(new Runnable(){
            public void run() {
                try{
                    // TODO Auto-generated method stub
                    while(!th.isInterrupted())
                    {
                        Thread.sleep(3000000);

                        //retrofit 통신
                        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                                .addConverterFactory(GsonConverterFactory.create()).build();
                        Interface_authLogin auth = client.create(Interface_authLogin.class);
                        Call<Master> call = auth.authLogin(ManageSingleton.getInstance().getManagerID(),
                                ManageSingleton.getInstance().getManagePW());
                        call.enqueue(new Callback<Master>() {
                            @Override
                            public void onResponse(Call<Master> call, Response<Master> response) {
                                ManageSingleton.getInstance().setToken(response.body().getToken());
                            }

                            @Override
                            public void onFailure(Call<Master> call, Throwable t) {
                                log.appendLog("inMinService failure");
                                t.printStackTrace();
                            }
                        });
                    }
                }catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
        });

        th.start();

        super.onCreate();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        if(th!=null&&th.isAlive()){
            th.interrupt();
            th = null;
        }
        stopSelf();
        super.onDestroy();
    }
}
