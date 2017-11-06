package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.R;

public class SaoYiSaoTanChuActivity extends Activity {
    private TextView t1,t2,t3,t4,t5,t6,t7;
    private Button b1,b2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_yi_sao_tan_chu);

        iniView();


    }

    private void iniView() {
        ImageView imageView= (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView textView= (TextView) findViewById(R.id.title);
        textView.setText("设备维保与测试");
        t1= (TextView) findViewById(R.id.t1);
        t2= (TextView) findViewById(R.id.t2);
        t3= (TextView) findViewById(R.id.t3);
        t4= (TextView) findViewById(R.id.t4);
        t5= (TextView) findViewById(R.id.t5);
        t6= (TextView) findViewById(R.id.t6);
        t7= (TextView) findViewById(R.id.t7);
        b1= (Button) findViewById(R.id.baozhang);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaoYiSaoTanChuActivity.this,BaoZhangDengJiActivity.class));
            }
        });
        b2= (Button) findViewById(R.id.chakan);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }


}
