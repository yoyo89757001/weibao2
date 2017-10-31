package com.examples.weibao.fargments;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.XiaoXiAdapter;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.beans.ItemIdBean;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.ui.XiaoXiActivity;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.recyclerview.ProgressStyle;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment2 extends Fragment {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
 //   private List<String> dataList;
    private XiaoXiAdapter taiZhangAdapter;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    private int dangQianYe = 1;
    private int qingQiuYe = 1;
    private List<ItemIdBean.ObjectsBean> objectsBeanList=new ArrayList<>();


    public Fragment2() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);

       View view=inflater.inflate(R.layout.fragment_fragment2, container, false);
        TextView title= (TextView)view.findViewById(R.id.title);
        title.setText("消息");
        ImageView left= (ImageView)view.findViewById(R.id.leftim);
        left.setVisibility(View.GONE);



        lRecyclerView= (LRecyclerView)view.findViewById(R.id.lrecyclerview);
        taiZhangAdapter=new XiaoXiAdapter(objectsBeanList,getActivity());
        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);

        linearLayoutManager=new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        //设置头部加载颜色
        lRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.blake, android.R.color.white);
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lRecyclerView.setFooterViewColor(R.color.textcolor, R.color.blake, android.R.color.white);
        //设置底部加载文字提示
        lRecyclerView.setFooterViewHint("拼命加载中", "--------没有更多了--------", "网络不给力...");
        lRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.transparent)
                .build();
        lRecyclerView.addItemDecoration(divider);


        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                startActivity(new Intent(getContext(),XiaoXiActivity.class).putExtra("idid",objectsBeanList.get(position).getId()));

            }
        });


        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                Log.d("Fragment2", "下拉刷新");
                dangQianYe = 1;
                qingQiuYe = 1;
                link(qingQiuYe);

            }
        });

        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                qingQiuYe++;
                //加载更多
                link(qingQiuYe);


            }
        });


        link(1);
        return view;
    }

    private void link(int pageNum) {
      //  showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();


        String nonce= Utils.getNonce();
        String timestamp=Utils.getTimestamp();


//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd",100);

            //  jsonObject.put("pageSize",10044);
              jsonObject.put("pageSize",15);
              jsonObject.put("pageNum",pageNum);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+"15"+pageNum+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(dengLuBean.getZhuji() + "items.app");



        // step 3：创建 Call 对象
        call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
              //  dismissDialog();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (objectsBeanList.size() != 0) {
                                objectsBeanList.clear();
                            }
                            // lRecyclerView.refreshComplete(0);// REQUEST_COUNT为每页加载数量
                            taiZhangAdapter.notifyDataSetChanged();

                           showMSG("请求数据失败",3);
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
              //  dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string().trim();
                    Log.d("InFoActivity", "ss" + ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    final ItemIdBean zhaoPianBean=gson.fromJson(jsonObject,ItemIdBean.class);
                    if (qingQiuYe == dangQianYe) {

                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (objectsBeanList.size() != 0) {
                                        objectsBeanList.clear();
                                    }
                                    objectsBeanList.addAll(zhaoPianBean.getObjects() != null ? zhaoPianBean.getObjects() : new ArrayList<ItemIdBean.ObjectsBean>());
                                    lRecyclerView.refreshComplete(objectsBeanList.size());// REQUEST_COUNT为每页加载数量
                                    taiZhangAdapter.notifyDataSetChanged();
                                    //   Log.d("Fragment3", "d进来得到");

                                }
                            });
                        }


                    } else {


                        if (getActivity() != null) {
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int size = zhaoPianBean.getObjects().size();
                                    for (int i = 0; i < size; i++) {
                                        objectsBeanList.add(zhaoPianBean.getObjects().get(i));
                                    }
                                    lRecyclerView.refreshComplete(15);// REQUEST_COUNT为每页加载数量
                                    taiZhangAdapter.notifyDataSetChanged();

                                }
                            });
                        }

                    }
                    if (zhaoPianBean.getObjects().size() == 0 && objectsBeanList.size() >= 15) {

                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                lRecyclerView.setNoMore(true);
                            }
                        });

                    }

                }catch (Exception e){
                    if (getActivity() != null) {
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (objectsBeanList.size() != 0) {
                                    objectsBeanList.clear();
                                }
                                // lRecyclerView.refreshComplete(0);// REQUEST_COUNT为每页加载数量
                                taiZhangAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }


    private void showDialog(){
        if (getActivity()!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (tiJIaoDialog==null){
                        tiJIaoDialog=new TiJIaoDialog(getContext());
                        if (!getActivity().isFinishing())
                            tiJIaoDialog.show();
                    }
                }
            });
        }

    }
    private void dismissDialog(){
        if (getActivity()!=null) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (tiJIaoDialog != null && tiJIaoDialog.isShowing()) {
                        tiJIaoDialog.dismiss();
                        tiJIaoDialog = null;
                    }
                }
            });
        }
    }

    private void showMSG(final String s,final int i){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(getContext(),s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

    @Override
    public void onDestroyView() {
        if (call!=null)
        call.cancel();
        super.onDestroyView();

    }



}
