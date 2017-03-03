package com.donga.examples.boomin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.retrofit.retrofitChange_att.Interface_change_att;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-22.
 */
public class Wisper_OkDialogActivity extends Activity {
    AppendLog log = new AppendLog();

    @BindView(R.id.dialog_wisper_content)
    TextView send_content;
    @BindView(R.id.wisper_none_id_dialog)
    TextView wisper_none_id_dialog;

    @OnClick(R.id.wisper_no)
    void wisper_no(){
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_change_att change_att = client.create(Interface_change_att.class);
        Call<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> call = change_att.change_att(wisper_none_id_dialog.getText().toString(), 2);
        call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitChange_att.Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> response) {
                if(response.body().getResult_code() == 1){
                    Log.i("WisperOKDialog", "to 2 change done");
                    finish();
                }else{
                    log.appendLog("inWisper_OkDialog2change code not matched");
                    finish();
                }
            }
            @Override
            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> call, Throwable t) {
                t.printStackTrace();
                log.appendLog("inWisper_OkDialog2change failure");
                finish();
            }
        });
    }

    @OnClick(R.id.wisper_ok)
    void wisper_ok(){
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_change_att change_att = client.create(Interface_change_att.class);
        Call<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> call = change_att.change_att(wisper_none_id_dialog.getText().toString(), 1);
        call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitChange_att.Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> response) {
                if(response.body().getResult_code() == 1){
                    Log.i("WisperOKDialog", "1change done");
                    finish();
                }else{
                    log.appendLog("inWisper_OkDialog1change code not matched");
                    finish();
                }
            }
            @Override
            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitChange_att.Master> call, Throwable t) {
                t.printStackTrace();
                log.appendLog("inWisper_OkDialog1change failure");
                finish();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        setContentView(R.layout.activity_wisper_ok_dialog);
        ButterKnife.bind(this);

        Intent i = getIntent();
        try{
            send_content.setText(i.getExtras().getString("content"));
            wisper_none_id_dialog.setText(i.getExtras().getString("circle_notis_id"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}