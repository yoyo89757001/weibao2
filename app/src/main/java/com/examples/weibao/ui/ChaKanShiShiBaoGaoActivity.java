package com.examples.weibao.ui;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.content.Intent;
import android.content.res.AssetManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import android.support.v4.os.ResultReceiver;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import android.webkit.WebView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.DownloadService.DownloadService;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.beans.FanHuiBean;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.OpenFiles;
import com.examples.weibao.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import java.io.IOException;
import java.util.List;
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
    private long planId=0;
    private String TAG="ddddddddd";
    private WebView webView;
    private  FanHuiBean fanHuiBean=null;
  //  private TbsReaderView mTbsReaderView;
    private String ididid=null;
    public  final String PATH_DOC =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+ "docpathdoc"+File.separator;
    public  final String PATH_IMAGE =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+ "docpathim"+File.separator;
    public  final String PATH_HTML =Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+ "docpathhtml"+File.separator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        planId=getIntent().getLongExtra("planId",0);
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);

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



        webView= (WebView) findViewById(R.id.forum_context);


        link_save();

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
            jsonObject.put("cmd","100");
            jsonObject.put("planId",planId);
          //  jsonObject.put("fileName","e");
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
                .url(dengLuBean.getZhuji() + "exportReport.app");

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

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                                   Log.d("ChaKanShiShiBaoGaoActiv", "开始下载");

                            AndPermission.with(ChaKanShiShiBaoGaoActivity.this)
                                    .requestCode(300)
                                    .permission(Permission.STORAGE,Permission.CAMERA)
                                    .callback(listener)
                                    .start();

                        }
                    });


                }catch (Exception e){
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 300) {

                File file = new File(PATH_DOC);
                if (!file.exists()) {
                    file.mkdirs();
                   // Log.d("ChaKanShiShiBaoGaoActiv", "创建目录");
                }
                File file2 = new File(PATH_IMAGE);
                if (!file2.exists()) {
                    file2.mkdirs();
                   // Log.d("ChaKanShiShiBaoGaoActiv", "创建目录");
                }
                File file3 = new File(PATH_HTML);
                if (!file3.exists()) {
                    file3.mkdirs();
                   // Log.d("ChaKanShiShiBaoGaoActiv", "创建目录");
                }

                Intent intent33 = new Intent(ChaKanShiShiBaoGaoActivity.this, DownloadService.class);
                intent33.putExtra("url", "http://14.23.169.42:8090/upload/realTimePlanReport/"+fanHuiBean.getDtoDesc());
                intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
                intent33.putExtra("urlName",fanHuiBean.getDtoDesc().substring(7,fanHuiBean.getDtoDesc().length()));
                startService(intent33);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 200) {
                showMSG("存储授权失败",3);

            }
        }
    };




    private class DownloadReceiver extends ResultReceiver {
        @SuppressLint("RestrictedApi")
        public DownloadReceiver(Handler handler) {
            super(handler);
        }
        @SuppressLint("RestrictedApi")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == DownloadService.UPDATE_PROGRESS) {
                 ididid=resultData.getString("ididid2");
             //   Log.d("DownloadReceiver", ididid);
                int progress = resultData.getInt("progress");
               // Log.d("ChaKanShiShiBaoGaoActiv", "progress:" + progress);

                if (progress == 100) {
                    Log.d("ChaKanShiShiBaoGaoActiv", "下载完成");
//                    Bundle bundle = new Bundle();
//                    bundle.putString("filePath", PATH_DOC+ididid);
//                    bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
//                    boolean result = mTbsReaderView.preOpen(parseFormat(ididid), false);
//                    if (result) {
//                        mTbsReaderView.openFile(bundle);
//                    }
                    webView.loadUrl("file://"+PATH_DOC+"ttt.html");

//                    try{
//                        //  sdcard/test2.docx为本地doc文件的路径
//                        Intent intent =  OpenFiles.getWordFileIntent(PATH_DOC+ididid,ChaKanShiShiBaoGaoActivity.this);
//                        startActivity(intent);
//                        finish();
//                }catch (Exception e){
//                        Log.d("DownloadReceiver", e.getMessage()+"");
//                        //没有安装第三方的软件会提示
//                       showMSG("没有安装打开该文档的软件,请安装wps",4);
//                   }
                }
            }
        }
    }

    private String parseFormat(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }

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
        super.onDestroy();
    }


}
