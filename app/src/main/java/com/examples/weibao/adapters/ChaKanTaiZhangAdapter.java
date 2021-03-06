package com.examples.weibao.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.intface.ClickIntface;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class ChaKanTaiZhangAdapter extends RecyclerView.Adapter<ChaKanTaiZhangAdapter.ViewHolder> {
    private List<DevicesBean> datas;
    private ClickIntface clickIntface;
    private String s;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public ChaKanTaiZhangAdapter(List<DevicesBean> datas,String s) {
        this.datas = datas;
        this.s=s;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.chakantaizhang_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.content.setText(datas.get(position).getName());
        viewHolder.bianhao.setText("编号:"+datas.get(position).getDeviceNum());
        viewHolder.weizhi.setText("位置:"+s);

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bianhao,weizhi,content ;


        private ViewHolder(View view){
            super(view);
            content = (TextView) view.findViewById(R.id.content);
            bianhao = (TextView) view.findViewById(R.id.bianhao);
            weizhi = (TextView) view.findViewById(R.id.weizhi);

        }
    }
}
