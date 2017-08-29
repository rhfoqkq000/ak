package com.donga.examples.boomin.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebView;

import com.donga.examples.boomin.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RoomDetailActivity extends AppCompatActivity {

    @BindView(R.id.webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_detail);
        ButterKnife.bind(this);

        Intent i = getIntent();
        String strUrl = i.getStringExtra("url");
        Log.i("RoomDetailActivity", strUrl);

        webView.loadUrl(strUrl);
    }
}
