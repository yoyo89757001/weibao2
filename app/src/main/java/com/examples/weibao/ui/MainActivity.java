package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
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

public class MainActivity extends Activity implements View.OnClickListener {
    private TextView title,wangji;
    private ImageView fanhui;
    private EditText zhanghao,mima;
    private Button login;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        if (dengLuBean!=null && dengLuBean.getAccount()!=null && !dengLuBean.getAccount().equals("")){
            setContentView(R.layout.activity_kai_ping);
            link_save(dengLuBean.getAccount(),dengLuBean.getMima());
        }else {
            setContentView(R.layout.activity_main);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                SystemBarTintManager tintManager = new SystemBarTintManager(this);
                tintManager.setStatusBarTintEnabled(true);
                tintManager.setStatusBarTintResource(R.color.lanse33);
            }

            fanhui= (ImageView) findViewById(R.id.leftim);
            fanhui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    finish();
                }
            });
            title= (TextView) findViewById(R.id.title);
            title.setText("用户登录");

            wangji= (TextView) findViewById(R.id.wangji);
            wangji.setOnClickListener(this);
            zhanghao= (EditText) findViewById(R.id.zhanghao);
            mima= (EditText) findViewById(R.id.mima);
            login= (Button) findViewById(R.id.login);
            login.setOnClickListener(this);
        }



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                if (!zhanghao.getText().toString().trim().equals("") && !mima.getText().toString().trim().equals("")){
                    link_save(zhanghao.getText().toString().trim(),mima.getText().toString().trim());
                }else {
                    showMSG("请先填写完整信息!",4);
                }

                break;
            case R.id.wangji:
                startActivity(new Intent(MainActivity.this,WangJiMiMaActivity.class));
                break;

        }

    }

    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(MainActivity.this);
                    if (!MainActivity.this.isFinishing())
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

                Toast tastyToast= TastyToast.makeText(MainActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }


    private void link_save(final String zhanghao, final String mima) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String jiami=Utils.jiami(mima).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("account",zhanghao);
            jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", "0")
                .header("sign", Utils.encode("100"+zhanghao+jiami+nonce+timestamp
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
                finish();
                startActivity(new Intent(MainActivity.this,HomePageActivity.class));
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
                        zhaoPianBean.setMima(mima);
                        zhaoPianBean.setQqTime(dengLuBean.getQqTime()==null?"2017-01-01 11:11:11":dengLuBean.getQqTime());
                        zhaoPianBean.setZhuji("http://14.23.169.42:8090/api/");
                        dengLuBeanDao.update(zhaoPianBean);
                        finish();
                        startActivity(new Intent(MainActivity.this,HomePageActivity.class));

                    }else {
                        if (dengLuBean!=null && dengLuBean.getAccount()!=null && !dengLuBean.getAccount().equals("")){
                            finish();
                            startActivity(new Intent(MainActivity.this,HomePageActivity.class));
                        }
                    }

                }catch (Exception e){
                    finish();
                    startActivity(new Intent(MainActivity.this,HomePageActivity.class));
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
