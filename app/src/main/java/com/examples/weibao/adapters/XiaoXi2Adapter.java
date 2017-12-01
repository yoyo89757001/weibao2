package com.examples.weibao.adapters;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;
import com.bumptech.glide.request.RequestOptions;
import com.examples.weibao.R;
import com.examples.weibao.intface.ClickIntface;

import com.examples.weibao.xiaoxibeans.XiaoXiBean;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class XiaoXi2Adapter extends RecyclerView.Adapter<XiaoXi2Adapter.ViewHolder> {
    private List<XiaoXiBean.ObjectsBean> datas;
    private ClickIntface clickIntface;
    private Context context;
    private String actiom;
    private RequestOptions requestOptions = RequestOptions.circleCropTransform();

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public XiaoXi2Adapter(List<XiaoXiBean.ObjectsBean> datas, Context context,String actiom) {
        this.datas = datas;
        this.context=context;
        this.actiom=actiom;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.xiaoxi2_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (datas.get(position).getOperName().equals(actiom)){
            //自己的,显示在右边
            viewHolder.name2.setText(datas.get(position).getOperName());
            viewHolder.button2.setText(datas.get(position).getContent());
            Glide.with(context)
                    .load(R.drawable.tuijianyisheng)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                  //  .transform(new GlideCircleTransform(context,2, Color.parseColor("#ffffffff")))
                    .into(viewHolder.touxiang2);
            viewHolder.name2.setVisibility(View.VISIBLE);
            viewHolder.button2.setVisibility(View.VISIBLE);
            viewHolder.touxiang2.setVisibility(View.VISIBLE);
            viewHolder.name1.setVisibility(View.GONE);
            viewHolder.button1.setVisibility(View.GONE);
            viewHolder.touxiang.setVisibility(View.GONE);

        }else {
            viewHolder.name1.setVisibility(View.VISIBLE);
            viewHolder.button1.setVisibility(View.VISIBLE);
            viewHolder.touxiang.setVisibility(View.VISIBLE);
            viewHolder.name2.setVisibility(View.GONE);
            viewHolder.button2.setVisibility(View.GONE);
            viewHolder.touxiang2.setVisibility(View.GONE);

            viewHolder.name1.setText(datas.get(position).getOperName());
            viewHolder.button1.setText(datas.get(position).getContent());
            Glide.with(context)
                    .load(R.drawable.tuijianyisheng)
                    .apply(RequestOptions.bitmapTransform(new CircleCrop()))
                  //  .transform(new GlideCircleTransform(context,2, Color.parseColor("#ffffffff")))
                    .into(viewHolder.touxiang);
        }

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView name1,name2 ;
        private TextView button1,button2;
        private ImageView touxiang,touxiang2;


        private ViewHolder(View view){
            super(view);
            button1 = (TextView) view.findViewById(R.id.content1);
            button2 = (TextView) view.findViewById(R.id.content2);
            name1= (TextView) view.findViewById(R.id.name1);
            name2= (TextView) view.findViewById(R.id.name2);
            touxiang= (ImageView) view.findViewById(R.id.touxiang);
            touxiang2= (ImageView) view.findViewById(R.id.touxiang2);

        }
    }
}
