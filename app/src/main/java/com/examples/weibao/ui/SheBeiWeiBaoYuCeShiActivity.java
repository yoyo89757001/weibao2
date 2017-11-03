package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.adapters.SheBeiAdapter;
import com.examples.weibao.dialogs.ZhuangTaiXuanZe2Dialog;
import com.examples.weibao.dialogs.ZhuangTaiXuanZeDialog;
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
    private List<String> dataList;
    private SheBeiAdapter sheBeiAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int isTrue;
    private String miaoshu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_bei_wei_bao_yu_ce_shi);

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


        dw = Utils.getDisplaySize(this).x;
        dh = Utils.getDisplaySize(this).y;

        dataList=new ArrayList<>();
        dataList.add("ddddddd");
        dataList.add("dfff");

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
        t1= (TextView) findViewById(R.id.t1);
        t1= (TextView) findViewById(R.id.t1);


        LinearLayout l= (LinearLayout) findViewById(R.id.tl);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) l.getLayoutParams();
        layoutParams.height=(dh*20)/100;
        l.setLayoutParams(layoutParams);
        l.invalidate();

        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        sheBeiAdapter=new SheBeiAdapter(dataList);
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
