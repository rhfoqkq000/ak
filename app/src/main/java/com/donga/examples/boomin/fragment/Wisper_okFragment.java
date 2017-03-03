package com.donga.examples.boomin.fragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.adapter.MyData_Wisper;
import com.donga.examples.boomin.adapter.Wisper_okAdapter;
import com.donga.examples.boomin.retrofit.retrofitGetcircleNotis.Interface_getCircleNotis;
import com.donga.examples.boomin.retrofit.retrofitGetcircleNotis.Master;

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
public class Wisper_okFragment extends Fragment {
    AppendLog log = new AppendLog();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData_Wisper> myDataset;

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_wisper_ok, container, false);
        ButterKnife.bind(this, rootview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new Wisper_okAdapter(myDataset, getContext());
        mRecyclerView.setAdapter(mAdapter);

        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_getCircleNotis getCircleNotis = client.create(Interface_getCircleNotis.class);
        Call<Master> call = getCircleNotis.getPCircleNotis(String.valueOf(sharedPreferences.getInt("ID", 0)));
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                for (int i = 0; i < response.body().getResult_body().size(); i++) {
                    myDataset.add(new MyData_Wisper(response.body().getResult_body().get(i).getGetTime(), response.body().getResult_body().get(i).getTitle(),
                            response.body().getResult_body().get(i).getBody(), response.body().getResult_body().get(i).getContents(),
                            response.body().getResult_body().get(i).getId(), response.body().getResult_body().get(i).getRead_check()));
                    mAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                t.printStackTrace();
            }
        });

//        myDataset.add(new MyData_Wisper("날짜","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","참석?","참석여부"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","참석?","참석여부"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","참석?","참석여부"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","참석?","참석여부"));

        return rootview;
    }
}