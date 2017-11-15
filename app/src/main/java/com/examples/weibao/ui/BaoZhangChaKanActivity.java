package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.WindowManager;

import com.examples.weibao.R;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class BaoZhangChaKanActivity extends Activity {
    private int shebeiID=0;
    private long baozhangID=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shebeiID=getIntent().getIntExtra("shebeiID",0);
        baozhangID=getIntent().getLongExtra("baozhangID",0);

        setContentView(R.layout.activity_bao_zhang_cha_kan);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }



    }
}
