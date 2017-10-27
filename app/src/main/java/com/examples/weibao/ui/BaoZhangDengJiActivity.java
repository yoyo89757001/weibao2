package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.DateUtils;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BaoZhangDengJiActivity extends Activity {
    @BindView(R.id.mingcheng)
    EditText mingcheng;
    @BindView(R.id.dizhi)
    EditText dizhi;
    @BindView(R.id.shebei)
    TextView shebei;
    @BindView(R.id.shebei_rl)
    RelativeLayout shebeiRl;
    @BindView(R.id.guzhang_et)
    EditText guzhangEt;
    @BindView(R.id.shijian)
    TextView shijian;
    @BindView(R.id.shijian_rl)
    RelativeLayout shijianRl;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dianhua)
    EditText dianhua;
    @BindView(R.id.chakan)
    Button chakan;
    @BindView(R.id.tijiao)
    Button tijiao;
    private RecyclerView recyclerView;
    private ZhaoPianAdapter zhaoPianAdapter = null;
    private List<String> stringList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_zhang_deng_ji);
        ButterKnife.bind(this);
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
        TextView title = (TextView) findViewById(R.id.title);
        title.setText("报障登记");
        ImageView left = (ImageView) findViewById(R.id.leftim);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        stringList = new ArrayList<>();
        stringList.add("jij");
        stringList.add("jij");
        stringList.add("jij");

        initView();
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        zhaoPianAdapter = new ZhaoPianAdapter(stringList);
        recyclerView.setAdapter(zhaoPianAdapter);

        shijian.setText(DateUtils.timet2(System.currentTimeMillis()+""));

    }

    @OnClick({R.id.shebei_rl, R.id.shijian_rl, R.id.chakan, R.id.tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shebei_rl:

                break;
            case R.id.shijian_rl:

                Intent intent = new Intent(BaoZhangDengJiActivity.this, DatePickActivity.class);
                startActivityForResult(intent,2);


                break;
            case R.id.chakan:

                break;
            case R.id.tijiao:

                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            shijian.setText(date);
        }
    }



    private class ZhaoPianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> datas;
        private ClickIntface clickIntface;

        public void setClickIntface(ClickIntface clickIntface) {
            this.clickIntface = clickIntface;
        }

        public ZhaoPianAdapter(List<String> datas) {
            this.datas = datas;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tupian_item, viewGroup, false);
            return new ViewHolder(view);
        }


        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            //  viewHolder.bianhao.setText(datas.get(position));

        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.size();
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView bianhao, ceshi;


            private ViewHolder(View view) {
                super(view);
                //  bianhao = (TextView) view.findViewById(R.id.bianhao);
                //  ceshi = (TextView) view.findViewById(R.id.ceshi);


            }
        }
    }
}
