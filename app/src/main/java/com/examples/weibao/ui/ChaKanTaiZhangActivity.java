package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.ChaKanTaiZhangAdapter;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;

public class ChaKanTaiZhangActivity extends Activity {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private ChaKanTaiZhangAdapter taiZhangAdapter;

  //  private List<LiXianBeans> liXianBeansList=new ArrayList<>();
    //  private LiXianBeans liXianBeans=null;
  //  private LiXianBeansDao liXianBeansDao=null;
    private DevicesBeanDao devicesBeanDao=null;
    private List<DevicesBean> devicesBeanList=new ArrayList<>();
    private long position=-1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        position=getIntent().getLongExtra("position",-1);

        devicesBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();

       if (position!=-1){
        List<DevicesBean> ddd=devicesBeanDao.queryBuilder().where(DevicesBeanDao.Properties.ItemId.eq(position)).list();
           if (ddd!=null){
               devicesBeanList.addAll(ddd);
           }

       }

        setContentView(R.layout.activity_cha_kan_tai_zhang);
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

        TextView title= (TextView) findViewById(R.id.title);
        title.setText("查看项目设备台帐");
        ImageView left= (ImageView) findViewById(R.id.leftim);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        taiZhangAdapter=new ChaKanTaiZhangAdapter(devicesBeanList,"");
        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);

        linearLayoutManager=new LinearLayoutManager(ChaKanTaiZhangActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerView.setLoadMoreEnabled(false);

        DividerDecoration divider = new DividerDecoration.Builder(this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.transparent)
                .build();
        lRecyclerView.addItemDecoration(divider);

        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });




    }
}
