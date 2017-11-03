package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
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
import com.examples.weibao.adapters.WeiBaoJiHuaAdapter;
import com.examples.weibao.allbeans.LiXianBeans;
import com.examples.weibao.allbeans.LiXianBeansDao;
import com.examples.weibao.allbeans.PlansBean;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class WeiBaoJiHuaActivity extends Activity {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private List<String> dataList;
    private WeiBaoJiHuaAdapter taiZhangAdapter;

    private List<LiXianBeans> liXianBeansList=new ArrayList<>();
  //  private LiXianBeans liXianBeans=null;
    private LiXianBeansDao liXianBeansDao=null;
    private List<PlansBean> plansBeanList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        liXianBeansDao= MyAppLaction.myAppLaction.getDaoSession().getLiXianBeansDao();
        liXianBeansList.addAll(liXianBeansDao.loadAll());


        setContentView(R.layout.activity_wei_bao_ji_hua);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }


        TextView t= (TextView) findViewById(R.id.title);
        t.setText("维保计划");
        ImageView imageView= (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        taiZhangAdapter=new WeiBaoJiHuaAdapter(plansBeanList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);

        linearLayoutManager=new LinearLayoutManager(WeiBaoJiHuaActivity.this);
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

                startActivity(new Intent(WeiBaoJiHuaActivity.this,ChaKnaXiangMuJiHuaActivity.class));

            }
        });

        Log.d("WeiBaoJiHuaActivity", liXianBeansList.get(0).getPlansBeans().get(0).getArea());
        new Thread(new Runnable() {
            @Override
            public void run() {
                int size=liXianBeansList.size();
                for (int i=0;i<size;i++){
                   plansBeanList.addAll(liXianBeansList.get(i).getPlansBeans());

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        taiZhangAdapter.notifyDataSetChanged();
                    }
                });

            }
        }).start();


    }
}
