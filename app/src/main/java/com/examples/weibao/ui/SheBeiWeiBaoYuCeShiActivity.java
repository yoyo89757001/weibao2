package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.SheBeiAdapter;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.Utils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class SheBeiWeiBaoYuCeShiActivity extends Activity implements ClickIntface {
    private int dw,dh;
    private LRecyclerView lRecyclerView;
    private TextView t1,t2,t3;
    private SheBeiAdapter sheBeiAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int isTrue;
    private String miaoshu;
    private long parentId=-2L;


    private List<MenusBean> menusBeanList2=null;
    private MenusBean menusBean=null;
    private MenusBeanDao menusBeanDao=null;
    private DevicesBeanDao devicesBeanDao=null;
    private List<DevicesBean> devicesBeanList=new ArrayList<>();
    private String dizhi,xitong,weibaoxiang;
    private long itemId=0;
    private String serialNumber3="";
    private String serialNumber4="";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        itemId=getIntent().getLongExtra("itemId",0);
        serialNumber3=getIntent().getStringExtra("serialNumber3");
        serialNumber4=getIntent().getStringExtra("serialNumber4");
        xitong=getIntent().getStringExtra("xitong");
        dizhi=getIntent().getStringExtra("dizhi");
        weibaoxiang=getIntent().getStringExtra("weibaoxiang");
        parentId=getIntent().getLongExtra("parentId",-2L);
        menusBeanDao= MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
        devicesBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        Log.d("SheBeiWeiBaoYuCeShiActi", serialNumber3+"");
        Log.d("SheBeiWeiBaoYuCeShiActi", serialNumber4+"");
        Log.d("SheBeiWeiBaoYuCeShiActi", "itemId:" + itemId);
        if (serialNumber4!=null){
            List<DevicesBean> bb=devicesBeanDao.queryBuilder().where(DevicesBeanDao.Properties.WeibaoSystemId.eq(serialNumber3)
                    ,DevicesBeanDao.Properties.WeibaoSubSystemId.eq(serialNumber4),DevicesBeanDao.Properties.ItemId.eq(itemId)).list();
            if (bb.size()>0){
                devicesBeanList.addAll(bb);
            }
        }else {
            List<DevicesBean> bb=devicesBeanDao.queryBuilder().where(DevicesBeanDao.Properties.WeibaoSystemId.eq(serialNumber3)
                    ,DevicesBeanDao.Properties.ItemId.eq(itemId)).list();
            if (bb.size()>0){
                devicesBeanList.addAll(bb);
            }
        }

        menusBeanList2=menusBeanDao.queryBuilder().where(MenusBeanDao.Properties.ParentId.eq(parentId)).list();
        if (menusBeanList2!=null){
            for (int i=0;i<menusBeanList2.size();i++){
                Log.d("SheBeiWeiBaoYuCeShiActi", menusBeanList2.get(i).getName()+menusBeanList2.get(i).getType());
            }
        }

        setContentView(R.layout.activity_she_bei_wei_bao_yu_ce_shi);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);

        }


        dw = Utils.getDisplaySize(this).x;
        dh = Utils.getDisplaySize(this).y;

        TextView t= (TextView) findViewById(R.id.title);
        t.setText("设备维保与测试");
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


        LinearLayout l= (LinearLayout) findViewById(R.id.tl);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) l.getLayoutParams();
        layoutParams.height=(dh*20)/100;
        l.setLayoutParams(layoutParams);
        l.invalidate();

        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        sheBeiAdapter=new SheBeiAdapter(devicesBeanList,menusBeanDao,SheBeiWeiBaoYuCeShiActivity.this,menusBeanList2);
        sheBeiAdapter.setClickIntface(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(sheBeiAdapter);

        linearLayoutManager=new LinearLayoutManager(SheBeiWeiBaoYuCeShiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);

        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerView.setLoadMoreEnabled(false);

        DividerDecoration divider = new DividerDecoration.Builder(SheBeiWeiBaoYuCeShiActivity.this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.view_bg)
                .build();
        lRecyclerView.addItemDecoration(divider);

        t1.setText(dizhi+"");
        t2.setText(xitong+"");
        t3.setText(weibaoxiang+"");
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public int BackId(int id) {


        return 0;
    }
}
