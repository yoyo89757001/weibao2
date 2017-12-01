package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
        menusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
        idid=getIntent().getLongExtra("idid",0);
        if (idid!=0){
            devicesBean=devicesBeanDao.load(idid);
            if (devicesBean!=null){
                itemsBean=itemsBeanDao.load((long) devicesBean.getItemId());
                menusBean=menusBeanDao.load(Long.parseLong(devicesBean.getWeibaoSubSystemId()));
                Log.d("SaoYiSaoTanChuActivity", menusBean.getName());
            }

        }

        dengLuBean=dengLuBeanDao.load(123456L);
        setContentView(R.layout.activity_sao_yi_sao_tan_chu);

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

    if (dengLuBean.getStatus()==3){
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


    }




}
