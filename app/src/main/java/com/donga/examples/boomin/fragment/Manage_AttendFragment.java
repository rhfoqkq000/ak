package com.donga.examples.boomin.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.ManageSingleton;
import com.donga.examples.boomin.adapter.AttendAdapter;
import com.donga.examples.boomin.adapter.MyData;
import com.donga.examples.boomin.retrofit.retrofitGetPCircleNotis.Interface_getPCircleNotis;
import com.donga.examples.boomin.retrofit.retrofitGetPCircleNotis.Master;

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
public class Manage_AttendFragment extends Fragment {
    AppendLog log = new AppendLog();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData> myDataset;

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_attend, container, false);
        ButterKnife.bind(this, rootview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new AttendAdapter(myDataset);
        mRecyclerView.setAdapter(mAdapter);

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_getPCircleNotis getCircleNotis = client.create(Interface_getPCircleNotis.class);
        Call<Master> call = getCircleNotis.getPCircleNotis("bearer " + ManageSingleton.getInstance().getToken());
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                if (response.body().getResult_code() == 1) {
                    for(int i = 0; i<response.body().getResult_body().size(); i++){
                        myDataset.add(new MyData(response.body().getResult_body().get(i).getCreated_at(), response.body().getResult_body().get(i).getBody(), response.body().getResult_body().get(i).getId()));
                        mAdapter.notifyDataSetChanged();
//                        ManageSingleton.getInstance().setPcnotis_id(response.body().getResult_body().get(i).getId());
                    }

                } else {
                    log.appendLog("inAttendFragment code not matched");
                }
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                log.appendLog("inAttendFragment failure");
                t.printStackTrace();
            }
        });

        return rootview;
    }


}