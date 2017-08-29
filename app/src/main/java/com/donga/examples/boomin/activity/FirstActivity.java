package com.donga.examples.boomin.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.Singleton.PushSingleton;
import com.donga.examples.boomin.retrofit.retrofitCheckCircle.Interface_checkCircle;
import com.donga.examples.boomin.retrofit.retrofitDeviceUpdate.Interface_DeviceUpdate;
import com.google.firebase.iid.FirebaseInstanceId;
import com.orhanobut.logger.Logger;

import io.fabric.sdk.android.Fabric;
import com.appsee.Appsee;

import java.util.UUID;

import es.dmoral.toasty.Toasty;
import me.leolin.shortcutbadger.ShortcutBadger;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FirstActivity extends AppCompatActivity {

    AppendLog log = new AppendLog();

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }

    @Override
    protected void onStart() {
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if(sharedPreferences.contains("pushCount")){

        }else{
            int badgeCount = 0;
            ShortcutBadger.applyCount(getApplicationContext(), badgeCount);
            editor.putInt("pushCount", badgeCount);
            editor.apply();
        }

//        networkCheck();

        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        Fabric.with(this);
        Appsee.start(getString(R.string.com_appsee_apikey));
        setContentView(R.layout.splash);
        if (getIntent().getExtras() != null) {
            if (getIntent().getExtras().getString("contents") != null) {
                Log.i("INTENT", getIntent().getExtras().getString("contents"));
                PushSingleton.getInstance().setmString(getIntent().getExtras().getString("contents"));
            }
        }

        final SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedPreferences.edit();

        if (sharedPreferences.contains("stuID") && sharedPreferences.contains("ID") && sharedPreferences.contains("pw")) {

            Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Interface_DeviceUpdate deviceUpdate = client.create(Interface_DeviceUpdate.class);

            TelephonyManager tManager = (TelephonyManager) getBaseContext()
                    .getSystemService(Context.TELEPHONY_SERVICE);
            String token = FirebaseInstanceId.getInstance().getToken();
//            Logger.d("getDevicesUUID::"+GetDevicesUUID(getApplicationContext())+"  , Build.Model::"+Build.MODEL+",  " +
//                    "Operator::"+tManager.getNetworkOperatorName()+",  API LEVEL::"+String.valueOf(Build.VERSION.SDK_INT)+",  token::"+token+
//                    ",  ID::"+String.valueOf(sharedPreferences.getInt("ID", 0)));
            Call<com.donga.examples.boomin.retrofit.retrofitDeviceUpdate.Master> call =
                    deviceUpdate.deviceUpdate(GetDevicesUUID(getApplicationContext()), "ANDROID", Build.MODEL, tManager.getNetworkOperatorName(),
                            String.valueOf(Build.VERSION.SDK_INT), token, String.valueOf(sharedPreferences.getInt("ID", 0)));
            call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitDeviceUpdate.Master>() {
                @Override
                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitDeviceUpdate.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitDeviceUpdate.Master> response) {
                    if (response.body().getResult_code().equals("1")){
                        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                                .addConverterFactory(GsonConverterFactory.create()).build();
                        Interface_checkCircle chk = client.create(Interface_checkCircle.class);
                        Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call7 = chk.checkCircle(String.valueOf(sharedPreferences.getInt("ID", 0)));
                        call7.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master>() {
                            @Override
                            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> response) {
                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);

                                InfoSingleton.getInstance().setStuId(String.valueOf(sharedPreferences.getInt("stuID", 0)));
                                InfoSingleton.getInstance().setStuPw(sharedPreferences.getString("pw", ""));

                                if (PushSingleton.getInstance().getmString() != null) {
                                    Bundle bun = new Bundle();
                                    bun.putString("contents", PushSingleton.getInstance().getmString());
                                    intent.putExtras(bun);
                                }

                                if(response.body().getResult_code() == 1){
                                    //동아리가 있을 때
                                    editor.putInt("checkCircle", 1);
                                    editor.apply();
                                }else{
                                    //동아리가 없을 때
                                    editor.putInt("checkCircle", 0);
                                    editor.commit();
                                }
                                startActivity(intent);
                            }

                            @Override
                            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call, Throwable t) {
                                Logger.d("DeviceUpdate onFailure");
                                Toasty.info(getApplicationContext(), "로그인 실패! 다시 시도해주세요..", Toast.LENGTH_SHORT).show();
                                t.printStackTrace();

                            }
                        });
                    }else{
                        Logger.d("DeviceUpdate ResultCode not matched >.<");
                        Toasty.info(getApplicationContext(), "로그인 실패! 다시 시도해주세요..", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitDeviceUpdate.Master> call, Throwable t) {
                    t.printStackTrace();
                    Toasty.info(getApplicationContext(), "로그인 실패! 다시 시도해주세요..", Toast.LENGTH_SHORT).show();
                }
            });

//            //retrofit 통신
//            Retrofit client = new Retrofit.Builder().baseUrl(
//                    getString(R.string.retrofit_url))
//                    .addConverterFactory(GsonConverterFactory.create()).build();
//            Interface_login room = client.create(Interface_login.class);
//            Call<Master> call4 = null;
//            try {
//                call4 = room.loginUser(String.valueOf(sharedPreferences.getInt("stuID", 0)), Decrypt(sharedPreferences.getString("pw", ""), getString(R.string.decrypt_key)));
//            } catch (Exception e) {
//                e.printStackTrace();
//                log.appendLog("inFirstActivity LOGIN FAILED");
//            }
//            call4.enqueue(new Callback<Master>() {
//                @Override
//                public void onResponse(Call<Master> call, Response<Master> response) {
//                    if (response.body().getResult_code() == 1) {
//
//                        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
//                                .addConverterFactory(GsonConverterFactory.create()).build();
//                        Interface_checkCircle chk = client.create(Interface_checkCircle.class);
//                        Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call7 = chk.checkCircle(String.valueOf(sharedPreferences.getInt("ID", 0)));
//                        call7.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master>() {
//                            @Override
//                            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> response) {
//                                Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
//
//                                InfoSingleton.getInstance().setStuId(String.valueOf(sharedPreferences.getInt("stuID", 0)));
//                                InfoSingleton.getInstance().setStuPw(sharedPreferences.getString("pw", ""));
//
//                                if (PushSingleton.getInstance().getmString() != null) {
//                                    Bundle bun = new Bundle();
//                                    bun.putString("contents", PushSingleton.getInstance().getmString());
//                                    intent.putExtras(bun);
//                                }
//
//                                if(response.body().getResult_code() == 1){
//                                    //동아리가 있을 때
//                                    editor.putInt("checkCircle", 1);
//                                    editor.commit();
//                                }else{
//                                    //동아리가 없을 때
//                                    editor.putInt("checkCircle", 0);
//                                    editor.commit();
//                                }
//                                startActivity(intent);
//                            }
//
//                            @Override
//                            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call, Throwable t) {
//
//                            }
//                        });
//
//
//
//
//                    } else {
//                        log.appendLog("inFirstActivity Att2 code not matched move to LoginActivity");
//                        moveToLoginActivity();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Master> call, Throwable t) {
//                    //retrofit 통신 실패시 SharedPreferences 삭제 후 LoginActivity로 이동
//                    t.printStackTrace();
//                    log.appendLog("inFirstActivity AUTO LOGIN FAILED");
//                    moveToLoginActivity();
//                }
//            });


        } else {
            //기기에 저장된 SharedPreferences 없으면 LoginActivity로 이동
//            log.appendLog("inFirstActivity no sharedPreferences move to LoginActivity");
//            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//            startActivity(intent);
            moveToLoginActivity();
        }
    }



//    public static String Decrypt(String text, String key) throws Exception {
//        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
//        byte[] keyBytes = new byte[16];
//        byte[] b = key.getBytes("UTF-8");
//        int len = b.length;
//        if (len > keyBytes.length) len = keyBytes.length;
//        System.arraycopy(b, 0, keyBytes, 0, len);
//        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
//        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
//        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
//        byte[] results = cipher.doFinal(Base64.decode(text, 0));
//        return new String(results, "UTF-8");
//    }

    public void moveToLoginActivity() {
        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();

        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

//    public Boolean networkCheck() {
//        ConnectivityManager manager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
//        NetworkInfo phone = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
//        NetworkInfo wifi = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
//
//        if(phone.isConnected()&&wifi.isConnected()){
////            Toasty.normal(getApplicationContext(), "wifi", Toast.LENGTH_SHORT).show();
//        }else if(phone.isConnected()&&!wifi.isConnected()){
////            Toasty.normal(getApplicationContext(), "data", Toast.LENGTH_SHORT).show();
//        }else if(!phone.isConnected()&&wifi.isConnected()){
////            Toasty.normal(getApplicationContext(), "wifi", Toast.LENGTH_SHORT).show();
//        }else if(!phone.isConnected()&&!wifi.isConnected()){
////            Toasty.normal(getApplicationContext(), "no internet", Toast.LENGTH_SHORT).show();
//        }
//        return true;
//    }

    // Device UUID 구하기
    private String GetDevicesUUID(Context mContext) {
        final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        return deviceUuid.toString();
    }

}