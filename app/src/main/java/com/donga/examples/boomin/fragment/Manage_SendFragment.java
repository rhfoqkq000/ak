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
import com.donga.examples.boomin.adapter.MyData_Send;
import com.donga.examples.boomin.adapter.SendAdapter;
import com.donga.examples.boomin.retrofit.retrofitPNormalNotis.Interface_getPNormalNotis;

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
public class Manage_SendFragment extends Fragment {
    AppendLog log = new AppendLog();

    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ArrayList<MyData_Send> myDataset;

    @BindView(R.id.my_recycler_view)
    RecyclerView mRecyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootview = inflater.inflate(R.layout.fragment_manage_send, container, false);
        ButterKnife.bind(this,rootview);

        mRecyclerView.setHasFixedSize(true);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new SendAdapter(myDataset, getContext());
        mRecyclerView.setAdapter(mAdapter);

        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_getPNormalNotis getPNormalNotis = client.create(Interface_getPNormalNotis.class);
        Call<com.donga.examples.boomin.retrofit.retrofitPNormalNotis.Master> call = getPNormalNotis.getPNormalNotis("bearer " + ManageSingleton.getInstance().getToken());
        call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitPNormalNotis.Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitPNormalNotis.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitPNormalNotis.Master> response) {
                if(response.body().getResult_code() == 1){
                    for(int i = 0; i<response.body().getResult_body().size(); i++){
                        myDataset.add(new MyData_Send(response.body().getResult_body().get(i).getCreated_at(), response.body().getResult_body().get(i).getBody(), response.body().getResult_body().get(i).getData()));
                        mAdapter.notifyDataSetChanged();
                    }
                }else{
                    log.appendLog("inSendFragment code not matched");
                }
            }

            @Override
            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitPNormalNotis.Master> call, Throwable t) {
                log.appendLog("inSendFragment failure");
                t.printStackTrace();
            }
        });

//        myDataset.add(new MyData_Send("2017.02.02","공지 알림","공지내용"));
//        myDataset.add(new MyData_Send("2017.02.03","공지 알림","공지내용"));
//        myDataset.add(new MyData_Send("2017.02.04","공지 알림","공지내용"));
//        myDataset.add(new MyData_Send("2017.02.05","공지 알림","공지내용"));
//        myDataset.add(new MyData_Send("2017.02.06","공지 알림","공지내용"));

        return rootview;
    }

//    @Override
//    public void onPause() {
//        super.onPause();
//        Log.i("SENDFRAG", "onPause");
//        Intent i = new Intent(getContext(), AttendActivity.class);
//        startActivity(i);
//    }
}