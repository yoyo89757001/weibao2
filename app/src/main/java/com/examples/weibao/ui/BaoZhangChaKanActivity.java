package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.BaoZhangDengJiBean;
import com.examples.weibao.allbeans.BaoZhangDengJiBeanDao;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.FaultsBean;
import com.examples.weibao.allbeans.FaultsBeanDao;
import com.examples.weibao.allbeans.ItemsBean;
import com.examples.weibao.allbeans.ItemsBeanDao;
import com.examples.weibao.beans.FanHuiBean;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.utils.DateUtils;
import com.examples.weibao.utils.FileUtil;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.jude.rollviewpager.HintView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class BaoZhangChaKanActivity extends Activity {
    private int shebeiID=0;
    private long baozhangID=0;
    private DengLuBeanDao dengLuBeanDao=null;
    private DengLuBean dengLuBean=null;
    private FaultsBeanDao faultsBeanDao=null;
    private FaultsBean faultsBean=null;
    private DevicesBeanDao devicesBeanDao=null;
    private DevicesBean devicesBean=null;
    private TextView shebei,baozhangtoubu_tv,bianhao,gongsi,weizhi,guzhangshijian,baozhangdianhua,
            lianxidianhua,shengyushijian,paichashijian,huifuren,chuliren;
    private EditText huifuneirong,chulineirong;
    private ImageView tupian;
    private RelativeLayout paicha_rl;
    private View view;
    private Button bt1,bt2;
    private TiJIaoDialog tiJIaoDialog=null;
    private RollPagerView rollPagerView=null;
    private  List<String> photoPathList=new ArrayList<>();
    private int status=-2;
    private ItemsBeanDao itemsBeanDao=null;
    private ItemsBean itemsBean=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status=getIntent().getIntExtra("status",-2);
        shebeiID=getIntent().getIntExtra("shebeiID",0);
        baozhangID=getIntent().getLongExtra("baozhangID",0);
        faultsBeanDao= MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
        devicesBean=devicesBeanDao.load((long) shebeiID);
        faultsBean=faultsBeanDao.load(baozhangID);
        if (devicesBean!=null)
        itemsBean=itemsBeanDao.load((long) devicesBean.getItemId());

        setContentView(R.layout.activity_bao_zhang_cha_kan);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }
        ImageView l= (ImageView) findViewById(R.id.leftim);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        initTuPian();


    }

    private void initTuPian() {
        if (faultsBean!=null && faultsBean.getFaultImage()!=null){
            String s[] =faultsBean.getFaultImage().split(";");
            if (photoPathList.size()>0){
                photoPathList.clear();
            }
            for (String value : s) {
                photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator + value);
            }
//            photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator +"fdgg.jpg");
//            photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator +"fdgg.jpg");
//            photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator +"fdgg.jpg");
        }

        rollPagerView.setAdapter(new TestLoopAdapter(rollPagerView));


    }

    private class TestLoopAdapter extends LoopPagerAdapter {

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            try {

                view.setImageDrawable(getImageDrawable(photoPathList.get(position)));
            } catch (IOException e) {
                Log.d("test", e.getMessage());
            }

            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount() {
            return photoPathList.size();
        }
    }
    /**
     * 将文件生成位图
     * @param path
     * @return
     * @throws IOException
     */
    public BitmapDrawable getImageDrawable(String path)
            throws IOException
    {
        //打开文件
        File file = new File(path);
        if(!file.exists())
        {
            return null;
        }

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bt = new byte[1024];

        //得到文件的输入流
        InputStream in = new FileInputStream(file);

        //将文件读出到输出流中
        int readLength = in.read(bt);
        while (readLength != -1) {
            outStream.write(bt, 0, readLength);
            readLength = in.read(bt);
        }

        //转换成byte 后 再格式化成位图
        byte[] data = outStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// 生成位图
        BitmapDrawable bd = new BitmapDrawable(getResources(),bitmap);

        return bd;
    }

    private void initView() {
        rollPagerView= (RollPagerView) findViewById(R.id.lunbo);
        view=findViewById(R.id.bt_view);
        bt1= (Button) findViewById(R.id.bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dengLuBean.getStatus()){
                    case 0:


                        break;
                    case 1:
                        //主管
                        link_huifu_shenhe(2);

                        break;
                    case 2:
                        //甲方


                        break;
                }

            }
        });
        bt2= (Button) findViewById(R.id.bt2);
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (dengLuBean.getStatus()){
                    case 0:
                        if (!huifuneirong.getText().toString().trim().equals("")){
                            if (!paichashijian.getText().toString().trim().equals("暂无")){
                                link_save();
                            }else {
                                showMSG("请选择上门排查时间",4);
                            }

                        }else {
                            showMSG("请填写回复内容",4);
                        }

                        break;

                    case 1:
                        //主管
                        link_huifu_shenhe(3);

                        break;
                    case 2:
                        //甲方


                        break;

                }

            }
        });
        shebei= (TextView) findViewById(R.id.shebei_tv);
        baozhangtoubu_tv= (TextView) findViewById(R.id.baozhang_tv);
        bianhao= (TextView) findViewById(R.id.bianhao);
        gongsi= (TextView) findViewById(R.id.gongsi);
        weizhi= (TextView) findViewById(R.id.weizhi);
        guzhangshijian= (TextView) findViewById(R.id.shijian_tv);
        baozhangdianhua= (TextView) findViewById(R.id.dianhua1);
        lianxidianhua= (TextView) findViewById(R.id.dianhua2);
        shengyushijian= (TextView) findViewById(R.id.shenyushijian);
        paichashijian= (TextView) findViewById(R.id.paichashijian);
        huifuren= (TextView) findViewById(R.id.renyuan);
        chuliren= (TextView) findViewById(R.id.chuliren);
        paicha_rl= (RelativeLayout) findViewById(R.id.paicha_rl);
        paicha_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(BaoZhangChaKanActivity.this, DatePickActivity.class);
                startActivityForResult(intent,2);
            }
        });
        huifuneirong= (EditText) findViewById(R.id.neirong_et);
        chulineirong= (EditText) findViewById(R.id.chulineirong);
        if (itemsBean!=null){
            gongsi.setText("项目:"+itemsBean.getName());
        }

        if (faultsBean!=null){
            bianhao.setText("编号:"+faultsBean.getDeviceNumber());
            weizhi.setText("位置:"+faultsBean.getAddress());
            guzhangshijian.setText("故障发现时间: "+ DateUtils.time(faultsBean.getFaultTime()+""));
            lianxidianhua.setText("联系电话:"+faultsBean.getContactTel());

            baozhangtoubu_tv.setText(faultsBean.getRemark());
            if (faultsBean.getPlanCheckTime()==0){
                paichashijian.setText("暂无");
              //  Log.d("BaoZhangChaKanActivity", DateUtils.getTimeDifference(faultsBean.getFaultTime() + "", System.currentTimeMillis() + ""));
                shengyushijian.setText("剩余时间:"+DateUtils.getTimeDifference(System.currentTimeMillis()+"",faultsBean.getFaultTime()+""));
            }else {
                paichashijian.setText(DateUtils.time(faultsBean.getPlanCheckTime()+""));
                shengyushijian.setText("剩余时间:"+DateUtils.getTimeDifference2(System.currentTimeMillis()+"",faultsBean.getPlanCheckTime()+""));
            }

            if (faultsBean.getReplyContent()!=null)
            huifuneirong.setText(faultsBean.getReplyContent());
            if (faultsBean.getProcessContent()!=null)
            chulineirong.setText(faultsBean.getProcessContent());
            if (faultsBean.getReplyUsername()!=null && !faultsBean.getReplyUsername().equals("")){
                huifuren.setText(faultsBean.getReplyUsername());
            }else {
                huifuren.setText("暂无");
            }
            if (faultsBean.getProcessUsername()!=null && !faultsBean.getProcessUsername().equals("")){
                chuliren.setText(faultsBean.getProcessUsername());
            }else {
                chuliren.setText("暂无");
            }

        }

        if (devicesBean != null) {
            shebei.setText(devicesBean.getName());
        }

        //是主管跟甲方的时候 屏蔽所有能输入的框，上门排查时间点击

        if (dengLuBean!=null){
            switch (dengLuBean.getStatus()){
                case 0:
                    //工程师
                    bt1.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                    switch (status){
                        case 0:
                            //待回复
                            chulineirong.setEnabled(false);
                            chulineirong.setHint("");


                            break;
                        case 1:
                            //回复待审核
                            huifuneirong.setEnabled(false);
                            paicha_rl.setEnabled(false);
                            chulineirong.setEnabled(false);
                            chulineirong.setHint("");
                            bt2.setVisibility(View.GONE);
                            //  link_huifu_shenhe();

                            break;
                        case 2:
                            //回复审核通过 通过后就是待处理了

                            huifuneirong.setEnabled(false);
                            paicha_rl.setEnabled(false);


                            break;
                        case 3:
                            //回复审核不通过

                            chulineirong.setEnabled(false);
                            chulineirong.setHint("");

                            break;
                        case 4:
                            //处理待审核
                            huifuneirong.setEnabled(false);
                            paicha_rl.setEnabled(false);
                            chulineirong.setEnabled(false);
                            chulineirong.setHint("");
                            bt2.setVisibility(View.GONE);

                            break;
                        case 5:
                            //处理审核通过
                            huifuneirong.setEnabled(false);
                            paicha_rl.setEnabled(false);
                            chulineirong.setEnabled(false);
                            chulineirong.setHint("");
                            bt2.setVisibility(View.GONE);

                            break;
                        case 6:
                            //处理审核不通过  //继续处理
                            huifuneirong.setEnabled(false);
                            paicha_rl.setEnabled(false);

                            break;
                        case 7:
                            //7已完成处理
                            huifuneirong.setEnabled(false);
                            paicha_rl.setEnabled(false);
                            chulineirong.setEnabled(false);
                            chulineirong.setHint("");
                            bt2.setVisibility(View.GONE);

                            break;

                    }

                    break;
                case 1:
                    //主管
                    bt1.setText("审核通过");
                    bt2.setText("审核不通过");
                    chulineirong.setEnabled(false);
                    huifuneirong.setEnabled(false);
                    paicha_rl.setEnabled(false);
                    switch (status){
                        case 0:
                            //待回复
                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);


                            break;
                        case 1:
                            //回复待审核

                            //  link_huifu_shenhe();

                            break;
                        case 2:
                            //回复审核通过

                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);


                            break;
                        case 3:
                            //回复审核不通过


                            break;
                        case 4:
                            //处理待审核


                            break;
                        case 5:
                            //处理审核通过
                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);

                            break;
                        case 6:
                            //处理审核不通过


                            break;
                        case 7:
                            //7已完成处理
                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);

                            break;

                    }
                    break;
                case 2:
                    bt1.setText("确认审核通过");
                    bt2.setText("确认审核不通过");
                    chulineirong.setEnabled(false);
                    huifuneirong.setEnabled(false);
                    paicha_rl.setEnabled(false);
                    switch (status){
                        case 0:
                            //待回复
                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);


                            break;
                        case 1:
                            //回复待审核

                            //  link_huifu_shenhe();

                            break;
                        case 2:
                            //回复审核通过

                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);


                            break;
                        case 3:
                            //回复审核不通过

                            break;
                        case 4:
                            //处理待审核


                            break;
                        case 5:
                            //处理审核通过

                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);
                            break;
                        case 6:
                            //处理审核不通过


                            break;
                        case 7:
                            //7已完成处理

                            bt1.setVisibility(View.GONE);
                            bt2.setVisibility(View.GONE);
                            view.setVisibility(View.GONE);
                            break;

                    }
                    break;

            }


    }

        }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            paichashijian.setText(date);
        }
    }


    private void showMSG(final String s,final int i){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(BaoZhangChaKanActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

    private void link_save() {
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
            tijiao.put("replyContent",huifuneirong.getText().toString().trim());
            tijiao.put("replyUsername",dengLuBean.getName());
            tijiao.put("replyTime",System.currentTimeMillis());
            tijiao.put("planCheckTime",DateUtils.getTimes(paichashijian.getText().toString().trim()));

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

                        showMSG("保存成功",4);
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


    private void link_huifu_shenhe(int ooo) {
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
            tijiao.put("status",ooo);
            tijiao.put("id",faultsBean.getId());

            jsonArray.put(tijiao);


            Log.d("BaoZhangDengJiActivity", tijiao.toString());
            //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        builder.addFormDataPart("cmd","102");
        builder.addFormDataPart("fault",jsonArray.toString());
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

                        showMSG("审核成功",4);

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


    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(BaoZhangChaKanActivity.this);
                    if (!BaoZhangChaKanActivity.this.isFinishing())
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

}
