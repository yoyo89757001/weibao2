//package com.examples.weibao.DownloadService;//package com.meijie.mjwt.DownloadService;
//
//import android.annotation.SuppressLint;
//import android.os.Bundle;
//import android.os.Handler;
//import android.support.v4.os.ResultReceiver;
//
///**
// * Created by Administrator on 2016/7/6 0006.
// */
//public class DownloadReceiver extends ResultReceiver  {
//    @SuppressLint("RestrictedApi")
//    public DownloadReceiver(Handler handler) {
//        super(handler);
//    }
//    @SuppressLint("RestrictedApi")
//    @Override
//    protected void onReceiveResult(int resultCode, Bundle resultData) {
//        super.onReceiveResult(resultCode, resultData);
//        if (resultCode == DownloadService.UPDATE_PROGRESS) {
//            int progress = resultData.getInt("progress");
//            mProgressDialog.setProgress(progress);
//            if (progress == 100) {
//                mProgressDialog.dismiss();
//            }
//        }
//    }
//}