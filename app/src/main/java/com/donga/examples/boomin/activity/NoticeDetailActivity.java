package com.donga.examples.boomin.activity;

/**
 * Created by horse on 2017. 9. 25..
 */


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.donga.examples.boomin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NoticeDetailActivity extends AppCompatActivity {

    @BindView(R.id.notice_detail_title)
    TextView textTitle;
    @BindView(R.id.notice_detail_time) TextView textTime;
    @BindView(R.id.notice_detail_content) TextView textContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_notice_detail);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        textTitle.setText(intent.getStringExtra("title"));
        textTime.setText(intent.getStringExtra("time"));
        textContent.setText(intent.getStringExtra("content"));
    }
}