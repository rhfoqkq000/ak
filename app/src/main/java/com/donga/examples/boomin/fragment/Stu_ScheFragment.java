package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.Singleton.ScheduleSingleton;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Interface_sche;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Master;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Document document;
    Manager manager;
    Database database;

    public static final String DB_NAME = "app";

    public static final String TAG = "BOO_Stu_ScheFragment";

    String blank = "";

    private ProgressDialog mProgressDialog;
    AppendLog log = new AppendLog();

    ArrayList<TextView> monTvArray = null;
    ArrayList<TextView> tueTvArray = null;
    ArrayList<TextView> wedTvArray = null;
    ArrayList<TextView> thuTvArray = null;
    ArrayList<TextView> friTvArray = null;

    @BindView(R.id.dayLinear)
    LinearLayout dayLinear;
    @BindView(R.id.dayTimeLinear)
    LinearLayout dayTimeLinear;
    @BindView(R.id.dayMonLinear)
    LinearLayout dayMonLinear;
    @BindView(R.id.dayTueLinear)
    LinearLayout dayTueLinear;
    @BindView(R.id.dayWedLinear)
    LinearLayout dayWedLinear;
    @BindView(R.id.dayThuLinear)
    LinearLayout dayThuLinear;
    @BindView(R.id.dayFriLinear)
    LinearLayout dayFriLinear;

    @BindView(R.id.bigLinear)
    LinearLayout nightLinear;
    @BindView(R.id.timeLinear)
    LinearLayout timeLinear;
    @BindView(R.id.monLinear)
    LinearLayout monLinear;
    @BindView(R.id.tueLinear)
    LinearLayout tueLinear;
    @BindView(R.id.wedLinear)
    LinearLayout wedLinear;
    @BindView(R.id.thuLinear)
    LinearLayout thuLinear;
    @BindView(R.id.friLinear)
    LinearLayout friLinear;

    @BindView(R.id.timeNight)
    TextView timeNight;
    @BindView(R.id.monNight)
    TextView monNight;
    @BindView(R.id.tueNight)
    TextView tueNight;
    @BindView(R.id.wedNight)
    TextView wedNight;
    @BindView(R.id.thuNight)
    TextView thuNight;
    @BindView(R.id.friNight)
    TextView friNight;

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

    @BindView(R.id.night_mon1)
    TextView night_mon1;
    @BindView(R.id.night_mon2)
    TextView night_mon2;
    @BindView(R.id.night_mon3)
    TextView night_mon3;
    @BindView(R.id.night_mon4)
    TextView night_mon4;
    @BindView(R.id.night_mon5)
    TextView night_mon5;
    @BindView(R.id.night_mon6)
    TextView night_mon6;
    @BindView(R.id.night_mon7)
    TextView night_mon7;
    @BindView(R.id.night_mon8)
    TextView night_mon8;
    @BindView(R.id.night_mon9)
    TextView night_mon9;
    @BindView(R.id.night_mon10)
    TextView night_mon10;

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

    @BindView(R.id.night_tue1)
    TextView night_tue1;
    @BindView(R.id.night_tue2)
    TextView night_tue2;
    @BindView(R.id.night_tue3)
    TextView night_tue3;
    @BindView(R.id.night_tue4)
    TextView night_tue4;
    @BindView(R.id.night_tue5)
    TextView night_tue5;
    @BindView(R.id.night_tue6)
    TextView night_tue6;
    @BindView(R.id.night_tue7)
    TextView night_tue7;
    @BindView(R.id.night_tue8)
    TextView night_tue8;
    @BindView(R.id.night_tue9)
    TextView night_tue9;
    @BindView(R.id.night_tue10)
    TextView night_tue10;

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

    @BindView(R.id.night_wed1)
    TextView night_wed1;
    @BindView(R.id.night_wed2)
    TextView night_wed2;
    @BindView(R.id.night_wed3)
    TextView night_wed3;
    @BindView(R.id.night_wed4)
    TextView night_wed4;
    @BindView(R.id.night_wed5)
    TextView night_wed5;
    @BindView(R.id.night_wed6)
    TextView night_wed6;
    @BindView(R.id.night_wed7)
    TextView night_wed7;
    @BindView(R.id.night_wed8)
    TextView night_wed8;
    @BindView(R.id.night_wed9)
    TextView night_wed9;
    @BindView(R.id.night_wed10)
    TextView night_wed10;

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

    @BindView(R.id.night_thu1)
    TextView night_thu1;
    @BindView(R.id.night_thu2)
    TextView night_thu2;
    @BindView(R.id.night_thu3)
    TextView night_thu3;
    @BindView(R.id.night_thu4)
    TextView night_thu4;
    @BindView(R.id.night_thu5)
    TextView night_thu5;
    @BindView(R.id.night_thu6)
    TextView night_thu6;
    @BindView(R.id.night_thu7)
    TextView night_thu7;
    @BindView(R.id.night_thu8)
    TextView night_thu8;
    @BindView(R.id.night_thu9)
    TextView night_thu9;
    @BindView(R.id.night_thu10)
    TextView night_thu10;

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

    @BindView(R.id.night_fri1)
    TextView night_fri1;
    @BindView(R.id.night_fri2)
    TextView night_fri2;
    @BindView(R.id.night_fri3)
    TextView night_fri3;
    @BindView(R.id.night_fri4)
    TextView night_fri4;
    @BindView(R.id.night_fri5)
    TextView night_fri5;
    @BindView(R.id.night_fri6)
    TextView night_fri6;
    @BindView(R.id.night_fri7)
    TextView night_fri7;
    @BindView(R.id.night_fri8)
    TextView night_fri8;
    @BindView(R.id.night_fri9)
    TextView night_fri9;
    @BindView(R.id.night_fri10)
    TextView night_fri10;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_sche, container, false);
        ButterKnife.bind(this, rootview);

        showProgressDialog();

        final SwipeRefreshLayout swipeRefreshLayout = rootview.findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgressDialog();
                retrofitSche();
                swipeRefreshLayout.setRefreshing(false);
                hideProgressDialog();
            }
        });

        monTvArray = new ArrayList<>();
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
        monTvArray.add(night_mon1);
        monTvArray.add(night_mon2);
        monTvArray.add(night_mon3);
        monTvArray.add(night_mon4);
        monTvArray.add(night_mon5);
        monTvArray.add(night_mon6);
        monTvArray.add(night_mon7);
        monTvArray.add(night_mon8);
        monTvArray.add(night_mon9);
        monTvArray.add(night_mon10);

        tueTvArray = new ArrayList<>();
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
        tueTvArray.add(night_tue1);
        tueTvArray.add(night_tue2);
        tueTvArray.add(night_tue3);
        tueTvArray.add(night_tue4);
        tueTvArray.add(night_tue5);
        tueTvArray.add(night_tue6);
        tueTvArray.add(night_tue7);
        tueTvArray.add(night_tue8);
        tueTvArray.add(night_tue9);
        tueTvArray.add(night_tue10);

        wedTvArray = new ArrayList<>();
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
        wedTvArray.add(night_wed1);
        wedTvArray.add(night_wed2);
        wedTvArray.add(night_wed3);
        wedTvArray.add(night_wed4);
        wedTvArray.add(night_wed5);
        wedTvArray.add(night_wed6);
        wedTvArray.add(night_wed7);
        wedTvArray.add(night_wed8);
        wedTvArray.add(night_wed9);
        wedTvArray.add(night_wed10);

        thuTvArray = new ArrayList<>();
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
        thuTvArray.add(night_thu1);
        thuTvArray.add(night_thu2);
        thuTvArray.add(night_thu3);
        thuTvArray.add(night_thu4);
        thuTvArray.add(night_thu5);
        thuTvArray.add(night_thu6);
        thuTvArray.add(night_thu7);
        thuTvArray.add(night_thu8);
        thuTvArray.add(night_thu9);
        thuTvArray.add(night_thu10);

        friTvArray = new ArrayList<>();
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
        friTvArray.add(night_fri1);
        friTvArray.add(night_fri2);
        friTvArray.add(night_fri3);
        friTvArray.add(night_fri4);
        friTvArray.add(night_fri5);
        friTvArray.add(night_fri6);
        friTvArray.add(night_fri7);
        friTvArray.add(night_fri8);
        friTvArray.add(night_fri9);
        friTvArray.add(night_fri10);

//        manager = null;
//        database = null;
//        try {
//            manager = new Manager(new AndroidContext(getContext()), Manager.DEFAULT_OPTIONS);
//            database = manager.getDatabase(DB_NAME);
//        } catch (Exception e) {
//            Log.e(TAG, "Error getting database", e);
//        }
//        sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
//        document = database.getDocument(String.valueOf(sharedPreferences.getInt("stuID", 0)));
//
//        if (document.getProperty("properties") == null) {
//                try {
//                Logger.d("이프없당");
//
//                retrofitSche();
//            } catch (Exception e) {
//                log.appendLog("inScheFragment exception");
//                hideProgressDialog();
//                Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//            }
//        } else{
//            Logger.d("엘스");
//            ArrayList<ArrayList<String>> resultBody = (ArrayList<ArrayList<String>>)document.getProperties().get("properties");
//            setSchedule(resultBody);
//            hideProgressDialog();
//        }

        try {
            Logger.d(InfoSingleton.getInstance().getStuId()+", "+InfoSingleton.getInstance().getStuPw());
        } catch (Exception e) {
            e.printStackTrace();
        }

        retrofitSche();

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
//        int end = Integer.parseInt(get5string.substring(get5string.indexOf("-")+1, get5string.indexOf("(")));
        tvArray.get(start).setText(get5string.substring(get5string.indexOf("(")+1, get5string.indexOf(" ")));
        if(!resultBody.get(i).get(3).equals("")){
            blank = resultBody.get(i).get(3);
            tvArray.get(start+1).setText(resultBody.get(i).get(3));
        }else{
            tvArray.get(start+1).setText(blank);
        }
//        tvArray.get(start+2).setText(resultBody.get(i).get(4));
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


//    private void helloCBL(ArrayList<ArrayList<String>> resultBody) {
//
//        sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
////        editor = sharedPreferences.edit();
//
//        // Create the documenteDocument(database);
//        String documentId = String.valueOf(sharedPreferences.getInt("stuID", 0));
//        // stuID라는 이름의 Document 생성
//        document = database.getDocument(documentId);
//
//        if (document.getProperty("properties") == null){
//            Map<String, Object> properties = new HashMap<>();
//            // properties 형식 안에 "properties"라는 이름의 resultBody를 put
//            properties.put("properties", resultBody);
//            try {
//                document.putProperties(properties);
//                Log.i("dddddd", String.valueOf(document.getProperties()));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

//    private void updateDoc(Database database, String documentId) {
//        Document document = database.getDocument(documentId);
//        try {
//            // Update the document with more data
//            Map<String, Object> updatedProperties = new HashMap<String, Object>();
//            updatedProperties.putAll(document.getProperties());
//            updatedProperties.put("eventDescription", "Everyone is invited!");
//            updatedProperties.put("address", "123 Elm St.");
//            // Save to the Couchbase local Couchbase Lite DB
//            document.putProperties(updatedProperties);
//        } catch (CouchbaseLiteException e) {
//            Log.e(TAG, "Error putting", e);
//        }
//    }

    public void setSchedule(ArrayList<ArrayList<String>> resultBody){
        ArrayList<String> codeArray = new ArrayList<String>();
        String[] colorArray = getResources().getStringArray(R.array.colorArray);
        ArrayList<String> colorArray2 = new ArrayList(Arrays.asList(colorArray));
        int currentMinTime = 21;
        int currentMaxTime = 0;

        for(int i = 0; i < resultBody.size(); i++){
            String get5string = resultBody.get(i).get(6);
            if(!get5string.equals("")){
                if(!get5string.equals(" ")) {
                    if(!resultBody.get(i).get(1).equals("")){
                        if (!codeArray.contains(resultBody.get(i).get(1))) {
                            codeArray.add(resultBody.get(i).get(1));
                        }
                    }
                    if (Integer.parseInt(get5string.substring(1, get5string.indexOf("-"))) < currentMinTime) {
                        currentMinTime = Integer.parseInt(get5string.substring(1, get5string.indexOf("-")));
                        ScheduleSingleton.getInstance().setCurrentMinTime(currentMinTime);
                    }
                    if (Integer.parseInt(get5string.substring(get5string.indexOf("-") + 1, get5string.indexOf("("))) > currentMaxTime) {
                        currentMaxTime = Integer.parseInt(get5string.substring(get5string.indexOf("-") + 1, get5string.indexOf("(")));
                        ScheduleSingleton.getInstance().setCurrentMaxTime(currentMaxTime);
                    }
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
                        break;
                }
            }
        }

        if(2<currentMinTime && currentMaxTime<21){
            //주간
            nightLinear.setVisibility(View.GONE);
            timeLinear.setVisibility(View.GONE);
            monLinear.setVisibility(View.GONE);
            tueLinear.setVisibility(View.GONE);
            wedLinear.setVisibility(View.GONE);
            thuLinear.setVisibility(View.GONE);
            friLinear.setVisibility(View.GONE);
        }else if(20<currentMinTime){
            //야간
            dayLinear.setVisibility(View.GONE);
            timeNight.setVisibility(View.VISIBLE);
            monNight.setVisibility(View.VISIBLE);
            tueNight.setVisibility(View.VISIBLE);
            wedNight.setVisibility(View.VISIBLE);
            thuNight.setVisibility(View.VISIBLE);
            friNight.setVisibility(View.VISIBLE);
        }else {
            //주야간
        }
    }

    public void retrofitSche(){

        showProgressDialog();
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_sche sche = client.create(Interface_sche.class);

        Call<Master> call;
        try {
            call = sche.getTimeTable(InfoSingleton.getInstance().getStuId(),
                    InfoSingleton.getInstance().getStuPw());
            call.enqueue(new Callback<Master>() {
                @Override
                public void onResponse(Call<Master> call, Response<Master> response) {
                    if(response.body().getResult_code() == 1){
                        ArrayList<ArrayList<String>> resultBody = response.body().getResult_body();
//                        helloCBL(resultBody);
                        setSchedule(resultBody);
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
            Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }

    }


}