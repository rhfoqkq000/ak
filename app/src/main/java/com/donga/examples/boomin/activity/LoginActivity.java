package com.donga.examples.boomin.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.telephony.TelephonyManager;
import android.util.Base64;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.retrofit.retrofitCheckCircle.Interface_checkCircle;
import com.donga.examples.boomin.retrofit.retrofitFirstLogin.Interface_FirstLogin;
import com.donga.examples.boomin.retrofit.retrofitLogin.Interface_login;
import com.donga.examples.boomin.retrofit.retrofitLogin.Master;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {
    private ProgressDialog mProgressDialog;

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    AppendLog log = new AppendLog();

    @BindView(R.id.s_id)
    EditText s_id;
    @BindView(R.id.s_pw)
    EditText s_pw;

    @OnClick(R.id.login_bt)
    void loginButton() {
        showProgressDialog();

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_login room = client.create(Interface_login.class);
        Call<Master> call4 = room.loginUser(String.valueOf(s_id.getText().toString()), s_pw.getText().toString());
        Log.i("inLoginActivity", "id:" + s_id.getText().toString() + ",pw:" + s_pw.getText().toString());
        call4.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                if (response.body().getResult_code() == 1) {
                    final SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

                    final SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("stuID", response.body().getResult_body().getStuId());
                    editor.putInt("ID", response.body().getResult_body().getId());
                    editor.putString("major", response.body().getResult_body().getMajor());
                    editor.putInt("isFirst", 22);

                    InfoSingleton.getInstance().setId(String.valueOf(response.body().getResult_body().getId()));
                    editor.putString("UUID", GetDevicesUUID(getApplicationContext()));
                    try {
                        editor.putString("pw", Encrypt(s_pw.getText().toString(), getString(R.string.decrypt_key)));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    editor.commit();

                    //Firebase push token
                    String token = FirebaseInstanceId.getInstance().getToken();
                    Log.e("PUSH_SERVICE_TOKEN", token);

                    Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                            .addConverterFactory(GsonConverterFactory.create()).build();
                    Interface_FirstLogin firstLogin = client.create(Interface_FirstLogin.class);
                    TelephonyManager tManager = (TelephonyManager) getBaseContext()
                            .getSystemService(Context.TELEPHONY_SERVICE);
                    Call<com.donga.examples.boomin.retrofit.retrofitFirstLogin.Master> call5 = firstLogin.loginUser(GetDevicesUUID(getApplicationContext()),
                            "ANDROID", Build.MODEL, tManager.getNetworkOperatorName(), String.valueOf(Build.VERSION.SDK_INT), token, String.valueOf(sharedPreferences.getInt("ID", 0)));
                    call5.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitFirstLogin.Master>() {
                        @Override
                        public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitFirstLogin.Master> call,
                                               Response<com.donga.examples.boomin.retrofit.retrofitFirstLogin.Master> response) {
                            if (response.body().getResult_code() == 1 || response.body().getResult_code() == 2) {
                                Log.i("FirstLoginOnResponse", String.valueOf(response.body().getResult_code()));

                                InfoSingleton.getInstance().setStuId(String.valueOf(sharedPreferences.getInt("stuID", 0)));
                                InfoSingleton.getInstance().setStuPw(sharedPreferences.getString("pw", ""));


                                Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                                        .addConverterFactory(GsonConverterFactory.create()).build();
                                Interface_checkCircle chk = client.create(Interface_checkCircle.class);
                                Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call7 = chk.checkCircle(String.valueOf(sharedPreferences.getInt("ID", 0)));
                                Log.i("shared?ID?", String.valueOf(sharedPreferences.getInt("ID", 0)));
                                call7.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master>() {
                                    @Override
                                    public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> response) {
                                        Log.i("rrrrrr", ""+response.body().getResult_code());
                                        if(response.body().getResult_code() == 1){
                                            //선택된 동아리가 있을 때
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            Log.i("LoginActivity", "동아리잇다");
                                            editor.putInt("checkCircle", 1);
                                            hideProgressDialog();
                                            startActivity(intent);
                                        }else{
                                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                                            hideProgressDialog();
                                            Log.i("LoginActivity", "동아리업다");
                                            editor.putInt("checkCircle", 0);
                                            startActivity(intent);
                                        }

                                    }

                                    @Override
                                    public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitCheckCircle.Master> call, Throwable t) {
                                        t.printStackTrace();
                                    }
                                });



                            } else {
                                log.appendLog("FirstLogin code not matched");
                                hideProgressDialog();
                                Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT);
                            }
                        }

                        @Override
                        public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitFirstLogin.Master> call, Throwable t) {
                            log.appendLog("FirstLogin onFailure");
                            hideProgressDialog();
                            Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT);
                            t.printStackTrace();
                        }
                    });


                } else {
                    Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT);
                    log.appendLog("inLoginActivity Att2 code not matched");
                    hideProgressDialog();
                }

            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "로그인 실패!", Toast.LENGTH_SHORT);
                log.appendLog("inLoginActivity login failure");
                hideProgressDialog();
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        if (sharedPreferences.contains("stuID") && sharedPreferences.contains("ID") && sharedPreferences.contains("pw")) {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
            Log.i("로그인 에러", "아놔");
        } else {
            Toast.makeText(getApplicationContext(), "로그인해주세요", Toast.LENGTH_SHORT).show();
        }
    }

    // MD5 암호화
    public static String Encrypt(String text, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.ENCRYPT_MODE, keySpec, ivSpec);

        byte[] results = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.encodeToString(results, 0);
    }

    // Device UUID 구하기
    private String GetDevicesUUID(Context mContext) {
        final TelephonyManager tm = (TelephonyManager) mContext.getSystemService(Context.TELEPHONY_SERVICE);
        final String tmDevice, tmSerial, androidId;
        tmDevice = "" + tm.getDeviceId();
        tmSerial = "" + tm.getSimSerialNumber();
        androidId = "" + android.provider.Settings.Secure.getString(getContentResolver(), android.provider.Settings.Secure.ANDROID_ID);
        UUID deviceUuid = new UUID(androidId.hashCode(), ((long) tmDevice.hashCode() << 32) | tmSerial.hashCode());
        String deviceId = deviceUuid.toString();
        return deviceId;
    }

    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {
            super.onBackPressed();
        } else {
            backPressedTime = tempTime;
            Toast.makeText(getApplicationContext(), "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        finish();
        super.onPause();
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    private void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.hide();
            mProgressDialog.dismiss();
        }
    }
}
