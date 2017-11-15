package com.examples.weibao.adapters;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.BaogaoBeans.BaoGaoBean;
import com.examples.weibao.DownloadService.DownloadService;
import com.examples.weibao.R;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.ui.ChaKanShiShiBaoGaoActivity;
import com.examples.weibao.utils.OpenFiles;
import com.sdsmdg.tastytoast.TastyToast;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class BaoGaoAdapter2 extends RecyclerView.Adapter<BaoGaoAdapter2.ViewHolder> {
    private List<BaoGaoBean.ObjectsBean> datas;
    private ClickIntface clickIntface;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public BaoGaoAdapter2(List<BaoGaoBean.ObjectsBean> datas) {
        this.datas = datas;
    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baogao2_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.t1.setText(datas.get(position).getItemName());

    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView t1 ;


        private ViewHolder(View view){
            super(view);
            t1 = (TextView) view.findViewById(R.id.t1);


        }
    }


}
