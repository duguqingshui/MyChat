package com.tao.mychat.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tao.mychat.R;
import com.tao.mychat.app.home.HomeActivity;

/**
 * Created by AMOBBS on 2017/2/6.
 */

public class LoginActivity extends AppCompatActivity{
    private EditText ed_loginuser,ed_pass;
    private Button bt_login;
    private TextView tv_nologin,tv_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ActionBar actionBar = getSupportActionBar();
        actionBar.show();
        actionBar.setHomeButtonEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(false);
        setTitle("登录用户");

        initUI();
        initData();
    }


    private void initUI() {
        ed_loginuser=(EditText)findViewById(R.id.ed_loginuser);
        ed_pass=(EditText)findViewById(R.id.ed_pass);
        bt_login=(Button) findViewById(R.id.bt_login);
        tv_nologin=(TextView)findViewById(R.id.tv_nologin);
        tv_register=(TextView)findViewById(R.id.tv_register);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LoginActivity.this,HomeActivity.class);
                startActivity(it);
                finish();
            }
        });
        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(it);
                finish();
            }
        });
    }

    private void initData() {

    }
    public class InputWatcher implements TextWatcher {
        private static final String TAG = "InputWatcher" ;
        private Button mBtnClear;
        private EditText mEtContainer ;

        /**
         *
         * @param btnClear 清空按钮 可以是button的子类
         * @param etContainer edittext
         */
        public InputWatcher(Button btnClear, EditText etContainer) {
            if (btnClear == null || etContainer == null) {
                throw new IllegalArgumentException("请确保btnClear和etContainer不为空");
            }
            this.mBtnClear = btnClear;
            this.mEtContainer = etContainer;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (!TextUtils.isEmpty(s)) {
                if (mBtnClear != null) {
                    mBtnClear.setVisibility(View.VISIBLE);
                    mBtnClear.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mEtContainer != null) {
                                mEtContainer.getText().clear();
                            }
                        }
                    });
                }
            } else {
                if (mBtnClear != null) {
                    mBtnClear.setVisibility(View.GONE);
                }
            }
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    }
}
