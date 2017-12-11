package com.examples.weibao.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.BaoZhangAdapter1;
import com.examples.weibao.adapters.PopupWindowAdapter5;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.FaultsBean;
import com.examples.weibao.allbeans.FaultsBeanDao;
import com.examples.weibao.beans.XuanZeBean;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class BaoZhangChuLiActivity extends Activity {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private BaoZhangAdapter1 adapter1=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private DengLuBean dengLuBean=null;
    private FaultsBeanDao faultsBeanDao=null;
    private List<FaultsBean> faultsBeanList0=new ArrayList<>();
    private List<FaultsBean> faultsBeanList1=new ArrayList<>();
    private List<FaultsBean> faultsBeanList2=new ArrayList<>();
    private List<FaultsBean> faultsBeanList3=new ArrayList<>();
    private List<FaultsBean> faultsBeanList4=new ArrayList<>();
    private List<FaultsBean> faultsBeanList5=new ArrayList<>();
    private List<FaultsBean> faultsBeanList6=new ArrayList<>();
    private List<FaultsBean> faultsBeanList7=new ArrayList<>();
    private PopupWindow popupWindow=null;
    private List<XuanZeBean> stringList=new ArrayList<>();
    private int p4=-1;
    private LinearLayout ll;
    private RelativeLayout ee;
    private List<FaultsBean> faultsBeanList=new ArrayList<>();
    private  List<FaultsBean> f=null;
    private int  pp=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_zhang_chu_li);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }
        faultsBeanDao= MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);

        stringList.add(new XuanZeBean("全部",-1));
        stringList.add(new XuanZeBean("待回复",0));
        stringList.add(new XuanZeBean("回复待审核",1));
        stringList.add(new XuanZeBean("回复审核通过",2));
        stringList.add(new XuanZeBean("回复审核不通过",3));
        stringList.add(new XuanZeBean("处理待审核",4));
        stringList.add(new XuanZeBean("处理审核通过",5));
        stringList.add(new XuanZeBean("处理审核不通过",6));
        stringList.add(new XuanZeBean("已完成处理",7));


        findById();
        init();

    }

    private void findById() {
        ll= (LinearLayout) findViewById(R.id.ttttt);
        ee= (RelativeLayout) findViewById(R.id.ee);
        TextView tit= (TextView) findViewById(R.id.title);
        tit.setText("报障列表");
        ImageView ff= (ImageView) findViewById(R.id.leftim);
        ff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        final TextView xx= (TextView) findViewById(R.id.xuanze);
        final ImageView ri= (ImageView) findViewById(R.id.rightim);
        ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View contentView4 = LayoutInflater.from(BaoZhangChuLiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView4,400, 600);
                ListView listView4= (ListView) contentView4.findViewById(R.id.dddddd);
                PopupWindowAdapter5 adapter4=new PopupWindowAdapter5(BaoZhangChuLiActivity.this,stringList);
                listView4.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p4=stringList.get(position).getP();
                        xx.setText(stringList.get(position).getS());
                       if (faultsBeanList.size()>0){
                           faultsBeanList.clear();
                       }
                        switch (stringList.get(position).getP()){
                            case -1:
                                faultsBeanList.addAll(f);
                                adapter1.notifyDataSetChanged();
                                break;
                            case 0:
                                faultsBeanList.addAll(faultsBeanList0);
                                adapter1.notifyDataSetChanged();
                                break;
                            case 1:
                              //  Log.d("BaoZhangChuLiActivity", "faultsBeanList1:" + faultsBeanList1.size());
                                faultsBeanList.addAll(faultsBeanList1);
                                //Log.d("BaoZhangChuLiActivity", "faultsBeanList:" + faultsBeanList.size());
                                adapter1.notifyDataSetChanged();
                                break;
                            case 2:
                                faultsBeanList.addAll(faultsBeanList2);
                                adapter1.notifyDataSetChanged();
                                break;
                            case 3:
                                faultsBeanList.addAll(faultsBeanList3);
                                adapter1.notifyDataSetChanged();
                                break;
                            case 4:
                                faultsBeanList.addAll(faultsBeanList4);
                                adapter1.notifyDataSetChanged();
                                break;
                            case 5:
                                faultsBeanList.addAll(faultsBeanList5);
                                adapter1.notifyDataSetChanged();
                                break;
                            case 6:
                                faultsBeanList.addAll(faultsBeanList6);
                                adapter1.notifyDataSetChanged();
                                break;
                            case 7:
                                faultsBeanList.addAll(faultsBeanList7);
                                adapter1.notifyDataSetChanged();
                                break;

                        }

                        popupWindow.dismiss();


                    }
                });
                listView4.setAdapter(adapter4);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                popupWindow.showAsDropDown(ee,ll.getLeft(),0);


            }
        });

        lRecyclerView= (LRecyclerView)findViewById(R.id.lrecyclerview);


        Button baozhangdengji= (Button) findViewById(R.id.dengji);
        baozhangdengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BaoZhangChuLiActivity.this,BaoZhangDengJiActivity.class));
            }
        });
    }

    @Override
    protected void onPause() {
      pp= lRecyclerView.getChildAdapterPosition(lRecyclerView.getChildAt(0));

        super.onPause();


    }

    private void init() {


    }

    @Override
    protected void onResume() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                clecnList();
                f=faultsBeanDao.loadAll();
                if (f!=null){
                    int s=f.size();
                    for (int i=0;i<s;i++){
                        switch (f.get(i).getStatus()){
                            case 0:
                                faultsBeanList0.add(f.get(i));
                                break;
                            case 1:
                                faultsBeanList1.add(f.get(i));
                                break;
                            case 2:
                                faultsBeanList2.add(f.get(i));
                                break;
                            case 3:
                                faultsBeanList3.add(f.get(i));
                                break;
                            case 4:
                                faultsBeanList4.add(f.get(i));
                                break;
                            case 5:
                                faultsBeanList5.add(f.get(i));
                                break;
                            case 6:
                                faultsBeanList6.add(f.get(i));
                                break;
                            case 7:
                                faultsBeanList7.add(f.get(i));
                                break;
                        }
                    }
                    switch (p4){
                        case -1:
                            faultsBeanList.addAll(f);
                            break;
                        case 0:
                            faultsBeanList.addAll(faultsBeanList0);
                            break;
                        case 1:
                            faultsBeanList.addAll(faultsBeanList1);
                            break;
                        case 2:
                            faultsBeanList.addAll(faultsBeanList2);
                            break;
                        case 3:
                            faultsBeanList.addAll(faultsBeanList3);
                            break;
                        case 4:
                            faultsBeanList.addAll(faultsBeanList4);
                            break;
                        case 5:
                            faultsBeanList.addAll(faultsBeanList5);
                            break;
                        case 6:
                            faultsBeanList.addAll(faultsBeanList6);
                            break;
                        case 7:
                            faultsBeanList.addAll(faultsBeanList7);
                            break;
                    }


                    //循环完了
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            adapter1=new BaoZhangAdapter1(faultsBeanList);
                            lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter1);
                            linearLayoutManager=new LinearLayoutManager(BaoZhangChuLiActivity.this);
                            linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                            lRecyclerView.setLayoutManager(linearLayoutManager);
                            lRecyclerView.setAdapter(lRecyclerViewAdapter);
                            lRecyclerView.setPullRefreshEnabled(false);
                            lRecyclerView.setLoadMoreEnabled(false);
                            DividerDecoration divider = new DividerDecoration.Builder(BaoZhangChuLiActivity.this)
                                    .setHeight(R.dimen.default_divider_height)
                                    .setPadding(R.dimen.default_divider_padding)
                                    .setColorResource(R.color.transparent)
                                    .build();
                            lRecyclerView.addItemDecoration(divider);
                            lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void onItemClick(View view, int position) {
                                    switch (p4){
                                        case -1:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",f.get(position).getStatus())
                                                    .putExtra("shebeiID",f.get(position).getDeviceId())
                                                    .putExtra("baozhangID",f.get(position).getId()));
                                            break;
                                        case 0:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList0.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList0.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList0.get(position).getId()));
                                            break;
                                        case 1:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList1.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList1.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList1.get(position).getId()));
                                            break;
                                        case 2:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList2.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList2.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList2.get(position).getId()));
                                            break;
                                        case 3:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList3.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList3.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList3.get(position).getId()));
                                            break;
                                        case 4:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList4.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList4.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList4.get(position).getId()));
                                            break;
                                        case 5:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList5.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList5.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList5.get(position).getId()));
                                            break;
                                        case 6:
                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList6.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList6.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList6.get(position).getId()));
                                            break;
                                        case 7:

                                            startActivity(new Intent(BaoZhangChuLiActivity.this, BaoZhangChaKanActivity.class)
                                                    .putExtra("status",faultsBeanList7.get(position).getStatus())
                                                    .putExtra("shebeiID",faultsBeanList7.get(position).getDeviceId())
                                                    .putExtra("baozhangID",faultsBeanList7.get(position).getId()));
                                            break;

                                    }


                                }
                            });

                            linearLayoutManager.scrollToPositionWithOffset(pp+1,0);

                        }
                    });

                }

            }
        }).start();

        super.onResume();

    }

    private void clecnList(){
        if (faultsBeanList.size()>0){
            faultsBeanList.clear();
        }
        if (faultsBeanList0.size()>0){
            faultsBeanList0.clear();
        }
        if (faultsBeanList1.size()>0){
            faultsBeanList1.clear();
        }
        if (faultsBeanList2.size()>0){
            faultsBeanList2.clear();
        }
        if (faultsBeanList3.size()>0){
            faultsBeanList3.clear();
        }
        if (faultsBeanList4.size()>0){
            faultsBeanList4.clear();
        }
        if (faultsBeanList5.size()>0){
            faultsBeanList5.clear();
        }
        if (faultsBeanList6.size()>0){
            faultsBeanList6.clear();
        }
        if (faultsBeanList7.size()>0){
            faultsBeanList7.clear();
        }
    }

}
