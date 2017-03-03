package com.donga.examples.boomin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.donga.examples.boomin.listviewAdapter.PartListViewAdapter;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.GradeSingleton;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rhfoq on 2017-02-15.
 */
public class Stu_Achiev_Part_Fragment extends Fragment {
    @BindView(R.id.list_part)
    ListView list_part;
    @BindView(R.id.get)
    TextView get;
    @BindView(R.id.aver)
    TextView aver;

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
        final View rootview = inflater.inflate(R.layout.fragment_achiev_part, container, false);
        ButterKnife.bind(this,rootview);

        below.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                achiev_bottom.setVisibility(View.GONE);
            }
        });

        adapter = new PartListViewAdapter();
        list_part.setAdapter(adapter);

        list_part.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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

        get.setText(GradeSingleton.getInstance().getPartGrade());
        aver.setText(GradeSingleton.getInstance().getPartAvg());

        int GradeDetailSize = GradeSingleton.getInstance().getDetail2().size();
        ArrayList<ArrayList<String>> DetailList = GradeSingleton.getInstance().getDetail2();
        ArrayList<String> yearList = new ArrayList<String>();

        for(int i = 1; i<GradeDetailSize; i++){
            if (GradeSingleton.getInstance().getDetail2().get(i).get(0).length() == 4) {
                yearList.add(String.valueOf(i));
            }
        }
        ArrayList<String> fTitle = new ArrayList<String>(yearList.size());
        ArrayList<String> sTitle = new ArrayList<String>(yearList.size());
        ArrayList<Integer> position = new ArrayList<Integer>();
        for (int i = 1; i < GradeDetailSize; i++) {
            if (GradeSingleton.getInstance().getDetail2().get(i).get(0).length() == 4) {
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

        return rootview;
    }
}