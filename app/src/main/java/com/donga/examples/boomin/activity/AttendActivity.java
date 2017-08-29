package com.donga.examples.boomin.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.ProgressDialogController;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.ManageSingleton;
import com.donga.examples.boomin.listviewAdapter.AttendListViewAdapter;
import com.donga.examples.boomin.retrofit.retrofitAdminCircleNotis.Att2;
import com.donga.examples.boomin.retrofit.retrofitAdminCircleNotis.Interface_adminCircleNotis;
import com.donga.examples.boomin.retrofit.retrofitAdminCircleNotis.Master;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-21.
 */
public class AttendActivity extends AppCompatActivity {
    AppendLog log = new AppendLog();
    private ProgressDialog mProgressDialog;

    int attend;
    String attend2;
    int none, ok, no = 0;

    @BindView(R.id.list_attend)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attend);
        ButterKnife.bind(this);

        final AttendListViewAdapter adapter = new AttendListViewAdapter();
        listView.setAdapter(adapter);

        Intent i = getIntent();

        showProgressDialog();
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_adminCircleNotis adminCircleNotis = client.create(Interface_adminCircleNotis.class);
        Call<Master> call = adminCircleNotis.adminCircleNotis("bearer " + ManageSingleton.getInstance().getToken(), i.getExtras().getString("pnotis_id"));
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                if (response.body().getResult_code() == 1) {
                    int bodySize = response.body().getResult_body().size();
                    ArrayList<Att2> getResultBody = response.body().getResult_body();
                    for (int i = 0; i < bodySize; i++) {
                        attend = getResultBody.get(i).getAtt();
                        switch (attend) {
                            case 0:
                                attend2 = "무응답";
                                none++;
                                Log.i("무응답", String.valueOf(none));
                                break;
                            case 1:
                                attend2 = "참석";
                                ok++;
                                Log.i("참석", String.valueOf(ok));
                                break;
                            case 2:
                                attend2 = "불참";
                                no++;
                                Log.i("불참", String.valueOf(no));
                                break;
                        }
                        adapter.addItem(getResultBody.get(i).getStuId(), getResultBody.get(i).getName(), attend2);
                        adapter.notifyDataSetChanged();
                        hideProgressDialog();
                    }

                    View view = findViewById(R.id.linearView);
                    final TextView attend_people = view.findViewById(R.id.attend_people);
                    final TextView attend_ok_people = view.findViewById(R.id.attend_ok_people);
                    int total_people = none + ok + no;
                    attend_people.setText(String.valueOf(total_people));
                    attend_ok_people.setText(String.valueOf(ok));

                } else {
                    log.appendLog("inAttendActivity code not matched");
                    hideProgressDialog();
                    Toasty.error(getApplicationContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                hideProgressDialog();
                t.printStackTrace();
                log.appendLog("inAttendActivity failure");
                Toasty.error(getApplicationContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
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