package com.examples.weibao.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.intface.ClickIntface;

import java.util.List;

/**
 * Created by Administrator on 2017/10/9.
 */


public class YiChangAdapter extends BaseAdapter {

    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private List<MenusBean> fuWuQiBeanList;

    private ClickIntface clickIntface;


    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    /*构造函数*/
    public YiChangAdapter(Context context, List<MenusBean> fuWuQiBeanList) {
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
        final ViewHolder holder;
        //观察convertView随ListView滚动情况

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.yczhuangtai_iem,null);
            holder = new ViewHolder();
                    /*得到各个控件的对象*/
            holder.title = (TextView) convertView.findViewById(R.id.dizhi);
            holder.imageview = (ImageView) convertView.findViewById(R.id.yuan);
            convertView.setTag(holder);//绑定ViewHolder对象
        }
        else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        holder.title.setText(fuWuQiBeanList.get(position).getName());
        if (fuWuQiBeanList.get(position).getPageNum()!=null && fuWuQiBeanList.get(position).getPageNum().equals("ww")){
            holder.imageview.setImageResource(R.drawable.ic_selected);
        }else {
            holder.imageview.setImageResource(R.drawable.ic_select);
        }
//        convertView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                clickIntface.BackId(position);
//            }
//        });


        return convertView;
    }
    /*存放控件*/
   private class ViewHolder{
        public TextView title;
        public ImageView imageview;

    }

}

