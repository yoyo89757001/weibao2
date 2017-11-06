package com.examples.weibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.allbeans.DevicesBean;
import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */


public class XuanZeSheBeiAdapter extends BaseAdapter {

    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private List<DevicesBean> fuWuQiBeanList;


    /*构造函数*/
    public XuanZeSheBeiAdapter(Context context, List<DevicesBean> fuWuQiBeanList) {
        this.mInflater = LayoutInflater.from(context);
        this.fuWuQiBeanList=fuWuQiBeanList;
    }

    @Override
    public int getCount() {

        return fuWuQiBeanList.size();//返回数组的长度
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }
    /*书中详细解释该方法*/
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        //观察convertView随ListView滚动情况

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.xuanzeshebei_item,null);
            holder = new ViewHolder();
                    /*得到各个控件的对象*/
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.xianghao = (TextView) convertView.findViewById(R.id.xinghao);
            convertView.setTag(holder);//绑定ViewHolder对象
        }
        else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        holder.name.setText("设备名:"+fuWuQiBeanList.get(position).getName());
        holder.xianghao.setText("型号:"+fuWuQiBeanList.get(position).getDeviceNum());
        return convertView;
    }
    /*存放控件*/
   private class ViewHolder{
        public TextView name,xianghao;
      //  public ImageView text;

    }
}

