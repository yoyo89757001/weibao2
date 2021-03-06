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
import com.examples.weibao.beans.WeiBaoCeShiCSBean;
import com.examples.weibao.dialogs.XinXiDialog;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.Utils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import org.parceler.Parcels;
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
    private WeiBaoCeShiCSBean ceShiCSBean=null;
    private Long xitongID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ceShiCSBean = Parcels.unwrap(getIntent().getParcelableExtra("chuansong"));
        itemId=getIntent().getLongExtra("itemId",0);
        serialNumber3=getIntent().getStringExtra("serialNumber3");//系统的
        serialNumber4=getIntent().getStringExtra("serialNumber4");//维保项的
        xitong=getIntent().getStringExtra("xitong");
        xitongID=getIntent().getLongExtra("xitongID",0L);
        dizhi=getIntent().getStringExtra("dizhi");
        weibaoxiang=getIntent().getStringExtra("weibaoxiang");
        parentId=getIntent().getLongExtra("parentId",-2L);
        menusBeanDao= MyAppLaction.myAppLaction.getDaoSession().getMenusBeanDao();
        devicesBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();


        List<DevicesBean> bb=devicesBeanDao.queryBuilder().where(DevicesBeanDao.Properties.WeibaoDeviceId.eq(serialNumber4)
                ,DevicesBeanDao.Properties.WeibaoSubSystemId.eq(serialNumber3),DevicesBeanDao.Properties.ItemId.eq(itemId)).list();
        if (bb.size()>0){
            devicesBeanList.addAll(bb);
        }
        menusBeanList2=menusBeanDao.queryBuilder().where(MenusBeanDao.Properties.ParentId.eq(parentId)).list();
      //  Log.d("SheBeiWeiBaoYuCeShiActi", "menusBeanList2.size():" + menusBeanList2.size());
        //Log.d("SheBeiWeiBaoYuCeShiActi", "devicesBeanList.size():" + devicesBeanList.size());
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
        sheBeiAdapter=new SheBeiAdapter(devicesBeanList,menusBeanDao,SheBeiWeiBaoYuCeShiActivity.this,menusBeanList2,ceShiCSBean,xitongID);
      //  Log.d("SheBeiWeiBaoYuCeShiActi", ceShiCSBean.toString());

        lRecyclerViewAdapter = new LRecyclerViewAdapter(sheBeiAdapter);
        sheBeiAdapter.setClickIntface(this);

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
    public int BackId(int id, String s) {

        if (menusBeanList2.size()>0 && menusBeanList2.size()>id && menusBeanList2.get(id).getDesc()!=null){
            final XinXiDialog xinXiDialog=new XinXiDialog(SheBeiWeiBaoYuCeShiActivity.this);
            xinXiDialog.setCountText(menusBeanList2.get(id).getDesc());
            xinXiDialog.setOnPositiveListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (xinXiDialog.isShowing())
                        xinXiDialog.dismiss();
                }
            });
            xinXiDialog.show();
        }

        return 0;
    }
}
