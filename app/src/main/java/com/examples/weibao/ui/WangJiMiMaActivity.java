package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class WangJiMiMaActivity extends Activity {
    private TextView title,huoqu;
    private ImageView fanhui;
    private Button tijiao;
    private EditText shouji,yanzhengma,mima1,mima2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_ji_mi_ma);
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
        tijiao= (Button) findViewById(R.id.baocun);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        fanhui= (ImageView) findViewById(R.id.leftim);
        fanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        title= (TextView) findViewById(R.id.title);
        title.setText("忘记密码");
        shouji= (EditText) findViewById(R.id.shouji);
        yanzhengma= (EditText) findViewById(R.id.yanzhengma);
        huoqu= (TextView) findViewById(R.id.huoqu);
        mima1= (EditText) findViewById(R.id.mima1);
        mima2= (EditText) findViewById(R.id.mima2);


    }
}
