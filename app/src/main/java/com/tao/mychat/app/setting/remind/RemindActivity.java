package com.tao.mychat.app.setting.remind;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tao.mychat.R;

/**
 * Created by AMOBBS on 2017/2/27.
 */

public class RemindActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remind);
        getSupportActionBar().show();
        setTitle(R.string.message_remind);
    }
}
