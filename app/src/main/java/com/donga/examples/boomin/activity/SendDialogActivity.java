package com.donga.examples.boomin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.donga.examples.boomin.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rhfoq on 2017-02-22.
 */
public class SendDialogActivity extends Activity {

    //    private String notiMessage;
    @BindView(R.id.wisper_notice_name)
    TextView send_date;
    @BindView(R.id.wisper_notice_title)
    TextView send_title;
    @BindView(R.id.wisper_notice_content)
    TextView send_content;

    @OnClick(R.id.wisper_notice_ok)
    void close(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

//        setContentView(R.layout.activity_send_dialog);
        setContentView(R.layout.activity_wisper_notice);
        ButterKnife.bind(this);

        Intent i = getIntent();
        send_date.setText("날짜 : "+i.getExtras().getString("date"));
        send_title.setText("제목 : "+i.getExtras().getString("title"));
        send_content.setText("내용 : "+i.getExtras().getString("content"));

    }
}