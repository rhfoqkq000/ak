package com.donga.examples.boomin.fragment;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.listviewAdapter.PartListViewAdapter;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.GradeSingleton;

import java.util.ArrayList;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Stu_Achiev_All_Fragment extends Fragment {
    AppendLog log = new AppendLog();
    private ProgressDialog mProgressDialog;
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

        tv_getAllGrade.setText(GradeSingleton.getInstance().getAllGrade());
        tv_getAllAverage.setText(GradeSingleton.getInstance().getAvgGrade());

        int GradeDetailSize = GradeSingleton.getInstance().getDetail().size();
        ArrayList<ArrayList<String>> DetailList = GradeSingleton.getInstance().getDetail();
        ArrayList<String> yearList = new ArrayList<String>();

        for(int i = 1; i<GradeDetailSize; i++){
            if (GradeSingleton.getInstance().getDetail().get(i).get(0).length() == 4) {
                yearList.add(String.valueOf(i));
            }
        }
        ArrayList<String> fTitle = new ArrayList<String>(yearList.size());
        ArrayList<String> sTitle = new ArrayList<String>(yearList.size());
        ArrayList<Integer> position = new ArrayList<Integer>();
        for (int i = 1; i < GradeDetailSize; i++) {
            if (GradeSingleton.getInstance().getDetail().get(i).get(0).length() == 4) {
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

        return rootview;
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