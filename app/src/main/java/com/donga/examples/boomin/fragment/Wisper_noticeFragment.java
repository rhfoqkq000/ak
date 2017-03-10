package com.donga.examples.boomin.fragment;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.NoticeSingleton;
import com.donga.examples.boomin.adapter.MyData_Wisper;
import com.donga.examples.boomin.adapter.WisperAdapter;
import com.donga.examples.boomin.retrofit.retrofitNormalNotis.Interface_getNormalNotis;
import com.donga.examples.boomin.retrofit.retrofitNormalNotis.Master;
import com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis.Interface_removeNormalNotis;
import com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis.JsonRequest;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
    private ProgressDialog mProgressDialog;
    SharedPreferences sharedPreferences;
    ArrayList<String> noticeIdArray;

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
//        mRecyclerView.getRecycledViewPool().setMaxRecycledViews(, 0);

        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        myDataset = new ArrayList<>();
        mAdapter = new WisperAdapter(myDataset, getContext());
        mRecyclerView.setAdapter(mAdapter);

        sharedPreferences = this.getActivity().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);

        retrofit();

//        myDataset.add(new MyData_Wisper("날짜","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));
//        myDataset.add(new MyData_Wisper("2017.02.02","망아지","공지 알림","공지내용"));

        return rootview;
    }

    @OnClick(R.id.garbage)
    void onGarbageClicked(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // 여기서 부터는 알림창의 속성 설정
        builder.setTitle("삭제 확인 대화 상자")
                .setMessage("정말로 해당 쪽지를 삭제하시겠습니까?")
                .setCancelable(false)        // 뒤로 버튼 클릭시 취소 가능 설정
                .setPositiveButton("확인", new DialogInterface.OnClickListener(){
                    // 확인 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        noticeIdArray = NoticeSingleton.getInstance().getNoticeIdArray();
                        if(noticeIdArray.isEmpty()){
                            Toast.makeText(getContext(), "선택된 항목이 없습니다.", Toast.LENGTH_SHORT).show();
                        }else{
                            showProgressDialog();
                            //retrofit 통신
                            Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                                    .addConverterFactory(GsonConverterFactory.create()).build();
                            Interface_removeNormalNotis remove = client.create(Interface_removeNormalNotis.class);
                            JsonRequest jsonRequest = new JsonRequest(NoticeSingleton.getInstance().getNoticeIdArray());
                            Call<com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis.Master> call = remove.removeNormalNotis("application/json",
                                    jsonRequest);
                            call.enqueue(new Callback<com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis.Master>() {
                                @Override
                                public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis.Master> call, Response<com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis.Master> response) {
                                    for(int i = 0; i<noticeIdArray.size(); i++){
                                        myDataset.remove(i);
                                    }
                                    mAdapter.notifyDataSetChanged();
                                    noticeIdArray.clear();
                                    NoticeSingleton.getInstance().setNoticeIdArray(noticeIdArray);
                                    hideProgressDialog();
                                }

                                @Override
                                public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitRemoveNormalNotis.Master> call, Throwable t) {
                                    hideProgressDialog();
                                    t.printStackTrace();
                                }
                            });
                        }
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener(){
                    // 취소 버튼 클릭시 설정
                    public void onClick(DialogInterface dialog, int whichButton){
                        dialog.cancel();
                    }
                });

        AlertDialog dialog = builder.create();    // 알림창 객체 생성
        dialog.show();    // 알림창 띄우기

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

    public void retrofit(){
        showProgressDialog();
        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_getNormalNotis getNormalNotis = client.create(Interface_getNormalNotis.class);
        Call<Master> call = getNormalNotis.getNormalNotis(String.valueOf(sharedPreferences.getInt("ID", 0)));
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<com.donga.examples.boomin.retrofit.retrofitNormalNotis.Master> call, Response<Master> response) {
                if(response.body().getResult_code() == 1){
                    for(int i = 0; i<response.body().getResult_body().size(); i++){
                        myDataset.add(new MyData_Wisper(response.body().getResult_body().get(i).getGetTime(), response.body().getResult_body().get(i).getTitle(),
                                response.body().getResult_body().get(i).getBody(), response.body().getResult_body().get(i).getContents(),
                                response.body().getResult_body().get(i).getId(), response.body().getResult_body().get(i).getRead_check()));
                        mAdapter.notifyDataSetChanged();
                    }
                }else{
                    log.appendLog("inWisperActivity code not matched");
                    Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                }
                hideProgressDialog();
            }
            @Override
            public void onFailure(Call<com.donga.examples.boomin.retrofit.retrofitNormalNotis.Master> call, Throwable t) {
                log.appendLog("inWisperActivity failure");
                Toast.makeText(getContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                hideProgressDialog();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onPause() {
        noticeIdArray = NoticeSingleton.getInstance().getNoticeIdArray();
        noticeIdArray.clear();
        NoticeSingleton.getInstance().setNoticeIdArray(noticeIdArray);
        super.onPause();
    }
}