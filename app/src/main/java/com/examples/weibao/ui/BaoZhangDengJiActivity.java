package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.adapters.BaoZhangAdapter1;
import com.examples.weibao.intface.ClickIntface;

import java.util.ArrayList;
import java.util.List;

public class BaoZhangDengJiActivity extends Activity {
    private RecyclerView recyclerView;
    private ZhaoPianAdapter zhaoPianAdapter=null;
    private List<String> stringList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_zhang_deng_ji);

        stringList=new ArrayList<>();
        stringList.add("jij");
        stringList.add("jij");


        initView();
    }

    private void initView() {
        recyclerView= (RecyclerView) findViewById(R.id.recy);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        zhaoPianAdapter=new ZhaoPianAdapter(stringList);
        recyclerView.setAdapter(zhaoPianAdapter);

    }


    private class ZhaoPianAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private List<String> datas;
        private ClickIntface clickIntface;

        public void setClickIntface(ClickIntface clickIntface){
            this.clickIntface=clickIntface;
        }

        public ZhaoPianAdapter(List<String> datas) {
            this.datas = datas;
        }
        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tupian_item,viewGroup,false);
            return new ViewHolder(view);
        }


        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            //  viewHolder.bianhao.setText(datas.get(position));

        }
        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.size();
        }
        //自定义的ViewHolder，持有每个Item的的所有界面元素
        class ViewHolder extends RecyclerView.ViewHolder {
            private TextView bianhao,ceshi ;


            private ViewHolder(View view){
                super(view);
                //  bianhao = (TextView) view.findViewById(R.id.bianhao);
                //  ceshi = (TextView) view.findViewById(R.id.ceshi);


            }
        }
    }
}
