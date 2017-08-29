package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.boomin.R;
import com.donga.examples.boomin.retrofit.retrofitMeal.Interface_meal;
import com.donga.examples.boomin.retrofit.retrofitMeal.Master3;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by horse on 2017. 8. 29..
 */

public class Res_HadanFragment extends Fragment {
    private ProgressDialog mProgressDialog;
    public int count = 0;

    @BindView(R.id.date_text)
    TextView date_text;
    @BindView(R.id.pre_res)
    ImageView pre_res;
    @BindView(R.id.next_res)
    ImageView next_res;

    @BindView(R.id.hadan_stu)
    TextView hadan_stu;
    @BindView(R.id.hadan_kyo)
    TextView hadan_kyo;
    @BindView(R.id.hadan_library)
    TextView hadan_library;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_res_hadan, container, false);
        ButterKnife.bind(this, rootview);
        SimpleDateFormat msimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        String now = msimpleDateFormat.format(currentTime);


        retrofit(now);

        date_text.setText(now);
//        date_text.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DialogFragment newFragment = new CalendarFragment();
//                newFragment.show(getSupportFragmentManager(), "Date Picker");
//            }
//        });

        pre_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count - 1;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, count); // +1은 내일

                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String pre = date.format(cal.getTime());
                retrofit(pre);
                date_text.setText(pre);
            }
        });
        next_res.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count = count + 1;

                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.DATE, count); // +1은 내일

                SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                String next = date.format(cal.getTime());

                retrofit(next);
                date_text.setText(next);
            }
        });
        return rootview;
    }
    public void retrofit(String getTime) {

        showProgressDialog();

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_meal meal = client.create(Interface_meal.class);
        retrofit2.Call<Master3> call3 = meal.getMeal(getTime);
        call3.enqueue(new Callback<Master3>() {
            @Override
            public void onResponse(Call<Master3> call, Response<Master3> response) {
                if (response.body().getResult_code() == 1) {
                    String source_guk = response.body().getResult_body().getInter();
                    hadan_stu.setText(Html.fromHtml(source_guk));
                    hadan_stu.setMovementMethod(LinkMovementMethod.getInstance());

                    String source_bumin = response.body().getResult_body().getBumin_kyo();
                    hadan_kyo.setText(Html.fromHtml(source_bumin));
                    hadan_kyo.setMovementMethod(LinkMovementMethod.getInstance());

                    String source_gang = response.body().getResult_body().getGang();
                    hadan_library.setText(Html.fromHtml(source_gang));
                    hadan_library.setMovementMethod(LinkMovementMethod.getInstance());

                    hideProgressDialog();
                } else {
//                    log.appendLog("inResActivity code not matched");
                    Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<Master3> call, Throwable t) {
//                log.appendLog("inResActivity failure");
                hideProgressDialog();
                Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(getContext());
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
