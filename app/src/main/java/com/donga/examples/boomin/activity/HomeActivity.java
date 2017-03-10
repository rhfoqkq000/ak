package com.donga.examples.boomin.activity;

import android.app.ActivityManager;
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
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.donga.examples.boomin.R;
import com.donga.examples.boomin.Singleton.PushSingleton;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.leolin.shortcutbadger.ShortcutBadger;

/**
 * Created by rhfoq on 2017-02-07.
 */
public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;
    int pushCount = 0;
    Bundle bundle = null;
    int check;

    @BindView(R.id.toolbar_home)
    Toolbar toolbar;
    @BindView(R.id.drawer_layout_home)
    DrawerLayout drawer;
    @BindView(R.id.nav_view_home)
    NavigationView navigationView;


    @OnClick(R.id.menu_res)
    void menu_res() {
        Intent intent = new Intent(getApplicationContext(), ResActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_room)
    void menu_room() {
        Intent intent = new Intent(getApplicationContext(), RoomActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_empty)
    void menu_empty() {
        Intent intent = new Intent(getApplicationContext(), EmptyActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_stu)
    void menu_stu() {
        Intent intent = new Intent(getApplicationContext(), StudentActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_prof)
    void menu_prof() {
        Intent intent = new Intent(getApplicationContext(), ProActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.menu_wisper)
    void menu_site() {
        Intent intent = new Intent(getApplicationContext(), WisperActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        ActivityManager am = (ActivityManager)getApplicationContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskList = am.getRunningTasks(100);
        for( int i=0; i < taskList.size(); i++){
            Log.d("INFO","base="+taskList.get(i).baseActivity.getPackageName()+",top="+taskList.get(i).topActivity.getPackageName());
            Log.d("INFO","base="+taskList.get(i).baseActivity.getClassName()+",top="+taskList.get(i).topActivity.getClassName());
            }


        try {
            bundle = getIntent().getExtras().getBundle("contents");
        } catch (Exception e){
            bundle = null;
        }
        if (bundle!=null) {
//            Log.i("getExtras", getIntent().getExtras().getString("contents"));
//            Intent intent = new Intent(this, AlertDialogActivity.class);
//            Bundle bun = new Bundle();
//            bun.putString("contents", getIntent().getExtras().getString("contents"));
////            bun.putString("send", PushSingleton.getInstance().getmStringSend());
////            bun.putString("title", PushSingleton.getInstance().getmStringTitle());
//            intent.putExtras(bun);
//            startActivity(intent);

            Intent i = new Intent(getApplicationContext(), WisperActivity.class);
            startActivity(i);
        }else{

//        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.back);

            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
            int stuID = sharedPreferences.getInt("stuID", 0);
            Log.i("HomeActivity", "" + stuID);

//        if(!sharedPreferences.contains("pushCount")){
//            SharedPreferences.Editor editor = sharedPreferences.edit();
//            editor.putInt("pushCount", 0);
//            ShortcutBadger.applyCount(this, sharedPreferences.getInt("pushCount", 0));
//            Log.i("HomeActivity", "pushCount:"+sharedPreferences.getInt("pushCount", 0));
//            //쪽지함 dialog 색깔, 시간표 "준비 중입니다.", 공지사항이랑 버전정보 없앨까,
//            // 관리자 로그인 후 회원목록이랑 쪽지함 지우기 안보이게
//        }
            Log.i("HomeActivity", ""+sharedPreferences.getInt("checkCircle", 1));

            int check = sharedPreferences.getInt("checkCircle", 0);
            if(check == 0){
//                Log.i("HomeActivity", "check=0");
//                Intent i= new Intent(this, SelectDialogActivity.class);
//                i.putExtra("major", sharedPreferences.getString("major", ""));
//                final SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("checkCircle", 1);
//                editor.commit();
//                startActivity(i);
                boolean wrapInScrollView = false;
                new MaterialDialog.Builder(this)
                        .customView(R.layout.activity_select_dialog, wrapInScrollView)
                        .show();
//                SelectDialogActivity selectDialogActivity = new SelectDialogActivity(HomeActivity.this);
//                final SharedPreferences.Editor editor = sharedPreferences.edit();
//                editor.putInt("checkCircle", 1);
//                editor.commit();
//                selectDialogActivity.show();
            }
        }
    }


    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {    //뒤로가기 버튼 두 번 누르면 종료
            if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime)  //연속 누를 때 2초 안에 안누르면 종료 x
            {
                super.onBackPressed();
            } else    //종료
            {
                backPressedTime = tempTime;
                Toast.makeText(getApplicationContext(), "'뒤로'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT).show();
            }
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
        }   else if (id == R.id.nav_help) {
            Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_logout) {
            SharedPreferences sharedPreferences = getSharedPreferences(getResources().getString(R.string.SFLAG), Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.clear();
            editor.commit();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {
            Intent intent = new Intent(getApplicationContext(), ManageLoginActivity.class);
            startActivity(intent);
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_home);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onPause() {
//        finish();
        super.onPause();
    }
}