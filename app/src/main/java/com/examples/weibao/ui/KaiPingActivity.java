package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.examples.weibao.R;

public class KaiPingActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_ping);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    startActivity(new Intent(KaiPingActivity.this,MainActivity.class));
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
