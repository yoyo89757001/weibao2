package com.examples.weibao.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.R;
import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class SheBeiAdapter extends RecyclerView.Adapter<SheBeiAdapter.ViewHolder> {
    private List<String> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public SheBeiAdapter(List<String> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shebei_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.bianhao.setText(datas.get(position));
        viewHolder.r1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIntface.BackId(R.id.rl_1);
            }
        });
        viewHolder.r2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIntface.BackId(R.id.rl_2);
            }
        });
        viewHolder.r3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIntface.BackId(R.id.rl_3);
            }
        });
        viewHolder.r4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIntface.BackId(R.id.rl_4);
            }
        });
        viewHolder.r5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIntface.BackId(R.id.rl_5);
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
        private TextView bianhao,ceshi ;
        private RelativeLayout r1,r2,r3,r4,r5;

        private ViewHolder(View view){
            super(view);
            bianhao = (TextView) view.findViewById(R.id.bianhao);
            ceshi = (TextView) view.findViewById(R.id.ceshi);
            r1= (RelativeLayout) view.findViewById(R.id.rl_1);
            r2= (RelativeLayout) view.findViewById(R.id.rl_2);
            r3= (RelativeLayout) view.findViewById(R.id.rl_3);
            r4= (RelativeLayout) view.findViewById(R.id.rl_4);
            r5= (RelativeLayout) view.findViewById(R.id.rl_5);


        }
    }
}
