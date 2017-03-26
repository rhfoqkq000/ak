package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Interface_sche;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Master;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;

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

        final ArrayList<TextView> monTvArray = new ArrayList<>();
        monTvArray.add(mon1);
        monTvArray.add(mon2);
        monTvArray.add(mon3);
        monTvArray.add(mon4);
        monTvArray.add(mon5);
        monTvArray.add(mon6);
        monTvArray.add(mon7);
        monTvArray.add(mon8);
        monTvArray.add(mon9);
        monTvArray.add(mon10);
        monTvArray.add(mon11);
        monTvArray.add(mon12);
        monTvArray.add(mon13);
        monTvArray.add(mon14);
        monTvArray.add(mon15);
        monTvArray.add(mon16);
        monTvArray.add(mon17);
        monTvArray.add(mon18);

        final ArrayList<TextView> tueTvArray = new ArrayList<>();
        tueTvArray.add(tue1);
        tueTvArray.add(tue2);
        tueTvArray.add(tue3);
        tueTvArray.add(tue4);
        tueTvArray.add(tue5);
        tueTvArray.add(tue6);
        tueTvArray.add(tue7);
        tueTvArray.add(tue8);
        tueTvArray.add(tue9);
        tueTvArray.add(tue10);
        tueTvArray.add(tue11);
        tueTvArray.add(tue12);
        tueTvArray.add(tue13);
        tueTvArray.add(tue14);
        tueTvArray.add(tue15);
        tueTvArray.add(tue16);
        tueTvArray.add(tue17);
        tueTvArray.add(tue18);

        final ArrayList<TextView> wedTvArray = new ArrayList<>();
        wedTvArray.add(wed1);
        wedTvArray.add(wed2);
        wedTvArray.add(wed3);
        wedTvArray.add(wed4);
        wedTvArray.add(wed5);
        wedTvArray.add(wed6);
        wedTvArray.add(wed7);
        wedTvArray.add(wed8);
        wedTvArray.add(wed9);
        wedTvArray.add(wed10);
        wedTvArray.add(wed11);
        wedTvArray.add(wed12);
        wedTvArray.add(wed13);
        wedTvArray.add(wed14);
        wedTvArray.add(wed15);
        wedTvArray.add(wed16);
        wedTvArray.add(wed17);
        wedTvArray.add(wed18);

        final ArrayList<TextView> thuTvArray = new ArrayList<>();
        thuTvArray.add(thu1);
        thuTvArray.add(thu2);
        thuTvArray.add(thu3);
        thuTvArray.add(thu4);
        thuTvArray.add(thu5);
        thuTvArray.add(thu6);
        thuTvArray.add(thu7);
        thuTvArray.add(thu8);
        thuTvArray.add(thu9);
        thuTvArray.add(thu10);
        thuTvArray.add(thu11);
        thuTvArray.add(thu12);
        thuTvArray.add(thu13);
        thuTvArray.add(thu14);
        thuTvArray.add(thu15);
        thuTvArray.add(thu16);
        thuTvArray.add(thu17);
        thuTvArray.add(thu18);

        final ArrayList<TextView> friTvArray = new ArrayList<>();
        friTvArray.add(fri1);
        friTvArray.add(fri2);
        friTvArray.add(fri3);
        friTvArray.add(fri4);
        friTvArray.add(fri5);
        friTvArray.add(fri6);
        friTvArray.add(fri7);
        friTvArray.add(fri8);
        friTvArray.add(fri9);
        friTvArray.add(fri10);
        friTvArray.add(fri11);
        friTvArray.add(fri12);
        friTvArray.add(fri13);
        friTvArray.add(fri14);
        friTvArray.add(fri15);
        friTvArray.add(fri16);
        friTvArray.add(fri17);
        friTvArray.add(fri18);

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_sche sche = client.create(Interface_sche.class);
        try {
            Call<Master> call = sche.getTimeTable(InfoSingleton.getInstance().getStuId(),
                    Decrypt(InfoSingleton.getInstance().getStuPw(), getString(R.string.decrypt_key)));
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<Master> call, Response<Master> response) {
                    if(response.body().getResult_code() == 1){
                        ArrayList<String> codeArray = new ArrayList<String>();
                        String[] colorArray = getResources().getStringArray(R.array.colorArray);
                        ArrayList<String> colorArray2 = new ArrayList(Arrays.asList(colorArray));
                        ArrayList<ArrayList<String>> resultBody = response.body().getResult_body();
                        for(int i = 0; i < resultBody.size(); i++){
                            String get5string = resultBody.get(i).get(5);
//                            String get5string = "수21-24(BC-0106 부민)";
                            if(!get5string.equals("")){
                                if(!codeArray.contains(resultBody.get(i).get(0))){
                                    codeArray.add(resultBody.get(i).get(0));
                                }

                                switch (get5string.substring(0, 1)){
                                    case "월" :
                                        setText(monTvArray, get5string, resultBody, i);
                                        setColor(get5string, monTvArray, colorArray2, codeArray);
                                        break;
                                    case "화" :
                                        setText(tueTvArray, get5string, resultBody, i);
                                        setColor(get5string, tueTvArray, colorArray2, codeArray);
                                        break;
                                    case "수" :
                                        setText(wedTvArray, get5string, resultBody, i);
                                        setColor(get5string, wedTvArray, colorArray2, codeArray);
                                        break;
                                    case "목" :
                                        setText(thuTvArray, get5string, resultBody, i);
                                        setColor(get5string, thuTvArray, colorArray2, codeArray);
                                        break;
                                    case "금" :
                                        setText(friTvArray, get5string, resultBody, i);
                                        setColor(get5string, friTvArray, colorArray2, codeArray);
                                        break;
                                    default:
                                        Logger.d("default");
                                        break;
                                }
                            }
                        }
                        hideProgressDialog();
                    }else{
                        hideProgressDialog();
                        Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                        log.appendLog("inScheFragment code not matched");
                    }
                }

                @Override
                public void onFailure(Call<Master> call, Throwable t) {
                    Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    log.appendLog("inScheFragment failure");
                    hideProgressDialog();
                    t.printStackTrace();
                }
            });
        } catch (Exception e) {
            log.appendLog("inScheFragment exception");
            hideProgressDialog();
            Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
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

    public void setText(ArrayList<TextView> tvArray, String get5string, ArrayList<ArrayList<String>> resultBody, int i){
        int start = Integer.parseInt(get5string.substring(1, get5string.indexOf("-")))-3;
        int end = Integer.parseInt(get5string.substring(get5string.indexOf("-")+1, get5string.indexOf("(")));
        tvArray.get(start).setText(get5string.substring(get5string.indexOf("(")+1, get5string.indexOf(" ")));
        tvArray.get(start+1).setText(resultBody.get(i).get(2));
        tvArray.get(start+2).setText(resultBody.get(i).get(4));
//        Logger.d(end-(start+3)+1);
    }

    public void setColor(String get5string, ArrayList<TextView> tvArray, ArrayList<String> colorArray2, ArrayList<String> codeArray){
        for(int j = 0; j < codeArray.size(); j++){
            int start = Integer.parseInt(get5string.substring(1, get5string.indexOf("-")))-3;
            int end = Integer.parseInt(get5string.substring(get5string.indexOf("-")+1, get5string.indexOf("(")));
            tvArray.get(start).setBackgroundColor(Color.parseColor(colorArray2.get(j)));
            tvArray.get(start+1).setBackgroundColor(Color.parseColor(colorArray2.get(j)));
            if(!((end-(start+3)+1)==2)){
                tvArray.get(start+2).setBackgroundColor(Color.parseColor(colorArray2.get(j)));
            }
            if((end-(start+3)+1)==4){
                tvArray.get(start+3).setBackgroundColor(Color.parseColor(colorArray2.get(j)));
            }
        }
    }
}