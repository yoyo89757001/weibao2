package com.examples.weibao.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.ShouKuanBean.ShouKuansBean;
import com.examples.weibao.intface.ClickIntface;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class XiangMuKuanAdapter extends RecyclerView.Adapter<XiangMuKuanAdapter.ViewHolder> {
    private List<ShouKuansBean.ObjectsBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public XiangMuKuanAdapter(List<ShouKuansBean.ObjectsBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xiangmukuan_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.content.setText(datas.get(position).getWeibaoCompanyName());
        if (datas.get(position).getItemAmount()>datas.get(position).getItemAmountReceived()){
            //未收完
            viewHolder.fff.setText("代收款");
            viewHolder.fff.setBackgroundResource(R.drawable.ju);
        }else {
            //已收
            viewHolder.fff.setText("已收款");
            viewHolder.fff.setBackgroundResource(R.drawable.luse);
        }


    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content,fff ;


        private ViewHolder(View view){
            super(view);
            content = (TextView) view.findViewById(R.id.content);
            fff = (TextView) view.findViewById(R.id.fff);

        }
    }
}
