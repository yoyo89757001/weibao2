package com.examples.weibao.ui;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.os.ResultReceiver;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.examples.weibao.DownloadService.DownloadService;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DetectionsBean;
import com.examples.weibao.allbeans.DetectionsBeanDao;
import com.examples.weibao.allbeans.DeviceRefBean;
import com.examples.weibao.allbeans.DeviceRefBeanDao;
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
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.fargments.Fragment1;
import com.examples.weibao.fargments.Fragment2;
import com.examples.weibao.fargments.Fragment3;
import com.examples.weibao.fargments.Fragment4;
import com.examples.weibao.utils.DateUtils;
import com.examples.weibao.utils.FileUtil;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.examples.weibao.views.ViewPagerFragmentAdapter;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout r1,r2,r3,r4;
    private LinearLayout zhong_ll;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ImageView tabIm,tabIm2,tabIm3,tabIm4;
    private TextView tabText,tabText2,tabText3,tabText4;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
   // private int maxCount=0;
    //定义一个过滤器；
    private IntentFilter intentFilter;
    //定义一个广播监听器；
    private NetChangReceiver netChangReceiver;

   // private LiXianBeans liXianBeans=null;
    private ItemsBeanDao itemsBeanDao=null;
    private DeviceRefBeanDao deviceRefBeanDao=null;
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
    private ImageView fff;


    Handler mHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExit = false;
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_home_page);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }
        fff= (ImageView) findViewById(R.id.fff);

        initDao();

        //实例化过滤器；
        intentFilter = new IntentFilter();
        //添加过滤的Action值；
        intentFilter.addAction("guanbiyemian");
        //实例化广播监听器；
        netChangReceiver = new NetChangReceiver();
        //将广播监听器和过滤器注册在一起；
        registerReceiver(netChangReceiver, intentFilter);

        initFragmetList();

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);

        initView();
        initViewPager();


        AndPermission.with(HomePageActivity.this)
                .requestCode(300)
                .permission(Permission.STORAGE,Permission.CAMERA)
                .callback(listener)
                .start();



    }


    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 300) {
                link_jc();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 200) {
                showMSG("拍照授权失败",3);

            }
        }
    };

    private void initDao() {
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
        deviceRefBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDeviceRefBeanDao();
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        detectionsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDetectionsBeanDao();
        menusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
        plansBeanDao=MyAppLaction.myAppLaction.getDaoSession().getPlansBeanDao();
        menurefsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenurefsBeanDao();
        faultsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
    }


    private  class NetChangReceiver extends BroadcastReceiver {

        //重写onReceive方法，该方法的实体为，接收到广播后的执行代码；
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals("guanbiyemian")){
                finish();
            }
        }
    }


    private void link_jc() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();


        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();
        String time=DateUtils.getTodayDateTimes();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
          //  jsonObject.put("itemId","0");
            jsonObject.put("stime",dengLuBean.getQqTime());
            jsonObject.put("etime", time);
         //   Log.d("HomePageActivity", dengLuBean.getQqTime());
         //   Log.d("HomePageActivity", time);
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
                String ss=null;

                //获得返回体
                try {

                    ResponseBody body = response.body();
                     ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    JianChaBean zhaoPianBean=gson.fromJson(jsonObject,JianChaBean.class);
                    if (zhaoPianBean.getTotal()>0){
                        //有更新
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
                ItemsBean zhaoPianBean=null;
                String ss=null;
                //获得返回体
                try {


                    ResponseBody body = response.body();
                     ss = body.string().trim();
                    int i9 = 0;
                    while (true) {
                        if (i9 + 4000 >= ss.length()) {
                            Log.d("HomePageActivity", ss.substring(i9, ss.length()));
                            break;
                        } else {
                            Log.d("HomePageActivity", ss.substring(i9, i9 += 4000));
                        }

                    }


                    JsonObject jsonObject = GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson = new Gson();
                    JsonArray items = jsonObject.get("items").getAsJsonArray();
                    JsonArray detections = jsonObject.get("detections").getAsJsonArray();
                    JsonArray menus = jsonObject.get("menus").getAsJsonArray();
                    JsonArray devices = jsonObject.get("devices").getAsJsonArray();
                    JsonArray plans = jsonObject.get("plans").getAsJsonArray();
                    JsonArray menurefs = jsonObject.get("menurefs").getAsJsonArray();
                    JsonArray faults = jsonObject.get("faults").getAsJsonArray();
                    JsonArray deviceRef = jsonObject.get("menuDeviceRef").getAsJsonArray();

                    //保存时间
                    Log.d("HomePageActivity", "保存时间" + time);
                    dengLuBean.setQqTime(ctime);
                    dengLuBeanDao.update(dengLuBean);


                    int itemSize = items.size();
                    for (int i = 0; i < itemSize; i++) {
                        zhaoPianBean = gson.fromJson(items.get(i), ItemsBean.class);
                        int i1 = zhaoPianBean.getDtoResult();
                        if (itemsBeanDao.load(zhaoPianBean.getId()) == null && (i1 == 1 || i1 == 2)) {
                            // Log.d("HomePageActivity", "插入items");
                            itemsBeanDao.insert(zhaoPianBean);
                        } else if (i1 == 1 || i1 == 2) {
                            itemsBeanDao.update(zhaoPianBean);
                            // Log.d("HomePageActivity", "更新items");
                        } else {
                            itemsBeanDao.delete(zhaoPianBean);
                            //  Log.d("HomePageActivity", "删除items");
                        }
                    }


                    int detectionsSize = detections.size();
                    for (int i = 0; i < detectionsSize; i++) {
                        DetectionsBean detectionsBean = gson.fromJson(detections.get(i), DetectionsBean.class);
                        int i2 = detectionsBean.getDtoResult();
                        if (detectionsBeanDao.load(detectionsBean.getId()) == null && (i2 == 1 || i2 == 2)) {
                            detectionsBeanDao.insert(detectionsBean);
                            //  Log.d("HomePageActivity", "插入detectionsBean");
                        } else if (i2 == 1 || i2 == 2) {
                            detectionsBeanDao.update(detectionsBean);
                            //  Log.d("HomePageActivity", "更新detectionsBean");
                        } else {
                            detectionsBeanDao.delete(detectionsBean);
                            //  Log.d("HomePageActivity", "删除detectionsBean");
                        }
                    }


                    int menusSize = menus.size();
                    for (int i = 0; i < menusSize; i++) {
                        MenusBean menusBean = gson.fromJson(menus.get(i), MenusBean.class);
                        int i3 = menusBean.getDtoResult();
                        if (menusBeanDao.load(menusBean.getId()) == null && (i3 == 1 || i3 == 2)) {
                            menusBeanDao.insert(menusBean);
                            // Log.d("HomePageActivity", "插入menus");
                        } else if (i3 == 1 || i3 == 2) {
                            menusBeanDao.update(menusBean);
                            // Log.d("HomePageActivity", "更新menus");
                        } else {
                            menusBeanDao.delete(menusBean);
                            //  Log.d("HomePageActivity", "删除menus");
                        }
                    }


                    int devicesSize = devices.size();
                    for (int i = 0; i < devicesSize; i++) {
                        DevicesBean devicesBean = gson.fromJson(devices.get(i), DevicesBean.class);
                        int i4 = devicesBean.getDtoResult();
                        if (devicesBeanDao.load(devicesBean.getId()) == null && (i4 == 1 || i4 == 2)) {
                            devicesBeanDao.insert(devicesBean);
                            //  Log.d("HomePageActivity", "插入devicesBean");
                        } else if (i4 == 1 || i4 == 2) {
                            devicesBeanDao.update(devicesBean);
                            // Log.d("HomePageActivity", "更新devicesBean");
                        } else {
                            devicesBeanDao.delete(devicesBean);
                            // Log.d("HomePageActivity", "删除devicesBean");
                        }
                    }

                    int plansSize = plans.size();
                    for (int i = 0; i < plansSize; i++) {
                        PlansBean plansBean = gson.fromJson(plans.get(i), PlansBean.class);
                        int i5 = plansBean.getDtoResult();
                        if (plansBeanDao.load(plansBean.getId()) == null && (i5 == 1 || i5 == 2)) {
                            plansBeanDao.insert(plansBean);
                            // Log.d("HomePageActivity", "插入plansBeanBean");
                        } else if (i5 == 1 || i5 == 2) {
                            plansBeanDao.update(plansBean);
                            //   Log.d("HomePageActivity", "更新plansBeanBean");
                        } else {
                            plansBeanDao.delete(plansBean);
                            //  Log.d("HomePageActivity", "删除plansBeanBean");
                        }
                    }

                    int menurefsSize = menurefs.size();
                    for (int i = 0; i < menurefsSize; i++) {
                       // Log.d("HomePageActivity", menurefs.get(i).toString());
                        MenurefsBean menurefsBean = gson.fromJson(menurefs.get(i), MenurefsBean.class);
                        int i6 = menurefsBean.getDtoResult();
                        if (menurefsBeanDao.load(menurefsBean.getId()) == null && (i6 == 1 || i6 == 2)) {
                            menurefsBeanDao.insert(menurefsBean);
                            //  Log.d("HomePageActivity", "插入menurefsBean");
                        } else if (i6 == 1 || i6 == 2) {
                            menurefsBeanDao.update(menurefsBean);
                            //  Log.d("HomePageActivity", "更新menurefsBean");
                        } else {
                            menurefsBeanDao.delete(menurefsBean);
                            //  Log.d("HomePageActivity", "删除menurefsBean");
                        }
                    }

                    final int faultsSize = faults.size();
                    for (int i = 0; i < faultsSize; i++) {
                        FaultsBean faultsBean = gson.fromJson(faults.get(i), FaultsBean.class);
                        int i7 = faultsBean.getDtoResult();
                        if (faultsBeanDao.load(faultsBean.getId()) == null && (i7 == 1 || i7 == 2)) {
                            faultsBean.setIsXiazai(false);
                            faultsBeanDao.insert(faultsBean);
                            //  Log.d("HomePageActivity", "插入menurefsBean");
                        } else if (i7 == 1 || i7 == 2) {
                            faultsBean.setIsXiazai(false);
                            faultsBeanDao.update(faultsBean);
                            //  Log.d("HomePageActivity", "更新menurefsBean");
                        } else {
                            faultsBeanDao.delete(faultsBean);
                            //  Log.d("HomePageActivity", "删除menurefsBean");
                        }
                    }

                    int deviceRefSize = deviceRef.size();
                    for (int i = 0; i < deviceRefSize; i++) {
                        DeviceRefBean deviceRefBean = gson.fromJson(deviceRef.get(i), DeviceRefBean.class);
                        int i1 = deviceRefBean.getDtoResult();
                        if (deviceRefBeanDao.load(deviceRefBean.getId()) == null && (i1 == 1 || i1 == 2)) {
                            // Log.d("HomePageActivity", "插入items");
                            deviceRefBeanDao.insert(deviceRefBean);
                        } else if (i1 == 1 || i1 == 2) {
                            deviceRefBeanDao.update(deviceRefBean);
                            // Log.d("HomePageActivity", "更新items");
                        } else {
                            deviceRefBeanDao.delete(deviceRefBean);
                            //  Log.d("HomePageActivity", "删除items");
                        }
                    }


                    //  Log.d("HomePageActivity", dengLuBeanDao.load(123456L).getQqTime());

                    final List<FaultsBean> faultsBeanList = faultsBeanDao.loadAll();

                    if (faultsBeanList != null) {
                       final int  fsize = faultsBeanList.size();
                       // Log.d("HomePageActivity", "faults:" + fsize);
                        runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            for (int ii = 0; ii < fsize; ii++) {
                                if (!faultsBeanList.get(ii).getIsXiazai()) {
                                    //下载图片
                                    if (faultsBeanList.get(ii).getFaultImage()!=null){

                                       String ss[] = faultsBeanList.get(ii).getFaultImage().split(";");
                                     //   String ss[] = "dsfds.jpg;fdgg.jpg;hghghg.jpg;".split(";");
                                       int ds=ss.length;

                                        for (String fn : ss) {
                                            FileUtil.isExists(FileUtil.PATH, fn);

                                            Intent intent33 = new Intent(HomePageActivity.this, DownloadService.class);
                                            intent33.putExtra("url", "http://14.23.169.42:8090/faultImages/" + fn);
                                            intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
                                            intent33.putExtra("faultsId", faultsBeanList.get(ii).getId());
                                            intent33.putExtra("urlName", FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator + fn);
                                            startService(intent33);
                                        }


                                    }


                                }

                            }


                        }
                    });
                }
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
                  FaultsBean f=faultsBeanDao.load(faultsId);
                  f.setIsXiazai(true);
                  faultsBeanDao.update(f);
                  Log.d("DownloadReceiver", "下载成功");
              }

            }
        }
    }

    /***
     *保存bitmap对象到文件中
     * @param bm
     * @param path
     * @param quality
     * @return
     */
    public  void saveBitmap2File(Bitmap bm, final String path, int quality) {
        if (null == bm || bm.isRecycled()) {
            Log.d("InFoActivity", "回收|空");
            return ;
        }
        try {
            File file = new File(path);
            if (file.exists()) {
                file.delete();
            }
            BufferedOutputStream bos = new BufferedOutputStream(
                    new FileOutputStream(file));
            bm.compress(Bitmap.CompressFormat.JPEG, quality, bos);
            bos.flush();
            bos.close();


        } catch (Exception e) {
            e.printStackTrace();

        }
        //finally {

//            if (!bm.isRecycled()) {
//                bm.recycle();
//            }
//            bm = null;
   //     }
    }

    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(HomePageActivity.this);
                    tiJIaoDialog.setCanceledOnTouchOutside(false);
                    tiJIaoDialog.setTextView("更新数据中,请稍后...");
                    if (!HomePageActivity.this.isFinishing())
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

                Toast tastyToast= TastyToast.makeText(HomePageActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

    @Override
    protected void onDestroy() {
        if (call!=null)
            call.cancel();
        super.onDestroy();
        unregisterReceiver(netChangReceiver);
    }

    private void initViewPager() {

        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
        mViewPager.setOffscreenPageLimit(4);
        updateBottomLinearLayoutSelect(0);
    }

    private void initFragmetList() {
        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new Fragment2();
        Fragment fragment3 = new Fragment3();
        Fragment fragment4 = new Fragment4();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.viewpage);
        r1= (RelativeLayout) findViewById(R.id.homeLayout);
        r1.setOnClickListener(this);
        r2= (RelativeLayout) findViewById(R.id.chosenLayout);
        r2.setOnClickListener(this);
        r3= (RelativeLayout) findViewById(R.id.localLayout);
        r3.setOnClickListener(this);
        r4= (RelativeLayout) findViewById(R.id.settingLayout);
        r4.setOnClickListener(this);
        zhong_ll= (LinearLayout) findViewById(R.id.searchLayout);
        zhong_ll.setOnClickListener(this);
        tabIm= (ImageView) findViewById(R.id.tabImg);
        tabIm2= (ImageView) findViewById(R.id.tabImg2);
        tabIm3= (ImageView) findViewById(R.id.tabImg3);
        tabIm4= (ImageView) findViewById(R.id.tabImg4);
        tabText= (TextView) findViewById(R.id.tabText);
        tabText2= (TextView) findViewById(R.id.tabText2);
        tabText3= (TextView) findViewById(R.id.tabText3);
        tabText4= (TextView) findViewById(R.id.tabText4);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result != null) {
            if(result.getContents() != null) {
                Log.d("HomePageActivity", result.getContents());
              //  Toast.makeText(this, "Scanned: " + result.getContents(), Toast.LENGTH_LONG).show();
                startActivity(new Intent(HomePageActivity.this,SaoYiSaoTanChuActivity.class).putExtra("scanned",result.getContents()));
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLayout:
                mViewPager.setCurrentItem(0);
                updateBottomLinearLayoutSelect(0);
                break;
            case R.id.chosenLayout:
                mViewPager.setCurrentItem(1);
                updateBottomLinearLayoutSelect(1);
                break;
            case R.id.localLayout:
                mViewPager.setCurrentItem(2);
                updateBottomLinearLayoutSelect(2);
                break;
            case R.id.settingLayout:
                mViewPager.setCurrentItem(3);
                updateBottomLinearLayoutSelect(3);
                break;

            case R.id.searchLayout:
                //扫描
                IntentIntegrator integrator = new IntentIntegrator(this);
               // integrator.setDesiredBarcodeFormats(IntentIntegrator.ONE_D_CODE_TYPES);
               // integrator.setPrompt("对准二维码自动扫描");
               // integrator.setCameraId(0);  // Use a specific camera of the device
                integrator.setBeepEnabled(true);
                integrator.setCaptureActivity(ErWeiMaActivity.class);
              //  integrator.setBarcodeImageEnabled(true);
                integrator.initiateScan();
               // new IntentIntegrator(this).initiateScan();
                //  startActivity(new Intent(HomePageActivity.this,SaoMaTiaoZhuangActivity.class));

                break;
            default:
                break;
        }
    }



    private void  updateBottomLinearLayoutSelect(int position) {
        tabText.setTextColor(Color.parseColor("#8c050505"));
        tabText2.setTextColor(Color.parseColor("#8c050505"));
        tabText3.setTextColor(Color.parseColor("#8c050505"));
        tabText4.setTextColor(Color.parseColor("#8c050505"));

        switch (position){
            case 0:
                tabIm.setImageResource(R.drawable.nav_mt_hover);
                tabIm2.setImageResource(R.drawable.nav_msg);
                tabIm3.setImageResource(R.drawable.nav_news);
                tabIm4.setImageResource(R.drawable.nav_me);
                tabText.setTextColor(Color.parseColor("#FF1c97fe"));
                break;
            case 1:
                tabText2.setTextColor(Color.parseColor("#FF1c97fe"));
                tabIm.setImageResource(R.drawable.nav_mt);
                tabIm2.setImageResource(R.drawable.nav_msg_hover);
                tabIm3.setImageResource(R.drawable.nav_news);
                tabIm4.setImageResource(R.drawable.nav_me);

                break;
            case 2:
                tabText3.setTextColor(Color.parseColor("#FF1c97fe"));
                tabIm.setImageResource(R.drawable.nav_mt);
                tabIm2.setImageResource(R.drawable.nav_msg);
                tabIm3.setImageResource(R.drawable.nav_news_hover);
                tabIm4.setImageResource(R.drawable.nav_me);
                break;
            case 3:
                tabText4.setTextColor(Color.parseColor("#FF1c97fe"));
                tabIm.setImageResource(R.drawable.nav_mt);
                tabIm2.setImageResource(R.drawable.nav_msg);
                tabIm3.setImageResource(R.drawable.nav_news);
                tabIm4.setImageResource(R.drawable.nav_me_hover);
                break;
        }

    }
   private class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.d(TAG,"onPageScrooled");
        }
        @Override
        public void onPageSelected(int position) {

            updateBottomLinearLayoutSelect(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("home","onPageScrollStateChanged");
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    private void exit() {
        if (!isExit) {
            isExit = true;
            TastyToast.makeText(getApplicationContext(), "再按一次退出程序",TastyToast.LENGTH_SHORT,TastyToast.INFO).show();
            // 利用handler延迟发送更改状态信息
            mHandler.sendEmptyMessageDelayed(0, 2000);
        } else {

            finish();
            System.exit(0);
        }
    }
}
