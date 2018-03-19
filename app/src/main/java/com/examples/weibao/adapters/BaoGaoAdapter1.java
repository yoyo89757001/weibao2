package com.examples.weibao.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.BaogaoBeans.BaoGaoBean;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.PlansBean;
import com.examples.weibao.allbeans.PlansBeanDao;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.ui.ChaKanShiShiBaoGaoActivity;
import com.examples.weibao.views.MYListView;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class BaoGaoAdapter1 extends RecyclerView.Adapter<BaoGaoAdapter1.ViewHolder> {
    private List<BaoGaoBean.ObjectsBean> datas;
    private ClickIntface clickIntface;
    private boolean isA;
    private Context context;
    private PlansBeanDao plansBeanDao=null;
    private List<PlansBean> plansBeanList=new ArrayList<>();


    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;

    }

    public BaoGaoAdapter1(Context context,List<BaoGaoBean.ObjectsBean> datas) {
        this.datas = datas;
        isA=false;
        this.context=context;
        plansBeanDao= MyAppLaction.myAppLaction.getDaoSession().getPlansBeanDao();
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baogao1_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        viewHolder.t1.setText(datas.get(position).getItemName());
        switch (datas.get(position).getStatus()){
            case 0:
                //待发布
                viewHolder.t2.setText("待发布");
                viewHolder.t2.setBackgroundResource(R.drawable.ju);
                break;
            case 10:
                //待审核
                viewHolder.t2.setText("待审核");
                viewHolder.t2.setBackgroundResource(R.drawable.ju);
                break;
            case 11:
                //审核通过
                viewHolder.t2.setText("审核通过");
                viewHolder.t2.setBackgroundResource(R.drawable.luse);
                break;
            case 12:
                //审核不通过
                viewHolder.t2.setText("审核不通过");
                viewHolder.t2.setBackgroundResource(R.drawable.ju);
                break;
            case 13:
                //确认通过
                viewHolder.t2.setText("确认通过");
                viewHolder.t2.setBackgroundResource(R.drawable.luse);
                break;
            case 14:
                //确认不通过
                viewHolder.t2.setText("确认不通过");
                viewHolder.t2.setBackgroundResource(R.drawable.ju);
                break;
        }
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isA){
                    viewHolder.myListView.setVisibility(View.GONE);
                    isA=false;
                }else {

                    List<PlansBean>  plansBeans= plansBeanDao.queryBuilder().where(PlansBeanDao.Properties.ItemId.eq(datas.get(position).getItem_id())).list();
                    if (plansBeans!=null){
                        if (plansBeanList.size()!=0){
                            plansBeanList.clear();
                        }
                        plansBeanList.addAll(plansBeans);
                        BaoGao22Adapter adapter=new BaoGao22Adapter(context,plansBeanList);

                        viewHolder.myListView.setAdapter(adapter);

                    }else {
                        Toast tastyToast= TastyToast.makeText(context,"该项目下暂时没有实时报告!",TastyToast.LENGTH_LONG,4);
                        tastyToast.setGravity(Gravity.CENTER,0,0);
                        tastyToast.show();
                    }
                    viewHolder.myListView.setVisibility(View.VISIBLE);
                    isA=true;
                }


            }
        });
        viewHolder.myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                context.startActivity(new Intent(context, ChaKanShiShiBaoGaoActivity.class)
                        .putExtra("planId",plansBeanList.get(position).getId())
                        .putExtra("styleee",plansBeanList.get(position).getStatus()));
            }
        });


    }


    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t1,t2 ;
        private MYListView myListView;


        private ViewHolder(View view){
            super(view);
            t1 = (TextView) view.findViewById(R.id.t1);
            t2 = (TextView) view.findViewById(R.id.t2);
            myListView= (MYListView) view.findViewById(R.id.mtlistview);


        }
    }
}
