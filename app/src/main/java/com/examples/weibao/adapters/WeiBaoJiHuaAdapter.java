package com.examples.weibao.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.allbeans.PlansBean;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.DateUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class WeiBaoJiHuaAdapter extends RecyclerView.Adapter<WeiBaoJiHuaAdapter.ViewHolder> {
    private List<PlansBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public WeiBaoJiHuaAdapter(List<PlansBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.weibaojihua_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.time.setText(DateUtils.time(datas.get(position).getModifyTime()+""));
        viewHolder.jihua.setText(datas.get(position).getArea()+datas.get(position).getPlanMonth()+"月份计划");

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView time,jihua ;


        private ViewHolder(View view){
            super(view);
            time= (TextView) view.findViewById(R.id.time);
            jihua= (TextView) view.findViewById(R.id.jihua);

        }
    }
}
