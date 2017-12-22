package com.examples.weibao.fargments;



import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.utils.Utils;
import com.sdsmdg.tastytoast.TastyToast;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3 extends Fragment {
 //   private LRecyclerView lRecyclerView;
  //  private LRecyclerViewAdapter lRecyclerViewAdapter;
   // private LinearLayoutManager linearLayoutManager;
   // private BaoZhangJiLuAdapter taiZhangAdapter;
    private TiJIaoDialog tiJIaoDialog=null;
    private DengLuBean dengLuBean=null;
    private DengLuBeanDao dengLuBeanDao=null;
  //  private List<BaoZhangDengJiBean> plansBeanList=new ArrayList<>();
    private WebView webView;


    public Fragment3() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);

       View view=inflater.inflate(R.layout.fragment_fragment3, container, false);
       webView= (WebView) view.findViewById(R.id.web);
//        TextView t= (TextView) view.findViewById(R.id.title);
//        t.setText("资讯");
//        ImageView imageView= (ImageView) view.findViewById(R.id.leftim);
//        imageView.setVisibility(View.GONE);
//        lRecyclerView= (LRecyclerView)view.findViewById(R.id.lrecyclerview);
//        taiZhangAdapter=new BaoZhangJiLuAdapter(plansBeanList);
//        lRecyclerViewAdapter = new LRecyclerViewAdapter(taiZhangAdapter);
//
//        linearLayoutManager=new LinearLayoutManager(getActivity());
//        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
//        lRecyclerView.setLayoutManager(linearLayoutManager);
//        lRecyclerView.setAdapter(lRecyclerViewAdapter);
//        lRecyclerView.setPullRefreshEnabled(false);
//        lRecyclerView.setLoadMoreEnabled(false);
//
//        DividerDecoration divider = new DividerDecoration.Builder(getActivity())
//                .setHeight(R.dimen.default_divider_height)
//                .setPadding(R.dimen.default_divider_padding)
//                .setColorResource(R.color.transparent)
//                .build();
//        lRecyclerView.addItemDecoration(divider);
//
//
//        lRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
//            @Override
//            public void onItemClick(View view, int position) {
//
//                startActivity(new Intent(getActivity(),ZiXunActivity.class));
//
//            }
//        });
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);
        // 若加载的 html 里有JS 在执行动画等操作，会造成资源浪费（CPU、电量）
        // 在 onStop 和 onResume 里分别把 setJavaScriptEnabled() 给设置成 false 和 true 即可
        //支持插件
        // webSettings.setPluginsEnabled(true);
        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件
        webSettings.setAppCacheEnabled(true);
        //设置 缓存模式
        webSettings.setCacheMode(WebSettings.LOAD_DEFAULT);
        // 开启 DOM storage API 功能
        webSettings.setDomStorageEnabled(true);
        //其他细节操作
      //  webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式
        webView.setWebViewClient(new WebViewClient() {
            //覆盖shouldOverrideUrlLoading 方法
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        link_save();

        return view;
    }


    private void link_save() {
        showDialog();
        final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        final OkHttpClient okHttpClient= MyAppLaction.getOkHttpClient();

       // String jiami= Utils.jiami(mima).toUpperCase();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

//    /* form的分割线,自己定义 */
//        String boundary = "xx--------------------------------------------------------------xx";
//        JSONObject jsonObject = new JSONObject();
//        try {
//            jsonObject.put("cmd","100");
//            jsonObject.put("account",zhanghao);
//            jsonObject.put("password",jiami);
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
       // RequestBody body = RequestBody.create(JSON, jsonObject.toString());

        final Request.Builder requestBuilder = new Request.Builder()
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
               // .post(body)
                .get()
                .url(dengLuBean.getZhuji() + "getInfoListPage.do");

        // step 3：创建 Call 对象
      Call  call = okHttpClient.newCall(requestBuilder.build());
        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
                dismissDialog();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
             //   Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {
                    String strUrl = "http://14.23.169.42:8090/weibao/infoListClient.html";
                    CookieJar cookieJar = okHttpClient.cookieJar();
                    HttpUrl url = HttpUrl.parse(strUrl);
                    List<Cookie> cookies = cookieJar.loadForRequest(url);
                    StringBuilder sb = new StringBuilder();
                    for (Cookie cookie : cookies) {
                        sb.append(cookie.toString());
                        sb.append(";");
                    }
                    String strCookies = sb.toString();
                    android.webkit.CookieManager cookieManager= android.webkit.CookieManager.getInstance();
                    cookieManager.setCookie(strUrl, strCookies);

                   // synCookies(getActivity(),"http://14.23.169.42:8090/weibao/infoListClient.html");
                  //  ResponseBody body = response.body();
                 //   final String ss=body.string().trim();

                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                            webView.loadUrl("http://14.23.169.42:8090/weibao/infoListClient.html");
                        }
                    });

                   // Log.d("InFoActivity", "ss" + ss.substring(1000,ss.length()));
//                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
//                    Gson gson=new Gson();
//                    JsonObject jsonElement= jsonObject.get("account").getAsJsonObject();
//                    DengLuBean zhaoPianBean=gson.fromJson(jsonElement,DengLuBean.class);
//                    if (jsonObject.get("dtoResult").getAsString().equals("0")) {
//                        showMSG(jsonObject.get("dtoDesc").getAsString(), 4);
//                    }

                }catch (Exception e){

                    dismissDialog();
                    showMSG("获取数据失败",3);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

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
