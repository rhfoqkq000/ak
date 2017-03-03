package com.donga.examples.boomin.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.ManageSingleton;
import com.donga.examples.boomin.retrofit.retrofitAuthLogin.Interface_authLogin;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ManageLoginActivity extends AppCompatActivity {
    AppendLog log = new AppendLog();

    @BindView(R.id.s_manageId)
    EditText s_manageId;
    @BindView(R.id.s_managePw)
    EditText s_managePw;
    @BindView(R.id.login_manageBt)
    Button login_manageBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_login);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.login_manageBt)
    void onLoginClicked(){
        Log.i("CLICK", s_manageId.getText().toString()+","+s_managePw.getText().toString());

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_authLogin authLogin = client.create(Interface_authLogin.class);
        Call<com.donga.examples.boomin.retrofit.retrofitAuthLogin.Master> call4 =
                authLogin.authLogin(s_manageId.getText().toString(), s_managePw.getText().toString());
        call4.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitAuthLogin.Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitAuthLogin.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitAuthLogin.Master> response) {
                if(response.body().getToken()!=null){
                    ManageSingleton.getInstance().setToken(response.body().getToken());
                    ManageSingleton.getInstance().setManagerID(s_manageId.getText().toString());
                    ManageSingleton.getInstance().setManagePW(s_managePw.getText().toString());

                    Log.i("ManageLoginActivity", "started");
//
                    Intent i = new Intent(getApplicationContext(), ManageActivity.class);
                    startActivity(i);
                }else {
                    try{
                        log.appendLog("inManageLoginActivity code not matched");
                        Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT);
                    }catch(Exception e){
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitAuthLogin.Master> call, Throwable t) {
                log.appendLog("inManageLoginActivity failure");
                Toast.makeText(getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT);
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
