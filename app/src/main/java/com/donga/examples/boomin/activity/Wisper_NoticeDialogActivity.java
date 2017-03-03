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

    //    private String notiMessage;
    @BindView(R.id.dialog_wisper_content)
    TextView send_content;

    @OnClick(R.id.popup_close)
    void close(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);



        setContentView(R.layout.activity_wisper_dialog);
        ButterKnife.bind(this);

        Intent i = getIntent();
        send_content.setText(i.getExtras().getString("content"));

    }
}