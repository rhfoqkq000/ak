package com.donga.examples.boomin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

    @OnClick(R.id.wisper_notice_ok_text)
    void close(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_wisper_notice);
        ButterKnife.bind(this);

        Intent i = getIntent();
        send_content.setText("내용 : "+i.getExtras().getString("content"));
        wisper_notice_name.setText("보낸 이 : "+i.getExtras().getString("name"));
        wisper_notice_title.setText("제목 : "+i.getExtras().getString("title"));

    }
}