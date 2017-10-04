package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.examples.weibao.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class WeiBaoYuCeShiActivity extends Activity implements View.OnClickListener {
    private TextView t1,t2,t3,t4;
    private RelativeLayout r1,r2,r3,r4;
    private Button button;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bao_yu_ce_shi);
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
        TextView t= (TextView) findViewById(R.id.title);
        t.setText("维保与测试");
        ImageView imageView= (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        t1= (TextView) findViewById(R.id.t1);
        t2= (TextView) findViewById(R.id.t2);
        t3= (TextView) findViewById(R.id.t3);
        t4= (TextView) findViewById(R.id.t4);
        r1= (RelativeLayout) findViewById(R.id.r1);
        r1.setOnClickListener(this);
        r2= (RelativeLayout) findViewById(R.id.r2);
        r2.setOnClickListener(this);
        r3= (RelativeLayout) findViewById(R.id.r3);
        r3.setOnClickListener(this);
        r4= (RelativeLayout) findViewById(R.id.r4);
        r4.setOnClickListener(this);
        button= (Button) findViewById(R.id.xiayibu);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r1:

                break;
            case R.id.r2:

                break;
            case R.id.r3:

                break;
            case R.id.r4:

                break;
            case R.id.xiayibu:

            startActivity(new Intent(WeiBaoYuCeShiActivity.this,SheBeiWeiBaoYuCeShiActivity.class));

                break;

        }

    }
}
