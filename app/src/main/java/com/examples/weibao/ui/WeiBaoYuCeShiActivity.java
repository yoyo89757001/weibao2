package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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
import com.examples.weibao.R;
import com.examples.weibao.adapters.PopupWindowAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class WeiBaoYuCeShiActivity extends Activity implements View.OnClickListener {
    private TextView t1,t2,t3,t4;
    private RelativeLayout r1,r2,r3,r4;
    private Button button;
    private PopupWindow popupWindow=null;
    private ImageView go,go3,go4;
    private List<String> stringList=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wei_bao_yu_ce_shi);
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

        stringList.add("dsdsds");
        stringList.add("dsdsds");
        stringList.add("dsdsds");
        stringList.add("dsdsds");
        stringList.add("dsdsds");

        initView();

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
                PopupWindowAdapter adapter=new PopupWindowAdapter(WeiBaoYuCeShiActivity.this,stringList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("WeiBaoYuCeShiActivity", "position:" + position);
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
                View contentView3 = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView3,600, 660);
                ListView listView3= (ListView) contentView3.findViewById(R.id.dddddd);
                PopupWindowAdapter adapter3=new PopupWindowAdapter(WeiBaoYuCeShiActivity.this,stringList);
                listView3.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("WeiBaoYuCeShiActivity", "position:" + position);
                        popupWindow.dismiss();
                    }
                });
                listView3.setAdapter(adapter3);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                popupWindow.showAsDropDown(r3,go3.getLeft()-600,0);
                break;
            case R.id.r4:
                View contentView4 = LayoutInflater.from(WeiBaoYuCeShiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView4,600, 600);
                ListView listView4= (ListView) contentView4.findViewById(R.id.dddddd);
                PopupWindowAdapter adapter4=new PopupWindowAdapter(WeiBaoYuCeShiActivity.this,stringList);
                listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Log.d("WeiBaoYuCeShiActivity", "position:" + position);
                        popupWindow.dismiss();
                    }
                });
                listView4.setAdapter(adapter4);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                popupWindow.showAsDropDown(r4,go4.getLeft()-600,0);

                break;
            case R.id.xiayibu:

            startActivity(new Intent(WeiBaoYuCeShiActivity.this,SheBeiWeiBaoYuCeShiActivity.class));

                break;

        }

    }
}
