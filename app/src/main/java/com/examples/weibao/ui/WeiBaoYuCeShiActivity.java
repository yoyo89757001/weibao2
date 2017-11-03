package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.PopupWindowAdapter;
import com.examples.weibao.adapters.PopupWindowAdapter4;
import com.examples.weibao.adapters.PopupWindowAdapter3;
import com.examples.weibao.allbeans.DetectionsBean;
import com.examples.weibao.allbeans.DetectionsBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.LiXianBeans;
import com.examples.weibao.allbeans.LiXianBeansDao;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.allbeans.PlansBean;
import com.examples.weibao.allbeans.PlansBeanDao;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;

public class WeiBaoYuCeShiActivity extends Activity implements View.OnClickListener {
    private TextView t1,t2,t3,t4;
    private RelativeLayout r1,r2,r3,r4;
    private Button button;
    private PopupWindow popupWindow=null;
    private ImageView go,go3,go4;

    private List<LiXianBeans> liXianBeansList=new ArrayList<>();
    private LiXianBeans liXianBeans=null;
    private LiXianBeansDao liXianBeansDao=null;
    private List<DevicesBean> devicesBeanList=null;
    private DevicesBean devicesBean=null;
    private DevicesBeanDao devicesBeanDao=null;
    private List<DetectionsBean> detectionsBeanList=null;
    private DetectionsBean detectionsBean=null;
    private DetectionsBeanDao detectionsBeanDao=null;
    private List<MenusBean> menusBeanList=new ArrayList<>();
    private MenusBean menusBean=null;
    private MenusBeanDao menusBeanDao=null;
    private List<PlansBean> plansBeanList=null;
    private PlansBean plansBean=null;
    private PlansBeanDao plansBeanDao=null;
    //2个零时的 List<MenusBean> menusBeanList=null;
    private List<MenusBean> menusBeanList3=new ArrayList<>();
    private List<MenusBean> menusBeanList4=new ArrayList<>();
    private int p1=-1;
    private int p4=-1;
    private int p3=-1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bao_yu_ce_shi);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }

        initDao();

        initView();

    }

    private void initDao() {
       // dengLuBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
      //  dengLuBean=dengLuBeanDao.load(123456L);
        liXianBeansDao=MyAppLaction.myAppLaction.getDaoSession().getLiXianBeansDao();
        liXianBeansList.addAll(liXianBeansDao.loadAll());
//        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
//        detectionsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDetectionsBeanDao();
//        menusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
//        plansBeanDao=MyAppLaction.myAppLaction.getDaoSession().getPlansBeanDao();

    }

    private void initView() {
        TextView t= (TextView) findViewById(R.id.title);
        t.setText("维保与测试");
        ImageView imageView= (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        t1= (TextView) findViewById(R.id.t1);
        t2= (TextView) findViewById(R.id.t2);
        t3= (TextView) findViewById(R.id.t3);
        t4= (TextView) findViewById(R.id.t4);
        go= (ImageView) findViewById(R.id.go);
        go3= (ImageView) findViewById(R.id.go3);
        go4= (ImageView) findViewById(R.id.go4);
        r1= (RelativeLayout) findViewById(R.id.r1);
        r1.setOnClickListener(this);
        r2= (RelativeLayout) findViewById(R.id.r2);
        r2.setOnClickListener(this);
        r3= (RelativeLayout) findViewById(R.id.r3);
        r3.setOnClickListener(this);
        r4= (RelativeLayout) findViewById(R.id.r4);
        r4.setOnClickListener(this);
        button= (Button) findViewById(R.id.xiayibu);
        button.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r1:

                View contentView = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView,600, 660);
                ListView listView= (ListView) contentView.findViewById(R.id.dddddd);
                PopupWindowAdapter adapter=new PopupWindowAdapter(WeiBaoYuCeShiActivity.this,liXianBeansList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p1=position;
                        p3=-1;
                        p4=-1;
                     //   Log.d("WeiBaoYuCeShiActivity", "position:" + position);
                        t2.setText(liXianBeansList.get(position).getAddress());
                        t1.setText(liXianBeansList.get(position).getName());

                        t3.setText("请选择");
                        t4.setText("请选择");
                        popupWindow.dismiss();
                    }
                });
                listView.setAdapter(adapter);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                popupWindow.showAsDropDown(r1,go.getLeft()-600,0);

                break;
            case R.id.r2:

                break;
            case R.id.r3:
              //  Log.d("WeiBaoYuCeShiActivity", "liXianBeansList.get(p1).getMenusBeans():" + liXianBeansList.get(p1).getMenusBeans());

                if (menusBeanList3.size()!=0){
                    menusBeanList3.clear();
                }

                if (p1==-1){
                    break;
                }

                List<MenusBean> mb =liXianBeansList.get(p1).getMenusBeans();
                int s=mb.size();
                for (int i=0;i<s;i++){
                    if (mb.get(i).getParentId()==-1){
                        //-1是系统；
                        menusBeanList3.add(mb.get(i));
                    }
                }

                View contentView3 = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView3,600, 660);
                ListView listView3= (ListView) contentView3.findViewById(R.id.dddddd);
                PopupWindowAdapter3 adapter3=new PopupWindowAdapter3(WeiBaoYuCeShiActivity.this,menusBeanList3);
                listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p3=position;
                        p4=-1;
                        t3.setText(menusBeanList3.get(position).getName());
                        t4.setText("请选择");
                        popupWindow.dismiss();
                    }
                });
                listView3.setAdapter(adapter3);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                if (menusBeanList3.size()!=0){
                    popupWindow.showAsDropDown(r3,go3.getLeft()-600,0);
                }

                break;
            case R.id.r4:

                if (p3==-1)
                    break;

                if (menusBeanList4.size()!=0){
                    menusBeanList4.clear();
                }
                List<MenusBean> mb4 =liXianBeansList.get(p1).getMenusBeans();
                int s4=mb4.size();
                for (int i=0;i<s4;i++){
                    if (mb4.get(i).getParentId()==menusBeanList3.get(p3).getId()){
                        //-1是系统；
                        menusBeanList4.add(mb4.get(i));
                    }
                }

                View contentView4 = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView4,600, 600);
                ListView listView4= (ListView) contentView4.findViewById(R.id.dddddd);
                PopupWindowAdapter4 adapter4=new PopupWindowAdapter4(WeiBaoYuCeShiActivity.this,menusBeanList4);
                listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p4=position;
                        t4.setText(menusBeanList4.get(position).getName());
                        popupWindow.dismiss();
                    }
                });
                listView4.setAdapter(adapter4);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                if (menusBeanList4.size()!=0){
                    popupWindow.showAsDropDown(r4,go4.getLeft()-600,0);
                }

                break;
            case R.id.xiayibu:

            startActivity(new Intent(WeiBaoYuCeShiActivity.this,SheBeiWeiBaoYuCeShiActivity.class));

                break;

        }

    }
}
