package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.ShouKuanBean.ShouKuansBean;
import com.examples.weibao.adapters.XiangMuKuanAdapter;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class XiangMuKuanJinDuActivity extends Activity {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<ShouKuansBean.ObjectsBean> dataList=new ArrayList<>();
    private XiangMuKuanAdapter taiZhangAdapter;

    private TiJIaoDialog tiJIaoDialog=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);

        setContentView(R.layout.activity_xiang_mu_kuan_jin_du);
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


        TextView title= (TextView) findViewById(R.id.title);
        title.setText("项目款进度");
        ImageView left= (ImageView) findViewById(R.id.leftim);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        taiZhangAdapter=new XiangMuKuanAdapter(dataList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);

        linearLayoutManager=new LinearLayoutManager(XiangMuKuanJinDuActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerView.setLoadMoreEnabled(false);

        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.transparent)
                .build();
        lRecyclerView.addItemDecoration(divider);


        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                startActivity(new Intent(XiangMuKuanJinDuActivity.this,XiangMuShouKuanActivity.class)
                         .putExtra("itemId",dataList.get(position).getId())
                         .putExtra("xmm",dataList.get(position).getName())
                         .putExtra("zong",dataList.get(position).getItemAmount())
                         .putExtra("yiwancheng",dataList.get(position).getItemAmountReceived()));

            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();
        link_save();
    }

    private void link_save() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

       // String jiami= Utils.jiami(mima).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
           // jsonObject.put("account",zhanghao);
           // jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
//        Log.d("XiangMuKuanJinDuActivit", "dengLuBean.getUserId():" + dengLuBean.getUserId());
//        Log.d("XiangMuKuanJinDuActivit", nonce);
//        Log.d("XiangMuKuanJinDuActivit", timestamp);
//        Log.d("XiangMuKuanJinDuActivit", Utils.encode("100" + nonce + timestamp
//                + dengLuBean.getUserId() + Utils.signaturePassword));
        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "itemAmounts.app");

        // step 3：创建 Call 对象
      Call  call = okHttpClient.newCall(requestBuilder.build());

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
                    ShouKuansBean zhaoPianBean=gson.fromJson(jsonObject,ShouKuansBean.class);
                    if (zhaoPianBean.getObjects().size()>0){
                        if (dataList.size()>0){
                            dataList.clear();
                        }
                        dataList.addAll(zhaoPianBean.getObjects());
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                taiZhangAdapter.notifyDataSetChanged();
                            }
                        });
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
                    tiJIaoDialog=new TiJIaoDialog(XiangMuKuanJinDuActivity.this);
                    if (!XiangMuKuanJinDuActivity.this.isFinishing())
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

                Toast tastyToast= TastyToast.makeText(XiangMuKuanJinDuActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }
}
