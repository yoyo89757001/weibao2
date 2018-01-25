package com.examples.weibao.fargments;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.os.ResultReceiver;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.DownloadService.DownloadService;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.BaoZhangDengJiBean;
import com.examples.weibao.allbeans.BaoZhangDengJiBeanDao;
import com.examples.weibao.allbeans.BenDiMenusBean;
import com.examples.weibao.allbeans.BenDiMenusBeanDao;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DetectionsBean;
import com.examples.weibao.allbeans.DetectionsBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.FaultsBean;
import com.examples.weibao.allbeans.FaultsBeanDao;
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
import com.examples.weibao.cookies.CookiesManager;
import com.examples.weibao.dialogs.QueRenDialog;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.ui.BaoZhangChuLiActivity;
import com.examples.weibao.ui.TaiZhangActivity;
import com.examples.weibao.ui.WeiBaoBaoGaoActivity;
import com.examples.weibao.ui.WeiBaoJiHuaActivity;
import com.examples.weibao.ui.WeiBaoYuCeShiActivity;
import com.examples.weibao.ui.XiangMuKuanJinDuActivity;
import com.examples.weibao.utils.DateUtils;
import com.examples.weibao.utils.FileUtil;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
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
    private BaoZhangDengJiBeanDao baoZhangDengJiBeanDao=null;
    private List<BaoZhangDengJiBean> baoZhangDengJiBeanList=null;
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
    private FaultsBeanDao faultsBeanDao=null;
    public static boolean isTrue=true;
    public static boolean isTrue2=true;
    public static int count=0;
    private int maxCount=0;
    private TiJIaoDialog tiJIaoDialog=null;
    private BenDiMenusBeanDao benDiMenusBeanDao=null;
    private List<BenDiMenusBean> benDiMenusBeanList=null;
    private List<BenDiMenusBean> benDiMenusBeanList_ls=new ArrayList<>();
    private List<String> stringList=new ArrayList<>();

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
        faultsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
        benDiMenusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getBenDiMenusBeanDao();
        baoZhangDengJiBeanDao=MyAppLaction.myAppLaction.getDaoSession().getBaoZhangDengJiBeanDao();


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
                final QueRenDialog dialog=new QueRenDialog(getActivity());
                dialog.setCountText("提交前请确认一台设备的所有维保项都已维护？点击确定将立即提交!");
                dialog.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                //本地维保与测试的
                                benDiMenusBeanList=benDiMenusBeanDao.loadAll();
                                if (benDiMenusBeanList!=null){
                                    if (benDiMenusBeanList_ls.size()>0){
                                        benDiMenusBeanList_ls.clear();
                                    }
                                    int s=benDiMenusBeanList.size();
                                    for (int i=0;i<s;i++){
                                        if (!benDiMenusBeanList.get(i).getIsTijiao()){
                                            //没有提交的
                                            benDiMenusBeanList_ls.add(benDiMenusBeanList.get(i));
                                        }else {
                                            //提交过的
                                            benDiMenusBeanDao.delete(benDiMenusBeanList.get(i));
                                        }

                                    }
                                    int si=benDiMenusBeanList_ls.size();
                                    if (si>0){
                                        JSONObject jsonObject = new JSONObject();
                                        JSONArray jsonArray=new JSONArray();
                                        try {
                                        for (int i=0;i<si;i++){
                                           // Log.d("Fragment1", "benDiMenusBeanList_ls.get(i).getPlanId():" + benDiMenusBeanList_ls.get(i).getPlanId());
                                                JSONObject tijiao = new JSONObject();
                                                //  tijiao.put("status",0);
                                                tijiao.put("id",0);
                                                tijiao.put("planId",benDiMenusBeanList_ls.get(i).getPlanId());
                                                tijiao.put("menuLevel1Id",benDiMenusBeanList_ls.get(i).getMenuLevel1Id());
                                                tijiao.put("menuId",benDiMenusBeanList_ls.get(i).getMenuId2());
                                                tijiao.put("menuLevel3Id",benDiMenusBeanList_ls.get(i).getMenuLevel3Id());
                                                tijiao.put("menuLevel4Id",benDiMenusBeanList_ls.get(i).getMenuLevel4Id());
                                                tijiao.put("deviceId",benDiMenusBeanList_ls.get(i).getDeviceId());
                                                tijiao.put("remark",benDiMenusBeanList_ls.get(i).getRemark());
                                                tijiao.put("testData",benDiMenusBeanList_ls.get(i).getName());
                                                tijiao.put("createBy",dengLuBean.getUserId());
                                                tijiao.put("createTime",System.currentTimeMillis());
                                                jsonArray.put(tijiao);

                                                //    Log.d("ZhuangTaiXuanZeDialog", benDiMenusBean.getRemark());
                                                // jsonObject.put("password",jiami);

                                        }


                                        jsonObject.put("cmd","100");
                                        jsonObject.put("records",jsonArray);
                                        } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                        link_shangchuan(jsonObject);
                                    }

                                }
                                //本地报障的
                                if (stringList.size()>0){
                                    stringList.clear();
                                }
                                baoZhangDengJiBeanList=baoZhangDengJiBeanDao.loadAll();
                               int sss= baoZhangDengJiBeanList.size();
                                for (int i=0;i<sss;i++){
                                    if (!baoZhangDengJiBeanList.get(i).getIsTijiao()){
                                        //没有提交过的
                                        String ss[] =baoZhangDengJiBeanList.get(i).getFaultImage().split(";");
                                       // int s=ss.length;
                                        for (String s1 : ss) {
                                            stringList.add(0, s1);
                                        }
                                        link_P1(stringList,baoZhangDengJiBeanList.get(i));
                                    }
                                }

                                //提交本地回复和审核等
                               List<FaultsBean> fff= faultsBeanDao.loadAll();
                                for (int f=0;f<fff.size();f++){
                                    switch (dengLuBean.getStatus()){
                                        case 0:
                                            //工程师
                                            if (!fff.get(f).getIsHuifu()){
                                                link_huifu(fff.get(f));
                                            }
                                            if (!fff.get(f).getIsChuli()){
                                                link_tijiaochuli(fff.get(f));
                                            }

                                            break;
                                        case 1:
                                            //主管
                                            if (!fff.get(f).getIsShenhehuifu()){
                                                link_huifu_shenhe(fff.get(f));
                                            }
                                            if (!fff.get(f).getIsShenhechuli()){
                                                link_chuli_shenhe(fff.get(f));
                                            }


                                            break;
                                        case 2:
                                            //甲方
                                            if (!fff.get(f).getIsQueren()){
                                                link_chuli_shenhe(fff.get(f));
                                            }

                                            break;
                                    }

                                }

                            }
                        }).start();
                        dialog.dismiss();
                    }
                });
                dialog.setOnQuXiaoListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();



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
            //Log.d("HomePageActivity","开始"+ dengLuBean.getQqTime());
           // Log.d("HomePageActivity","结束"+ time);
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
                        link_xz(zhaoPianBean.getCtime());

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


    private void link_xz(final String ctime) {
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
            jsonObject.put("etime", ctime);
            Log.d("HomePageActivity", "下载开始时间"+dengLuBean.getQqTime());
            Log.d("HomePageActivity", "下载结束时间"+ctime);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+dengLuBean.getQqTime()+ctime+nonce+timestamp
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
               // dismissDialog();
                ItemsBean zhaoPianBean=null;
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                String ss=null;
                //获得返回体
                try {

                    ResponseBody body = response.body();
                     ss=body.string().trim();
                    int i9 = 0;
                    while (true) {
                        if (i9 + 4000 >= ss.length()) {
                            Log.d("HomePageActivity", ss.substring(i9, ss.length()));
                            break;
                        } else {
                            Log.d("HomePageActivity", ss.substring(i9, i9 += 4000));
                        }

                    }

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    JsonArray items= jsonObject.get("items").getAsJsonArray();
                    JsonArray detections= jsonObject.get("detections").getAsJsonArray();
                    JsonArray menus= jsonObject.get("menus").getAsJsonArray();
                    JsonArray devices= jsonObject.get("devices").getAsJsonArray();
                    JsonArray plans= jsonObject.get("plans").getAsJsonArray();
                    JsonArray menurefs= jsonObject.get("menurefs").getAsJsonArray();
                    JsonArray faults= jsonObject.get("faults").getAsJsonArray();

                    //保存时间
                    Log.d("HomePageActivity", "保存时间"+time);
                    dengLuBean.setQqTime(ctime);
                    dengLuBeanDao.update(dengLuBean);

                    int itemSize=items.size();
                    for (int i=0;i<itemSize;i++){
                        zhaoPianBean=gson.fromJson(items.get(i),ItemsBean.class);
                        int i1=zhaoPianBean.getDtoResult();
                        if (itemsBeanDao.load(zhaoPianBean.getId())==null && (i1==1 || i1==2)){
                            // Log.d("HomePageActivity", "插入items");
                            itemsBeanDao.insert(zhaoPianBean);
                        }else if (i1==1 || i1==2){
                            itemsBeanDao.update(zhaoPianBean);
                            // Log.d("HomePageActivity", "更新items");
                        }else {
                            itemsBeanDao.delete(zhaoPianBean);
                            //  Log.d("HomePageActivity", "删除items");
                        }
                    }


                    int detectionsSize=detections.size();
                    for (int i=0;i<detectionsSize;i++){
                        DetectionsBean detectionsBean=gson.fromJson(detections.get(i),DetectionsBean.class);
                        int i2=detectionsBean.getDtoResult();
                        if (detectionsBeanDao.load(detectionsBean.getId())==null && (i2==1 || i2==2)){
                            detectionsBeanDao.insert(detectionsBean);
                            //  Log.d("HomePageActivity", "插入detectionsBean");
                        }else if (i2==1 || i2==2){
                            detectionsBeanDao.update(detectionsBean);
                            //  Log.d("HomePageActivity", "更新detectionsBean");
                        }else {
                            detectionsBeanDao.delete(detectionsBean);
                            //  Log.d("HomePageActivity", "删除detectionsBean");
                        }
                    }



                    int menusSize=menus.size();
                    for (int i=0;i<menusSize;i++){
                        MenusBean menusBean=gson.fromJson(menus.get(i),MenusBean.class);
                        int i3=menusBean.getDtoResult();
                        if (menusBeanDao.load(menusBean.getId())==null && (i3==1 || i3==2)){
                            menusBeanDao.insert(menusBean);
                            // Log.d("HomePageActivity", "插入menus");
                        }else if (i3==1 || i3==2){
                            menusBeanDao.update(menusBean);
                            // Log.d("HomePageActivity", "更新menus");
                        }else {
                            menusBeanDao.delete(menusBean);
                            //  Log.d("HomePageActivity", "删除menus");
                        }
                    }


                    int devicesSize=devices.size();
                    for (int i=0;i<devicesSize;i++){
                        DevicesBean devicesBean=gson.fromJson(devices.get(i),DevicesBean.class);
                        int i4=devicesBean.getDtoResult();
                        if (devicesBeanDao.load(devicesBean.getId())==null && (i4==1 || i4==2)){
                            devicesBeanDao.insert(devicesBean);
                            //  Log.d("HomePageActivity", "插入devicesBean");
                        }else if (i4==1 || i4==2){
                            devicesBeanDao.update(devicesBean);
                            // Log.d("HomePageActivity", "更新devicesBean");
                        }else {
                            devicesBeanDao.delete(devicesBean);
                            // Log.d("HomePageActivity", "删除devicesBean");
                        }
                    }

                    int plansSize=plans.size();
                    for (int i=0;i<plansSize;i++){
                        PlansBean plansBean=gson.fromJson(plans.get(i),PlansBean.class);
                        int i5=plansBean.getDtoResult();
                        if (plansBeanDao.load(plansBean.getId())==null && (i5==1 || i5==2)){
                            plansBeanDao.insert(plansBean);
                            // Log.d("HomePageActivity", "插入plansBeanBean");
                        }else if (i5==1 || i5==2){
                            plansBeanDao.update(plansBean);
                            //   Log.d("HomePageActivity", "更新plansBeanBean");
                        }else {
                            plansBeanDao.delete(plansBean);
                            //  Log.d("HomePageActivity", "删除plansBeanBean");
                        }
                    }

                    int menurefsSize=menurefs.size();
                    for (int i=0;i<menurefsSize;i++){
                        MenurefsBean menurefsBean=gson.fromJson(menurefs.get(i),MenurefsBean.class);
                        int i6=menurefsBean.getDtoResult();
                        if (menurefsBeanDao.load(menurefsBean.getId())==null && (i6==1 || i6==2)){
                            menurefsBeanDao.insert(menurefsBean);
                            //  Log.d("HomePageActivity", "插入menurefsBean");
                        }else if (i6==1 || i6==2){
                            menurefsBeanDao.update(menurefsBean);
                            //  Log.d("HomePageActivity", "更新menurefsBean");
                        }else {
                            menurefsBeanDao.delete(menurefsBean);
                            //  Log.d("HomePageActivity", "删除menurefsBean");
                        }
                    }

                    int faultsSize=faults.size();
                    for (int i=0;i<faultsSize;i++){
                        FaultsBean faultsBean=gson.fromJson(faults.get(i),FaultsBean.class);
                      //  Log.d("Fragment1", "faultsBean.getFaultTime():" + faultsBean.getFaultTime());
                        int i7=faultsBean.getDtoResult();
                        if (faultsBeanDao.load(faultsBean.getId())==null && (i7==1 || i7==2)){
                            faultsBeanDao.insert(faultsBean);
                            //  Log.d("HomePageActivity", "插入menurefsBean");
                        }else if (i7==1 || i7==2){
                            faultsBeanDao.update(faultsBean);
                            //  Log.d("HomePageActivity", "更新menurefsBean");
                        }else {
                            faultsBeanDao.delete(faultsBean);
                            //  Log.d("HomePageActivity", "删除menurefsBean");
                        }
                    }

                    //  Log.d("HomePageActivity", dengLuBeanDao.load(123456L).getQqTime());

                    final List<FaultsBean> faultsBeanList = faultsBeanDao.loadAll();

                    if (faultsBeanList != null) {
                        final int  fsize = faultsBeanList.size();

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                for (int ii = 0; ii < fsize; ii++) {
                                    if (!faultsBeanList.get(ii).getIsXiazai()) {
                                        //下载图片
                                        if (faultsBeanList.get(ii).getFaultImage()!=null){

                                            String ss[] = faultsBeanList.get(ii).getFaultImage().split(";");
                                            //   String ss[] = "dsfds.jpg;fdgg.jpg;hghghg.jpg;".split(";");
                                            int ds=ss.length;

                                            for (int d=0;d<ds;d++){
                                                String fn=ss[d];
                                                FileUtil.isExists(FileUtil.PATH,fn);

                                                Intent intent33 = new Intent(getActivity(), DownloadService.class);
                                                intent33.putExtra("url", "http://14.23.169.42:8090/faultImages/"+fn);
                                                intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
                                                intent33.putExtra("faultsId",faultsBeanList.get(ii).getId());
                                                intent33.putExtra("urlName",FileUtil.SDPATH+ File.separator+FileUtil.PATH+File.separator+fn);
                                                getActivity().startService(intent33);
                                            }


                                        }


                                    }

                                }


                            }
                        });
                    }

                    dismissDialog();
                    showMSG("更新成功",4);
                }catch (Exception e){

                    try {
                        JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                        Gson gson=new Gson();
                        FanHuiBean ddd=gson.fromJson(jsonObject,FanHuiBean.class);
                        if (ddd.getDtoResult()==-33){
                            showMSG("账号登陆过期,请重新登陆",3);
                        }
                    }catch (Exception ee){
                        dismissDialog();
                        showMSG("更新数据失败",3);
                        Log.d("WebsocketPushMsg", e.getMessage());
                    }
                }
            }
        });

    }


    private class DownloadReceiver extends ResultReceiver {

        @SuppressLint("RestrictedApi")
        private DownloadReceiver(Handler handler) {
            super(handler);
        }

        @SuppressLint("RestrictedApi")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == DownloadService.UPDATE_PROGRESS) {
                //  String  ididid=resultData.getString("ididid2");
                long faultsId=resultData.getLong("faultsId");
                int progress = resultData.getInt("progress");
                if (progress==100){
                    Log.d("DownloadReceiver", "下载完成");
                    FaultsBean f=faultsBeanDao.load(faultsId);
                    f.setIsXiazai(true);
                    faultsBeanDao.update(f);

                }

            }
        }
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

    //维保与测试
    private void link_shangchuan(JSONObject jsonObject) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

        // String jiami=Utils.jiami(mima).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
//        JSONObject jsonObject = new JSONObject();
//        JSONArray jsonArray=null;
//        try {
//            JSONObject tijiao = new JSONObject();
//            //  tijiao.put("status",0);
//            tijiao.put("id",0);
//            tijiao.put("planId",benDiMenusBean.getPlanId());
//            tijiao.put("menuLevel1Id",benDiMenusBean.getMenuLevel1Id());
//            tijiao.put("menuId",benDiMenusBean.getMenuId2());
//            tijiao.put("menuLevel3Id",benDiMenusBean.getMenuLevel3Id());
//            tijiao.put("menuLevel4Id",benDiMenusBean.getMenuLevel4Id());
//            tijiao.put("deviceId",benDiMenusBean.getDeviceId());
//            tijiao.put("remark",benDiMenusBean.getRemark());
//            tijiao.put("testData",benDiMenusBean.getName());
//            tijiao.put("createBy",dengLuBean.getUserId());
//            tijiao.put("createTime",System.currentTimeMillis());
//
//            jsonArray=new JSONArray();
//            jsonArray.put(tijiao);
//
//            jsonObject.put("cmd","100");
//            jsonObject.put("records",jsonArray);
//            //    Log.d("ZhuangTaiXuanZeDialog", benDiMenusBean.getRemark());
//            // jsonObject.put("password",jiami);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());
       // Log.d("Fragment1", jsonObject.toString());
        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "uploadWeibaoRecords.app");

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
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){

                        int s=benDiMenusBeanList_ls.size();
                        for (int i=0;i<s;i++){
                            benDiMenusBeanList_ls.get(i).setIsTijiao(true);
                            benDiMenusBeanDao.update(benDiMenusBeanList_ls.get(i));
                        }
//                        BenDiMenusBean gg= benDiMenusBeanDao.queryBuilder().where(BenDiMenusBeanDao.Properties.MensuId.eq(menusBeanList.get(p).getParentId()),
//                                BenDiMenusBeanDao.Properties.ParentId.eq(shebeiId)).unique();
//                        gg.setIsTijiao(true);
//                        benDiMenusBeanDao.update(gg);
                        showMSG("保存成功",4);
                    }else if (zhaoPianBean.getDtoResult()==-33){
                        showMSG("账号登陆失效,请重新登陆",4);
                    }else {
                        showMSG(zhaoPianBean.getDtoDesc(),4);
                    }

                }catch (Exception e){
                    //  finish();
                    //  startActivity(new Intent(MainActivity.this,HomePageActivity.class));
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    public static final int TIMEOUT = 1000 * 180;
    private void link_P1(List<String> stringList, final BaoZhangDengJiBean dengJiBean) {
        showDialog();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

        //final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        //http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();
        MultipartBody mBody;
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        int ss=stringList.size()-1;
        for (int i=0;i<ss;i++){
            File file1 = new File(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator+stringList.get(i));
            RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream") , file1);
            builder. addFormDataPart("file" , stringList.get(i), fileBody1);
            //  Log.d("BaoZhangDengJiActivity", stringList.get(i).substring(stringList.get(i).lastIndexOf("/")+1,stringList.get(i).length())+">>>>");
        }
        JSONObject tijiao = null;
        // JSONArray jsonArray=null;
        try {
            tijiao = new JSONObject();
            tijiao.put("status",0);
            tijiao.put("id",0);
            tijiao.put("accountId",dengLuBean.getUserId());
            tijiao.put("address",dengJiBean.getAddress());
            tijiao.put("companyId",dengLuBean.getCompanyId());
            tijiao.put("deviceId",dengJiBean.getDeviceId());
            tijiao.put("planId",dengJiBean.getPlanId());
            tijiao.put("deviceNumber",dengJiBean.getDeviceNumber());
            tijiao.put("faultTime",System.currentTimeMillis());
            tijiao.put("remark",dengJiBean.getRemark());
            tijiao.put("contactTel",dengJiBean.getContactTel());
            tijiao.put("faultImage",dengJiBean.getFaultImage());

            // jsonArray=new JSONArray();
            // jsonArray.put(tijiao);

            // jsonObject.put("cmd","100");
            // jsonObject.put("faults",jsonArray);
            //  Log.d("BaoZhangDengJiActivity", jsonObject.toString());
            //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        builder.addFormDataPart("cmd","100");
        builder.addFormDataPart("fault",tijiao.toString());
        mBody=builder.build();
        //   Log.d("BaoZhangDengJiActivity", tijiao.toString());

//         /* 第一个要上传的file */
//       File file1 = new File(filename1);
//        RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream") , file1);
//        final String file1Name = System.currentTimeMillis()+"testFile1.jpg";
//
//
//        MultipartBody mBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//            /* 底下是上传了两个文件 */
//                .addFormDataPart("file" , file1Name , fileBody1)
//                  /* 上传一个普通的String参数 */
//                //  .addFormDataPart("subject_id" , subject_id+"")
//                //  .addFormDataPart("image_2" , file2Name , fileBody2)
//                .build();
        Request.Builder requestBuilder = new Request.Builder()
                // .header("Content-Type", "application/json")
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(mBody)
                .url(dengLuBean.getZhuji() + "uploadFault.app");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                dismissDialog();
                showMSG("上传图片出错",4);
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string();


                    //  link_save(dengJiBean);
                    Log.d("AllConnects", "aa   "+ss);

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){
                        //更新状态
                        dengJiBean.setIsTijiao(true);
                        baoZhangDengJiBeanDao.update(dengJiBean);
                        showMSG("提交成功",4);

                    }else if (zhaoPianBean.getDtoResult()==-33){
                        showMSG("登录过期,请重新登录",4);
                    }

                }catch (Exception e){
                    dismissDialog();
                    showMSG("上传图片出错",4);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void link_huifu(final FaultsBean faultsBean) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();
        MultipartBody mBody;
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        //  String jiami=Utils.jiami(mima).toUpperCase();
        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject tijiao =null;
        try {
            tijiao = new JSONObject();
            tijiao.put("status",1);
            tijiao.put("id",faultsBean.getId());
            tijiao.put("replyBy",dengLuBean.getUserId());
            tijiao.put("replyContent",faultsBean.getReplyContent());
            tijiao.put("replyUsername",dengLuBean.getName());
            tijiao.put("replyTime",System.currentTimeMillis());
            tijiao.put("planCheckTime",faultsBean.getPlanCheckTime());

            Log.d("BaoZhangDengJiActivity", tijiao.toString());
            //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        builder.addFormDataPart("cmd","101");
        builder.addFormDataPart("fault",tijiao.toString());
        mBody=builder.build();


        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("101"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(mBody)
                .url(dengLuBean.getZhuji() + "uploadFault.app");

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
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){
                       // faultsBean.setStatus(1);
                        faultsBean.setIsHuifu(true);
                        faultsBeanDao.update(faultsBean);
                      //  showMSG("保存成功",4);
                    }else if (zhaoPianBean.getDtoResult()==-33){
                        showMSG("账号登陆失效,请重新登陆",4);
                    }else {
                        showMSG(zhaoPianBean.getDtoDesc(),4);
                    }
                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void link_huifu_shenhe(final FaultsBean faultsBean) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();
        MultipartBody mBody;
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        //  String jiami=Utils.jiami(mima).toUpperCase();
        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray=new JSONArray();
        JSONObject tijiao=null;
        try {
            tijiao = new JSONObject();
            tijiao.put("status",faultsBean.getStatus());
            tijiao.put("id",faultsBean.getId());

            jsonArray.put(tijiao);
            jsonObject.put("cmd","102");
            jsonObject.put("faults",jsonArray);

            //  Log.d("BaoZhangDengJiActivity", tijiao.toString());
            //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Log.d("BaoZhangChaKanActivity", jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("102"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "auditFault.app");

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
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){

                        faultsBean.setIsShenhehuifu(true);
                        faultsBeanDao.update(faultsBean);

                      //  showMSG("审核成功",4);
                    }else if (zhaoPianBean.getDtoResult()==-33){
                        showMSG("账号登陆失效,请重新登陆",4);
                    }else {
                        showMSG(zhaoPianBean.getDtoDesc(),4);
                    }
                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }

    private void link_chuli_shenhe(final FaultsBean faultsBean) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();
        MultipartBody mBody;
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        //  String jiami=Utils.jiami(mima).toUpperCase();
        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray=new JSONArray();
        JSONObject tijiao=null;
        try {
            tijiao = new JSONObject();
            tijiao.put("status",faultsBean.getStatus());
            tijiao.put("id",faultsBean.getId());

            jsonArray.put(tijiao);
            jsonObject.put("cmd","102");
            jsonObject.put("faults",jsonArray);

            //  Log.d("BaoZhangDengJiActivity", tijiao.toString());
            //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Log.d("BaoZhangChaKanActivity", jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("102"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "auditFault.app");

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
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){
                        if (faultsBean.getStatus()==5 ||faultsBean.getStatus()==6){
                           // faultsBean.setStatus(ooo);
                            faultsBean.setIsShenhechuli(true);
                            faultsBeanDao.update(faultsBean);
                        }else {
                           // faultsBean.setStatus(ooo);
                            faultsBean.setIsQueren(true);
                            faultsBeanDao.update(faultsBean);
                        }

                      //  showMSG("审核成功",4);

                    }else if (zhaoPianBean.getDtoResult()==-33){
                        showMSG("账号登陆失效,请重新登陆",4);
                    }else {
                        showMSG(zhaoPianBean.getDtoDesc(),4);
                    }
                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void link_tijiaochuli(final  FaultsBean faultsBean) {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();
        MultipartBody mBody;
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        //  String jiami=Utils.jiami(mima).toUpperCase();
        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject tijiao =null;
        try {
            tijiao = new JSONObject();
            tijiao.put("status",4);
            tijiao.put("id",faultsBean.getId());
            tijiao.put("processBy",dengLuBean.getUserId());
            tijiao.put("processContent",faultsBean.getProcessContent());
            tijiao.put("processUsername",dengLuBean.getName());
            tijiao.put("processTime",System.currentTimeMillis());
            // tijiao.put("planCheckTime",DateUtils.getTimes(paichashijian.getText().toString().trim()));

            //  Log.d("BaoZhangDengJiActivity", tijiao.toString());
            //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        builder.addFormDataPart("cmd","102");
        builder.addFormDataPart("fault",tijiao.toString());
        mBody=builder.build();


        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("102"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(mBody)
                .url(dengLuBean.getZhuji() + "uploadFault.app");

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
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){

                        faultsBean.setIsChuli(true);
                        faultsBeanDao.update(faultsBean);
                     //   showMSG("保存成功",4);
                    }else if (zhaoPianBean.getDtoResult()==-33){
                        showMSG("账号登陆失效,请重新登陆",4);
                    }else {
                        showMSG(zhaoPianBean.getDtoDesc(),4);
                    }
                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


}
