package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.beans.DengLuBean;
import com.examples.weibao.beans.DengLuBeanDao;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.fargments.Fragment1;
import com.examples.weibao.fargments.Fragment2;
import com.examples.weibao.fargments.Fragment3;
import com.examples.weibao.fargments.Fragment4;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.examples.weibao.views.ViewPagerFragmentAdapter;
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

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout r1,r2,r3,r4;
    private LinearLayout zhong_ll;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private FragmentManager mFragmentManager;
    private String[] titleName = new String[] {"首页","维保","消息","资讯"};
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ImageView tabIm,tabIm2,tabIm3,tabIm4;
    private TextView tabText,tabText2,tabText3,tabText4;

    // 定义一个变量，来标识是否退出
    private static boolean isExit = false;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;



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
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_home_page);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }



        initFragmetList();

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);

        initView();
        initViewPager();

    }


    private void link_save() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();


        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();


//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("account",zhanghao.getText().toString().trim());
            jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", "0")
                .header("sign", Utils.encode("100"+zhanghao.getText().toString().trim()+jiami+nonce+timestamp
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
                        zhaoPianBean.setZhuji("http://14.23.169.42:8090/api/");
                        dengLuBeanDao.update(zhaoPianBean);
                        finish();


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
                    tiJIaoDialog=new TiJIaoDialog(HomePageActivity.this);
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
    }

    private void initViewPager() {

        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
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
            startActivity(new Intent(HomePageActivity.this,SaoMaTiaoZhuangActivity.class));

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
