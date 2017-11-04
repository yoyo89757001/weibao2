package com.examples.weibao.fargments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DetectionsBean;
import com.examples.weibao.allbeans.DetectionsBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.ItemsBean;
import com.examples.weibao.allbeans.ItemsBeanDao;
import com.examples.weibao.allbeans.MenurefsBean;
import com.examples.weibao.allbeans.MenurefsBeanDao;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.allbeans.PlansBean;
import com.examples.weibao.allbeans.PlansBeanDao;
import com.examples.weibao.beans.FanHuiBean;
import com.examples.weibao.beans.JianChaBean;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.ui.BaoZhangChuLiActivity;
import com.examples.weibao.ui.TaiZhangActivity;
import com.examples.weibao.ui.WeiBaoBaoGaoActivity;
import com.examples.weibao.ui.WeiBaoJiHuaActivity;
import com.examples.weibao.ui.WeiBaoYuCeShiActivity;
import com.examples.weibao.ui.XiangMuKuanJinDuActivity;
import com.examples.weibao.utils.DateUtils;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment implements View.OnClickListener {
    private LinearLayout l1,l2,l3,l4,l5,l6,l7,l8;
    private int dw,dh;
    private LinearLayout top_ll;
    private ImageView touxiang;
    private TextView name,zhiwu1,zhiwu2;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;

   // private LiXianBeans liXianBeans=null;
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

    public static boolean isTrue=true;
    public static boolean isTrue2=true;
    public static int count=0;
    private int maxCount=0;
    private TiJIaoDialog tiJIaoDialog=null;


    public Fragment1() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initDao();

        dw = Utils.getDisplaySize(getActivity()).x;
        dh = Utils.getDisplaySize(getActivity()).y;

        View view=inflater.inflate(R.layout.fragment_fragment1, container, false);

        initView(view);

        name.setText(dengLuBean.getName());

        return view;
    }


    private void initDao() {
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        detectionsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDetectionsBeanDao();
        menusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
        plansBeanDao=MyAppLaction.myAppLaction.getDaoSession().getPlansBeanDao();
        menurefsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenurefsBeanDao();

    }


    private void initView(View view) {

        top_ll= (LinearLayout) view.findViewById(R.id.top_ll);
        top_ll.setOnClickListener(this);
        l1= (LinearLayout) view.findViewById(R.id.l1);
        l1.setOnClickListener(this);
        l2= (LinearLayout) view.findViewById(R.id.l2);
        l2.setOnClickListener(this);
        l3= (LinearLayout) view.findViewById(R.id.l3);
        l3.setOnClickListener(this);
        l4= (LinearLayout) view.findViewById(R.id.l4);
        l4.setOnClickListener(this);
        l5= (LinearLayout) view.findViewById(R.id.l5);
        l5.setOnClickListener(this);
        l6= (LinearLayout) view.findViewById(R.id.l6);
        l6.setOnClickListener(this);
        l7= (LinearLayout) view.findViewById(R.id.l7);
        l7.setOnClickListener(this);
        l8= (LinearLayout) view.findViewById(R.id.l8);
        l8.setOnClickListener(this);
        name= (TextView) view.findViewById(R.id.name);
        zhiwu1= (TextView) view.findViewById(R.id.zhiwu1);
        zhiwu2= (TextView) view.findViewById(R.id.zhiwu2);
        touxiang= (ImageView) view.findViewById(R.id.touxiang);

        LinearLayout.LayoutParams lp_tx = (LinearLayout.LayoutParams) touxiang.getLayoutParams();
        lp_tx.width=dw/6;
        lp_tx.height=dw/6;
        touxiang.setLayoutParams(lp_tx);
        touxiang.invalidate();
        ViewGroup.LayoutParams lp =  top_ll.getLayoutParams();
        //弹窗的高宽
        lp.height=dh/5;
        top_ll.setLayoutParams(lp);
        top_ll.invalidate();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l1:
            startActivity(new Intent(getContext(), WeiBaoYuCeShiActivity.class));

                break;
            case R.id.l2:
                startActivity(new Intent(getContext(), BaoZhangChuLiActivity.class));
                break;
            case R.id.l3:
                startActivity(new Intent(getContext(), XiangMuKuanJinDuActivity.class));
                break;
            case R.id.l4:

                break;
            case R.id.l5:
                startActivity(new Intent(getContext(), WeiBaoJiHuaActivity.class));
                break;
            case R.id.l6:
                startActivity(new Intent(getContext(), WeiBaoBaoGaoActivity.class));
                break;
            case R.id.l7:
                startActivity(new Intent(getContext(), TaiZhangActivity.class));
                break;
            case R.id.l8:
               link_jc();

                break;
            case R.id.top_ll:
               // startActivity(new Intent(getContext(), GeRenActivity.class));
                break;

        }

    }

    private void link_jc() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();


        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();
        String time= DateUtils.getTodayDateTimes();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
          //  jsonObject.put("itemId","0");
            jsonObject.put("stime",dengLuBean.getQqTime());
            jsonObject.put("etime", time);
            Log.d("HomePageActivity", dengLuBean.getQqTime());
            Log.d("HomePageActivity", time);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+dengLuBean.getQqTime()+time+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "checkDownload.app");



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
                // dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                String ss=null;

                //获得返回体
                try {

                    ResponseBody body = response.body();
                    ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    JianChaBean zhaoPianBean=gson.fromJson(jsonObject,JianChaBean.class);
                    if (zhaoPianBean.getTotal()!=0){
                        link_xz();

                    }else {
                        showMSG("暂无数据更新",4);
                        dismissDialog();
                    }


                }catch (Exception e){
                    dismissDialog();
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


    private void link_xz() {
        // showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();


        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();
        final String time=DateUtils.getTodayDateTimes();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
           // jsonObject.put("itemId",integer.toString());
            jsonObject.put("stime",dengLuBean.getQqTime());
            jsonObject.put("etime", time);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+dengLuBean.getQqTime()+time+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "downloadData.app");



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
                   // Log.d("InFoActivity", "ss" + ss.substring(0,2000));
                   // Log.d("InFoActivity", "ss" + ss.substring(2000,ss.length()));

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    JsonObject items= jsonObject.get("item").getAsJsonObject();
                    JsonArray detections= jsonObject.get("detections").getAsJsonArray();
                    JsonArray menus= jsonObject.get("menus").getAsJsonArray();
                    JsonArray devices= jsonObject.get("devices").getAsJsonArray();
                    JsonArray plans= jsonObject.get("plans").getAsJsonArray();
                    JsonArray menurefs= jsonObject.get("menurefs").getAsJsonArray();

                    ItemsBean zhaoPianBean=gson.fromJson(items,ItemsBean.class);
                    int i1=zhaoPianBean.getDtoResult();
                    if (itemsBeanDao.load(zhaoPianBean.getId())==null && (i1==1 || i1==2)){
                        Log.d("HomePageActivity", "插入items");
                        itemsBeanDao.insert(zhaoPianBean);
                    }else if (i1==1 || i1==2){
                        itemsBeanDao.update(zhaoPianBean);
                        Log.d("HomePageActivity", "更新items");
                    }else {
                        itemsBeanDao.delete(zhaoPianBean);
                        Log.d("HomePageActivity", "删除items");
                    }

                    int detectionsSize=detections.size();
                    for (int i=0;i<detectionsSize;i++){
                        DetectionsBean detectionsBean=gson.fromJson(detections.get(i),DetectionsBean.class);
                        int i2=detectionsBean.getDtoResult();
                        if (detectionsBeanDao.load(detectionsBean.getId())==null && (i2==1 || i2==2)){
                            detectionsBeanDao.insert(detectionsBean);
                            Log.d("HomePageActivity", "插入detectionsBean");
                        }else if (i2==1 || i2==2){
                            detectionsBeanDao.update(detectionsBean);
                            Log.d("HomePageActivity", "更新detectionsBean");
                        }else {
                            detectionsBeanDao.delete(detectionsBean);
                            Log.d("HomePageActivity", "删除detectionsBean");
                        }
                    }

                    int menusSize=menus.size();
                    for (int i=0;i<menusSize;i++){
                        MenusBean menusBean=gson.fromJson(menus.get(i),MenusBean.class);
                        int i3=menusBean.getDtoResult();
                        if (menusBeanDao.load(menusBean.getId())==null && (i3==1 || i3==2)){
                            menusBeanDao.insert(menusBean);
                            Log.d("HomePageActivity", "插入menus");
                        }else if (i3==1 || i3==2){
                            menusBeanDao.update(menusBean);
                            Log.d("HomePageActivity", "更新menus");
                        }else {
                            menusBeanDao.delete(menusBean);
                            Log.d("HomePageActivity", "删除menus");
                        }
                    }


                    int devicesSize=devices.size();
                    for (int i=0;i<devicesSize;i++){
                        DevicesBean devicesBean=gson.fromJson(devices.get(i),DevicesBean.class);
                        int i4=devicesBean.getDtoResult();
                        if (devicesBeanDao.load(devicesBean.getId())==null && (i4==1 || i4==2)){
                            devicesBeanDao.insert(devicesBean);
                            Log.d("HomePageActivity", "插入devicesBean");
                        }else if (i4==1 || i4==2){
                            devicesBeanDao.update(devicesBean);
                            Log.d("HomePageActivity", "更新devicesBean");
                        }else {
                            devicesBeanDao.delete(devicesBean);
                            Log.d("HomePageActivity", "删除devicesBean");
                        }
                    }

                    int plansSize=plans.size();
                    for (int i=0;i<plansSize;i++){
                        PlansBean plansBean=gson.fromJson(plans.get(i),PlansBean.class);
                        int i5=plansBean.getDtoResult();
                        if (plansBeanDao.load(plansBean.getId())==null && (i5==1 || i5==2)){
                            plansBeanDao.insert(plansBean);
                            Log.d("HomePageActivity", "插入plansBeanBean");
                        }else if (i5==1 || i5==2){
                            plansBeanDao.update(plansBean);
                            Log.d("HomePageActivity", "更新plansBeanBean");
                        }else {
                            plansBeanDao.delete(plansBean);
                            Log.d("HomePageActivity", "删除plansBeanBean");
                        }
                    }

                    int menurefsSize=menurefs.size();
                    for (int i=0;i<menurefsSize;i++){
                        MenurefsBean menurefsBean=gson.fromJson(menurefs.get(i),MenurefsBean.class);
                        int i6=menurefsBean.getDtoResult();
                        if (menurefsBeanDao.load(menurefsBean.getId())==null && (i6==1 || i6==2)){
                            menurefsBeanDao.insert(menurefsBean);
                            Log.d("HomePageActivity", "插入menurefsBean");
                        }else if (i6==1 || i6==2){
                            menurefsBeanDao.update(menurefsBean);
                            Log.d("HomePageActivity", "更新menurefsBean");
                        }else {
                            menurefsBeanDao.delete(menurefsBean);
                            Log.d("HomePageActivity", "删除menurefsBean");
                        }
                    }

                        //保存时间
                        Log.d("HomePageActivity", "保存时间"+time);
                     //   dengLuBean.setQqTime(time);
                        //   dengLuBeanDao.update(dengLuBean);
                        //  Log.d("HomePageActivity", dengLuBeanDao.load(123456L).getQqTime());


                }catch (Exception e){

                    dismissDialog();
                    showMSG("更新数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void showDialog(){
        if (getActivity()!=null)
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(getActivity());
                    tiJIaoDialog.setCanceledOnTouchOutside(false);
                    tiJIaoDialog.setTextView("更新数据中,请稍后...");
                    if (!getActivity().isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }
    private void dismissDialog(){
        if (getActivity()!=null)
        getActivity().runOnUiThread(new Runnable() {
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
        if (getActivity()!=null)
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(getActivity(),s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

}
