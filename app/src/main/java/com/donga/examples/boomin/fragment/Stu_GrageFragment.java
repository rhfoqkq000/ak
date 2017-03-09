package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.retrofit.retrofitGrad.Interface_grad;
import com.donga.examples.boomin.retrofit.retrofitGrad.Master;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
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

        showProgressDialog();

        final long start = System.currentTimeMillis();


        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_grad sche = client.create(Interface_grad.class);
        try {
            String decryptedStuPw = Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key));
            Call<Master> call =
                    sche.getSchedule(InfoSingleton.getInstance().getStuId(), decryptedStuPw);
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitGrad.Master> call, Response<Master> response) {
                    if (response.body().getResult_code() == 1) {

                        long end = System.currentTimeMillis();
                        Log.i("Stu_GradeFragment", "retrofit시간:"+(end-start)/1000.0);

                        tv_multi.setText(response.body().getResult_body().getInfo().getMulti());
                        tv_sub.setText(response.body().getResult_body().getInfo().getSub());
                        tv_year.setText(response.body().getResult_body().getInfo().getYear());
                        InfoSingleton.getInstance().setYear(response.body().getResult_body().getInfo().getYear());
                        tv_avgGrade.setText(response.body().getResult_body().getInfo().getAvgGrade());
                        tv_early.setText(response.body().getResult_body().getInfo().getEarly());
                        tv_smart.setText(response.body().getResult_body().getInfo().getSmart());

                        ArrayList<String> title = response.body().getResult_body().getTitle2();
                        kyo_02.setText(title.get(0).split("교양")[0]);
                        kyo_03.setText(title.get(1).split("교양")[0]);
                        kyo_04.setText(title.get(2).split("교양")[0]);
                        kyo_05.setText(title.get(3).split("교양")[0]);

                        kyo_11.setText(response.body().getResult_body().getNeed().get(1));
                        kyo_12.setText(response.body().getResult_body().getNeed().get(2));
                        kyo_13.setText(response.body().getResult_body().getNeed().get(3));
                        kyo_14.setText(response.body().getResult_body().getNeed().get(4));

                        kyo_21.setText(response.body().getResult_body().getGet().get(1));
                        kyo_22.setText(response.body().getResult_body().getGet().get(2));
                        kyo_23.setText(response.body().getResult_body().getGet().get(3));
                        kyo_24.setText(response.body().getResult_body().getGet().get(4));

                        kyo_31.setText(response.body().getResult_body().getPm().get(1));
                        if (0 > Integer.parseInt(response.body().getResult_body().getPm().get(1))) {
                            kyo_31.setTextColor(getResources().getColor(R.color.Red));
                        }
                        kyo_32.setText(response.body().getResult_body().getPm().get(2));
                        if (0 > Integer.parseInt(response.body().getResult_body().getPm().get(2))) {
                            kyo_32.setTextColor(getResources().getColor(R.color.Red));
                        }
                        kyo_33.setText(response.body().getResult_body().getPm().get(3));
                        if (0 > Integer.parseInt(response.body().getResult_body().getPm().get(3))) {
                            kyo_33.setTextColor(getResources().getColor(R.color.Red));
                        }
                        kyo_34.setText(response.body().getResult_body().getPm().get(4));
                        if (0 > Integer.parseInt(response.body().getResult_body().getPm().get(4))) {
                            kyo_34.setTextColor(getResources().getColor(R.color.Red));
                        }

                        jun_01.setText(response.body().getResult_body().getTitle2().get(6));
                        jun_02.setText(response.body().getResult_body().getTitle2().get(7));

                        jun_11.setText(response.body().getResult_body().getNeed().get(7));
                        jun_12.setText(response.body().getResult_body().getNeed().get(8));

                        jun_21.setText(response.body().getResult_body().getGet().get(7));
                        jun_22.setText(response.body().getResult_body().getGet().get(8));

                        jun_31.setText(response.body().getResult_body().getPm().get(7));
                        if (0 > Integer.parseInt(response.body().getResult_body().getPm().get(7))) {
                            jun_31.setTextColor(getResources().getColor(R.color.Red));
                        }
                        jun_32.setText(response.body().getResult_body().getPm().get(8));
                        if (0 > Integer.parseInt(response.body().getResult_body().getPm().get(8))) {
                            jun_32.setTextColor(getResources().getColor(R.color.Red));
                        }

                        ja_1.setText(response.body().getResult_body().getNeed().get(10));
                        ja_2.setText(response.body().getResult_body().getGet().get(10));
                        ja_3.setText(response.body().getResult_body().getPm().get(10));
                        if (0 > Integer.parseInt(response.body().getResult_body().getPm().get(10))) {
                            ja_3.setTextColor(getResources().getColor(R.color.Red));
                        }

                        hideProgressDialog();
                    } else {
                        hideProgressDialog();
                        log.appendLog("inStu_GradeFragment code not matched");
                        Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitGrad.Master> call, Throwable t) {
                    hideProgressDialog();
                    Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    log.appendLog("inStu_GradeFragment failure");
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
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

    public static boolean validateEmail(String emailStr) {
        final Pattern VALID_PERCENT_REGEX = Pattern.compile("-\\d+%");
        Matcher matcher = VALID_PERCENT_REGEX.matcher(emailStr);
        return matcher.find();
    }
}