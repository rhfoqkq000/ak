package com.donga.examples.boomin.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.adapter.MyData_Wisper;
import com.donga.examples.boomin.adapter.WisperAdapter;
import com.donga.examples.boomin.retrofit.retrofitNormalNotis.Interface_getNormalNotis;
import com.donga.examples.boomin.retrofit.retrofitNormalNotis.Master;

import java.util.ArrayList;

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
public class Wisper_noticeFragment extends Fragment {
    AppendLog log = new AppendLog();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData_Wisper> myDataset;

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_wisper_noice, container, false);
        ButterKnife.bind(this,rootview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new WisperAdapter(myDataset, getContext());
        mRecyclerView.setAdapter(mAdapter);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

        final long start = System.currentTimeMillis();

        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_getNormalNotis getNormalNotis = client.create(Interface_getNormalNotis.class);
        Call<Master> call = getNormalNotis.getNormalNotis(String.valueOf(sharedPreferences.getInt("ID", 0)));
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitNormalNotis.Master> call, Response<Master> response) {
                if(response.body().getResult_code() == 1){
                    long end = System.currentTimeMillis();
                    Log.i("Wisper_noticeFragment", "retrofit 시간:"+(end-start)/1000.0);

                    final long start2 = System.currentTimeMillis();

                    for(int i = 0; i<response.body().getResult_body().size(); i++){
                        myDataset.add(new MyData_Wisper(response.body().getResult_body().get(i).getGetTime(), response.body().getResult_body().get(i).getTitle(),
                                response.body().getResult_body().get(i).getBody(), response.body().getResult_body().get(i).getContents(),
                                response.body().getResult_body().get(i).getId(), response.body().getResult_body().get(i).getRead_check()));
                        mAdapter.notifyDataSetChanged();

                        long end2 = System.currentTimeMillis();
                        Log.i("Wisper_noticeFragment", "FOR문 시간:"+(end2-start2)/1000.0);
                    }
                }else{
                    log.appendLog("inWisperActivity code not matched");
                    Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT);
                }
            }
            @Override
            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitNormalNotis.Master> call, Throwable t) {
                log.appendLog("inWisperActivity failure");
                Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT);
                t.printStackTrace();
            }
        });

//        myDataset.add(new MyData_Wisper("날짜","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));

        return rootview;
    }

}