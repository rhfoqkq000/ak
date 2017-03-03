package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Stu_ScheFragment extends Fragment {
    private ProgressDialog mProgressDialog;
    AppendLog log = new AppendLog();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_sche, container, false);
        ButterKnife.bind(this, rootview);


//        showProgressDialog();
//
//        //retrofit 통신
//        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
//                .addConverterFactory(GsonConverterFactory.create()).build();
//        Interface_sche sche = client.create(Interface_sche.class);
//        try {
//            Call<Master> call = sche.getTimeTable(InfoSingleton.getInstance().getStuId(), Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)));
//            call.enqueue(new Callback<Master>() {
//                @Override
//                public void onResponse(Call<Master> call, Response<Master> response) {
//                    Log.i("Schedule onResponse", response.body().getResult_body().get(0).getMon().get(0));
//
//                    hideProgressDialog();
//                }
//
//                @Override
//                public void onFailure(Call<Master> call, Throwable t) {
//                    hideProgressDialog();
//                    t.printStackTrace();
//                    appendLog.appendLog("Schedule onFailure");
//                }
//            });
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

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