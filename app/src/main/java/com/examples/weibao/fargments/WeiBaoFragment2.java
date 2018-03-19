package com.examples.weibao.fargments;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.os.ResultReceiver;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.examples.weibao.BaogaoBeans.BaoGaoBean;
import com.examples.weibao.DownloadService.DownloadService;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.BaoGaoAdapter2;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.beans.FanHuiBean;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.ui.ChaKanShiShiBaoGaoActivity;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.OpenFiles;
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
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Permission;
import com.yanzhenjie.permission.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
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
public class WeiBaoFragment2 extends Fragment {
    private LRecyclerView lRecyclerView;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private BaoGaoAdapter2 adapter1=null;
    private List<BaoGaoBean.ObjectsBean> stringList;
    private DengLuBeanDao baoCunBeanDao=null;
    private DengLuBean baoCunBean=null;
    private String ss=null;
    private int dangQianYe=1;
    private int qingQiuYe=1;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
    public  final String PATH_DOC = Environment.getExternalStorageDirectory().getAbsolutePath()+ File.separator+ "docpathdoc"+File.separator;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private int p=0;



    public WeiBaoFragment2() {
        stringList=new ArrayList<>();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        baoCunBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        baoCunBean=baoCunBeanDao.load(123456L);




        View view=inflater.inflate(R.layout.fragment_wei_bao_fragment2, container, false);

        lRecyclerView= (LRecyclerView)view.findViewById(R.id.lrecyclerview);

        adapter1=new BaoGaoAdapter2(stringList);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(adapter1);
        linearLayoutManager=new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);
        lRecyclerView.setAdapter(lRecyclerViewAdapter);

        DividerDecoration divider = new DividerDecoration.Builder(getContext())
                .setHeight(R.dimen.default_divider_height2)
                .setPadding(R.dimen.default_divider_padding2)
                .setColorResource(R.color.transparent)
                .build();
        lRecyclerView.addItemDecoration(divider);
        //设置头部加载颜色
        lRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.blake ,android.R.color.white);
        lRecyclerView.setRefreshProgressStyle(ProgressStyle.LineSpinFadeLoader);
        lRecyclerView.setFooterViewColor(R.color.textcolor, R.color.blake ,android.R.color.white);
        //设置底部加载文字提示
        lRecyclerView.setFooterViewHint("拼命加载中","--------我是有底线的--------","网络不给力...");
        lRecyclerView.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);

        lRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                //下拉刷新
                dangQianYe=1;
                qingQiuYe=1;
                link_liebiao("",qingQiuYe);
            }
        });

        lRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                qingQiuYe++;
                //加载更多
                link_liebiao("",qingQiuYe);
            }
        });
        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
               // Log.d("WeiBaoFragment2", "点");

                 p=position;
                Intent intent33 = new Intent(getActivity(),ChaKanShiShiBaoGaoActivity.class);
                intent33.putExtra("url", "http://14.23.169.42:8090/upload/PlanReport/"+stringList.get(p).getReportFile());
              //  intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
               // intent33.putExtra("urlName",stringList.get(p).getReportFile().substring(7,stringList.get(p).getReportFile().length()));
                getActivity().startActivity(intent33);

//                AndPermission.with(getActivity())
//                        .requestCode(300)
//                        .permission(Permission.STORAGE,Permission.CAMERA)
//                        .callback(listener)
//                        .start();

            }
        });


        return view;
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 300) {
                Log.d("WeiBaoFragment2", "点2");
                File file = new File(PATH_DOC);
                if (!file.exists()) {
                    file.mkdirs();
                    // Log.d("ChaKanShiShiBaoGaoActiv", "创建目录");
                }


                Intent intent33 = new Intent(getActivity(), DownloadService.class);
                intent33.putExtra("url", "http://14.23.169.42:8090/upload/PlanReport/"+stringList.get(p).getReportFile());
                intent33.putExtra("receiver", new DownloadReceiver(new Handler()));
                intent33.putExtra("urlName",stringList.get(p).getReportFile().substring(7,stringList.get(p).getReportFile().length()));
                getActivity().startService(intent33);
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 200) {
                showMSG("存储授权失败",3);

            }
        }
    };


    @Override
    public void onResume() {
        lRecyclerView.forceToRefresh();
        super.onResume();

    }

    private void link_liebiao(String name,int pageNum) {
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();


        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("cmd","100");
            jsonObject.put("pageType",baoCunBean.getStatus());
            jsonObject.put("planId","");
            jsonObject.put("itemName","");
            jsonObject.put("planMonth","");
            jsonObject.put("itemDutyName","");
            jsonObject.put("safetyDutyName","");
            jsonObject.put("status","-1");
            jsonObject.put("pageNum",pageNum);
            jsonObject.put("pageSize","10");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", baoCunBean.getUserId()+"")
                .header("sign", Utils.encode("100"+baoCunBean.getStatus()+"-1"+pageNum+"10"+nonce+timestamp
                        +baoCunBean.getUserId()+Utils.signaturePassword))
                .post(body)
                .url(baoCunBean.getZhuji() + "reports.app");

        // step 3：创建 Call 对象
        Call   call = okHttpClient.newCall(requestBuilder.build());
        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (getActivity()!=null) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            if (stringList.size()!=0){
                                stringList.clear();
                            }
                            // lRecyclerView.refreshComplete(0);// REQUEST_COUNT为每页加载数量
                            adapter1.notifyDataSetChanged();

                            Toast tastyToast = TastyToast.makeText(getActivity(), "获取数据失败.", TastyToast.LENGTH_LONG, TastyToast.ERROR);
                            tastyToast.setGravity(Gravity.CENTER, 0, 0);
                            tastyToast.show();
                        }
                    });
                }
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    // Log.d("AllConnects", "识别结果返回"+response.body().string());
                    ss=body.string();
                    Log.d("Fragment2", ss);
                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    final BaoGaoBean zhaoPianBean=gson.fromJson(jsonObject,BaoGaoBean.class);
                    if (qingQiuYe==dangQianYe){

                        if (getActivity()!=null){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    if (stringList.size()!=0){
                                        stringList.clear();
                                    }
                                    stringList.addAll(zhaoPianBean.getObjects()!=null?zhaoPianBean.getObjects():new ArrayList<BaoGaoBean.ObjectsBean>());
                                    lRecyclerView.refreshComplete(stringList.size());// REQUEST_COUNT为每页加载数量
                                    adapter1.notifyDataSetChanged();

                                }
                            });
                        }


                    }else {

                        if (getActivity()!=null){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    int size=zhaoPianBean.getObjects().size();
                                    for (int i=0;i<size;i++){
                                        stringList.add(zhaoPianBean.getObjects().get(i));
                                    }

                                    lRecyclerView.refreshComplete(20);// REQUEST_COUNT为每页加载数量
                                    adapter1.notifyDataSetChanged();
                                }
                            });
                        }

                    }
                    if (zhaoPianBean.getObjects().size()==0 && stringList.size()>=20){
                        if (getActivity()!=null){
                            getActivity().runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    lRecyclerView.setNoMore(true);
                                }
                            });
                        }

                    }

                }catch (Exception e){
                    if (getActivity()!=null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                if (stringList.size()!=0){
                                    stringList.clear();
                                }
                                // lRecyclerView.refreshComplete(0);// REQUEST_COUNT为每页加载数量
                                adapter1.notifyDataSetChanged();
                            }
                        });
                    }

                    Log.d("WebsocketPushMsg", e.getMessage()+"");
                    try {
                        JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                        Gson gson=new Gson();
                        final FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                        if (zhaoPianBean.getDtoResult()==-33){
                            //登陆过期
                            if (getActivity()!=null){
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast tastyToast= TastyToast.makeText(getActivity(),"登陆过期,或账号在其它机器登陆,请重新登陆",TastyToast.LENGTH_LONG,TastyToast.ERROR);
                                        tastyToast.setGravity(Gravity.CENTER,0,0);
                                        tastyToast.show();
                                    }
                                });
                            }
                        }
                    }catch (Exception e1){
                        Log.d("Fragment1", "e1:" + e1);
                    }
                    if (getActivity()!=null){
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast tastyToast= TastyToast.makeText(getActivity(),"获取数据失败.",TastyToast.LENGTH_LONG,TastyToast.ERROR);
                                tastyToast.setGravity(Gravity.CENTER,0,0);
                                tastyToast.show();
                            }
                        });
                    }

                }
            }
        });
    }

    private class DownloadReceiver extends ResultReceiver {
        @SuppressLint("RestrictedApi")
        public DownloadReceiver(Handler handler) {
            super(handler);
        }
        @SuppressLint("RestrictedApi")
        @Override
        protected void onReceiveResult(int resultCode, Bundle resultData) {
            super.onReceiveResult(resultCode, resultData);
            if (resultCode == DownloadService.UPDATE_PROGRESS) {
                String  ididid=resultData.getString("ididid2");
                //   Log.d("DownloadReceiver", ididid);
                int progress = resultData.getInt("progress");
                // Log.d("ChaKanShiShiBaoGaoActiv", "progress:" + progress);

                if (progress == 100) {
                    Log.d("ChaKanShiShiBaoGaoActiv", "下载完成");
//                    Bundle bundle = new Bundle();
//                    bundle.putString("filePath", PATH_DOC+ididid);
//                    bundle.putString("tempPath", Environment.getExternalStorageDirectory().getPath());
//                    boolean result = mTbsReaderView.preOpen(parseFormat(ididid), false);
//                    if (result) {
//                        mTbsReaderView.openFile(bundle);
//                    }

                    try{
                        //  sdcard/test2.docx为本地doc文件的路径
                        Intent intent =  OpenFiles.getWordFileIntent(PATH_DOC+ididid,getActivity());
                        startActivity(intent);

                    }catch (Exception e){
                        Log.d("DownloadReceiver", e.getMessage()+"");
                        //没有安装第三方的软件会提示
                        showMSG("没有安装打开该文档的软件,请安装wps",4);
                    }
                }
            }
        }
    }

    private void showDialog(){
       getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(getActivity());
                    if (!getActivity().isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }
    private void dismissDialog(){
        getActivity().runOnUiThread(new Runnable() {
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

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(getActivity(),s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

}
