package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.R;

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView title,wangji;
    private ImageView fanhui;
    private EditText zhanghao,mima;
    private Button login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fanhui=findViewById(R.id.leftim);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title=findViewById(R.id.title);
        title.setText("用户登录");

        wangji=findViewById(R.id.wangji);
        wangji.setOnClickListener(this);
        zhanghao=findViewById(R.id.zhanghao);
        mima=findViewById(R.id.mima);
        login=findViewById(R.id.login);
        login.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:

                break;
            case R.id.wangji:

                break;

        }

    }
}
