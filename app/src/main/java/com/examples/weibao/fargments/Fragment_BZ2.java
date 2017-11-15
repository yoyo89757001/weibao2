package com.examples.weibao.fargments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.BaoZhangAdapter2;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.FaultsBean;
import com.examples.weibao.allbeans.FaultsBeanDao;
import com.examples.weibao.ui.BaoZhangChaKanActivity;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_BZ2 extends Fragment {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private BaoZhangAdapter2 adapter1=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private DengLuBean dengLuBean=null;
    private FaultsBeanDao faultsBeanDao=null;
    private List<FaultsBean> faultsBeanList=null;
    private DevicesBeanDao devicesBeanDao=null;
    private List<DevicesBean> devicesBeanList=null;

    public Fragment_BZ2() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        faultsBeanDao= MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        faultsBeanList=faultsBeanDao.loadAll();

        View view=inflater.inflate(R.layout.fragment_fragment__bz2, container, false);


        lRecyclerView= (LRecyclerView)view.findViewById(R.id.lrecyclerview);

        adapter1=new BaoZhangAdapter2(faultsBeanList==null ? new ArrayList<FaultsBean>():faultsBeanList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter1);
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerView.setLoadMoreEnabled(false);
        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.transparent)
                .build();
        lRecyclerView.addItemDecoration(divider);
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                startActivity(new Intent(getContext(), BaoZhangChaKanActivity.class)
                        .putExtra("shebeiID",faultsBeanList.get(position).getDeviceId())
                        .putExtra("baozhangID",faultsBeanList.get(position).getId()));
            }
        });

        return view;
    }

}
