package com.donga.examples.boomin.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StyleRes;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

import com.afollestad.materialdialogs.DialogAction;
import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewAdapter.SelectListViewAdapter;
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

public class SelectDialogActivity extends Dialog {
    AppendLog log = new AppendLog();
    ArrayList<String> circleIds = null;

    ArrayList<Circles> arrayCircles;
    @BindView(R.id.select_spinner)
    MaterialSpinner select_spinner;
    @BindView(R.id.list_select)
    ListView listView;

    SelectListViewAdapter adapter;

    public SelectDialogActivity(@NonNull Context context) {
        super(context);
    }


    @OnClick(R.id.popup_close)
    void onCloseClicked() {
        dismiss();
    }

    @OnClick(R.id.select_btn_ok)
    void onOkClicked() {
        int selected = select_spinner.getSelectedIndex();
        if (selected > -1) {
            Retrofit client = new Retrofit.Builder().baseUrl(getContext().getResources().getString(R.string.retrofit_url))
                    .addConverterFactory(GsonConverterFactory.create()).build();
            Interface_setCircle setCircle = client.create(Interface_setCircle.class);

            SharedPreferences sharedPreferences = getContext().getSharedPreferences(getContext().getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

            retrofit2.Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call = setCircle.setCircle(sharedPreferences.getInt("ID", 0), circleIds.get(selected));

            call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master>() {
                @Override
                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> response) {
                    Log.i("successsss?", String.valueOf(response.body().getResult_code()));

                    dismiss();
                }

                @Override
                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitSetCircle.Master> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        } else {

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_select_dialog);
        ButterKnife.bind(this);

        ArrayList<String> list_major = new ArrayList<String>();
        list_major.add("경영정보학과");
        list_major.add("경영학과");

        select_spinner.setItems(list_major);

        select_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener<String>() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, String item) {
                Logger.d("" + select_spinner.getItems().get(position));
                Log.i("selectDialog", "" + select_spinner.getItems().get(position));
            }
        });
    }
}