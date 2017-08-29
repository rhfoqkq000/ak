package com.donga.examples.boomin.activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.donga.examples.boomin.AppendLog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.listviewAdapter.RoomListViewAdapter;
import com.donga.examples.boomin.retrofit.retrofitRoom.Interface_room;
import com.donga.examples.boomin.retrofit.retrofitRoom.Master4;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;
import butterknife.ButterKnife;
import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by rhfoq on 2017-02-08.
 */
public class RoomActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private ProgressDialog mProgressDialog;
    AppendLog log = new AppendLog();

    @BindView(R.id.toolbar_room)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_room)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_room)
    NavigationView navigationView;
    @BindView(R.id.list_room)
    ListView listview;
    View header;
    TextView tv_room5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = getApplicationContext().getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        if(!sharedPreferences.contains("roomActivityHelp")){
            editor.putInt("roomActivityHelp", 0);
            editor.apply();
            showPopup();
        } else{
            showPopup();
        }

        showProgressDialog();

        final SwipeRefreshLayout swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiper);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showProgressDialog();
                retrofit();
                swipeRefreshLayout.setRefreshing(false);
            }
        });
        retrofit();
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_room);
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
            Intent intent = new Intent(getApplicationContext(), ResKActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_room) {
            Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_pro) {
            Intent intent = new Intent(getApplicationContext(), ProKActivity.class);
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
            Intent intent = new Intent(getApplicationContext(), NoticeKActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_change) {
            Intent intent = new Intent(getApplicationContext(), ChangeActivity.class);
            startActivity(intent);
        }   else if (id == R.id.nav_help) {
            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://45.77.31.224/"));
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_room);
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

    public void retrofit() {
        //retrofit 통신
        Retrofit client = new Retrofit.Builder().baseUrl("http://dongaboomin.xyz:3000/")
                .addConverterFactory(GsonConverterFactory.create()).build();
        Interface_room room = client.create(Interface_room.class);
        Call<Master4> call4 = room.getRoom();
        call4.enqueue(new Callback<Master4>() {
            @Override
            public void onResponse(Call<Master4> call, final Response<Master4> response) {
                if (response.body().getResult_code() == 200) {
                    // Adapter 생성
                    RoomListViewAdapter adapter = new RoomListViewAdapter();
                    // Adapter달기
                    listview.setAdapter(adapter);
                    for (int i = 0; i < response.body().getResult_body().size(); i++) {
                        adapter.addItem(response.body().getResult_body().get(i).getLoc(),
                                response.body().getResult_body().get(i).getAll(),
                                response.body().getResult_body().get(i).getUse(),
                                response.body().getResult_body().get(i).getRemain(),
                                response.body().getResult_body().get(i).getUtil());
                        if (validateEmail(response.body().getResult_body().get(i).getUtil())) {
                            header = getLayoutInflater().inflate(R.layout.listview_room, null, false);
                            tv_room5 = (TextView) header.findViewById(R.id.text_room5);
                            tv_room5.setTextColor(Color.RED);
                        }
                    }
                    listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int j, long l) {
                            Intent intent = new Intent(getApplicationContext(), RoomDetailActivity.class);
                            Log.i("before intent", response.body().getResult_body().get(j).getUrl());
                            intent.putExtra("url", response.body().getResult_body().get(j).getUrl());
                            startActivity(intent);
                        }
                    });

                    hideProgressDialog();
                } else {
                    log.appendLog("inRoomActivity code not matched");
                    Toasty.error(getApplicationContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                    hideProgressDialog();
                }
            }

            @Override
            public void onFailure(Call<Master4> call, Throwable t) {
                hideProgressDialog();
                Toasty.error(getApplicationContext(), "불러오기 실패", Toast.LENGTH_SHORT).show();
                log.appendLog("inRoomActivity failure");
                t.printStackTrace();
            }
        });
    }

    public static boolean validateEmail(String emailStr) {
        final Pattern VALID_PERCENT_REGEX = Pattern.compile("100");
        Matcher matcher = VALID_PERCENT_REGEX.matcher(emailStr);
        return matcher.find();
    }

    public void showPopup(){
        if(sharedPreferences.getInt("roomActivityHelp", 0)==0){
            AlertDialog.Builder alert_confirm = new AlertDialog.Builder(this);
            alert_confirm.setMessage("위쪽으로 스크롤하시면 새로고침됩니다. 각 줄을 터치하시면 좌석현황을 보실 수 있습니다.").setCancelable(false).setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 'YES'
                        }
                    }).setNegativeButton("다시보지않기",
                    new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // 'No'
                            editor.putInt("roomActivityHelp", 1);
                            editor.commit();
                        }
                    });
            AlertDialog alert = alert_confirm.create();
            alert.show();
        }
    }
}