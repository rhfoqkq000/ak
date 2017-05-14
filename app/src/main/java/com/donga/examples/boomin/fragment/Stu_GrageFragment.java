package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.retrofit.retrofitGrad.Interface_grad;
import com.donga.examples.boomin.retrofit.retrofitGrad.Master;
import com.donga.examples.boomin.retrofit.retrofitGrad.Result_body;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Stu_GrageFragment extends Fragment {
    AppendLog log = new AppendLog();

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Document document;
    Manager manager;
    Database database;

    public static final String DB_NAME = "app";

    @BindView(R.id.tv_multi)
    TextView tv_multi;
    @BindView(R.id.tv_sub)
    TextView tv_sub;
    @BindView(R.id.tv_year)
    TextView tv_year;
    @BindView(R.id.tv_avgGrade)
    TextView tv_avgGrade;
    @BindView(R.id.tv_early)
    TextView tv_early;
    @BindView(R.id.tv_smart)
    TextView tv_smart;

    @BindView(R.id.kyo_02)
    TextView kyo_02;
    @BindView(R.id.kyo_03)
    TextView kyo_03;
    @BindView(R.id.kyo_04)
    TextView kyo_04;
    @BindView(R.id.kyo_05)
    TextView kyo_05;
    @BindView(R.id.kyo_11)
    TextView kyo_11;
    @BindView(R.id.kyo_12)
    TextView kyo_12;
    @BindView(R.id.kyo_13)
    TextView kyo_13;
    @BindView(R.id.kyo_14)
    TextView kyo_14;
    @BindView(R.id.kyo_21)
    TextView kyo_21;
    @BindView(R.id.kyo_22)
    TextView kyo_22;
    @BindView(R.id.kyo_23)
    TextView kyo_23;
    @BindView(R.id.kyo_24)
    TextView kyo_24;
    @BindView(R.id.kyo_31)
    TextView kyo_31;
    @BindView(R.id.kyo_32)
    TextView kyo_32;
    @BindView(R.id.kyo_33)
    TextView kyo_33;
    @BindView(R.id.kyo_34)
    TextView kyo_34;

    @BindView(R.id.jun_01)
    TextView jun_01;
    @BindView(R.id.jun_02)
    TextView jun_02;
    @BindView(R.id.jun_11)
    TextView jun_11;
    @BindView(R.id.jun_12)
    TextView jun_12;
    @BindView(R.id.jun_21)
    TextView jun_21;
    @BindView(R.id.jun_22)
    TextView jun_22;
    @BindView(R.id.jun_31)
    TextView jun_31;
    @BindView(R.id.jun_32)
    TextView jun_32;

    @BindView(R.id.ja_1)
    TextView ja_1;
    @BindView(R.id.ja_2)
    TextView ja_2;
    @BindView(R.id.ja_3)
    TextView ja_3;


    private ProgressDialog mProgressDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_grades, container, false);
        ButterKnife.bind(this, rootview);

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgressDialog();
                retrofitGrade();
                swipeRefreshLayout.setRefreshing(false);
                hideProgressDialog();
            }
        });

        showProgressDialog();

        manager = null;
        database = null;
        try {
            manager = new Manager(new AndroidContext(getContext()), Manager.DEFAULT_OPTIONS);
            database = manager.getDatabase(DB_NAME);
        } catch (Exception e) {
            Log.e("stu_gradeFragment", "Error getting database", e);
        }
        sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
//        document = database.getDocument(String.valueOf(sharedPreferences.getInt("stuID", 0)));
        document = database.getDocument(String.valueOf(sharedPreferences.getInt("stuID", 0))+"GRADE");

        if(document.getProperty("grade") == null) {
            Logger.d("이프");

            retrofitGrade();
        }else{
            Logger.d("엘스당, "+document.getProperty("grade"));

            ObjectMapper mapper = new ObjectMapper();
            Result_body resultBody = mapper.convertValue(document.getProperties().get("grade"), Result_body.class);

            setGradeInfo(resultBody);
        }
        return rootview;
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

    // MD5 복호화
    public static String Decrypt(String text, String key) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        byte[] keyBytes = new byte[16];
        byte[] b = key.getBytes("UTF-8");
        int len = b.length;
        if (len > keyBytes.length) len = keyBytes.length;
        System.arraycopy(b, 0, keyBytes, 0, len);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        IvParameterSpec ivSpec = new IvParameterSpec(keyBytes);
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        byte[] results = cipher.doFinal(Base64.decode(text, 0));
        return new String(results, "UTF-8");
    }

    private void helloCBL(Result_body resultBody) {

        sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

        // Create the documenteDocument(database);
        String revDocumentId = String.valueOf(sharedPreferences.getInt("stuID", 0))+"GRADE";
        String documentId = revDocumentId;
//        String documentId = String.valueOf(sharedPreferences.getInt("stuID", 0));
        document = database.getDocument(documentId);
        if (document.getProperty("grade") == null) {

            Map<String, Object> properties = new HashMap<>();
            properties.put("grade", resultBody);
            try {
                document.putProperties(properties);
                Log.i("dddddd", String.valueOf(document.getProperty("grade")));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setGradeInfo(Result_body resultBody){
        tv_multi.setText(resultBody.getInfo().getMulti());
        tv_sub.setText(resultBody.getInfo().getSub());
        tv_year.setText(resultBody.getInfo().getYear());
        InfoSingleton.getInstance().setYear(resultBody.getInfo().getYear());
        tv_avgGrade.setText(resultBody.getInfo().getAvgGrade());
        tv_early.setText(resultBody.getInfo().getEarly());
        tv_smart.setText(resultBody.getInfo().getSmart());

        ArrayList<String> title = resultBody.getTitle2();
        kyo_02.setText(title.get(0).split("교양")[0]);
        kyo_03.setText(title.get(1).split("교양")[0]);
        kyo_04.setText(title.get(2).split("교양")[0]);
        kyo_05.setText(title.get(3).split("교양")[0]);

        kyo_11.setText(resultBody.getNeed().get(1));
        kyo_12.setText(resultBody.getNeed().get(2));
        kyo_13.setText(resultBody.getNeed().get(3));
        kyo_14.setText(resultBody.getNeed().get(4));

        kyo_21.setText(resultBody.getGet().get(1));
        kyo_22.setText(resultBody.getGet().get(2));
        kyo_23.setText(resultBody.getGet().get(3));
        kyo_24.setText(resultBody.getGet().get(4));

        kyo_31.setText(resultBody.getPm().get(1));
        if (0 > Integer.parseInt(resultBody.getPm().get(1))) {
            kyo_31.setTextColor(getResources().getColor(R.color.Red));
        }
        kyo_32.setText(resultBody.getPm().get(2));
        if (0 > Integer.parseInt(resultBody.getPm().get(2))) {
            kyo_32.setTextColor(getResources().getColor(R.color.Red));
        }
        kyo_33.setText(resultBody.getPm().get(3));
        if (0 > Integer.parseInt(resultBody.getPm().get(3))) {
            kyo_33.setTextColor(getResources().getColor(R.color.Red));
        }
        kyo_34.setText(resultBody.getPm().get(4));
        if (0 > Integer.parseInt(resultBody.getPm().get(4))) {
            kyo_34.setTextColor(getResources().getColor(R.color.Red));
        }

        jun_01.setText(resultBody.getTitle2().get(6));
        jun_02.setText(resultBody.getTitle2().get(7));

        jun_11.setText(resultBody.getNeed().get(7));
        jun_12.setText(resultBody.getNeed().get(8));

        jun_21.setText(resultBody.getGet().get(7));
        jun_22.setText(resultBody.getGet().get(8));

        jun_31.setText(resultBody.getPm().get(7));
        if (0 > Integer.parseInt(resultBody.getPm().get(7))) {
            jun_31.setTextColor(getResources().getColor(R.color.Red));
        }
        jun_32.setText(resultBody.getPm().get(8));
        if (0 > Integer.parseInt(resultBody.getPm().get(8))) {
            jun_32.setTextColor(getResources().getColor(R.color.Red));
        }

        ja_1.setText(resultBody.getNeed().get(10));
        ja_2.setText(resultBody.getGet().get(10));
        ja_3.setText(resultBody.getPm().get(10));
        if (0 > Integer.parseInt(resultBody.getPm().get(10))) {
            ja_3.setTextColor(getResources().getColor(R.color.Red));
        }

        hideProgressDialog();
    }

    public void retrofitGrade(){
        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_grad sche = client.create(Interface_grad.class);
        try {
            Call<Master> call =
                    sche.getSchedule(InfoSingleton.getInstance().getStuId(),
                            Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)));
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitGrad.Master> call, Response<Master> response) {
                    Result_body resultBody = response.body().getResult_body();
                    if (response.body().getResult_code() == 1) {

                        helloCBL(resultBody);

                        setGradeInfo(resultBody);
                    } else {
                        hideProgressDialog();
                        log.appendLog("inStu_GradeFragment code not matched");
                        Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitGrad.Master> call, Throwable t) {
                    hideProgressDialog();
                    Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    log.appendLog("inStu_GradeFragment failure");
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            hideProgressDialog();
            Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
            log.appendLog("inStu_GradeFragment failure");
            e.printStackTrace();
        }
    }
}