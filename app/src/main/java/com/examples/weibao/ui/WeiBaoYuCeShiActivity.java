package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
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
import android.widget.Toast;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.PopupWindowAdapter;
import com.examples.weibao.adapters.PopupWindowAdapter4;
import com.examples.weibao.adapters.PopupWindowAdapter3;
import com.examples.weibao.adapters.PopupWindowAdapter_jihua;
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
import com.examples.weibao.beans.WeiBaoCeShiCSBean;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.sdsmdg.tastytoast.TastyToast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class WeiBaoYuCeShiActivity extends Activity implements View.OnClickListener {
    private TextView t1,t2,t3,t4,jihua_tv;
    private RelativeLayout r1,r2,r3,r4,jihua_rl;
    private Button button;
    private PopupWindow popupWindow=null;
    private ImageView go,go3,go4,go6;

    private List<ItemsBean> itemsBeanList=new ArrayList<>();
    private ItemsBean itemsBean=null;
    private ItemsBeanDao itemsBeanDao=null;
    private List<DevicesBean> devicesBeanList=null;
    private DevicesBean devicesBean=null;
    private DevicesBeanDao devicesBeanDao=null;
    private List<DetectionsBean> detectionsBeanList=null;
    private DetectionsBean detectionsBean=null;
    private DetectionsBeanDao detectionsBeanDao=null;
    private List<MenusBean> menusBeanList=null;
    private MenusBean menusBean=null;
    private MenusBeanDao menusBeanDao=null;
    private List<PlansBean> plansBeanList=new ArrayList<>();
    private PlansBean plansBean=null;
    private PlansBeanDao plansBeanDao=null;
    private List<MenurefsBean> menurefsBeanList=null;
    private MenurefsBeanDao menurefsBeanDao=null;
    //2个零时的 List<MenusBean> menusBeanList=null;
    private List<MenusBean> menusBeanList3=new ArrayList<>();
    private List<MenusBean> menusBeanList4=new ArrayList<>();
    private int p1=-1;
    private int p4=-1;
    private int p3=-1;
    private int p6=-1;
    private WeiBaoCeShiCSBean ceShiCSBean=new WeiBaoCeShiCSBean();


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
        itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
        itemsBeanList.addAll(itemsBeanDao.loadAll());
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        detectionsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDetectionsBeanDao();
        menusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
        plansBeanDao=MyAppLaction.myAppLaction.getDaoSession().getPlansBeanDao();
        menurefsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getMenurefsBeanDao();
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
        go6= (ImageView) findViewById(R.id.go6);
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
        jihua_rl= (RelativeLayout) findViewById(R.id.jihua_rl);
        jihua_rl.setOnClickListener(this);
        jihua_tv= (TextView) findViewById(R.id.jihua_tv);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.r1:

                View contentView = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView,600, 660);
                ListView listView= (ListView) contentView.findViewById(R.id.dddddd);
                PopupWindowAdapter adapter=new PopupWindowAdapter(WeiBaoYuCeShiActivity.this,itemsBeanList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p1=position;
                        p3=-1;
                        p4=-1;
                        t2.setText(itemsBeanList.get(position).getAddress());
                        t1.setText(itemsBeanList.get(position).getName());
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
                if (p1==-1){
                    break;
                }
                if (menusBeanList3.size()!=0){
                    menusBeanList3.clear();
                }

                //项目ID
                long mb =itemsBeanList.get(p1).getId();

                //通过项目ID 在项目关系表中找出所有一级项目菜单
               menurefsBeanList = menurefsBeanDao.queryBuilder().where(MenurefsBeanDao.Properties.ItemId.eq(mb)).list();
               int s=0;
               if (menurefsBeanList!=null){
                   s=menurefsBeanList.size();
               }


                //通过菜单menurefsBeanList中的id查出所有菜单
                for (int i=0;i<s;i++){
                   MenusBean menusBean= menusBeanDao.queryBuilder().where(MenusBeanDao.Properties.Id.eq(menurefsBeanList.get(i).getWeibaoMenuId())).unique();
                    if (menusBean!=null && menusBean.getParentId()==-1){
                        menusBeanList3.add(menusBean);
                    }

                }

                View contentView3 = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView3,600, 600);
                ListView listView3= (ListView) contentView3.findViewById(R.id.dddddd);
                PopupWindowAdapter3 adapter3=new PopupWindowAdapter3(WeiBaoYuCeShiActivity.this,menusBeanList3);
                listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p3=position;
                        p4=-1;
                        t3.setText(menusBeanList3.get(position).getName());
                        t4.setText("请选择");
                        ceShiCSBean.setMenuLevel1Id(menusBeanList3.get(position).getId().intValue());

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
                Long  mb4 =menusBeanList3.get(p3).getId();
                //通过菜单ID找出下一级
                List<MenusBean> beanList=menusBeanDao.queryBuilder().where(MenusBeanDao.Properties.ParentId.eq(mb4)).list();
                menusBeanList4.addAll(beanList==null?new ArrayList<MenusBean>():beanList);


                View contentView4 = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView4,600, 540);
                ListView listView4= (ListView) contentView4.findViewById(R.id.dddddd);
                PopupWindowAdapter4 adapter4=new PopupWindowAdapter4(WeiBaoYuCeShiActivity.this,menusBeanList4);
                listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p4=position;
                        t4.setText(menusBeanList4.get(position).getName());
                        ceShiCSBean.setMenuId(menusBeanList4.get(position).getId().intValue());
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
                if (p4!=-1 && p6!=-1){
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("chuansong", Parcels.wrap(ceShiCSBean));

                    startActivity(new Intent(WeiBaoYuCeShiActivity.this,SheBeiWeiBaoYuCeShiActivity.class).
                            putExtra("itemId",itemsBeanList.get(p1).getId()).
                            putExtra("serialNumber3",menusBeanList3.get(p3).getSerialNumber()).
                            putExtra("serialNumber4",menusBeanList4.get(p4).getSerialNumber()).
                            putExtra("parentId",menusBeanList4.get(p4).getId()).
                            putExtra("dizhi",itemsBeanList.get(p1).getAddress()).
                            putExtra("xitong",menusBeanList3.get(p3).getName()).
                            putExtra("weibaoxiang",menusBeanList4.get(p4).getName()).putExtras(bundle));
                }else {
                    showMSG("信息没有选择完整!",4);
                }

                break;
            case R.id.jihua_rl:

                if (p1==-1){
                    break;
                }
                if (plansBeanList.size()!=0){
                    plansBeanList.clear();
                }

                //项目ID
                long itid =itemsBeanList.get(p1).getId();

                //通过项目ID 在项目关系表中找出所有一级项目菜单
               List<PlansBean> pp = plansBeanDao.queryBuilder().where(PlansBeanDao.Properties.ItemId.eq(itid)).list();
                if (pp!=null){
                    plansBeanList.addAll(pp);
                }

                View contentView6 = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView6,600, 660);
                ListView listView6= (ListView) contentView6.findViewById(R.id.dddddd);
                PopupWindowAdapter_jihua adapter6=new PopupWindowAdapter_jihua(WeiBaoYuCeShiActivity.this,plansBeanList);
                listView6.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p6=position;
                        ceShiCSBean.setPlanId(plansBeanList.get(position).getId().intValue());
                        jihua_tv.setText(plansBeanList.get(position).getArea());
                        popupWindow.dismiss();
                    }
                });
                listView6.setAdapter(adapter6);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                if (plansBeanList.size()!=0){
                    popupWindow.showAsDropDown(jihua_rl,go6.getLeft()-600,0);
                }

                break;

        }

    }

    private void showMSG(final String s,final int i){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(WeiBaoYuCeShiActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }
}
