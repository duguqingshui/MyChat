package com.tao.mychat.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.makeramen.roundedimageview.RoundedImageView;
import com.tao.mychat.R;

/**
 * Created by AMOBBS on 2017/2/6.
 */
public class RegisterActivity extends Activity {
    private RoundedImageView ri_user_image;
    private EditText ed_loginuser,ed_pass,ed_nextpass,ed_username;
    private Button bt_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initUI();
    }

    private void initUI() {
    ri_user_image=(RoundedImageView)findViewById(R.id.ri_user_image);
        ed_loginuser=(EditText)findViewById(R.id.ed_loginuser);
        ed_pass=(EditText)findViewById(R.id.ed_pass);
        ed_nextpass=(EditText)findViewById(R.id.ed_nextpass);
        ed_username=(EditText)findViewById(R.id.ed_username);
        bt_register=(Button) findViewById(R.id.bt_register);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(it);
                finish();
            }
        });
    }
}
