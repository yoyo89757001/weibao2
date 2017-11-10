package com.examples.weibao.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.BenDiMenusBean;
import com.examples.weibao.allbeans.BenDiMenusBeanDao;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.dialogs.ZhuangTaiXuanZeDialog;
import com.examples.weibao.intface.ClickIntface;
import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */
//维保项和测试项的 Adapter
public class SheBei2Adapter extends BaseAdapter {
    private List<MenusBean> datas;
    private ClickIntface clickIntface;
    private LayoutInflater mInflater;//得到一个LayoutInfalter对象用来导入布局
    private List<MenusBean> fuWuQiBeanList;
    private BenDiMenusBeanDao benDiMenusBeanDao=null;
    private Context context;
    private MenusBeanDao menusBeanDao=null;
    private long shebeiId=0;

    /*构造函数*/
    public SheBei2Adapter(Context context, List<MenusBean> fuWuQiBeanList, MenusBeanDao menusBeanDao,Long id) {
        this.mInflater = LayoutInflater.from(context);
        this.fuWuQiBeanList=fuWuQiBeanList;
        this.context=context;
        this.menusBeanDao=menusBeanDao;
        this.shebeiId=id;
        benDiMenusBeanDao=MyAppLaction.myAppLaction.getDaoSession().getBenDiMenusBeanDao();
    }



    public void gengxin(){
        if (fuWuQiBeanList.size()>0){
            fuWuQiBeanList.get(0).setPageNum("qq");
            this.notifyDataSetChanged();
        }

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
            convertView = mInflater.inflate(R.layout.shebei_item2,null);
            holder = new ViewHolder();
                    /*得到各个控件的对象*/
            holder.name = (TextView) convertView.findViewById(R.id.name);
            holder.item= (RelativeLayout) convertView.findViewById(R.id.jjj);
            holder.zhuangtai = (TextView) convertView.findViewById(R.id.zhuangtai);
            convertView.setTag(holder);//绑定ViewHolder对象
        }
        else{
            holder = (ViewHolder)convertView.getTag();//取出ViewHolder对象
        }
            /*设置TextView显示的内容，即我们存放在动态数组中的数据*/
        holder.name.setText(fuWuQiBeanList.get(position).getName());
        BenDiMenusBean bb=benDiMenusBeanDao.queryBuilder().where(BenDiMenusBeanDao.Properties.MensuId.eq(fuWuQiBeanList.get(position).getId()),
                BenDiMenusBeanDao.Properties.ParentId.eq(shebeiId)).unique();
        if (bb!=null){
            holder.zhuangtai.setText(bb.getName());
        }

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Log.d("SheBei2Adapter", "点击"+position);
                List<MenusBean> menusBeanList=menusBeanDao.queryBuilder().where(MenusBeanDao.Properties.ParentId.eq(fuWuQiBeanList.get(position).getId())).list();
                if (menusBeanList!=null){
                   // Log.d("SheBei2Adapter", "menusBeanList.size():" + menusBeanList.size());
                    int s= menusBeanList.size();
                    for (int i=0;i<s;i++){
                        menusBeanList.get(i).setPageNum("qq");
                    }
                    final ZhuangTaiXuanZeDialog dialog=new ZhuangTaiXuanZeDialog(context,menusBeanList,menusBeanDao,shebeiId);
                    dialog.setOnPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                         dialog.baocun(SheBei2Adapter.this,shebeiId);
                         dialog.dismiss();
                        }
                    });
                    dialog.setOnQuXiaoListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            dialog.dismiss();
                        }
                    });
                    dialog.show();

                }

            }
        });


        return convertView;
    }
    /*存放控件*/
    private class ViewHolder{
        private TextView name,zhuangtai;
        private RelativeLayout item;

    }

}
