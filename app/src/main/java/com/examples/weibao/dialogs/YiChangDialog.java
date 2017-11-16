package com.examples.weibao.dialogs;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.SheBei2Adapter;
import com.examples.weibao.adapters.YiChangAdapter;
import com.examples.weibao.allbeans.BenDiMenusBeanDao;
import com.examples.weibao.allbeans.DetectionsBean;
import com.examples.weibao.allbeans.MenusBeanDao;
import com.examples.weibao.intface.ClickIntface;

import java.util.List;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class YiChangDialog extends Dialog   {
    private TextView que,qu;
    private ListView listView;
    private YiChangAdapter adapter;
    private List<DetectionsBean> menusBeanList=null;
    private MenusBeanDao menusBeanDao=null;
    private Context context;
    private BenDiMenusBeanDao benDiMenusBeanDao=null;
    private int p=-1;
    private long sheBeiId=0;
   // private ClickIntface clickIntface;
    private long lv4Id=-1;


    public YiChangDialog(Context context, List<DetectionsBean> menusBeanList, MenusBeanDao menusBeanDao,long shedianId) {
        super(context, R.style.dialog_style2);
        this.menusBeanList=menusBeanList;
        this.menusBeanDao=menusBeanDao;
        this.context=context;
        this.sheBeiId=shedianId;
        benDiMenusBeanDao= MyAppLaction.myAppLaction.getDaoSession().getBenDiMenusBeanDao();
        setCustomDialog();
    }

//    public void setClickIntface(ClickIntface clickIntface){
//        this.clickIntface=clickIntface;
//    }


    //4个数据的
    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.yichang, null);
        que= (TextView) mView.findViewById(R.id.queding);
        qu= (TextView) mView.findViewById(R.id.quxiao);
        listView= (ListView) mView.findViewById(R.id.listview);
        adapter=new YiChangAdapter(getContext(),menusBeanList,sheBeiId);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                p=position;
                lv4Id=menusBeanList.get(position).getId();
               int s= menusBeanList.size();
               for (int i=0;i<s;i++){
                  menusBeanList.get(i).setPageNum("qq");
               }
                menusBeanList.get(position).setPageNum("ww");
                adapter.notifyDataSetChanged();


            }

        });
        super.setContentView(mView);


    }

    public long  getLv4Id(){
        return lv4Id;
    }

    public void baocun(SheBei2Adapter adapter){
        if (p!=-1){

//           BenDiMenusBean gg= benDiMenusBeanDao.queryBuilder().where(BenDiMenusBeanDao.Properties.MensuId.eq(menusBeanList.get(p).getParentId())).unique();
//            if (gg==null){
//                BenDiMenusBean benDiMenusBean=new BenDiMenusBean();
//                benDiMenusBean.setId(System.currentTimeMillis());
//                benDiMenusBean.setMensuId(menusBeanList.get(p).getParentId());
//                benDiMenusBean.setIsQiTa(false);
//                benDiMenusBean.setIsYiChang(false);
//                benDiMenusBean.setName(menusBeanList.get(p).getName());
//               benDiMenusBeanDao.insert(benDiMenusBean);
//
//            }else {
//                BenDiMenusBean benDiMenusBean=new BenDiMenusBean();
//                benDiMenusBean.setId(gg.getId());
//                benDiMenusBean.setMensuId(menusBeanList.get(p).getParentId());
//                benDiMenusBean.setIsQiTa(false);
//                benDiMenusBean.setIsYiChang(false);
//                benDiMenusBean.setName(menusBeanList.get(p).getName());
//                benDiMenusBeanDao.update(benDiMenusBean);

         //   }
           // adapter.gengxin();
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
