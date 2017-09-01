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
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.SavedRevision;
import com.couchbase.lite.android.AndroidContext;
import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.retrofit.retrofitGrad.Interface_grad;
import com.donga.examples.boomin.retrofit.retrofitGrad.Master;
import com.donga.examples.boomin.retrofit.retrofitGrad.Result_body;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.orhanobut.logger.Logger;

import java.lang.reflect.Array;
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

    @BindView(R.id.hak_01)
    TextView hak_01;
    @BindView(R.id.hak_02)
    TextView hak_02;
    @BindView(R.id.hak_11)
    TextView hak_11;
    @BindView(R.id.hak_12)
    TextView hak_12;
    @BindView(R.id.hak_21)
    TextView hak_21;
    @BindView(R.id.hak_22)
    TextView hak_22;
    @BindView(R.id.hak_31)
    TextView hak_31;
    @BindView(R.id.hak_32)
    TextView hak_32;

    @BindView(R.id.jun_01)
    TextView jun_01;
    @BindView(R.id.jun_02)
    TextView jun_02;
    @BindView(R.id.jun_03)
    TextView jun_03;
    @BindView(R.id.jun_11)
    TextView jun_11;
    @BindView(R.id.jun_12)
    TextView jun_12;
    @BindView(R.id.jun_13)
    TextView jun_13;
    @BindView(R.id.jun_21)
    TextView jun_21;
    @BindView(R.id.jun_22)
    TextView jun_22;
    @BindView(R.id.jun_23)
    TextView jun_23;
    @BindView(R.id.jun_31)
    TextView jun_31;
    @BindView(R.id.jun_32)
    TextView jun_32;
    @BindView(R.id.jun_33)
    TextView jun_33;

    @BindView(R.id.ja_0)
    TextView ja_0;
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

        final SwipeRefreshLayout swipeRefreshLayout = rootview.findViewById(R.id.swiper);
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

//        retrofitGrade();
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
            retrofitGrade();
        }else{
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
        //        String documentId = String.valueOf(sharedPreferences.getInt("stuID", 0));
        document = database.getDocument(String.valueOf(sharedPreferences.getInt("stuID", 0))+"GRADE");
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
        title.add("자유선택");
        ArrayList<String> needArr = resultBody.getNeed();
        ArrayList<String> getArr = resultBody.getGet();
        ArrayList<String> pmArr = resultBody.getPm();

        ArrayList<Integer> filledTitleIndex = new ArrayList<>();
        for (int i = 0; i < title.size(); i++){
            if (!title.get(i).equals("")) {
                filledTitleIndex.add(i);
            }
        }

        ArrayList<TextView> titleTvArray = new ArrayList<>();
        titleTvArray.add(kyo_02);
        titleTvArray.add(kyo_03);
        titleTvArray.add(kyo_04);
        titleTvArray.add(kyo_05);
        titleTvArray.add(hak_01);
        titleTvArray.add(hak_02);
        titleTvArray.add(jun_01);
        titleTvArray.add(jun_02);
        titleTvArray.add(jun_03);
        titleTvArray.add(ja_0);

        ArrayList<TextView> needTvArray = new ArrayList<>();
        needTvArray.add(kyo_11);
        needTvArray.add(kyo_12);
        needTvArray.add(kyo_13);
        needTvArray.add(kyo_14);
        needTvArray.add(hak_11);
        needTvArray.add(hak_12);
        needTvArray.add(jun_11);
        needTvArray.add(jun_12);
        needTvArray.add(jun_13);
        needTvArray.add(ja_1);

        ArrayList<TextView> getTvArray = new ArrayList<>();
        getTvArray.add(kyo_21);
        getTvArray.add(kyo_22);
        getTvArray.add(kyo_23);
        getTvArray.add(kyo_24);
        getTvArray.add(hak_21);
        getTvArray.add(hak_22);
        getTvArray.add(jun_21);
        getTvArray.add(jun_22);
        getTvArray.add(jun_23);
        getTvArray.add(ja_2);

        ArrayList<TextView> pmTvArray = new ArrayList<>();
        pmTvArray.add(kyo_31);
        pmTvArray.add(kyo_32);
        pmTvArray.add(kyo_33);
        pmTvArray.add(kyo_34);
        pmTvArray.add(hak_31);
        pmTvArray.add(hak_32);
        pmTvArray.add(jun_31);
        pmTvArray.add(jun_32);
        pmTvArray.add(jun_33);
        pmTvArray.add(ja_3);

        for (int i = 0; i < title.size(); i++){
            if (!title.get(i).equals("")){
                titleTvArray.get(i).setText(valid(title.get(i)));
//                Logger.d("원문:"+title.get(i)+", 결과:"+valid(title.get(i)));
            }
            if (!needArr.get(i+1).equals("")){
                needTvArray.get(i).setText(needArr.get(i+1));
//                Logger.d(i+"번째 필요학점 ~~~~" + needArr.get(i+1)+", title::"+title.get(i));
            }
            if (!getArr.get(i+1).equals("")){
                getTvArray.get(i).setText(getArr.get(i+1));
            }
            if (!pmArr.get(i+1).equals("")){
                pmTvArray.get(i).setText(pmArr.get(i+1));
                if (0 > Integer.parseInt(pmArr.get(i+1))) {
                    pmTvArray.get(i).setTextColor(getResources().getColor(R.color.Red));
                }
            }
        }

        hideProgressDialog();
    }

    public static boolean validate(String str) {
        final Pattern VALID_PERCENT_REGEX = Pattern.compile("교양");
        Matcher matcher = VALID_PERCENT_REGEX.matcher(str);
//        return matcher.find();
        return matcher.find();
    }

    public String valid(String str){
        return str.replaceAll("교양", "");
    }

    public void retrofitGrade(){
        try {
            document.delete();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_grad grad = client.create(Interface_grad.class);
        try {
            Call<Master> call =
                    grad.getSchedule(InfoSingleton.getInstance().getStuId(),
                            InfoSingleton.getInstance().getStuPw());
//            Call<Master> call = grad.getSchedule();
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