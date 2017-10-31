package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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

import com.examples.weibao.BuildConfig;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.XiaoXi2Adapter;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.beans.FanHuiBean;
import com.examples.weibao.beans.ItemIdBean;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.examples.weibao.xiaoxibeans.XiaoXiBean;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
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
    private int idid;
    private int dangQianYe = 1;
    private int qingQiuYe = 1;
    private List<XiaoXiBean.ObjectsBean> objectsBeans=new ArrayList<>();


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
        idid=getIntent().getIntExtra("idid",0);
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
                if (!neirong.getText().toString().trim().equals("")){
                    link_fasong(URLEncoder.encode(neirong.getText().toString().trim()),idid);
                    fasong.setEnabled(false);
                }

            }
        });


        dataList=new ArrayList<>();
        dataList.add("ddddddd");
        dataList.add("dfff");

        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        taiZhangAdapter=new XiaoXi2Adapter(objectsBeans,XiaoXiActivity.this,dengLuBean.getAccount());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);

        linearLayoutManager=new LinearLayoutManager(XiaoXiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        //设置头部加载颜色
        lRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.blake, android.R.color.white);
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lRecyclerView.setFooterViewColor(R.color.textcolor, R.color.blake, android.R.color.white);

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


        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                Log.d("Fragment2", "下拉刷新");
                qingQiuYe++;
                link_huoqu(qingQiuYe+"",idid,qingQiuYe );

            }
        });

        link_huoqu("1",idid,qingQiuYe );

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

    private void link_huoqu(String pageNum, int id, final int qingQiuYe) {
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
            jsonObject.put("itemId",id);
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
                .header("sign", Utils.encode("100"+id+"15"+pageNum+nonce+timestamp
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
                if (!XiaoXiActivity.this.isFinishing()){
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (objectsBeans.size() != 0) {
                                objectsBeans.clear();
                            }
                            // lRecyclerView.refreshComplete(0);// REQUEST_COUNT为每页加载数量
                            taiZhangAdapter.notifyDataSetChanged();

                            showMSG("请求数据失败",3);
                        }
                    });
                }
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
                    final XiaoXiBean zhaoPianBean=gson.fromJson(jsonObject,XiaoXiBean.class);
                    if (qingQiuYe == dangQianYe) {

                        if (!XiaoXiActivity.this.isFinishing()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (objectsBeans.size() != 0) {
                                        objectsBeans.clear();
                                    }
                                    List<XiaoXiBean.ObjectsBean> beanList=zhaoPianBean.getObjects() != null ? zhaoPianBean.getObjects() : new ArrayList<XiaoXiBean.ObjectsBean>();
                                    Collections.reverse(beanList); // 倒序排列
                                    objectsBeans.addAll(beanList);
                                    lRecyclerView.refreshComplete(objectsBeans.size());// REQUEST_COUNT为每页加载数量
                                    taiZhangAdapter.notifyDataSetChanged();
                                    //   Log.d("Fragment3", "d进来得到");
                                    MoveToPosition(linearLayoutManager,beanList.size()-1);
                                }
                            });
                        }

                    } else {

                        if (!XiaoXiActivity.this.isFinishing()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int size = zhaoPianBean.getObjects().size();
                                    for (int i = 0; i < size; i++) {
                                        objectsBeans.add(0,zhaoPianBean.getObjects().get(i));

                                    }
                                    lRecyclerView.refreshComplete(15);// REQUEST_COUNT为每页加载数量
                                    taiZhangAdapter.notifyDataSetChanged();
                                    MoveToPosition(linearLayoutManager,size);
                                }
                            });
                        }

                    }

                }catch (Exception e){

                    if (!XiaoXiActivity.this.isFinishing()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (objectsBeans.size() != 0) {
                                    objectsBeans.clear();
                                }
                                // lRecyclerView.refreshComplete(0);// REQUEST_COUNT为每页加载数量
                                taiZhangAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager   设置RecyclerView对应的manager
     * @param n  要跳转的位置
     */


        public  void MoveToPosition(LinearLayoutManager manager, int n) {
            manager.scrollToPositionWithOffset(n, 0);
           // manager.setStackFromEnd(false);
        }



    private void link_fasong(String content,int id) {
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
            jsonObject.put("itemId",id);
            jsonObject.put("content",content);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+id+content+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "addItemMessage.app");

        // step 3：创建 Call 对象
        call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
                //  dismissDialog();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fasong.setEnabled(true);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                // dismissDialog();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        fasong.setEnabled(true);
                    }
                });

                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                String ss=null;
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                neirong.setText("");
                            }
                        });
                        qingQiuYe=1;
                        link_huoqu("1",idid,qingQiuYe );
                    }

                }catch (Exception e){

                    try {
                        JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                        Gson gson=new Gson();
                        FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                        if (zhaoPianBean.getDtoResult()==-33){
                            showMSG("账号登陆过期,或在其它机器登录，请重新登录",3);
                        }
                    }catch (Exception e1){
                        e1.printStackTrace();
                        showMSG("获取数据失败",3);
                    }
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void showMSG(final String s,final int i){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(XiaoXiActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
