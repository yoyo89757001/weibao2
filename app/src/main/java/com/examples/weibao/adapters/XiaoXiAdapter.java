package com.examples.weibao.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.examples.weibao.R;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.views.GlideCircleTransform;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class XiaoXiAdapter extends RecyclerView.Adapter<XiaoXiAdapter.ViewHolder> {
    private List<String> datas;
    private ClickIntface clickIntface;
    private Context context;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public XiaoXiAdapter(List<String> datas, Context context) {
        this.datas = datas;
        this.context=context;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xiaoxi_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.content.setText(datas.get(position));
        Glide.with(context)
                .load(R.drawable.tuijianyisheng)
                .transform(new GlideCircleTransform(context,2, Color.parseColor("#ffffffff")))
                .into(viewHolder.touxiang);

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView content ,xiaoxi;
        private ImageView touxiang;


        private ViewHolder(View view){
            super(view);
            content = (TextView) view.findViewById(R.id.content);
            xiaoxi= (TextView) view.findViewById(R.id.xiaoxi);
            touxiang= (ImageView) view.findViewById(R.id.touxiang);

        }
    }
}
