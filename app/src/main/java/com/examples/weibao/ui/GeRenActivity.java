package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examples.weibao.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GeRenActivity extends Activity {

    @BindView(R.id.touxiang)
    ImageView touxiang;
    @BindView(R.id.danwei)
    TextView danwei;
    @BindView(R.id.r1)
    RelativeLayout r1;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.xingbie)
    TextView xingbie;
    @BindView(R.id.r2)
    RelativeLayout r2;
    @BindView(R.id.shoujihao)
    TextView shoujihao;
    @BindView(R.id.guhua)
    TextView guhua;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ge_ren);
        ButterKnife.bind(this);
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
        ImageView imageView = (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick({R.id.touxiang, R.id.r1, R.id.r2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.touxiang:
                break;
            case R.id.r1:
                break;
            case R.id.r2:
                break;
        }
    }
}
