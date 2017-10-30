package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.BuildConfig;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.XiangMuKuanAdapter;
import com.examples.weibao.adapters.XiaoXi2Adapter;
import com.examples.weibao.beans.DengLuBean;
import com.examples.weibao.beans.DengLuBeanDao;
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

public class XiaoXiActivity extends Activity {
    private EditText neirong;
    private Button fasong;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<String> dataList;
    private XiaoXi2Adapter taiZhangAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xiao_xi);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }

        dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);

        TextView title= (TextView) findViewById(R.id.title);
        title.setText("消息");
        ImageView left= (ImageView) findViewById(R.id.leftim);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        neirong= (EditText) findViewById(R.id.neirong);
        neirong.addTextChangedListener(textWatcher);
        fasong= (Button) findViewById(R.id.fasong);
        fasong.setEnabled(false);
        fasong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BuildConfig.DEBUG)
                    Log.d("XiaoXiActivity", "发送");
            }
        });


        dataList=new ArrayList<>();
        dataList.add("ddddddd");
        dataList.add("dfff");

        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        taiZhangAdapter=new XiaoXi2Adapter(dataList,XiaoXiActivity.this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);

        linearLayoutManager=new LinearLayoutManager(XiaoXiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.transparent)
                .build();
        lRecyclerView.addItemDecoration(divider);


        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

               // startActivity(new Intent(XiangMuKuanJinDuActivity.this,XiangMuShouKuanActivity.class));

            }
        });

        link("1");

    }

    private TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (s.toString().equals("")){
                fasong.setEnabled(false);
                fasong.setBackgroundResource(R.drawable.zidonghuoqu112);

            }else {
                fasong.setEnabled(true);
                fasong.setBackgroundResource(R.drawable.zidonghuoqu113);
            }

        }
    };

    private void link(String pageNum) {
      //  showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd",100);
            jsonObject.put("itemId",10044);
            jsonObject.put("pageSize","15");
            jsonObject.put("pageNum",pageNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+"10044"+"15"+pageNum+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "itemMessages.app");

        // step 3：创建 Call 对象
        call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
              //  dismissDialog();
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
                    Gson gson=new Gson();
                    JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
                    DengLuBean zhaoPianBean=gson.fromJson(jsonElement,DengLuBean.class);
                    if (jsonObject.get("dtoResult").getAsString().equals("0")){
                      //  showMSG(jsonObject.get("dtoDesc").getAsString(),4);
                        zhaoPianBean.setUserId(zhaoPianBean.getId());
                        zhaoPianBean.setId(123456L);
                        zhaoPianBean.setZhuji("http://14.23.169.42:8090/api/");
                        dengLuBeanDao.update(zhaoPianBean);



                    }

                }catch (Exception e){

                   // dismissDialog();
                   // showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }
}
