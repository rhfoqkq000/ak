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
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.couchbase.lite.Database;
import com.couchbase.lite.Document;
import com.couchbase.lite.Manager;
import com.couchbase.lite.android.AndroidContext;
import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.listviewAdapter.PartListViewAdapter;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.GradeSingleton;
import com.donga.examples.boomin.retrofit.retrofitAchieveAll.Result_body;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Stu_Achiev_All_Fragment extends Fragment {
    AppendLog log = new AppendLog();
    private ProgressDialog mProgressDialog;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    Document document;
    Manager manager;
    Database database;

    public static final String DB_NAME = "app";
    public static final String TAG = "BOO_AchievAllFragment";

    @BindView(R.id.list_all)
    ListView list_all;
    @BindView(R.id.tv_getAllGrade)
    TextView tv_getAllGrade;
    @BindView(R.id.tv_getAllAverage)
    TextView tv_getAllAverage;

    @BindView(R.id.achiev_bottom)
    LinearLayout achiev_bottom;
    @BindView(R.id.below2)
    ImageView below;
    @BindView(R.id.distin)
    TextView distin;
    @BindView(R.id.grade_number)
    TextView grade_number;

    private PartListViewAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_achiev_all, container, false);
        ButterKnife.bind(this, rootview);

//        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) rootview.findViewById(R.id.swiper);
//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                showProgressDialog();
//                setGrade();
//                swipeRefreshLayout.setRefreshing(false);
//                hideProgressDialog();
//            }
//        });

        below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                achiev_bottom.setVisibility(View.GONE);
            }
        });

//        showProgressDialog();

        adapter = new PartListViewAdapter();
        list_all.setAdapter(adapter);


        list_all.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(adapter.getItems(position).get(1)!=null){
                    distin.setText(adapter.getItems(position).get(1));
                    grade_number.setText(adapter.getItems(position).get(2));
                    achiev_bottom.setVisibility(View.VISIBLE);
                }else{
                    achiev_bottom.setVisibility(View.GONE);
                }
            }
        });

        setGrade();

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
//        if (document.getProperty("allGrade") == null) {
//            try {
//                Logger.d("이프없당");
//
//            } catch (Exception e) {
//                log.appendLog("inScheFragment exception");
//                hideProgressDialog();
//                Toasty.error(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
//                e.printStackTrace();
//            }
//        } else{
//            Logger.d("엘스");
//            ArrayList<ArrayList<String>> resultBody = (ArrayList<ArrayList<String>>)document.getProperties().get("properties");
//            hideProgressDialog();
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



    public void setGrade(){
        Result_body result_body = GradeSingleton.getInstance().getAllResultBody();
        tv_getAllGrade.setText(result_body.getAllGrade());
        tv_getAllAverage.setText(result_body.getAvgGrade());

        int GradeDetailSize = result_body.getDetail().size();
        ArrayList<ArrayList<String>> DetailList = result_body.getDetail();
        ArrayList<String> yearList = new ArrayList<String>();

        for(int i = 1; i<GradeDetailSize; i++){
            if (DetailList.get(i).get(0).length() == 4) {
                yearList.add(String.valueOf(i));
            }
        }
        ArrayList<String> fTitle = new ArrayList<String>(yearList.size());
        ArrayList<String> sTitle = new ArrayList<String>(yearList.size());
        ArrayList<Integer> position = new ArrayList<Integer>();
        for (int i = 1; i < GradeDetailSize; i++) {
            if (DetailList.get(i).get(0).length() == 4) {
                fTitle.add(DetailList.get(i).get(0));
                sTitle.add(DetailList.get(i).get(1));
            }
        }
        for(int q = 0; q<yearList.size(); q++){
            adapter.addItem(fTitle.get(q),sTitle.get(q));
            adapter.addItem1("교과목명", "성적");
            if(q<yearList.size()-1) {
                for (int j = Integer.parseInt(yearList.get(q)); j < Integer.parseInt(yearList.get(q+1)); j++) {
                    adapter.addItem2(String.valueOf(j), DetailList.get(j).get(3), DetailList.get(j).get(6), DetailList.get(j).get(4), DetailList.get(j).get(5));
                    position.add(j);
                }
            }else{
                for(int k = Integer.parseInt(yearList.get(q)); k<GradeDetailSize; k++){
                    adapter.addItem2(String.valueOf(k), DetailList.get(k).get(3), DetailList.get(k).get(6), DetailList.get(k).get(4), DetailList.get(k).get(5));
                    position.add(k);
                }
            }
        }
        GradeSingleton.getInstance().setPosition(position);
    }



    private void helloCBL(Result_body resultBody) {

        sharedPreferences = getContext().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        String documentId = String.valueOf(sharedPreferences.getInt("stuID", 0));
        document = database.getDocument(documentId);

        if (document.getProperty("allGrade") == null){
            Map<String, Object> properties = new HashMap<>();
            properties.put("allGrade", resultBody);
            try {
                document.putProperties(properties);
                Log.i("dddddd", String.valueOf(document.getProperties()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}