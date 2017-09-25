package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.R;

public class WangJiMiMaActivity extends Activity {
    private TextView title;
    private ImageView fanhui;
    private Button tijiao;
    private EditText shouji,yanzhengma,huoqu,mima1,mima2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_ji_mi_ma);

        initView();

    }

    private void initView() {
        tijiao=findViewById(R.id.baocun);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fanhui=findViewById(R.id.leftim);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title=findViewById(R.id.title);
        title.setText("忘记密码");
        shouji=findViewById(R.id.shouji);
        yanzhengma=findViewById(R.id.yanzhengma);
        huoqu=findViewById(R.id.huoqu);
        mima1=findViewById(R.id.mima1);
        mima2=findViewById(R.id.mima2);


    }
}
