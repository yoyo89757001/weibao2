package com.examples.weibao.fargments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DetectionsBeanDao;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.FaultsBeanDao;
import com.examples.weibao.allbeans.ItemsBeanDao;
import com.examples.weibao.allbeans.MenurefsBeanDao;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.allbeans.PlansBeanDao;
import com.examples.weibao.dialogs.QueRenDialog;
import com.examples.weibao.ui.MainActivity;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment4 extends Fragment {
    private DengLuBeanDao baoCunBeanDao=null;
    private DengLuBean baoCunBean=null;
    private TextView danwei,name,xingbie,shouji,guhua;
    private ItemsBeanDao itemsBeanDao=null;
    // private List<DevicesBean> devicesBeanList=null;
    private DevicesBeanDao devicesBeanDao=null;
    // private List<DetectionsBean> detectionsBeanList=null;
    private DetectionsBeanDao detectionsBeanDao=null;
    // private List<MenusBean> menusBeanList=null;
    private MenusBeanDao menusBeanDao=null;
    // private List<PlansBean> plansBeanList=null;
    private PlansBeanDao plansBeanDao=null;
    private MenurefsBeanDao menurefsBeanDao=null;
    private FaultsBeanDao faultsBeanDao=null;


    public Fragment4() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view=inflater.inflate(R.layout.fragment_fragment4, container, false);
       // TextView textView= (TextView) view.findViewById(R.id.zhanghao);
        baoCunBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        if (baoCunBeanDao!=null){
            baoCunBean=baoCunBeanDao.load(123456L);
        }
            itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
            devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
            detectionsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDetectionsBeanDao();
            menusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
            plansBeanDao=MyAppLaction.myAppLaction.getDaoSession().getPlansBeanDao();
            menurefsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenurefsBeanDao();
            faultsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
        RelativeLayout r3= (RelativeLayout) view.findViewById(R.id.r3);
        r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final QueRenDialog dialog2=new QueRenDialog(getActivity());
                dialog2.setVisibility_BT();
                dialog2.setCountText("你确定要退出?");
                dialog2.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        baoCunBeanDao.deleteByKey(123456L);
                        getActivity().sendBroadcast(new Intent("guanbiyemian"));
                        dialog2.dismiss();
                        baoCunBean.setQqTime(null);
                        baoCunBean.setAccount("");
                        baoCunBeanDao.update(baoCunBean);
                        itemsBeanDao.deleteAll();
                        faultsBeanDao.deleteAll();
                        detectionsBeanDao.deleteAll();
                        devicesBeanDao.deleteAll();
                        plansBeanDao.deleteAll();
                        menurefsBeanDao.deleteAll();
                        menusBeanDao.deleteAll();

                        link();
                        startActivity(new Intent(getActivity(),MainActivity.class));
                    }
                });
                dialog2.setOnQuXiaoListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                dialog2.show();
            }
        });
         danwei= (TextView) view.findViewById(R.id.danwei);
        name= (TextView) view.findViewById(R.id.name);
        xingbie= (TextView) view.findViewById(R.id.xingbie);
        shouji= (TextView) view.findViewById(R.id.shoujihao);
        guhua= (TextView) view.findViewById(R.id.guhua);

        name.setText(baoCunBean.getName());
        shouji.setText(baoCunBean.getPhone());
        danwei.setText(baoCunBean.getCompany());

        return view;
    }


    private void link() {

        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();


        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();


//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd",100);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", baoCunBean.getUserId()+"")
                .header("sign", Utils.encode("100"+nonce+timestamp
                        +baoCunBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(baoCunBean.getZhuji() + "loginOut.app");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

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


                }catch (Exception e){


                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

}
