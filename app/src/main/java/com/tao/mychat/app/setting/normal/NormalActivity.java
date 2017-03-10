package com.tao.mychat.app.setting.normal;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tao.mychat.R;

/**
 * Created by AMOBBS on 2017/2/28.
 */

public class NormalActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        getSupportActionBar().show();
        setTitle(R.string.normalsetting);
    }
}
