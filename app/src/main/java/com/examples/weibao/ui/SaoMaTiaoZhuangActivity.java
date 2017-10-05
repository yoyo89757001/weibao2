package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.examples.weibao.R;

public class SaoMaTiaoZhuangActivity extends Activity {
    private Button baozhang,chakan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_ma_tiao_zhuang);

        initView();

    }

    private void initView() {
        baozhang= (Button) findViewById(R.id.baozhang);
        baozhang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaoMaTiaoZhuangActivity.this,BaoZhangDengJiActivity.class));
            }
        });
        chakan= (Button) findViewById(R.id.chakan);
        chakan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
