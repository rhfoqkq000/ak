package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Interface_sche;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Master;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

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
public class Stu_ScheFragment extends Fragment {
    private ProgressDialog mProgressDialog;
    AppendLog log = new AppendLog();

    @BindView(R.id.mon1)
    TextView mon1;
    @BindView(R.id.mon2)
    TextView mon2;
    @BindView(R.id.mon3)
    TextView mon3;
    @BindView(R.id.mon4)
    TextView mon4;
    @BindView(R.id.mon5)
    TextView mon5;
    @BindView(R.id.mon6)
    TextView mon6;
    @BindView(R.id.mon7)
    TextView mon7;
    @BindView(R.id.mon8)
    TextView mon8;
    @BindView(R.id.mon9)
    TextView mon9;
    @BindView(R.id.mon10)
    TextView mon10;
    @BindView(R.id.mon11)
    TextView mon11;
    @BindView(R.id.mon12)
    TextView mon12;
    @BindView(R.id.mon13)
    TextView mon13;
    @BindView(R.id.mon14)
    TextView mon14;
    @BindView(R.id.mon15)
    TextView mon15;
    @BindView(R.id.mon16)
    TextView mon16;
    @BindView(R.id.mon17)
    TextView mon17;
    @BindView(R.id.mon18)
    TextView mon18;

    @BindView(R.id.tue1)
    TextView tue1;
    @BindView(R.id.tue2)
    TextView tue2;
    @BindView(R.id.tue3)
    TextView tue3;
    @BindView(R.id.tue4)
    TextView tue4;
    @BindView(R.id.tue5)
    TextView tue5;
    @BindView(R.id.tue6)
    TextView tue6;
    @BindView(R.id.tue7)
    TextView tue7;
    @BindView(R.id.tue8)
    TextView tue8;
    @BindView(R.id.tue9)
    TextView tue9;
    @BindView(R.id.tue10)
    TextView tue10;
    @BindView(R.id.tue11)
    TextView tue11;
    @BindView(R.id.tue12)
    TextView tue12;
    @BindView(R.id.tue13)
    TextView tue13;
    @BindView(R.id.tue14)
    TextView tue14;
    @BindView(R.id.tue15)
    TextView tue15;
    @BindView(R.id.tue16)
    TextView tue16;
    @BindView(R.id.tue17)
    TextView tue17;
    @BindView(R.id.tue18)
    TextView tue18;

    @BindView(R.id.wed1)
    TextView wed1;
    @BindView(R.id.wed2)
    TextView wed2;
    @BindView(R.id.wed3)
    TextView wed3;
    @BindView(R.id.wed4)
    TextView wed4;
    @BindView(R.id.wed5)
    TextView wed5;
    @BindView(R.id.wed6)
    TextView wed6;
    @BindView(R.id.wed7)
    TextView wed7;
    @BindView(R.id.wed8)
    TextView wed8;
    @BindView(R.id.wed9)
    TextView wed9;
    @BindView(R.id.wed10)
    TextView wed10;
    @BindView(R.id.wed11)
    TextView wed11;
    @BindView(R.id.wed12)
    TextView wed12;
    @BindView(R.id.wed13)
    TextView wed13;
    @BindView(R.id.wed14)
    TextView wed14;
    @BindView(R.id.wed15)
    TextView wed15;
    @BindView(R.id.wed16)
    TextView wed16;
    @BindView(R.id.wed17)
    TextView wed17;
    @BindView(R.id.wed18)
    TextView wed18;

    @BindView(R.id.thu1)
    TextView thu1;
    @BindView(R.id.thu2)
    TextView thu2;
    @BindView(R.id.thu3)
    TextView thu3;
    @BindView(R.id.thu4)
    TextView thu4;
    @BindView(R.id.thu5)
    TextView thu5;
    @BindView(R.id.thu6)
    TextView thu6;
    @BindView(R.id.thu7)
    TextView thu7;
    @BindView(R.id.thu8)
    TextView thu8;
    @BindView(R.id.thu9)
    TextView thu9;
    @BindView(R.id.thu10)
    TextView thu10;
    @BindView(R.id.thu11)
    TextView thu11;
    @BindView(R.id.thu12)
    TextView thu12;
    @BindView(R.id.thu13)
    TextView thu13;
    @BindView(R.id.thu14)
    TextView thu14;
    @BindView(R.id.thu15)
    TextView thu15;
    @BindView(R.id.thu16)
    TextView thu16;
    @BindView(R.id.thu17)
    TextView thu17;
    @BindView(R.id.thu18)
    TextView thu18;

    @BindView(R.id.fri1)
    TextView fri1;
    @BindView(R.id.fri2)
    TextView fri2;
    @BindView(R.id.fri3)
    TextView fri3;
    @BindView(R.id.fri4)
    TextView fri4;
    @BindView(R.id.fri5)
    TextView fri5;
    @BindView(R.id.fri6)
    TextView fri6;
    @BindView(R.id.fri7)
    TextView fri7;
    @BindView(R.id.fri8)
    TextView fri8;
    @BindView(R.id.fri9)
    TextView fri9;
    @BindView(R.id.fri10)
    TextView fri10;
    @BindView(R.id.fri11)
    TextView fri11;
    @BindView(R.id.fri12)
    TextView fri12;
    @BindView(R.id.fri13)
    TextView fri13;
    @BindView(R.id.fri14)
    TextView fri14;
    @BindView(R.id.fri15)
    TextView fri15;
    @BindView(R.id.fri16)
    TextView fri16;
    @BindView(R.id.fri17)
    TextView fri17;
    @BindView(R.id.fri18)
    TextView fri18;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_sche, container, false);
        ButterKnife.bind(this, rootview);

        showProgressDialog();

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_sche sche = client.create(Interface_sche.class);
        try {
            Call<Master> call = sche.getTimeTable(InfoSingleton.getInstance().getStuId(), Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)));
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<Master> call, Response<Master> response) {
                    ArrayList<String> monArr = response.body().getResult_body().get(0).getMon();
                    ArrayList<String> tueArr = response.body().getResult_body().get(1).getTue();
                    ArrayList<String> wedArr = response.body().getResult_body().get(2).getWen();
                    ArrayList<String> thuArr = response.body().getResult_body().get(3).getThu();
                    ArrayList<String> friArr = response.body().getResult_body().get(4).getFri();
//                    String monFirst = "none";
//                    String monSecond = "none";
//                    int monFirstNum = 0;
//                    int monSecondNum = 0;
//                    for(int i = 0; i<monArr.size(); i++){
//                        if(!monArr.get(i).equals("")){
//                            monFirst = monArr.get(i);
//                        }
//
//                        if(!m))
//
//                        if(monFirst.equals(monArr.get(i))){
//                            monFirstNum++;
//                        }
//                        Logger.d("nowValue:"+monArr.get(i)+", monFirst:"+monFirst+", monFirstNum:"+monFirstNum+", monSecond:"+monSecond);
//                    }
                    mon1.setText(monArr.get(0));
                    mon2.setText(monArr.get(1));
                    mon3.setText(monArr.get(2));
                    mon4.setText(monArr.get(3));
                    mon5.setText(monArr.get(4));
                    mon6.setText(monArr.get(5));
                    mon7.setText(monArr.get(6));
                    mon8.setText(monArr.get(7));
                    mon9.setText(monArr.get(8));
                    mon10.setText(monArr.get(9));
                    mon11.setText(monArr.get(10));
                    mon12.setText(monArr.get(11));
                    mon13.setText(monArr.get(12));
                    mon14.setText(monArr.get(13));
                    mon15.setText(monArr.get(14));
                    mon16.setText(monArr.get(15));
                    mon17.setText(monArr.get(16));
                    mon18.setText(monArr.get(17));

                    tue1.setText(tueArr.get(0));
                    tue2.setText(tueArr.get(1));
                    tue3.setText(tueArr.get(2));
                    tue4.setText(tueArr.get(3));
                    tue5.setText(tueArr.get(4));
                    tue6.setText(tueArr.get(5));
                    tue7.setText(tueArr.get(6));
                    tue8.setText(tueArr.get(7));
                    tue9.setText(tueArr.get(8));
                    tue10.setText(tueArr.get(9));
                    tue11.setText(tueArr.get(10));
                    tue12.setText(tueArr.get(11));
                    tue13.setText(tueArr.get(12));
                    tue14.setText(tueArr.get(13));
                    tue15.setText(tueArr.get(14));
                    tue16.setText(tueArr.get(15));
                    tue17.setText(tueArr.get(16));
                    tue18.setText(tueArr.get(17));

                    wed1.setText(wedArr.get(0));
                    wed2.setText(wedArr.get(1));
                    wed3.setText(wedArr.get(2));
                    wed4.setText(wedArr.get(3));
                    wed5.setText(wedArr.get(4));
                    wed6.setText(wedArr.get(5));
                    wed7.setText(wedArr.get(6));
                    wed8.setText(wedArr.get(7));
                    wed9.setText(wedArr.get(8));
                    wed10.setText(wedArr.get(9));
                    wed11.setText(wedArr.get(10));
                    wed12.setText(wedArr.get(11));
                    wed13.setText(wedArr.get(12));
                    wed14.setText(wedArr.get(13));
                    wed15.setText(wedArr.get(14));
                    wed16.setText(wedArr.get(15));
                    wed17.setText(wedArr.get(16));
                    wed18.setText(wedArr.get(17));

                    thu1.setText(thuArr.get(0));
                    thu2.setText(thuArr.get(1));
                    thu3.setText(thuArr.get(2));
                    thu4.setText(thuArr.get(3));
                    thu5.setText(thuArr.get(4));
                    thu6.setText(thuArr.get(5));
                    thu7.setText(thuArr.get(6));
                    thu8.setText(thuArr.get(7));
                    thu9.setText(thuArr.get(8));
                    thu10.setText(thuArr.get(9));
                    thu11.setText(thuArr.get(10));
                    thu12.setText(thuArr.get(11));
                    thu13.setText(thuArr.get(12));
                    thu14.setText(thuArr.get(13));
                    thu15.setText(thuArr.get(14));
                    thu16.setText(thuArr.get(15));
                    thu17.setText(thuArr.get(16));
                    thu18.setText(thuArr.get(17));

                    fri1.setText(friArr.get(0));
                    fri2.setText(friArr.get(1));
                    fri3.setText(friArr.get(2));
                    fri4.setText(friArr.get(3));
                    fri5.setText(friArr.get(4));
                    fri6.setText(friArr.get(5));
                    fri7.setText(friArr.get(6));
                    fri8.setText(friArr.get(7));
                    fri9.setText(friArr.get(8));
                    fri10.setText(friArr.get(9));
                    fri11.setText(friArr.get(10));
                    fri12.setText(friArr.get(11));
                    fri13.setText(friArr.get(12));
                    fri14.setText(friArr.get(13));
                    fri15.setText(friArr.get(14));
                    fri16.setText(friArr.get(15));
                    fri17.setText(friArr.get(16));
                    fri18.setText(friArr.get(17));

                    hideProgressDialog();
                }

                @Override
                public void onFailure(Call<Master> call, Throwable t) {
                    hideProgressDialog();
                    t.printStackTrace();
                    log.appendLog("inScheduleFragment onFailure");
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
}