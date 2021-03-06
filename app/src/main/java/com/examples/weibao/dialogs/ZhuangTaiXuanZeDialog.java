package com.examples.weibao.dialogs;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.SheBei2Adapter;
import com.examples.weibao.adapters.ZhuangTaiXuanZeAdapter;
import com.examples.weibao.allbeans.BenDiMenusBean;
import com.examples.weibao.allbeans.BenDiMenusBeanDao;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DetectionsBean;
import com.examples.weibao.allbeans.DetectionsBeanDao;
import com.examples.weibao.allbeans.MenusBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.beans.WeiBaoCeShiCSBean;
import com.sdsmdg.tastytoast.TastyToast;
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
    private Activity context;
    private BenDiMenusBeanDao benDiMenusBeanDao=null;
    private int p=-1;
    private long shebeiId=0;
    private DetectionsBeanDao detectionsBeanDao=null;
  //  private List<DetectionsBean> detectionsBeanList=null;
    private String lv4Id="";
    private String neirong="";
    private DengLuBeanDao dengLuBeanDao=null;
    private DengLuBean dengLuBean=null;
    private TiJIaoDialog tiJIaoDialog=null;
    private boolean i3=false,i4=false;

    public ZhuangTaiXuanZeDialog(Activity context, List<MenusBean> menusBeanList, MenusBeanDao menusBeanDao, long shebeiId) {
        super(context, R.style.dialog_style2);
        this.menusBeanList=menusBeanList;
        this.menusBeanDao=menusBeanDao;
        this.context=context;
        this.shebeiId=shebeiId;
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
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

                Log.d("ZhuangTaiXuanZeDialog", "s:" + s);

               for (int i=0;i<s;i++){
                  menusBeanList.get(i).setPageNum("qq");
               }
                menusBeanList.get(position).setPageNum("ww");
                adapter.notifyDataSetChanged();

                if (menusBeanList.get(position).getName().contains("其")){
                  //  Log.d("ZhuangTaiXuanZeDialog", "menusBeanList.get(position).getId():" + menusBeanList.get(position).getId());

                    final QiTaDialog qiTaDialog=new QiTaDialog(context);
                    qiTaDialog.setOnPositiveListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            qiTaDialog.dismiss();
                        }
                    });
                    qiTaDialog.setOnQuXiaoListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            neirong=qiTaDialog.getContents();
                            i4=false;
                            i3=true;
                            qiTaDialog.dismiss();
                        }
                    });
                    qiTaDialog.show();
                }else {
                    List<DetectionsBean> menusBeanListss=detectionsBeanDao.queryBuilder().where(DetectionsBeanDao.Properties.WeibaoMenuId.eq( menusBeanList.get(position).getId())).list();
                    if (menusBeanListss!=null){

                        int s2= menusBeanListss.size();
                        //  Log.d("ZhuangTaiXuanZeDialog", "s2:" + s2);
                        if (s2>0) {
                            for (int i = 0; i < s2; i++) {
                                menusBeanListss.get(i).setPageNum("qq");
                            }

                            final YiChangDialog dialog = new YiChangDialog(context, menusBeanListss, menusBeanDao, shebeiId);
                            dialog.setOnPositiveListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    lv4Id = dialog.getLv4Id();
                                    i3 = false;
                                    i4 = true;
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

                }
            }

        });
        super.setContentView(mView);


    }

    public void baocun(SheBei2Adapter adapter, long shebeiId, WeiBaoCeShiCSBean ceShiCSBean){

      //  Log.d("Fragment1", "shebeiId:" + shebeiId);
      //  Log.d("Fragment1", "ceShiCSBean.getDeviceId():" + ceShiCSBean.toString());
    //    Log.d("ZhuangTaiXuanZeDialog", "menusBeanList.get(p).getParentId():" + menusBeanList.get(p).getParentId());
        if (p!=-1){
           BenDiMenusBean gg= benDiMenusBeanDao.queryBuilder().where(BenDiMenusBeanDao.Properties.MensuId.eq(menusBeanList.get(p).getParentId()),
                   BenDiMenusBeanDao.Properties.ParentId.eq(shebeiId)).unique();
            BenDiMenusBean benDiMenusBean=null;
            if (gg==null){
                benDiMenusBean=new BenDiMenusBean();
                benDiMenusBean.setId(System.currentTimeMillis());
                benDiMenusBean.setMensuId(menusBeanList.get(p).getParentId());
                benDiMenusBean.setIsQiTa(false);
                benDiMenusBean.setParentId(shebeiId);
                benDiMenusBean.setIsYiChang(false);
                benDiMenusBean.setName(menusBeanList.get(p).getName());
                benDiMenusBean.setPlanId(ceShiCSBean.getPlanId());
                benDiMenusBean.setMenuId2(ceShiCSBean.getMenuId());
                benDiMenusBean.setDeviceId((int) shebeiId);
                benDiMenusBean.setIsTijiao(false);
                benDiMenusBean.setMenuLevel1Id(ceShiCSBean.getMenuLevel1Id());
                benDiMenusBean.setMenuLevel3Id(ceShiCSBean.getMenuLevel3Id());
                benDiMenusBean.setMenuLevel4Id(menusBeanList.get(p).getId().intValue());
                if (i3){
                    benDiMenusBean.setRemark(neirong);
                }
                if (i4){
                    benDiMenusBean.setRemark(lv4Id);
                }

               benDiMenusBeanDao.insert(benDiMenusBean);
                //Log.d("Fragment1", benDiMenusBean.toString());

            }else {
                benDiMenusBean=new BenDiMenusBean();
                benDiMenusBean.setId(gg.getId());
                benDiMenusBean.setMensuId(menusBeanList.get(p).getParentId()); //维保项ID 用于查询
                benDiMenusBean.setIsQiTa(false);
                benDiMenusBean.setParentId(shebeiId);
                benDiMenusBean.setIsYiChang(false);
                benDiMenusBean.setIsTijiao(false);
                benDiMenusBean.setName(menusBeanList.get(p).getName());
                benDiMenusBean.setDeviceId((int) shebeiId);
                benDiMenusBean.setPlanId(ceShiCSBean.getPlanId());
                benDiMenusBean.setMenuId2(ceShiCSBean.getMenuId());//维保项ID 用于上传
                benDiMenusBean.setMenuLevel1Id(ceShiCSBean.getMenuLevel1Id());
                benDiMenusBean.setMenuLevel3Id(ceShiCSBean.getMenuLevel3Id());

                benDiMenusBean.setMenuLevel4Id(menusBeanList.get(p).getId().intValue());
                if (i3){

                    benDiMenusBean.setRemark(neirong);
                }
                if (i4){
                    benDiMenusBean.setRemark(lv4Id);
                }

                benDiMenusBeanDao.update(benDiMenusBean);
              //  Log.d("Fragment1", benDiMenusBean.toString());
            }
            adapter.gengxin();
         //  boolean ff = !Utils.getNetTypeName(context).equals("无网络");
//           if (ff){
//               link_save(benDiMenusBean);
//           }else {
//               showMSG("没有检测到网络,已经保存到本地,请在有网络时点击主界面的'上传本地维保信息'",4);
//           }

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

    private void showDialog(){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(context);
                    if (!context.isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }
    private void dismissDialog(){
        context.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog!=null && tiJIaoDialog.isShowing()){
                    tiJIaoDialog.dismiss();
                    tiJIaoDialog=null;
                }
            }
        });
    }

    private void showMSG(final String s,final int i){

       context.runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(context,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }




}
