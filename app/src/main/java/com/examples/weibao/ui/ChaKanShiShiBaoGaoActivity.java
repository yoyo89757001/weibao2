package com.examples.weibao.ui;

import android.app.Activity;

import android.os.Build;
import android.os.Bundle;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;

import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.beans.FanHuiBean;
import com.examples.weibao.dialogs.QueRenDialog2;
import com.examples.weibao.dialogs.QueRenDialog3;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ChaKanShiShiBaoGaoActivity extends Activity  {
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private long planId=-1;
    private String TAG="ddddddddd";
    private WebView webView;
    private  FanHuiBean fanHuiBean=null;
  //  private TbsReaderView mTbsReaderView;
    private String ididid=null;
    private Button baocun;
    private String uil=null;
    private int styleee;
//    public  final String PATH_DOC =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+ "docpathdoc"+File.separator;
//    public  final String PATH_IMAGE =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+ "docpathim"+File.separator;
//    public  final String PATH_HTML =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+ "docpathhtml"+File.separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        planId=getIntent().getLongExtra("planId",-1);
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        uil=getIntent().getStringExtra("url");
        styleee=getIntent().getIntExtra("styleee",0);

        setContentView(R.layout.activity_cha_kan_shi_shi_bao_gao);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }
        TextView tit= (TextView) findViewById(R.id.title);
        tit.setText("维保报告详情");
        ImageView f= (ImageView) findViewById(R.id.leftim);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        baocun= (Button) findViewById(R.id.baocun);
        switch (dengLuBean.getStatus()){
            case 0:
                //维保工程师
                baocun.setText("提交审核报告");
                break;
            case 1:
                //维保主管
                baocun.setText("审核维保报告");

                break;
            case 2:
                //甲方
                baocun.setText("确认报告");

                break;
        }

        if (planId==-1){
            baocun.setVisibility(View.GONE);
        }
        if (styleee!=0){
            baocun.setVisibility(View.GONE);
        }

        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                switch (dengLuBean.getStatus()){
                    case 0:
                        //维保工程师
                        link_save1();

                        break;
                    case 1:
                        //维保主管
                        final QueRenDialog2 dialog2=new QueRenDialog2(ChaKanShiShiBaoGaoActivity.this);
                        dialog2.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                link_save2(11);
                                dialog2.dismiss();
                            }
                        });
                        dialog2.setCountText("请选择...");
                        dialog2.setOnQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                link_save2(12);
                                dialog2.dismiss();
                            }
                        });
                        dialog2.show();


                        break;
                    case 2:
                        //甲方
                        final QueRenDialog3 dialog3=new QueRenDialog3(ChaKanShiShiBaoGaoActivity.this);
                        dialog3.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                link_save3(13);
                                dialog3.dismiss();
                            }
                        });
                        dialog3.setCountText("请选择...");
                        dialog3.setOnQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                link_save3(14);
                                dialog3.dismiss();
                            }
                        });
                        dialog3.show();

                        break;
                }


            }
        });
        webView= (WebView) findViewById(R.id.forum_context);
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();

        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //支持插件
      // webSettings.setPluginsEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        if (uil!=null){
            webView.loadUrl(uil);
        }else {
            link_save();
        }


    }


    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(ChaKanShiShiBaoGaoActivity.this);
                    if (!ChaKanShiShiBaoGaoActivity.this.isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }
    private void dismissDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog!=null && tiJIaoDialog.isShowing()){
                    tiJIaoDialog.dismiss();
                    tiJIaoDialog=null;
                }
            }
        });
    }

    private void showMSG(final String s,final int i){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(ChaKanShiShiBaoGaoActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }


    private void link_save() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd",100);
            jsonObject.put("planId",(int)planId);
            jsonObject.put("baogaoModel",1);
         //   jsonObject.put("baogaoModel","1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+planId+"1"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "exportRealTimeReport.app");

        // step 3：创建 Call 对象
        call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
                dismissDialog();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    final String ss=body.string().trim();
                    Log.d("ChaKanShiShiBaoGaoActiv", ss);

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    fanHuiBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (fanHuiBean.getDtoResult()==0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Log.d("ChaKanShiShiBaoGaoActiv", "开始下载");
                                webView.loadUrl("http://14.23.169.42:8090/upload/realTimePlanReport/"+fanHuiBean.getDtoDesc());

//                                AndPermission.with(ChaKanShiShiBaoGaoActivity.this)
//                                        .requestCode(300)
//                                        .permission(Permission.STORAGE,Permission.CAMERA)
//                                        .callback(listener)
//                                        .start();

                            }
                        });
                    }else {
                        showMSG("获取数据失败",4);
                    }




                }catch (Exception e){
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void link_save1() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("planId",planId);
          //  jsonObject.put("baogaoModel","1");
            //   jsonObject.put("baogaoModel","1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+planId+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "publishReport.app");

        // step 3：创建 Call 对象
        call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
                dismissDialog();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    final String ss=body.string().trim();
                    Log.d("ChaKanShiShiBaoGaoActiv", ss+"");

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    fanHuiBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (fanHuiBean.getDtoResult()==0){

                    }else {
                        showMSG("获取数据失败",4);
                    }


                }catch (Exception e){
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void link_save2(int i) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("planId",planId);
            jsonObject.put("status",i);
            //jsonObject.put("baogaoModel","1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+planId+""+i+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "auditReport.app");

        // step 3：创建 Call 对象
        call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
                dismissDialog();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    final String ss=body.string().trim();
                    Log.d("ChaKanShiShiBaoGaoActiv", ss);

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    fanHuiBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (fanHuiBean.getDtoResult()==0){


                    }else {
                        showMSG("获取数据失败",4);
                    }


                }catch (Exception e){
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void link_save3(int i) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("planId",planId);
            jsonObject.put("status",i);
            //   jsonObject.put("baogaoModel","1");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+planId+""+i+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "confirmReport.app");

        // step 3：创建 Call 对象
        call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
                dismissDialog();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    final String ss=body.string().trim();
                    Log.d("ChaKanShiShiBaoGaoActiv", ss);

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    fanHuiBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (fanHuiBean.getDtoResult()==0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Log.d("ChaKanShiShiBaoGaoActiv", "开始下载");
                                webView.loadUrl("http://14.23.169.42:8090/upload/realTimePlanReport/"+fanHuiBean.getDtoDesc());

//                                AndPermission.with(ChaKanShiShiBaoGaoActivity.this)
//                                        .requestCode(300)
//                                        .permission(Permission.STORAGE,Permission.CAMERA)
//                                        .callback(listener)
//                                        .start();

                            }
                        });
                    }else {
                        showMSG("获取数据失败",4);
                    }




                }catch (Exception e){
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

//    private PermissionListener listener = new PermissionListener() {
//        @Override
//        public void onSucceed(int requestCode, List<String> grantedPermissions) {
//            // 权限申请成功回调。
//
//            // 这里的requestCode就是申请时设置的requestCode。
//            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
//            if(requestCode == 300) {
//
//                File file = new File(PATH_DOC);
//                if (!file.exists()) {
//                    file.mkdirs();
//                   // Log.d("ChaKanShiShiBaoGaoActiv", "创建目录");
//                }
//                File file2 = new File(PATH_IMAGE);
//                if (!file2.exists()) {
//                    file2.mkdirs();
//                   // Log.d("ChaKanShiShiBaoGaoActiv", "创建目录");
//                }
//                File file3 = new File(PATH_HTML);
//                if (!file3.exists()) {
//                    file3.mkdirs();
//                   // Log.d("ChaKanShiShiBaoGaoActiv", "创建目录");
//                }
//
////                Intent intent33 = new Intent(ChaKanShiShiBaoGaoActivity.this, DownloadService.class);
////                intent33.putExtra("url", "http://14.23.169.42:8090/upload/realTimePlanReport/"+fanHuiBean.getDtoDesc());
////                intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
////                intent33.putExtra("urlName",fanHuiBean.getDtoDesc().substring(7,fanHuiBean.getDtoDesc().length()));
////                startService(intent33);
//            }
//        }
//
//        @Override
//        public void onFailed(int requestCode, List<String> deniedPermissions) {
//            // 权限申请失败回调。
//            if(requestCode == 200) {
//                showMSG("存储授权失败",3);
//
//            }
//        }
//    };
//
//
//
//
//    private class DownloadReceiver extends ResultReceiver {
//        @SuppressLint("RestrictedApi")
//        public DownloadReceiver(Handler handler) {
//            super(handler);
//        }
//        @SuppressLint("RestrictedApi")
//        @Override
//        protected void onReceiveResult(int resultCode, Bundle resultData) {
//            super.onReceiveResult(resultCode, resultData);
//            if (resultCode == DownloadService.UPDATE_PROGRESS) {
//                 ididid=resultData.getString("ididid2");
//             //   Log.d("DownloadReceiver", ididid);
//                int progress = resultData.getInt("progress");
//               // Log.d("ChaKanShiShiBaoGaoActiv", "progress:" + progress);
//
//                if (progress == 100) {
//                    Log.d("ChaKanShiShiBaoGaoActiv", "下载完成");
////                    Bundle bundle = new Bundle();
////                    bundle.putString("filePath", PATH_DOC+ididid);
////                    bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
////                    boolean result = mTbsReaderView.preOpen(parseFormat(ididid), false);
////                    if (result) {
////                        mTbsReaderView.openFile(bundle);
////                    }
//                    webView.loadUrl("file://"+PATH_DOC+"ttt.html");
//
////                    try{
////                        //  sdcard/test2.docx为本地doc文件的路径
////                        Intent intent =  OpenFiles.getWordFileIntent(PATH_DOC+ididid,ChaKanShiShiBaoGaoActivity.this);
////                        startActivity(intent);
////                        finish();
////                }catch (Exception e){
////                        Log.d("DownloadReceiver", e.getMessage()+"");
////                        //没有安装第三方的软件会提示
////                       showMSG("没有安装打开该文档的软件,请安装wps",4);
////                   }
//                }
//            }
//        }
//    }
//
//    private String parseFormat(String fileName) {
//        return fileName.substring(fileName.lastIndexOf(".") + 1);
//    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    //删除文件夹和文件夹里面的文件
    public static void deleteDir(final String pPath) {
        File dir = new File(pPath);
        deleteDirWihtFile(dir);
    }

    public static void deleteDirWihtFile(File dir) {
        if (dir == null || !dir.exists() || !dir.isDirectory())
            return;
        for (File file : dir.listFiles()) {
            if (file.isFile())
                Log.d("ChaKanShiShiBaoGaoActiv", "file.delete():" + file.delete()); // 删除所有文件

          //  else if (file.isDirectory())
               // deleteDirWihtFile(file); // 递规的方式删除文件夹
        }
      //  dir.delete();// 删除目录本身
    }


    @Override
    protected void onDestroy() {
        if (call!=null)
            call.cancel();
     //   deleteDir(PATH_DOC);
      //  deleteDir(PATH_IMAGE);
     //   deleteDir(PATH_HTML);
      //  unregisterReceiver();
        super.onDestroy();
    }


}
