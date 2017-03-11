package com.donga.examples.boomin.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.ManageSingleton;
import com.donga.examples.boomin.retrofit.retrofitCircleFcm.Interface_CircleFcm;
import com.donga.examples.boomin.retrofit.retrofitCircleFcm.Master;
import com.donga.examples.boomin.retrofit.retrofitNormalFcm.Interface_normalFcm;
import com.donga.examples.boomin.retrofit.retrofitNormalFcm.JsonRequest;
import com.donga.examples.boomin.retrofit.retrofitNormalFcm.JsonRequest2;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Manage_LetterFragment extends Fragment {
    @BindView(R.id.manage_letter_spinner)
    Spinner manage_letter_spinner;
    @BindView(R.id.manage_letter_name)
    EditText manage_letter_name;
    @BindView(R.id.manage_letter_title)
    EditText manage_letter_title;
    @BindView(R.id.manage_letter_content)
    EditText manage_letter_content;
    @BindView(R.id.manage_letter_send)
    CardView manage_letter_send;

    AppendLog log = new AppendLog();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_letter, container, false);
        ButterKnife.bind(this, rootview);

        return rootview;
    }

    @OnClick(R.id.manage_letter_send)
    void onSendClick(){
        if(manage_letter_spinner.getSelectedItemPosition() == 0){
            //일반 공지 보낼 시
            Logger.d("일반");
            //retrofit 통신
            Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Interface_normalFcm fcm = client.create(Interface_normalFcm.class);
            JsonRequest2 jsonRequest2 = new JsonRequest2(manage_letter_title.getText().toString(),
                    manage_letter_name.getText().toString(), manage_letter_content.getText().toString());
            Call<com.donga.examples.boomin.retrofit.retrofitNormalFcm.Master> call = fcm.sendFcm("bearer "+ManageSingleton.getInstance().getToken(),
                    "application/json", new JsonRequest(jsonRequest2));

            call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitNormalFcm.Master>() {
                @Override
                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitNormalFcm.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitNormalFcm.Master> response) {
                    Log.i("onresponse", "done");
                    Toast.makeText(getContext(), "전송 완료!", Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitNormalFcm.Master> call, Throwable t) {
                    Toast.makeText(getContext(), "전송 실패!", Toast.LENGTH_SHORT).show();

                    t.printStackTrace();
                }
            });
        } else{
            //행사참여여부 공지 보낼 시
            //retrofit 통신
            Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Interface_CircleFcm fcm = client.create(Interface_CircleFcm.class);
            Call<Master> call = fcm.sendFcm("bearer "+ManageSingleton.getInstance().getToken(),
                    manage_letter_name.getText().toString(), manage_letter_title.getText().toString(), manage_letter_content.getText().toString());
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<Master> call, Response<Master> response) {
                    if(response.body().getSuccess().equals("HTTP 요청 처리 완료")){
                        Toast.makeText(getContext(), "전송 완료", Toast.LENGTH_SHORT).show();
                    }else{
                        log.appendLog("inLetterFragment code not matched");
                        Toast.makeText(getContext(), "전송 실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Master> call, Throwable t) {
                    t.printStackTrace();
                    log.appendLog("inLetterFragment failure");
                    Toast.makeText(getContext(), "전송 실패", Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}