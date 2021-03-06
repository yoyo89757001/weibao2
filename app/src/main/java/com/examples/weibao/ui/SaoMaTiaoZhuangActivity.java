package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.examples.weibao.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class SaoMaTiaoZhuangActivity extends Activity {
    private Button baozhang,chakan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sao_ma_tiao_zhuang);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            //  tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.lanse33);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
            // tintManager.setNavigationBarTintResource(R.color.dark_grey);
        }


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
