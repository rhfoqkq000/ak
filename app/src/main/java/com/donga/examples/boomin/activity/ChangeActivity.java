package com.donga.examples.boomin.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.ChangeSingleton;
import com.donga.examples.boomin.listviewAdapter.ChangeListViewAdapter;
import com.donga.examples.boomin.listviewAdapter.NoticeListViewAdapter;
import com.donga.examples.boomin.retrofit.retrofitChangePushPermit.Interface_changePushPermit;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Interface_getCircle;
import com.donga.examples.boomin.retrofit.retrofitGetCircle.Master;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.refactor.lib.colordialog.ColorDialog;
import cn.refactor.lib.colordialog.PromptDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by horse on 2017. 3. 6..
 */

public class ChangeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    AppendLog log = new AppendLog();
    private ProgressDialog mProgressDialog;
    ArrayList<String> changeArray;

    @BindView(R.id.toolbar_change)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_change)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_change)
    NavigationView navigationView;
    @BindView(R.id.change_spinner)
    MaterialSpinner change_spinner;
    @BindView(R.id.list_change)
    ListView listView;

    ChangeListViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

//      spinner 아이템채우기
        change_spinner.setItems("경영정보학과","국제관광학과","국제무역학과","경영학과","정치외교학과","행정학과","사회학과"
                ,"사회복지학과","미디어커뮤니케이션학과","경제학과","금융학과");



        getCircle(change_spinner.getItems().get(0).toString());

        change_spinner.setOnItemSelectedListener(new MaterialSpinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {
                switch (position){
                    case 0:
                        Logger.d(change_spinner.getItems().get(position).toString());
                        listView.setVisibility(View.VISIBLE);
                        getCircle(change_spinner.getItems().get(position).toString());
                        break;
                    case 1:
                        Logger.d("국제관광학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 2:
                        Logger.d("국제무역학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 3:
                        Logger.d("경영학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 4:
                        Logger.d("정치외교학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 5:
                        Logger.d("행정학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 6:
                        Logger.d("사회학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 7:
                        Logger.d("사회복지학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 8:
                        Logger.d("미디어커뮤니케이션학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 9:
                        Logger.d("경제학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    case 10:
                        Logger.d("금융학과");
                        getCircle(change_spinner.getItems().get(position).toString());

                        break;
                    default:
                        Logger.d("없쪄염");
                        break;
                }
            }
        });


    }

    public void getCircle(String major){
        //listview
        adapter = new ChangeListViewAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("모도리");
        adapter.addItem("오감도");
        adapter.addItem("포미스");
        adapter.addItem("심심풀이");
        adapter.addItem("노래몰이");
        adapter.addItem("평행봉");
        adapter.addItem("미파");

        //ColorDialog
        new PromptDialog(this)
                .setDialogType(PromptDialog.DIALOG_TYPE_INFO)
                .setAnimationEnable(true)
                .setTitleText("알림")
                .setContentText("동아리 변경을 하실 경우에는 기존의 선택하신 동아리 내역이 삭제됩니다.")
                .setPositiveListener("확인", new PromptDialog.OnPositiveListener() {
                    @Override
                    public void onClick(PromptDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
        showProgressDialog();
        Retrofit client = new Retrofit.Builder().baseUrl(getString(R.string.retrofit_url))
                .addConverterFactory(GsonConverterFactory.create()).build();
        final Interface_getCircle getCircle = client.create(Interface_getCircle.class);
        retrofit2.Call<Master> call = getCircle.getCircle(major);
        call.enqueue(new Callback<Master>() {
            @Override
            public void onResponse(Call<Master> call, Response<Master> response) {
                if(response.body().getResult_code() == 1){
                    int responseSize = response.body().getResult_body().size();
                    for(int i = 0; i<responseSize; i++){
                        adapter.addItem(response.body().getResult_body().get(i).getName());
                        adapter.notifyDataSetChanged();
                    }
                    hideProgressDialog();
                }else{
                    log.appendLog("inChangeActivity code not matched");
                    hideProgressDialog();
                    Toast.makeText(getApplicationContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Master> call, Throwable t) {
                log.appendLog("inChangeActivity failure");
                hideProgressDialog();
                Toast.makeText(getApplicationContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_change);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_res) {
            Intent intent = new Intent(getApplicationContext(), ResActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_room) {
            Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pro) {
            Intent intent = new Intent(getApplicationContext(), ProActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_stu) {
            Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_empty) {
            Intent intent = new Intent(getApplicationContext(), EmptyActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_wisper) {
            Intent intent = new Intent(getApplicationContext(), WisperActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_site) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.donga.ac.kr"));
            startActivity(intent);
        } else if (id == R.id.nav_noti) {
            Intent intent = new Intent(getApplicationContext(), NoticeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_change) {
            Intent intent = new Intent(getApplicationContext(), ChangeActivity.class);
            startActivity(intent);
        }  else if (id == R.id.nav_help) {
            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), ManageLoginActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_change);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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

    @Override
    protected void onPause() {
        changeArray = ChangeSingleton.getInstance().getmArray();
        changeArray.clear();
        ChangeSingleton.getInstance().setmArray(changeArray);
        super.onPause();
    }
}