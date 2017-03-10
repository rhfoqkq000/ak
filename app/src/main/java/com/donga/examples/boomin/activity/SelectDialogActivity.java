package com.donga.examples.boomin.activity;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
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
import android.widget.Toast;
import com.afollestad.materialdialogs.DialogAction;
import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewAdapter.SelectListViewAdapter;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Interface_getCircle;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Master;
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
    private ProgressDialog mProgressDialog;

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
    public SelectDialogActivity(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @OnClick(R.id.popup_close)
    void onCloseClicked() {
        getOwnerActivity().finish();
    }

    @OnClick(R.id.select_btn_ok)
    void onOkClicked() {
        int selected = select_spinner.getSelectedIndex();
        if (selected > -1) {
        if(selected>-1){
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
                Logger.d(""+select_spinner.getItems().get(position));
                getCircle(select_spinner.getItems().get(position).toString());
            }
        });

        getCircle(select_spinner.getItems().get(0).toString());


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

    public void getCircle(String major){
        showProgressDialog();
        Retrofit client = new Retrofit.Builder().baseUrl(getContext().getResources().getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        final Interface_getCircle getCircle = client.create(Interface_getCircle.class);
        retrofit2.Call<Master> call = getCircle.getCircle(major);
        final ArrayList<String> circleNames = new ArrayList<>();
        circleIds = new ArrayList<>();
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                if(response.body().getResult_code() == 1){
                    adapter = new SelectListViewAdapter();
                    for(int i = 0; i<response.body().getResult_body().size(); i++){
                        adapter.addItem(response.body().getResult_body().get(i).getName());
                        adapter.notifyDataSetChanged();
                    }
                    listView.setAdapter(adapter);
                    hideProgressDialog();
                }else{
                    hideProgressDialog();
                    log.appendLog("inSelectDialog code not matched");
                    Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                hideProgressDialog();
                log.appendLog("inSelectDialog failure");
                Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
            mProgressDialog.setMessage(getContext().getResources().getString(R.string.loading));
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