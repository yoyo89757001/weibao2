package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class WangJiMiMaActivity extends Activity {
    private TextView title,huoqu;
    private ImageView fanhui;
    private Button tijiao;
    private EditText shouji,yanzhengma,mima1,mima2,zhanghao;
    private Call call=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private TiJIaoDialog tiJIaoDialog=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wang_ji_mi_ma);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }
        dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);

        initView();

    }



    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(WangJiMiMaActivity.this);
                    if (!WangJiMiMaActivity.this.isFinishing())
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

                Toast tastyToast= TastyToast.makeText(WangJiMiMaActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

    private void initView() {
        zhanghao= (EditText) findViewById(R.id.zhanghao);
        tijiao= (Button) findViewById(R.id.baocun);
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String zh=zhanghao.getText().toString().trim();
                String mb=shouji.getText().toString().trim();
                String cd=yanzhengma.getText().toString().trim();
                String pw=mima1.getText().toString().trim();
                String pw2=mima2.getText().toString().trim();
                if (!zh.equals("") && !mb.equals("") && !cd.equals("") && !pw.equals("") && !pw2.equals("")){
                    if (pw.equals(pw2)){
                        link_save();
                    }else {
                        showMSG("两次输入的密码不一致",3);
                    }
                }else {
                    showMSG("请填写完整信息!",3);
                }

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
        huoqu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (shouji.getText().toString().trim().equals("")){
                    showMSG("请输先入电话号码",4);
                }else {
                     CountDownTimer timer = new CountDownTimer(60000, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            huoqu.setEnabled(false);
                            huoqu.setText((millisUntilFinished / 1000) + "秒后可重发");
                        }

                        @Override
                        public void onFinish() {
                            huoqu.setEnabled(true);
                            huoqu.setText("获取验证码");
                        }
                    };
                     timer.start();
                     link_huoqu();
                }
            }
        });
        mima1= (EditText) findViewById(R.id.mima1);
        mima2= (EditText) findViewById(R.id.mima2);

    }

    private void link_save() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String jiami=Utils.jiami(mima1.getText().toString().trim()).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();
        String zh=zhanghao.getText().toString().trim();
        String mb=shouji.getText().toString().trim();
        String cd=yanzhengma.getText().toString().trim();
       // String pw=mima1.getText().toString().trim();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","101");
            jsonObject.put("account",zh);
            jsonObject.put("mobile",mb);
            jsonObject.put("code",cd);
            jsonObject.put("password",jiami);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", "0")
                .header("sign", Utils.encode("101"+zh+mb+cd+jiami+nonce+timestamp
                        +"0"+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "forgetPwd.app");



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
               // dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                  //  Gson gson=new Gson();
                   // JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                   // DengLuBean zhaoPianBean=gson.fromJson(jsonElement,DengLuBean.class);
                    if (jsonObject.get("dtoResult").getAsInt()==0){
                      //  showMSG("修改成功",4);
                        Thread.sleep(500);
                       link_denglu();

                    }else {
                        showMSG("修改失败",3);
                        dismissDialog();
                    }

                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void link_huoqu() {
        //showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

       // String jiami= Utils.jiami(mima.getText().toString().trim()).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();
        String zh=zhanghao.getText().toString().trim();
        String mb=shouji.getText().toString().trim();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("account",zh);
            jsonObject.put("mobile",mb);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", "0")
                .header("sign", Utils.encode("100"+zh+mb+nonce+timestamp
                        +"0"+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "getCode.app");



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
               // dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                 //   Gson gson=new Gson();
                 //   JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                  //  DengLuBean zhaoPianBean=gson.fromJson(jsonElement,DengLuBean.class);
                    if (!jsonObject.get("dtoResult").getAsString().equals("0")){
                        showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                    }

                }catch (Exception e){

                   // dismissDialog();
                    showMSG("获取验证码失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void link_denglu() {
     //   showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String jiami=Utils.jiami(mima1.getText().toString().trim()).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("account",zhanghao.getText().toString().trim());
            jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", "0")
                .header("sign", Utils.encode("100"+zhanghao.getText().toString().trim()+jiami+nonce+timestamp
                        +"0"+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "login.app");

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
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    DengLuBean zhaoPianBean=gson.fromJson(jsonElement,DengLuBean.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")){
                        showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        zhaoPianBean.setUserId(zhaoPianBean.getId());
                        zhaoPianBean.setId(123456L);
                        zhaoPianBean.setQqTime(dengLuBean.getQqTime()==null?"2017-01-01 11:11:11":dengLuBean.getQqTime());
                        zhaoPianBean.setMima(mima1.getText().toString().trim());
                        zhaoPianBean.setZhuji("http://14.23.169.42:8090/api/");
                        dengLuBeanDao.update(zhaoPianBean);
                        finish();
                        startActivity(new Intent(WangJiMiMaActivity.this,HomePageActivity.class));

                    }

                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        if (call!=null)
            call.cancel();
        super.onDestroy();
    }

}
