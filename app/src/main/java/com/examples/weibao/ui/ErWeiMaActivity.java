package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;

import com.examples.weibao.R;
import com.journeyapps.barcodescanner.CaptureManager;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

public class ErWeiMaActivity extends Activity implements DecoratedBarcodeView.TorchListener {
    private CaptureManager captureManager;
    private DecoratedBarcodeView mDBV;
    private Button button;
    private boolean kai=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_er_wei_ma);

        mDBV = (DecoratedBarcodeView) findViewById(R.id.dbv_custom);
        mDBV.setTorchListener(this);

        button= (Button) findViewById(R.id.shan);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (kai){
                    mDBV.setTorchOff();
                    kai=false;
                    button.setText("开启闪光灯");

                }else {
                    mDBV.setTorchOn();
                    kai=true;
                    button.setText("关闭闪光灯");
                }

            }
        });
        //重要代码，初始化捕获
        captureManager = new CaptureManager(this, mDBV);
        captureManager.initializeFromIntent(getIntent(),savedInstanceState);
        captureManager.decode();
    }

    @Override
    protected void onPause() {
        super.onPause();
        captureManager.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        captureManager.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        captureManager.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        captureManager.onSaveInstanceState(outState);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return mDBV.onKeyDown(keyCode, event) || super.onKeyDown(keyCode, event);
    }

    @Override
    public void onTorchOn() {

    }

    @Override
    public void onTorchOff() {

    }
}
