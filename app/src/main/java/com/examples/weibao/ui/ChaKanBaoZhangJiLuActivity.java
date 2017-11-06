package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.BaoZhangJiLuAdapter;
import com.examples.weibao.adapters.WeiBaoJiHuaAdapter;
import com.examples.weibao.allbeans.BaoZhangDengJiBean;
import com.examples.weibao.allbeans.BaoZhangDengJiBeanDao;
import com.examples.weibao.allbeans.ItemsBean;
import com.examples.weibao.allbeans.ItemsBeanDao;
import com.examples.weibao.allbeans.PlansBean;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class ChaKanBaoZhangJiLuActivity extends Activity {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<String> dataList;
    private BaoZhangJiLuAdapter taiZhangAdapter;

   // private List<ItemsBean> itemsBeanList=new ArrayList<>();
    //  private LiXianBeans liXianBeans=null;
    private BaoZhangDengJiBeanDao baoZhangDengJiBeanDao=null;
    private List<BaoZhangDengJiBean> plansBeanList=new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baoZhangDengJiBeanDao= MyAppLaction.myAppLaction.getDaoSession().getBaoZhangDengJiBeanDao();
        List<BaoZhangDengJiBean> baoZhangDengJiBeans= baoZhangDengJiBeanDao.loadAll();
        if (baoZhangDengJiBeans!=null)
        plansBeanList.addAll(baoZhangDengJiBeans);
        setContentView(R.layout.activity_cha_kan_bao_zhang_ji_lu);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }
        TextView t= (TextView) findViewById(R.id.title);
        t.setText("查看报障记录");
        ImageView imageView= (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        taiZhangAdapter=new BaoZhangJiLuAdapter(plansBeanList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);

        linearLayoutManager=new LinearLayoutManager(ChaKanBaoZhangJiLuActivity.this);
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

                startActivity(new Intent(ChaKanBaoZhangJiLuActivity.this,BaoZhangDengJiActivity.class)
                        .putExtra("jilu",1).putExtra("idid",plansBeanList.get(position).getId()));

            }
        });


    }
}
