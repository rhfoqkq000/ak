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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewAdapter.SelectListViewAdapter;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Interface_getCircle;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Master;
import com.donga.examples.boomin.retrofit.retrofitMeal.Interface_meal;
import com.donga.examples.boomin.retrofit.retrofitMeal.Master3;
import com.donga.examples.boomin.retrofit.retrofitSetCircle.Circles;
import com.donga.examples.boomin.retrofit.retrofitSetCircle.Interface_setCircle;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.orhanobut.logger.Logger;

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
    MaterialSpinner select_spinner;
    @BindView(R.id.list_select)
    ListView listView;

    SelectListViewAdapter adapter;

    @OnClick(R.id.popup_close)
    void onCloseClicked() {
        finish();
    }

    @OnClick(R.id.select_btn_ok)
    void onOkClicked(){
        int selected = select_spinner.getSelectedIndex();
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

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        setContentView(R.layout.activity_select_dialog);
        ButterKnife.bind(this);

        ArrayList<String> list_major = new ArrayList<String>();
        list_major.add("경영정보학과");
        list_major.add("경영학과");

        select_spinner.setItems(list_major);

        select_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Logger.d(""+select_spinner.getItems().get(position));
                Log.i("selectDialog", ""+select_spinner.getItems().get(position));
            }
        });


//        SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
//        String major = sharedPreferences.getString("major", "");
//
//        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        final Interface_getCircle getCircle = client.create(Interface_getCircle.class);
//        retrofit2.Call<Master> call = getCircle.getCircle(major);
//        final ArrayList<String> circleNames = new ArrayList<>();
//        circleIds = new ArrayList<>();
//        call.enqueue(new Callback<Master>() {
//            @Override
//            public void onResponse(Call<Master> call, Response<Master> response) {
//                if(response.body().getResult_code() == 1){
//                    for(int i = 0; i<response.body().getResult_body().size(); i++){
//                        circleNames.add(response.body().getResult_body().get(i).getName());
//                        circleIds.add(response.body().getResult_body().get(i).getId());
//                    }
//                    select_spinner.setItems(circleNames);
//                    Log.i("done", "done");
//                }else{
//                    log.appendLog("inSelectDialog code not matched");
//                }
//            }
//
//            @Override
//            public void onFailure(Call<Master> call, Throwable t) {
//                log.appendLog("inSelectDialog failure");
//                t.printStackTrace();
//            }
//        });
    }
}