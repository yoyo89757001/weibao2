package com.examples.weibao.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.allbeans.DeviceRefBean;
import com.examples.weibao.allbeans.DeviceRefBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.beans.WeiBaoCeShiCSBean;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/10/3.
 */
//  设备的 Adapter
public class SheBeiAdapter extends RecyclerView.Adapter<SheBeiAdapter.ViewHolder>{
    private List<DevicesBean> datas;
    private ClickIntface clickIntface;
    private List<MenusBean> menusBeanList1=new ArrayList<>();
    private List<MenusBean> menusBeanList2=new ArrayList<>();
    private MenusBean menusBean=null;
    private MenusBeanDao menusBeanDao=null;
    private Activity context;
    private List<MenusBean> allMenusBeanList=null;
    private WeiBaoCeShiCSBean ceShiCSBean=null;
    private DeviceRefBeanDao deviceRefBeanDao=null;
    private Long xitongID;


    public void setClickIntface(ClickIntface clickIntface){
        this.clickIntface=clickIntface;
    }

    public SheBeiAdapter(List<DevicesBean> datas, MenusBeanDao menusBeanDao, Activity context, List<MenusBean> menusBeanList22, WeiBaoCeShiCSBean ceShiCSBean,Long xitongID) {
        this.datas = datas;
        this.menusBeanDao=menusBeanDao;
        this.context=context;
        this.ceShiCSBean=ceShiCSBean;
        this.xitongID=xitongID;
        allMenusBeanList=menusBeanList22;
        deviceRefBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDeviceRefBeanDao();
        int s=menusBeanList22.size();
        for (int i=0;i<s;i++){
            if (allMenusBeanList.get(i).getType()==1){
                menusBeanList1.add(allMenusBeanList.get(i));
            }else if (allMenusBeanList.get(i).getType()==2){
                menusBeanList2.add(allMenusBeanList.get(i));
            }
        }

    }
    //创建新View，被LayoutManager所调用
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shebei_item,viewGroup,false);
        return new ViewHolder(view);
    }
    //将数据与界面进行绑定的操作
    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {

        viewHolder.bianhao.setText(datas.get(position).getName()+" "+datas.get(position).getDeviceNum()+"");
      //  Log.d("SheBeiAdapter", "datas.get(position).getId():" + datas.get(position).getId());
        viewHolder.bianhao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickIntface.BackId(position,"s");
            }
        });
      //  if (allMenusBeanList.get(position).getType()==1){
           // menusBeanList1=menusBeanDao.queryBuilder().where(MenusBeanDao.Properties.ParentId.eq(datas.get(position).getId())).list();
          //  Log.d("SheBeiAdapter", "menusBeanList1.size():" + menusBeanList1.size());
       // ceShiCSBean.setDeviceId(datas.get(position).getId().intValue());
            WeiBaoCeShiCSBean csBean=new WeiBaoCeShiCSBean(ceShiCSBean.getId(),ceShiCSBean.getPlanId(),ceShiCSBean.getMenuLevel1Id(),ceShiCSBean.getMenuId(),ceShiCSBean.getMenuLevel3Id()
            ,ceShiCSBean.getMenuLevel4Id(),datas.get(position).getId().intValue(),ceShiCSBean.getRemark(),ceShiCSBean.getTestData(),ceShiCSBean.getCreateBy(),ceShiCSBean.getCreateTime());

     //   Log.d("SheBeiAdapter", csBean.toString());

            if (menusBeanList1!=null){
            SheBei2Adapter sheBei2Adapter=new SheBei2Adapter(context,menusBeanList1,menusBeanDao,datas.get(position).getId(),csBean,1);
            viewHolder.listView1.setAdapter(sheBei2Adapter);
           // fixListViewHeight(viewHolder.listView1);

            }
      //  }else {

         //   menusBeanList2=menusBeanDao.queryBuilder().where(MenusBeanDao.Properties.ParentId.eq(datas.get(position).getId())).list();
            if (menusBeanList2!=null){

                DeviceRefBean deviceRefBean= deviceRefBeanDao.queryBuilder().where(DeviceRefBeanDao.Properties.PlanId.eq(csBean.getPlanId()),
                        DeviceRefBeanDao.Properties.WeibaoMenuId.eq(xitongID),
                        DeviceRefBeanDao.Properties.DeviceId.eq(csBean.getDeviceId())).unique();
                //Log.d("SheBeiAdapter", "xitongID:" + xitongID);
                // Log.d("SheBeiAdapter", deviceRefBean.toString());
                if (deviceRefBean!=null && deviceRefBean.getIsDetection()==1){ //1显示
                    SheBei2Adapter sheBei2Adapter=new SheBei2Adapter(context,menusBeanList2,menusBeanDao,datas.get(position).getId(),csBean,2);
                    viewHolder.listView2.setAdapter(sheBei2Adapter);

                }else {
                    SheBei2Adapter sheBei2Adapter=new SheBei2Adapter(context,new ArrayList<MenusBean>(),menusBeanDao,datas.get(position).getId(),csBean,2);
                    viewHolder.listView2.setAdapter(sheBei2Adapter);
                }

              //  Log.d("SheBeiAdapter", menusBeanList2.get(position).getName());
            }

     //   }

        if (menusBeanList1==null || menusBeanList1.size()==0){
            viewHolder.weibaoxiang.setVisibility(View.GONE);
        }else {

            viewHolder.weibaoxiang.setVisibility(View.VISIBLE);
        }

        if (menusBeanList2==null || menusBeanList2.size()==0){
            viewHolder.ceshixiang.setVisibility(View.GONE);

        }else {

            DeviceRefBean deviceRefBean= deviceRefBeanDao.queryBuilder().where(DeviceRefBeanDao.Properties.PlanId.eq(csBean.getPlanId()),
                    DeviceRefBeanDao.Properties.WeibaoMenuId.eq(xitongID),
                    DeviceRefBeanDao.Properties.DeviceId.eq(csBean.getDeviceId())).unique();
            //Log.d("SheBeiAdapter", "xitongID:" + xitongID);
           // Log.d("SheBeiAdapter", deviceRefBean.toString());
            if (deviceRefBean!=null && deviceRefBean.getIsDetection()==1){ //1显示
                viewHolder.ceshixiang.setVisibility(View.VISIBLE);
            }else {
                viewHolder.ceshixiang.setVisibility(View.GONE);
            }

        }



    }
    //获取数据的数量
    @Override
    public int getItemCount() {
        return datas.size();
    }



    //自定义的ViewHolder，持有每个Item的的所有界面元素
      class ViewHolder extends RecyclerView.ViewHolder {
        private TextView bianhao,ceshi,weibaoxiang,ceshixiang;
        private ListView listView1,listView2;

        private ViewHolder(View view){
            super(view);
            bianhao = (TextView) view.findViewById(R.id.bianhao);
            ceshi = (TextView) view.findViewById(R.id.ceshi);
            weibaoxiang = (TextView) view.findViewById(R.id.weibaoxiang);
            ceshixiang = (TextView) view.findViewById(R.id.ceshixiang);
            listView1 = (ListView) view.findViewById(R.id.listview1);
            listView2 = (ListView) view.findViewById(R.id.listview2);


        }
    }

//    public void fixListViewHeight(ListView listView) {
//        // 如果没有设置数据适配器，则ListView没有子项，返回。
//        ListAdapter listAdapter = listView.getAdapter();
//        int totalHeight = 0;
//        if (listAdapter == null) {
//            return;
//        }
//        for (int index = 0, len = listAdapter.getCount(); index < len; index++) {
//            View listViewItem = listAdapter.getView(index , null, listView);
//            // 计算子项View 的宽高
//            listViewItem.measure(0, 0);
//            // 计算所有子项的高度和
//            totalHeight += listViewItem.getMeasuredHeight();
//        }
//
//        ViewGroup.LayoutParams params = listView.getLayoutParams();
//        // listView.getDividerHeight()获取子项间分隔符的高度
//        // params.height设置ListView完全显示需要的高度
//        params.height = totalHeight+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
//        listView.setLayoutParams(params);
//    }
}
