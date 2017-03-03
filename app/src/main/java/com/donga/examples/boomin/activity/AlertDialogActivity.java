package com.donga.examples.boomin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.donga.examples.boomin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AlertDialogActivity extends Activity {

    private String notiMessage, send, title;
    @BindView(R.id.tv_popup)
    TextView tv_popup;
    @BindView(R.id.popup_close)
    TextView popup_close;
    @BindView(R.id.tv_send)
    TextView tv_send;
    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.popup_show)
    TextView popup_show;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        Bundle bun = getIntent().getExtras();
        notiMessage = bun.getString("contents");
        send = bun.getString("send");
        title = bun.getString("title");

        Log.i("inAlertDialog", "제목:"+title+",발신자:"+send);

        setContentView(R.layout.activity_alert_dialog);
        ButterKnife.bind(this);

        tv_send.setText("발신자 : " + send);
        tv_title.setText("제목 : " + title);
        tv_popup.setText("내용 : " + notiMessage);
    }

    @OnClick(R.id.popup_close)
    void onCloseClicked() {
        finish();
    }

    @OnClick(R.id.popup_show)
    void onShowClicked() {
        Intent i = new Intent(getApplicationContext(), WisperActivity.class);
        startActivity(i);
    }
}