package com.examples.weibao.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.FaultsBean;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.DateUtils;

import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */

public class BaoZhangAdapter3 extends RecyclerView.Adapter<BaoZhangAdapter3.ViewHolder> {
    private List<FaultsBean> datas;
    private ClickIntface clickIntface;
    private DevicesBeanDao devicesBeanDao=null;
    private DevicesBean devicesBean=null;
    ;

    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public BaoZhangAdapter3(List<FaultsBean> datas) {
        this.datas = datas;
        devicesBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();

    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.baozhang_item3,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        viewHolder.weizhi.setText("位置:"+datas.get(position).getAddress());
        devicesBean=devicesBeanDao.load((long) datas.get(position).getDeviceId());
        if (devicesBean!=null){
            viewHolder.shebei.setText("设备:"+devicesBean.getName());
            viewHolder.bianhao.setText("编号:"+devicesBean.getDeviceNum());
            viewHolder.shijian.setText("报障时间:"+DateUtils.time(devicesBean.getFailureTime()+""));
        }


    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }
    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bianhao,shebei,weizhi,shijian ;


        private ViewHolder(View view){
            super(view);
            bianhao = (TextView) view.findViewById(R.id.bianhao);
            shebei = (TextView) view.findViewById(R.id.shebei);
            weizhi = (TextView) view.findViewById(R.id.weizhi);
            shijian = (TextView) view.findViewById(R.id.shijian);


        }
    }
}
