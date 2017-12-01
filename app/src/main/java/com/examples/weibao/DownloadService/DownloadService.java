package com.examples.weibao.DownloadService;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ResultReceiver;
import android.util.Log;


import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/7/6 0006.
 */
public class DownloadService extends IntentService {
    public static final int UPDATE_PROGRESS = 8344;
    public String ididid;

    public DownloadService() {
        super("DownloadService");
    }

    @SuppressLint("RestrictedApi")
    @Override
    protected void onHandleIntent(Intent intent) {
        String urlToDownload = intent.getStringExtra("url");
        ResultReceiver receiver = intent.getParcelableExtra("receiver");
        long faultsId=intent.getLongExtra("faultsId",0);
        ididid = intent.getStringExtra("urlName");
      //  Log.d("DownloadService", "获取的idid" + ididid);
     //   String nameType = intent.getStringExtra("nameType");

     //   Log.d("DownloadService", "下载地址" + urlToDownload);
        try {
            URL url = new URL(urlToDownload);
            URLConnection connection = url.openConnection();
            connection.connect();
            // this will be useful so that you can show a typical 0-100% progress bar
            int fileLength = connection.getContentLength();
            // download the file
            InputStream input = new BufferedInputStream(connection.getInputStream());
            OutputStream output = new FileOutputStream(ididid);
            byte data[] = new byte[1024];
            long total = 0;
            int count;
            while ((count = input.read(data)) != -1) {
                total += count;
                // publishing the progress....


//                Bundle resultData = new Bundle();
//                resultData.putInt("progress", (int) (total * 100 / fileLength));
//                resultData.putString("ididid2", ididid);
//                receiver.send(UPDATE_PROGRESS, resultData);
                output.write(data, 0, count);

            }
            output.flush();
            output.close();
            input.close();

            Bundle resultData = new Bundle();
            resultData.putInt("progress", 100);
            resultData.putLong("faultsId",faultsId);
            resultData.putString("ididid2", ididid);
            receiver.send(UPDATE_PROGRESS, resultData);

        } catch (IOException e) {
            e.printStackTrace();
            Bundle resultData = new Bundle();
            resultData.putLong("faultsId",faultsId);
            resultData.putString("ididid2", ididid);
            resultData.putInt("progress", 0);
            Log.d("DownloadService", "下载异常" + e.getMessage());
            receiver.send(UPDATE_PROGRESS, resultData);

        }


    }
}