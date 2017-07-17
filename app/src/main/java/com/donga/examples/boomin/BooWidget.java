package com.donga.examples.boomin;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.donga.examples.boomin.Singleton.InfoSingleton;
import com.donga.examples.boomin.Singleton.ScheduleSingleton;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Interface_sche;
import com.donga.examples.boomin.retrofit.retrofitSchedule.Master;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Implementation of App Widget functionality.
 */
public class BooWidget extends AppWidgetProvider {

    SharedPreferences sharedPreferences;

    Document document;
    Manager manager;
    Database database;

    public static final String DB_NAME = "app";
    static String blank = "";

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager,
                                int appWidgetId, ArrayList<ArrayList<String>> resultBody) {

//        ArrayList<String> test = new ArrayList<>();
//        test.add("전공필수");
//        test.add("MIS999");
//        test.add("01");
//        test.add("test");
//        test.add("3.0");
//        test.add("test2");
//        test.add("월9-10(BB-0703 부민)");
//        test.add("");
//        test.add("");
//        test.add("");
//        test.add("");
//        test.add("");
//        test.add("");
//        test.add("확정");
//        test.add("");
//        resultBody.add(test);

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.boo_widget);//이거 없으면 UI 요소 접근 못함

        ArrayList<String> codeArray = new ArrayList<String>();
        String[] colorArray = context.getResources().getStringArray(R.array.colorArray);
        ArrayList<String> colorArray2 = new ArrayList(Arrays.asList(colorArray));
        int currentMinTime = 21;
        int currentMaxTime = 0;

        for (int i = 0; i < resultBody.size(); i++) {
            String get5string = resultBody.get(i).get(6);
            if (!get5string.equals("")) {
                if (!get5string.equals(" ")) {
                    if (!resultBody.get(i).get(1).equals("")) {
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

                switch (get5string.substring(0, 1)) {
                    case "월":
                        setTextColor("mon", context, get5string, resultBody, i, codeArray, colorArray2, views);
                        break;
                    case "화":
                        setTextColor("tue", context, get5string, resultBody, i, codeArray, colorArray2, views);
                        break;
                    case "수":
                        setTextColor("wed", context, get5string, resultBody, i, codeArray, colorArray2, views);
                        break;
                    case "목":
                        setTextColor("thu", context, get5string, resultBody, i, codeArray, colorArray2, views);
                        break;
                    case "금":
                        setTextColor("fri", context, get5string, resultBody, i, codeArray, colorArray2, views);
                        break;
                    default:
                        Logger.d("DEFAULT");
                        break;
                }
            }
        }

        if(2<currentMinTime && currentMaxTime<21){
            //주간
            views.setViewVisibility(R.id.bigLinear, View.GONE);
            views.setViewVisibility(R.id.timeLinear, View.GONE);
            views.setViewVisibility(R.id.monLinear, View.GONE);
            views.setViewVisibility(R.id.tueLinear, View.GONE);
            views.setViewVisibility(R.id.wedLinear, View.GONE);
            views.setViewVisibility(R.id.thuLinear, View.GONE);
            views.setViewVisibility(R.id.friLinear, View.GONE);
        }else if(20<currentMinTime){
            //야간
            views.setViewVisibility(R.id.dayLinear, View.GONE);
            views.setViewVisibility(R.id.timeNight, View.VISIBLE);
            views.setViewVisibility(R.id.monNight, View.VISIBLE);
            views.setViewVisibility(R.id.tueNight, View.VISIBLE);
            views.setViewVisibility(R.id.wedNight, View.VISIBLE);
            views.setViewVisibility(R.id.thuNight, View.VISIBLE);
            views.setViewVisibility(R.id.friNight, View.VISIBLE);
        }else {
            //주야간
        }

        appWidgetManager.updateAppWidget(appWidgetId, views);//widget UI 변경에 대한 confirm. 이거 안하면 뭘 바꿔도 적용안됨
    }

    public static void setTextColor(String day, Context context, String get5string, ArrayList<ArrayList<String>> resultBody, int i, ArrayList<String> codeArray,
                                    ArrayList<String> colorArray2, RemoteViews views){
        int start = Integer.parseInt(get5string.substring(1, get5string.indexOf("-")))-2;
        int start1 = start+1;
        int start2 = start+2;
        int start3 = start+3;
        int end = Integer.parseInt(get5string.substring(get5string.indexOf("-") + 1, get5string.indexOf("(")));
        String tvName = day + start;//강의실
        String tvName2 = day + start1; //교수명
        String tvName3 = day + start2; //3번째 칸
        String tvName4 = day + start3;//4
        int tvNameAddress = context.getResources().getIdentifier(tvName, "id", context.getPackageName());
        int tvNameAddress2 = context.getResources().getIdentifier(tvName2, "id", context.getPackageName());
        int tvNameAddress3 = context.getResources().getIdentifier(tvName3, "id", context.getPackageName());
        int tvNameAddress4 = context.getResources().getIdentifier(tvName4, "id", context.getPackageName());
        views.setTextViewText(tvNameAddress, get5string.substring(get5string.indexOf("(") + 1, get5string.indexOf(" ")));

        if(!resultBody.get(i).get(3).equals("")){
            blank = resultBody.get(i).get(3);
            views.setTextViewText(tvNameAddress2, resultBody.get(i).get(3));
            views.setFloat(tvNameAddress2,"setTextSize",Float.valueOf("8.7"));
        }else{
            views.setTextViewText(tvNameAddress2, blank);
            views.setFloat(tvNameAddress2,"setTextSize",Float.valueOf("8.7"));
        }

        for(int j = 0; j < codeArray.size(); j++){
            views.setInt(tvNameAddress, "setBackgroundColor", Color.parseColor(colorArray2.get(j)));
            views.setInt(tvNameAddress2, "setBackgroundColor", Color.parseColor(colorArray2.get(j)));
            if(((end-(start+3)+1)==2)){
                views.setInt(tvNameAddress3, "setBackgroundColor", Color.parseColor(colorArray2.get(j)));
            }
            if((end-(start+3)+1)==3){
                views.setInt(tvNameAddress3, "setBackgroundColor", Color.parseColor(colorArray2.get(j)));
                views.setInt(tvNameAddress4, "setBackgroundColor", Color.parseColor(colorArray2.get(j)));
            }
        }
    }

    @Override
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] appWidgetIds) {
        manager = null;
        database = null;
        try {
            manager = new Manager(new AndroidContext(context), Manager.DEFAULT_OPTIONS);
            database = manager.getDatabase(DB_NAME);
        } catch (Exception e) {
            Toasty.error(context, "불러오기 실패", Toast.LENGTH_SHORT).show();
        }
        sharedPreferences = context.getSharedPreferences(context.getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        document = database.getDocument(String.valueOf(sharedPreferences.getInt("stuID", 0)));

        if(document.getProperty("properties")!=null){
            Logger.d(document.getProperty("properties"));
            ArrayList<ArrayList<String>> resultBody = (ArrayList<ArrayList<String>>)document.getProperties().get("properties");
            for (int appWidgetId : appWidgetIds) {
                updateAppWidget(context, appWidgetManager, appWidgetId, resultBody);
            }
        }else {
            Toasty.error(context, "BOO 앱에서 시간표를 먼저 확인해주세요.", Toast.LENGTH_SHORT).show();
//            Retrofit client = new Retrofit.Builder().baseUrl(context.getString(R.string.retrofit_url))
//                    .addConverterFactory(GsonConverterFactory.create()).build();
//            Interface_sche sche = client.create(Interface_sche.class);
//
//            Call<Master> call = null;
//            try {
//                call = sche.getTimeTable(InfoSingleton.getInstance().getStuId(),
//                        Decrypt(InfoSingleton.getInstance().getStuPw(), context.getString(R.string.decrypt_key)));
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//            call.enqueue(new Callback<Master>() {
//                @Override
//                public void onResponse(Call<Master> call, Response<Master> response) {
//                    if (response.body().getResult_code() == 1) {
//                        ArrayList<ArrayList<String>> resultBody = response.body().getResult_body();
////                    setSchedule(resultBody, context);
//
//                        for (int appWidgetId : appWidgetIds) {
//                            updateAppWidget(context, appWidgetManager, appWidgetId, resultBody);
//                        }
//                    } else {
//                        Toasty.error(context, "불러오기 실패", Toast.LENGTH_SHORT).show();
//                    }
//                }
//
//                @Override
//                public void onFailure(Call<Master> call, Throwable t) {
//                    Toasty.error(context, "불러오기 실패", Toast.LENGTH_SHORT).show();
//                    t.printStackTrace();
//                }
//            });
        }

    }

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
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

