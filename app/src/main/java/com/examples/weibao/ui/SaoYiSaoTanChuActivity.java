package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.ItemsBean;
import com.examples.weibao.allbeans.ItemsBeanDao;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class SaoYiSaoTanChuActivity extends Activity {
    private TextView t1,t2,t3,t4,t5,t6,t7;
    private Button b1,b2;
    private DengLuBeanDao dengLuBeanDao=null;
    private DengLuBean dengLuBean=null;
    private long idid;
    private DevicesBeanDao devicesBeanDao=null;
    private DevicesBean devicesBean=null;
    private ItemsBeanDao itemsBeanDao=null;
    private ItemsBean itemsBean=null;
    private MenusBeanDao menusBeanDao=null;
    private MenusBean menusBean=null;
    private int type;
    private String scanned=null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
        menusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
        idid=getIntent().getLongExtra("idid",0);
        type=getIntent().getIntExtra("type",0);
        scanned=getIntent().getStringExtra("scanned");
        if (idid!=0){
            devicesBean=devicesBeanDao.load(idid);
            if (devicesBean!=null){
                itemsBean=itemsBeanDao.load((long) devicesBean.getItemId());
                menusBean=menusBeanDao.load(Long.parseLong(devicesBean.getWeibaoSubSystemId()));
//                Log.d("SaoYiSaoTanChuActivity", menusBean.getName());
            }
        }
        dengLuBean=dengLuBeanDao.load(123456L);
        if (scanned!=null){
            String ss[] =scanned.split("&");
            Log.d("SaoYiSaoTanChuActivity", "ss.length:" + ss.length);
        }

        setContentView(R.layout.activity_sao_yi_sao_tan_chu);
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
        iniView();


    }

    private void iniView() {
        ImageView imageView= (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView textView= (TextView) findViewById(R.id.title);
        textView.setText("设备维保与测试");
        t1= (TextView) findViewById(R.id.t1);
        t2= (TextView) findViewById(R.id.t2);
        t3= (TextView) findViewById(R.id.t3);
        t4= (TextView) findViewById(R.id.t4);
        t5= (TextView) findViewById(R.id.t5);
        t6= (TextView) findViewById(R.id.t6);
        t7= (TextView) findViewById(R.id.t7);
        b1= (Button) findViewById(R.id.baozhang);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SaoYiSaoTanChuActivity.this,BaoZhangDengJiActivity.class));
            }
        });
        b2= (Button) findViewById(R.id.chakan);
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });

    if (dengLuBean.getStatus()==2){
        b2.setVisibility(View.GONE);
    }
    if (devicesBean!=null && itemsBean!=null){
        t1.setText(devicesBean.getName());
        t2.setText(devicesBean.getDeviceNum());
        t3.setText(itemsBean.getAddress());
        t4.setText(itemsBean.getName());
        t5.setText(dengLuBean.getCompany());
      //  t6.setText();

    }
        if (type==99){
            b2.setVisibility(View.GONE);
        }

    }




}
