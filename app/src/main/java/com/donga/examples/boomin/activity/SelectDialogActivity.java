package com.donga.examples.boomin.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.Spinner;
import android.widget.TextView;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Interface_getCircle;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Master;
import com.donga.examples.boomin.retrofit.retrofitMeal.Interface_meal;
import com.donga.examples.boomin.retrofit.retrofitMeal.Master3;
import com.donga.examples.boomin.retrofit.retrofitSetCircle.Circles;
import com.donga.examples.boomin.retrofit.retrofitSetCircle.Interface_setCircle;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SelectDialogActivity extends Activity {
    AppendLog log = new AppendLog();
    ArrayList<String> circleIds = null;

    ArrayList<Circles> arrayCircles;
    @BindView(R.id.select_spinner)
    Spinner select_spinner;

    @OnClick(R.id.popup_close)
    void onCloseClicked() {
        finish();
    }

    @OnClick(R.id.select_btn_ok)
    void onOkClicked(){
        int selected = select_spinner.getSelectedItemPosition();
        if(selected>-1){
            Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Interface_setCircle setCircle = client.create(Interface_setCircle.class);

            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

            retrofit2.Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call = setCircle.setCircle(sharedPreferences.getInt("ID", 0), circleIds.get(selected));

            call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master>() {
                @Override
                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> response) {
                    Log.i("successsss?", String.valueOf(response.body().getResult_code()));

                    finish();
                }

                @Override
                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }else{
//            Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
//                    .addConverterFactory(GsonConverterFactory.create()).build();
//            Interface_setCircle setCircle = client.create(Interface_setCircle.class);
//
////        Circles circles = new Circles(Integer.parseInt(circleIds.get(selected)));
////        circles.setCircles(Integer.parseInt(circleIds.get(selected)));
////        Log.i("circles", ""+circles.toString());
////        arrayCircles.add(circles);
////        arrayCircles.add(new Circles(Integer.parseInt(circleIds.get(selected))));
//            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
//
//            retrofit2.Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call = setCircle.setCircle(sharedPreferences.getInt("ID", 0), "1");
//
//            call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master>() {
//                @Override
//                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> response) {
//                    Log.i("successsss?", String.valueOf(response.body().getResult_code()));
//
//                    finish();
//                }
//
//                @Override
//                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call, Throwable t) {
//                    t.printStackTrace();
//                }
//            });
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setContentView(R.layout.activity_select_dialog);
        ButterKnife.bind(this);

        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        String major = sharedPreferences.getString("major", "");

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        final Interface_getCircle getCircle = client.create(Interface_getCircle.class);
        retrofit2.Call<Master> call = getCircle.getCircle(major);
        final ArrayList<String> circleNames = new ArrayList<>();
        circleIds = new ArrayList<>();
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                if(response.body().getResult_code() == 1){
//                    circleNames.add("없음");
                    for(int i = 0; i<response.body().getResult_body().size(); i++){
                        circleNames.add(response.body().getResult_body().get(i).getName());
                        circleIds.add(response.body().getResult_body().get(i).getId());
                    }
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, circleNames);
//                    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                            getApplicationContext(),
//                            android.R.layout.simple_list_item_multiple_choice, circleNames){
//                        @NonNull
//                        @Override
//                        public View getView(int position, View convertView, ViewGroup parent) {
//                            View view =super.getView(position, convertView, parent);
//
//                            CheckedTextView textView=(CheckedTextView) view.findViewById(android.R.id.text1);
//
//                            textView.setCheckMarkDrawable(0);
//                            textView.setCompoundDrawablesWithIntrinsicBounds (0, 0,R.drawable.checkbox_selector ,0);
//                            textView.setTextColor(getResources().getColor(R.color.one));
//                            //set background color for view
//                            return view;
//                        }
//                    };
                    select_spinner.setAdapter(adapter);
                    Log.i("done", "done");
                }else{
                    log.appendLog("inSelectDialog code not matched");
                }
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                log.appendLog("inSelectDialog failure");
                t.printStackTrace();
            }
        });
    }
}