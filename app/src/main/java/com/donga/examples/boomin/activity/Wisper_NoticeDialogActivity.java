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
public class Wisper_NoticeDialogActivity extends Activity {

    @BindView(R.id.wisper_notice_content)
    TextView send_content;
    @BindView(R.id.wisper_notice_name)
    TextView wisper_notice_name;
    @BindView(R.id.wisper_notice_title)
    TextView wisper_notice_title;

//    @OnClick(R.id.popup_close)
//    void close(){
//        finish();
//    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        setContentView(R.layout.activity_wisper_notice);
        ButterKnife.bind(this);

        Intent i = getIntent();
        send_content.setText(i.getExtras().getString("content"));
        wisper_notice_name.setText(i.getExtras().getString("name"));
        wisper_notice_title.setText(i.getExtras().getString("title"));

    }
}