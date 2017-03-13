package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.GradeSingleton;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.retrofit.retrofitAchieveAll.Interface_achall;
import com.donga.examples.boomin.retrofit.retrofitAchieveAll.Master;
import com.donga.examples.boomin.retrofit.retrofitAchieveSep.Interface_achsep;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.orhanobut.logger.Logger;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Stu_AchievFragment extends Fragment {
    AppendLog log = new AppendLog();

    private FragmentManager fm;
    private ProgressDialog mProgressDialog;
    HashMap<String, Integer> hash;

    @BindView(R.id.achiev_all)
    MaterialSpinner achiev_all;
    @BindView(R.id.part_layout)
    RelativeLayout part_layout;
    @BindView(R.id.all_ok_card)
    CardView all_ok_card;
    @BindView(R.id.hide)
    LinearLayout hide;
    @BindView(R.id.all_ok)
    Button all_ok;
    @BindView(R.id.part_ok)
    Button part_ok;
    @BindView(R.id.achiev_year)
    MaterialSpinner achieve_year;
    @BindView(R.id.achiev_side)
    MaterialSpinner achiev_side;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_achiev, container, false);
        ButterKnife.bind(this, rootview);

        hash = new HashMap<String, Integer>();
        hash.put("전학기", 00);
        hash.put("전적학교인정", 00);
        hash.put("1학기", 10);
        hash.put("계절학기(하기)", 11);
        hash.put("2학기", 20);
        hash.put("계절학기(동기)", 21);
        hash.put("협정대학이수", 30);
        hash.put("협정대학이수(1학기)", 31);
        hash.put("협정대학이수(하계)", 32);
        hash.put("협정대학이수(2학기)", 33);
        hash.put("협정대학이수(동계)", 34);
        hash.put("1학기국내협정대학이수", 50);
        hash.put("1학기국외협정대학이수", 51);
        hash.put("하계국내협정대학", 52);
        hash.put("하계국외협정대학", 53);
        hash.put("2학기국내협정대학이수", 60);
        hash.put("2학기국외협정대학이수", 61);
        hash.put("동계국내협정대학", 62);
        hash.put("동계국외협정대학", 63);
        final ArrayList<String> keyList = new ArrayList<>();
        Set key = hash.keySet();
        for (Iterator iterator = key.iterator(); iterator.hasNext(); ) {
            String keyName = (String) iterator.next();
            keyList.add(keyName);
        }

        fm = getFragmentManager();

        LayoutInflater layoutInflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View bottom = layoutInflater.inflate(R.layout.fragment_achiev_part, null);

//        below.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                achiev_bottom.setVisibility(View.GONE);
//            }
//        });

        achiev_all.setItems("전체학기성적", "일부학기성적");
        achiev_all.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                if (position == 0) {
                    all_ok_card.setVisibility(View.VISIBLE);
                    part_layout.setVisibility(View.GONE);
                } else {
                    ArrayList<String> years = new ArrayList<String>();
                    for (int i = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date(System.currentTimeMillis())));
                         i >= Integer.parseInt(InfoSingleton.getInstance().getYear()); i--) {
                        years.add(String.valueOf(i));
                    }
                    Object[] objectList = years.toArray();
                    String[] years2 =  Arrays.copyOf(objectList,objectList.length,String[].class);
                    achieve_year.setItems(years2);
                    achiev_side.setItems(keyList);

                    all_ok_card.setVisibility(View.GONE);
                    part_layout.setVisibility(View.VISIBLE);
                }
            }


        });
        return rootview;
    }

    @OnClick(R.id.all_ok)
    void allOkClicked() {
        showProgressDialog();
        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_achall ach = client.create(Interface_achall.class);
        try {
            Call<Master> call = ach.getAllGrade(InfoSingleton.getInstance().getStuId(), Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)));
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<Master> call, Response<Master> response) {
                    if (response.body().getResult_code() == 1) {
                        GradeSingleton.getInstance().setAllGrade(response.body().getResult_body().getAllGrade());
                        GradeSingleton.getInstance().setAvgGrade(response.body().getResult_body().getAvgGrade());
                        GradeSingleton.getInstance().setDetail(response.body().getResult_body().getDetail());
                        Log.i("ACHIFRAG GRADESINGLETON", "input");
                        hideProgressDialog();

                        hide.setVisibility(View.VISIBLE);
                        FragmentTransaction ft = fm.beginTransaction();
                        Stu_Achiev_All_Fragment achiev_all_fragment = new Stu_Achiev_All_Fragment();
                        ft.replace(R.id.frag_change, achiev_all_fragment);
                        ft.commit();
                    } else {
                        hideProgressDialog();
                        log.appendLog("inStu_AchievFragment code not matched");
                        Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Master> call, Throwable t) {
                    hideProgressDialog();
                    t.printStackTrace();
                    Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    log.appendLog("inStu_AchievFragment failure");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @OnClick(R.id.part_ok)
    void onPartOkClicked() {
        showProgressDialog();

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_achsep ach = client.create(Interface_achsep.class);
        try {
            Call<com.donga.examples.boomin.retrofit.retrofitAchieveSep.Master> call = ach.getSepGrade(InfoSingleton.getInstance().getStuId(),
                    Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)),
                    achieve_year.getItems().get(achieve_year.getSelectedIndex()).toString(),
                    String.valueOf(hash.get(achiev_side.getItems().get(achiev_side.getSelectedIndex()).toString())));
            call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitAchieveSep.Master>() {
                @Override
                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitAchieveSep.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitAchieveSep.Master> response) {
                    if (response.body().getResult_code() == 0 || response.body().getResult_code() == 500) {
                        hideProgressDialog();
                        log.appendLog("inStu_AchievFragment code not matched");
                        Toasty.error(getContext(), "조회 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    } else {
                        Log.i("ACHIEVE_SEP onResponse", response.body().getResult_body().getAllGrade());
                        GradeSingleton.getInstance().setPartGrade(response.body().getResult_body().getAllGrade());
                        GradeSingleton.getInstance().setPartAvg(response.body().getResult_body().getAvgGrade());
                        GradeSingleton.getInstance().setDetail2(response.body().getResult_body().getDetail());

                        hide.setVisibility(View.VISIBLE);
                        FragmentTransaction ft = fm.beginTransaction();
                        Stu_Achiev_Part_Fragment achiev_part_fragment = new Stu_Achiev_Part_Fragment();
                        hideProgressDialog();
                        ft.replace(R.id.frag_change, achiev_part_fragment);
                        ft.commit();
                    }
                }

                @Override
                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitAchieveSep.Master> call, Throwable t) {
                    hideProgressDialog();
                    t.printStackTrace();
                    Toasty.error(getContext(), "조회 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    log.appendLog("inStu_AchievFragment failure");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
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