package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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
import java.text.DecimalFormat;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class XiangMuShouKuanActivity extends Activity {
    private TextView benci,yiwancheng,yuji,xiangmuming;
    private EditText jine;
    private TiJIaoDialog tiJIaoDialog=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private int itemId=0;
    private String xiangmuMing="";
    private double yishou=0;
    private double zong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        itemId=getIntent().getIntExtra("itemId",0);
        xiangmuMing=getIntent().getStringExtra("xmm");
        zong=getIntent().getDoubleExtra("zong",0);
        yishou=getIntent().getDoubleExtra("yiwancheng",0);
        setContentView(R.layout.activity_xiang_mu_shou_kuan);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            //  tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.lanse33);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
            // tintManager.setNavigationBarTintResource(R.color.dark_grey);
        }
        ImageView left= (ImageView) findViewById(R.id.leftim);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        benci= (TextView) findViewById(R.id.bencishoukuan);
        yuji= (TextView) findViewById(R.id.yujishoukuang);
        yiwancheng= (TextView) findViewById(R.id.yiwancheng);
        jine= (EditText) findViewById(R.id.jine);
        jine.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().equals("")){
                    benci.setText("￥ "+s.toString());
                }else {
                    benci.setText("￥ 0.0");
                }


            }
        });
        xiangmuming= (TextView) findViewById(R.id.xiangmuming);
        Button baocun= (Button) findViewById(R.id.baocun);
        baocun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!jine.getText().toString().trim().equals("")){
                    try {
                        Double.parseDouble(jine.getText().toString().trim());
                        link_save();
                    }catch (NumberFormatException e){
                        showMSG("填写金额有误!",4);
                    }

                }else {
                    showMSG("你没有填写金额",4);
                }

            }
        });
        yuji.setText("￥ "+zong);
        yiwancheng.setText("￥ "+yishou);
        xiangmuming.setText(xiangmuMing);
    }

    /**
     * 将每三个数字加上逗号处理（通常使用金额方面的编辑）
     *
     * @param str 需要处理的字符串
     * @return 处理完之后的字符串
     */
    public static String addComma(String str) {
        DecimalFormat decimalFormat = new DecimalFormat(",###");
        return decimalFormat.format(Double.parseDouble(str));
    }


    private void link_save() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        final String jin=jine.getText().toString().trim();
        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
             jsonObject.put("itemId",itemId);
             jsonObject.put("curReceive",jin);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+itemId+jin+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "addItemAmount.app");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

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
                  //  Gson gson=new Gson();
                    int jsonElement= jsonObject.get("dtoResult").getAsInt();
                    if (jsonElement==0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                yiwancheng.setText(yishou+Double.parseDouble(jin)+"");
                                jine.setText("");
                            }
                        });
                        showMSG("保存成功!",4);
                    }else {
                        showMSG("保存失败!",4);
                    }

                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(XiangMuShouKuanActivity.this);
                    if (!XiangMuShouKuanActivity.this.isFinishing())
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

                Toast tastyToast= TastyToast.makeText(XiangMuShouKuanActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }
}
