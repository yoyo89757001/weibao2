package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.adapters.SheBeiAdapter;
import com.examples.weibao.dialogs.ZhuangTaiXuanZeDialog;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.Utils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_bei_wei_bao_yu_ce_shi);
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
        Log.d("SheBeiWeiBaoYuCeShiActi", "id:" + id);
        switch (id){
          case   R.id.rl_1:
              ZhuangTaiXuanZeDialog dialog=new ZhuangTaiXuanZeDialog(this);
              dialog.show();

            break;
            case   R.id.rl_2:

                break;
            case   R.id.rl_3:

                break;
            case   R.id.rl_4:

                break;
            case   R.id.rl_5:

                break;
        }

        return 0;
    }
}
