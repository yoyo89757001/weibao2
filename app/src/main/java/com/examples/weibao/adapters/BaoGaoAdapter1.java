package com.examples.weibao.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.weibao.BaogaoBeans.BaoGaoBean;
import com.examples.weibao.R;
import com.examples.weibao.intface.ClickIntface;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class BaoGaoAdapter1 extends RecyclerView.Adapter<BaoGaoAdapter1.ViewHolder> {
    private List<BaoGaoBean.ObjectsBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public BaoGaoAdapter1(List<BaoGaoBean.ObjectsBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baogao1_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.t1.setText(datas.get(position).getPlanArea());
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


    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t1,t2 ;


        private ViewHolder(View view){
            super(view);
            t1 = (TextView) view.findViewById(R.id.t1);
            t2 = (TextView) view.findViewById(R.id.t2);


        }
    }
}
