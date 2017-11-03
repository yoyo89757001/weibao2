package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import com.examples.weibao.R;
import com.examples.weibao.utils.Utils;

public class KaiPingActivity extends Activity {
    private boolean ff=false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kai_ping);

        ff = !Utils.getNetTypeName(KaiPingActivity.this).equals("无网络");


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                    if (ff){
                        startActivity(new Intent(KaiPingActivity.this,MainActivity.class));
                        finish();
                    }else {
                        startActivity(new Intent(KaiPingActivity.this,HomePageActivity.class));
                        finish();
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();


    }
}
