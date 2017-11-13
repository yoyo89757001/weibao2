package com.examples.weibao.dialogs;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.SheBei2Adapter;
import com.examples.weibao.adapters.ZhuangTaiXuanZeAdapter;
import com.examples.weibao.allbeans.BenDiMenusBean;
import com.examples.weibao.allbeans.BenDiMenusBeanDao;
import com.examples.weibao.allbeans.DetectionsBean;
import com.examples.weibao.allbeans.DetectionsBeanDao;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.intface.ClickIntface;

import java.util.List;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class ZhuangTaiXuanZeDialog extends Dialog   {
    private TextView que,qu;
    private ListView listView;
    private ZhuangTaiXuanZeAdapter adapter;
    private List<MenusBean> menusBeanList=null;
    private MenusBeanDao menusBeanDao=null;
    private Context context;
    private BenDiMenusBeanDao benDiMenusBeanDao=null;
    private int p=-1;
    private long shebeiId=0;
    private DetectionsBeanDao detectionsBeanDao=null;
    private List<DetectionsBean> detectionsBeanList=null;

    public ZhuangTaiXuanZeDialog(Context context, List<MenusBean> menusBeanList, MenusBeanDao menusBeanDao,long shebeiId) {
        super(context, R.style.dialog_style2);
        this.menusBeanList=menusBeanList;
        this.menusBeanDao=menusBeanDao;
        this.context=context;
        this.shebeiId=shebeiId;
        benDiMenusBeanDao= MyAppLaction.myAppLaction.getDaoSession().getBenDiMenusBeanDao();
        detectionsBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDetectionsBeanDao();
        setCustomDialog();
    }




    //4个数据的
    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.queren_ll5, null);
        que= (TextView) mView.findViewById(R.id.queding);
        qu= (TextView) mView.findViewById(R.id.quxiao);
        listView= (ListView) mView.findViewById(R.id.listview);
        adapter=new ZhuangTaiXuanZeAdapter(getContext(),menusBeanList,shebeiId);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
               int s= menusBeanList.size();
               for (int i=0;i<s;i++){
                  menusBeanList.get(i).setPageNum("qq");
               }
                menusBeanList.get(position).setPageNum("ww");
                adapter.notifyDataSetChanged();

                if (menusBeanList.get(position).getName().equals("设备异常")){
                    Log.d("ZhuangTaiXuanZeDialog", "menusBeanList.get(position).getId():" + menusBeanList.get(position).getId());
                    List<DetectionsBean> menusBeanListss=detectionsBeanDao.queryBuilder().where(DetectionsBeanDao.Properties.WeibaoMenuId.eq( menusBeanList.get(position).getId())).list();
                    if (menusBeanListss!=null){
                        int s2= menusBeanListss.size();
                        Log.d("ZhuangTaiXuanZeDialog", "s2:" + s2);
                        for (int i=0;i<s2;i++){
                            menusBeanListss.get(i).setPageNum("qq");
                        }

                        final YiChangDialog dialog=new YiChangDialog(context,menusBeanListss,menusBeanDao,shebeiId);
                        dialog.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

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


                }else if (menusBeanList.get(position).getName().equals("其他")){
                        QiTaDialog qiTaDialog=new QiTaDialog(context);
                        qiTaDialog.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        qiTaDialog.setOnQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });
                        qiTaDialog.show();

                }
            }

        });
        super.setContentView(mView);


    }

    public void baocun(SheBei2Adapter adapter, long shebeiId){
        if (p!=-1){
            if (menusBeanList.get(p).getName().equals("设备异常")){

            }else if (menusBeanList.get(p).getName().equals("其它")){

            }else {

            }
           BenDiMenusBean gg= benDiMenusBeanDao.queryBuilder().where(BenDiMenusBeanDao.Properties.MensuId.eq(menusBeanList.get(p).getParentId()),
                   BenDiMenusBeanDao.Properties.ParentId.eq(shebeiId)).unique();
            if (gg==null){
                BenDiMenusBean benDiMenusBean=new BenDiMenusBean();
                benDiMenusBean.setId(System.currentTimeMillis());
                benDiMenusBean.setMensuId(menusBeanList.get(p).getParentId());
                benDiMenusBean.setIsQiTa(false);
                benDiMenusBean.setParentId(shebeiId);
                benDiMenusBean.setIsYiChang(false);
                benDiMenusBean.setName(menusBeanList.get(p).getName());
               benDiMenusBeanDao.insert(benDiMenusBean);

            }else {
                BenDiMenusBean benDiMenusBean=new BenDiMenusBean();
                benDiMenusBean.setId(gg.getId());
                benDiMenusBean.setMensuId(menusBeanList.get(p).getParentId());
                benDiMenusBean.setIsQiTa(false);
                benDiMenusBean.setParentId(shebeiId);
                benDiMenusBean.setIsYiChang(false);
                benDiMenusBean.setName(menusBeanList.get(p).getName());
                benDiMenusBeanDao.update(benDiMenusBean);

            }
            adapter.gengxin();
        }

    }


    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        que.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnQuXiaoListener(View.OnClickListener listener){
        qu.setOnClickListener(listener);
    }


}
