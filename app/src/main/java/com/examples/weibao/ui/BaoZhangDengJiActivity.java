package com.examples.weibao.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.adapters.PopupWindowAdapter8;
import com.examples.weibao.adapters.XuanZeSheBeiAdapter;
import com.examples.weibao.allbeans.BaoZhangDengJiBean;
import com.examples.weibao.allbeans.BaoZhangDengJiBeanDao;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.ItemsBean;
import com.examples.weibao.allbeans.ItemsBeanDao;
import com.examples.weibao.allbeans.PlansBean;
import com.examples.weibao.allbeans.PlansBeanDao;
import com.examples.weibao.beans.FanHuiBean;
import com.examples.weibao.cookies.CookiesManager;
import com.examples.weibao.dialogs.TiJIaoDialog;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.DateUtils;
import com.examples.weibao.utils.FileUtil;
import com.examples.weibao.utils.GsonUtil;
import com.examples.weibao.utils.Utils;
import com.examples.weibao.views.MyDecoration;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;
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
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import top.zibin.luban.Luban;
import top.zibin.luban.OnCompressListener;

public class BaoZhangDengJiActivity extends Activity implements ClickIntface {
    @BindView(R.id.mingcheng)
    TextView mingcheng;
    @BindView(R.id.dizhi)
    EditText dizhi;
    @BindView(R.id.shebei)
    TextView shebei;
    @BindView(R.id.shebei_rl)
    RelativeLayout shebeiRl;
    @BindView(R.id.guzhang_et)
    EditText guzhangEt;
    @BindView(R.id.shijian)
    TextView shijian;
    @BindView(R.id.shijian_rl)
    RelativeLayout shijianRl;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.dianhua)
    EditText dianhua;
    @BindView(R.id.chakan)
    Button chakan;
    @BindView(R.id.tijiao)
    Button tijiao;
    private RecyclerView recyclerView;
    private ZhaoPianAdapter zhaoPianAdapter = null;
    private List<String> stringList;
    private DevicesBeanDao devicesBeanDao=null;
    private List<DevicesBean> devicesBeanList=null;
//    private PhotosBeanDao photosBeanDao=null;
    private BaoZhangDengJiBeanDao baoZhangDengJiBeanDao=null;
    private long shebeiID=-1;
    private File mSavePhotoFile=null;
    private boolean ff=false;
    private int jilu=0;
    private BaoZhangDengJiBean baoZhangDengJiBean=null;
    private long idid=0;
    private DengLuBeanDao dengLuBeanDao=null;
    private DengLuBean dengLuBean=null;
    private TiJIaoDialog tiJIaoDialog=null;
    private Call call=null;
    private String bianhao;
    private int itemId=-1;
    private ItemsBeanDao itemsBeanDao=null;
    private long ccc=0;
    private RelativeLayout rlrl;
    private PopupWindow popupWindow=null;
    private List<ItemsBean> itemsBeanList=new ArrayList<>();
    private int p1=-1;
    private int p2=-1;
    private PlansBeanDao plansBeanDao=null;
    private PlansBean plansBean=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        idid=getIntent().getLongExtra("idid",0);
        devicesBeanDao= MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();

        baoZhangDengJiBeanDao= MyAppLaction.myAppLaction.getDaoSession().getBaoZhangDengJiBeanDao();
        if (idid!=0)
        baoZhangDengJiBean= baoZhangDengJiBeanDao.load(idid);
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        itemsBeanDao=MyAppLaction.myAppLaction.getDaoSession().getItemsBeanDao();
        plansBeanDao=MyAppLaction.myAppLaction.getDaoSession().getPlansBeanDao();
        List<ItemsBean>  ii=itemsBeanDao.loadAll();
        if (ii!=null){
            itemsBeanList.addAll(ii);

        }

      //  photosBeanDao= MyAppLaction.myAppLaction.getDaoSession().getPhotosBeanDao();


        ff = !Utils.getNetTypeName(BaoZhangDengJiActivity.this).equals("无网络");

        setContentView(R.layout.activity_bao_zhang_deng_ji);
        ButterKnife.bind(this);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            //  getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            // 激活状态栏
            tintManager.setStatusBarTintEnabled(true);
            // enable navigation bar tint 激活导航栏
            //  tintManager.setNavigationBarTintEnabled(true);
            //设置系统栏设置颜色
            //tintManager.setTintColor(R.color.red);
            //给状态栏设置颜色
            tintManager.setStatusBarTintResource(R.color.lanse33);
            //Apply the specified drawable or color resource to the system navigation bar.
            //给导航栏设置资源
            // tintManager.setNavigationBarTintResource(R.color.dark_grey);
        }
        jilu=getIntent().getIntExtra("jilu",0);

        TextView title = (TextView) findViewById(R.id.title);
        title.setText("报障登记");
        ImageView left = (ImageView) findViewById(R.id.leftim);
        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        stringList = new ArrayList<>();
        stringList.add("pathOne");

        initView();
    }

    private void initView() {
        final ImageView ii9= (ImageView) findViewById(R.id.i19);
        rlrl= (RelativeLayout) findViewById(R.id.toprl);
        rlrl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                View contentView = LayoutInflater.from(BaoZhangDengJiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                popupWindow=new PopupWindow(contentView,600, 660);
                ListView listView= (ListView) contentView.findViewById(R.id.dddddd);
                PopupWindowAdapter8 adapter=new PopupWindowAdapter8(BaoZhangDengJiActivity.this,itemsBeanList);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        p1=position;
                        mingcheng.setText(itemsBeanList.get(position).getName());
                        dizhi.setText(itemsBeanList.get(position).getAddress());
                        popupWindow.dismiss() ;
                    }
                });
                listView.setAdapter(adapter);

                popupWindow.setFocusable(true);//获取焦点
                popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                popupWindow.setTouchable(true);//能够响应触摸事件
                popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                popupWindow.showAsDropDown(rlrl,ii9.getLeft()-600,0);
            }
        });
        recyclerView = (RecyclerView) findViewById(R.id.recy);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerView.setLayoutManager(manager);
        recyclerView.addItemDecoration(new MyDecoration(BaoZhangDengJiActivity.this, LinearLayoutManager.VERTICAL,10,R.color.transparent));


        shijian.setText(DateUtils.timet2(System.currentTimeMillis()+""));

        if (ff){
            tijiao.setText("提交");
        }else {
            tijiao.setText("保存到本地");
        }

        if (jilu==1){
            tijiao.setVisibility(View.GONE);
            chakan.setVisibility(View.GONE);

            dizhi.setText(baoZhangDengJiBean.getAddress());
            shebei.setText(baoZhangDengJiBean.getDeviceNumber());
            guzhangEt.setText(baoZhangDengJiBean.getRemark());
            shijian.setText(DateUtils.time(baoZhangDengJiBean.getFaultTime()+""));
            name.setText(dengLuBean.getName());
            dianhua.setText(baoZhangDengJiBean.getContactTel());

            String ss[] =baoZhangDengJiBean.getFaultImage().split(";");
           // int s=ss.length;
            ;
            for (String s1 : ss) {
                stringList.add(0, FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator + s1);
            }

        }else {
            name.setText(dengLuBean.getName());
        }



        zhaoPianAdapter = new ZhaoPianAdapter(stringList);
        zhaoPianAdapter.setClickIntface(this);
        recyclerView.setAdapter(zhaoPianAdapter);
    }

    @OnClick({R.id.shebei_rl, R.id.shijian_rl, R.id.chakan, R.id.tijiao})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.shebei_rl:
                try {
                if (p1!=-1) {

                    plansBean=plansBeanDao.queryBuilder().where(PlansBeanDao.Properties.ItemId.eq(itemsBeanList.get(p1).getId())).unique();
                    devicesBeanList = devicesBeanDao.queryBuilder().where(DevicesBeanDao.Properties.ItemId.eq(itemsBeanList.get(p1).getId())).list();
                    if (devicesBeanList != null && devicesBeanList.size()>0) {

                    View contentView = LayoutInflater.from(BaoZhangDengJiActivity.this).inflate(R.layout.xiangmu_po_item, null);
                    popupWindow = new PopupWindow(contentView, WindowManager.LayoutParams.MATCH_PARENT, 680);
                    ListView listView = (ListView) contentView.findViewById(R.id.dddddd);
                    XuanZeSheBeiAdapter adapter = new XuanZeSheBeiAdapter(BaoZhangDengJiActivity.this, devicesBeanList);
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            p2=position;
                            shebei.setText(devicesBeanList.get(position).getDeviceNum());
                            shebeiID = devicesBeanList.get(position).getId();
                            bianhao = devicesBeanList.get(position).getDeviceNum();
                            // itemId=devicesBeanList.get(position).getItemId();
                            // itemsBeanDao.load((long) itemId);

                            popupWindow.dismiss();
                        }
                    });
                    listView.setAdapter(adapter);

                    popupWindow.setFocusable(true);//获取焦点
                    popupWindow.setOutsideTouchable(true);//获取外部触摸事件
                    popupWindow.setTouchable(true);//能够响应触摸事件
                    popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000));//设置背景
                    popupWindow.showAsDropDown(shebeiRl, 0, 0);
                }else {
                        showMSG("该项目没有绑定的设备!",4);
                    }
                }else {
                    showMSG("请先选择项目",4);
                }
                }catch (Exception e){
            Log.d("BaoZhangDengJiActivity", e.getMessage());
        }
                break;
            case R.id.shijian_rl:

                Intent intent = new Intent(BaoZhangDengJiActivity.this, DatePickActivity.class);
                startActivityForResult(intent,2);

                break;
            case R.id.chakan:
                startActivity(new Intent(BaoZhangDengJiActivity.this,ChaKanBaoZhangJiLuActivity.class));
                break;
            case R.id.tijiao:
                 ccc=System.currentTimeMillis();

                String s1=mingcheng.getText().toString().trim();
                String s2=dizhi.getText().toString().trim();
                String s3=shebei.getText().toString().trim();
                String s4=guzhangEt.getText().toString().trim();
                String s5=shijian.getText().toString().trim();
                String s6=name.getText().toString().trim();
                String s7=dianhua.getText().toString().trim();


                if (!s1.equals("") && !s2.equals("") && !s3.equals("") && !s4.equals("") && !s1.equals("") && !s5.equals("") && !s6.equals("") && !s7.equals("") && p1!=-1 && p2!=-1){
                    //信息全了
                    BaoZhangDengJiBean dengJiBean=new BaoZhangDengJiBean();
                    dengJiBean.setId(ccc);
                    dengJiBean.setCompanyId(dengJiBean.getCompanyId());
                    dengJiBean.setAddress(s2);
                    dengJiBean.setDeviceId((int) shebeiID);
                    dengJiBean.setDeviceNumber(bianhao);
                    dengJiBean.setRemark(s4);
                    dengJiBean.setPlanId(itemsBeanList.get(p1).getId());
                    dengJiBean.setFaultTime(Long.parseLong(DateUtils.getTimes(s5)));
                    dengJiBean.setContactTel(s7);
                    dengJiBean.setIsTijiao(false);

                    if (stringList.size()>1){
                        StringBuilder buffer = new StringBuilder();
                        int ss=stringList.size()-1;
                        for (int i=0;i<ss;i++){
                            if (i<ss-1){

                                buffer.append(stringList.get(i).substring(stringList.get(i).lastIndexOf("/")+1,stringList.get(i).length()));
                                buffer.append(";");
                            }else {
                                buffer.append(stringList.get(i).substring(stringList.get(i).lastIndexOf("/")+1,stringList.get(i).length()));
                            }
                        }
                        dengJiBean.setFaultImage(buffer.toString());
                    }

                    baoZhangDengJiBeanDao.insert(dengJiBean);

                    if (!Utils.getNetTypeName(BaoZhangDengJiActivity.this).equals("无网络")){
                        //传图片
                        if (stringList.size()>1){
                            link_P1(stringList,dengJiBean);

                        }else {

                            showMSG("没有报障图片",4);

                        }

                  //  link_save();

                    }else {
                        showMSG("保存本地成功!",4);
                    }

                }else {
                    showMSG("信息没有填写完整",3);
                }



                break;
        }
    }

    private void showDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog==null){
                    tiJIaoDialog=new TiJIaoDialog(BaoZhangDengJiActivity.this);
                    if (!BaoZhangDengJiActivity.this.isFinishing())
                        tiJIaoDialog.show();
                }
            }
        });
    }
    private void dismissDialog(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (tiJIaoDialog!=null && tiJIaoDialog.isShowing()){
                    tiJIaoDialog.dismiss();
                    tiJIaoDialog=null;
                }
            }
        });
    }




    @Override
    protected void onDestroy() {
        if (call!=null)
            call.cancel();
        super.onDestroy();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK && requestCode == 2) {
            // 选择预约时间的页面被关闭
            String date = data.getStringExtra("date");
            shijian.setText(date);
        }
        if (resultCode == Activity.RESULT_OK && requestCode == 222) {

            try {
            handleImageOnKitkat(data);

        } catch (Exception e) {
            e.printStackTrace();
        }

        }
        if (resultCode == Activity.RESULT_OK && requestCode == 333) {
            try {

                Luban.with(this)
                        .load(mSavePhotoFile)   // 传人要压缩的图片列表
                        .ignoreBy(100)   // 忽略不压缩图片的大小
                        .setTargetDir(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator)  // 设置压缩后文件存储位置
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                                Log.d("BaoZhangDengJiActivity", "开始压缩");
                            }

                            @Override
                            public void onSuccess(File file) {
                                Log.d("BaoZhangDengJiActivity", "删除:" + mSavePhotoFile.delete());
                              //  Log.d("BaoZhangDengJiActivity", "file.length():" + file.length()+"  "+file.getAbsolutePath());
                                stringList.add(0,file.getAbsolutePath());
                                zhaoPianAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {

                               showMSG("压缩图片出现错误",4);
                            }
                        }).launch();    //启动压缩

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取压缩图片的options
     *
     * @return
     */
    public static BitmapFactory.Options getOptions(String path) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        options.inPreferredConfig = Bitmap.Config.RGB_565;
        options.inSampleSize = 4;      //此项参数可以根据需求进行计算
        options.inJustDecodeBounds = false;

        return options;
    }


    @Override
    public int BackId(int id,String s) {


        AndPermission.with(BaoZhangDengJiActivity.this)
                .requestCode(300)
                .permission(Permission.STORAGE,Permission.CAMERA)
                .callback(listener)
                .start();

        return 0;
    }

    private PermissionListener listener = new PermissionListener() {
        @Override
        public void onSucceed(int requestCode, List<String> grantedPermissions) {
            // 权限申请成功回调。

            // 这里的requestCode就是申请时设置的requestCode。
            // 和onActivityResult()的requestCode一样，用来区分多个不同的请求。
            if(requestCode == 300) {
                new AlertDialog.Builder(BaoZhangDengJiActivity.this).setItems(
                        new String[] { "拍摄照片", "从相册选择" },
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        String fn = System.currentTimeMillis()+"a.jpg";
                                        FileUtil.isExists(FileUtil.PATH, fn);
                                        mSavePhotoFile=new File( FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator + fn);

                                        Intent takePhotoIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                        Uri photoUri = FileProvider.getUriForFile(
                                                BaoZhangDengJiActivity.this,
                                                getPackageName() + ".fileprovider",
                                                mSavePhotoFile);
                                        takePhotoIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoUri);
                                        startActivityForResult(takePhotoIntent, 333);

                                        break;
                                    case 1:
                                        photoFromAlbum();
                                        break;
                                    default:
                                        break;
                                }
                            }
                        }).show();
            }
        }

        @Override
        public void onFailed(int requestCode, List<String> deniedPermissions) {
            // 权限申请失败回调。
            if(requestCode == 200) {
                showMSG("拍照授权失败",3);

            }
        }
    };

    private void photoFromAlbum() {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_PICK);
        intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 222);


    }



    private class ZhaoPianAdapter extends RecyclerView.Adapter<ZhaoPianAdapter.ViewHolder> {
        private List<String> datas;
        private ClickIntface clickIntface;

        public void setClickIntface(ClickIntface clickIntface) {
            this.clickIntface = clickIntface;
        }

        private ZhaoPianAdapter(List<String> datas) {
            this.datas = datas;
        }

        //创建新View，被LayoutManager所调用
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.tupian_item, viewGroup, false);
            return new ViewHolder(view);
        }


        //将数据与界面进行绑定的操作
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickIntface.BackId(position,null);
                }
            });
            if (datas.get(position).equals("pathOne")){
                viewHolder.tupian.setImageResource(R.drawable.icon_add);
                viewHolder.tupian.setPadding(60,60,60,60);
                viewHolder.shanchu.setVisibility(View.GONE);
            }else {
                Glide.with(BaoZhangDengJiActivity.this)
                        .load(datas.get(position))
                        //  .skipMemoryCache(true)
                        //  .diskCacheStrategy(DiskCacheStrategy.NONE)
                        //  .transform(new GlideCircleTransform(DengJiActivity.this,2, Color.parseColor("#ffffffff")))
                        .into(viewHolder.tupian);
                viewHolder.tupian.setPadding(20,20,20,20);
                viewHolder.shanchu.setVisibility(View.VISIBLE);
            }
            viewHolder.shanchu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    stringList.remove(position);
                    zhaoPianAdapter.notifyDataSetChanged();

                }
            });
           // Log.d("ZhaoPianAdapter", "stringList.size():" + stringList.size());
        }

        //获取数据的数量
        @Override
        public int getItemCount() {
            return datas.size();
        }

        //自定义的ViewHolder，持有每个Item的的所有界面元素
        class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView tupian,shanchu;


            private ViewHolder(View view) {
                super(view);
                  tupian = (ImageView) view.findViewById(R.id.tupian);
                  shanchu = (ImageView) view.findViewById(R.id.shanchu);

            }
        }
    }

    private void showMSG(final String s,final int i){

        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                Toast tastyToast= TastyToast.makeText(BaoZhangDengJiActivity.this,s,TastyToast.LENGTH_LONG,i);
                tastyToast.setGravity(Gravity.CENTER,0,0);
                tastyToast.show();

            }
        });
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private void handleImageOnKitkat(Intent data) {
        String imagePath = null;
        Uri uri = data.getData();
        if (DocumentsContract.isDocumentUri(this, uri)) {
            // 如果是document类型的Uri，则通过document id处理
            String docId = DocumentsContract.getDocumentId(uri);
            if ("com.android.providers.media.documents".equals(uri
                    .getAuthority())) {
                String id = docId.split(":")[1]; // 解析出数字格式的id
                String selection = MediaStore.Images.Media._ID + "=" + id;
                imagePath = getImagePath(
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
            } else if ("com.android.providers.downloads.documents".equals(uri
                    .getAuthority())) {
                Uri contentUri = ContentUris.withAppendedId(
                        Uri.parse("content://downloads/public_downloads"),
                        Long.valueOf(docId));
                imagePath = getImagePath(contentUri, null);
            }
        } else if ("content".equalsIgnoreCase(uri.getScheme())) {
            // 如果不是document类型的Uri，则使用普通方式处理
            imagePath = getImagePath(uri, null);
        }
        displayImage(imagePath); // 根据图片路径显示图片
       // System.err.println(imagePath);
    }

    private String getImagePath(Uri uri, String selection) {
        String path = null;
        // 通过uri和selection来获取真实的图片路径
        Cursor cursor = getContentResolver().query(uri, null, selection, null,
                null);
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                path = cursor.getString(cursor
                        .getColumnIndex(MediaStore.Images.Media.DATA));
            }
            cursor.close();
        }
        return path;
    }

    private void displayImage(String imagePath) {
        if (imagePath != null) {
            try {

                Luban.with(this)
                        .load(imagePath)   // 传人要压缩的图片列表
                        .ignoreBy(100)   // 忽略不压缩图片的大小
                        .setTargetDir(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator)  // 设置压缩后文件存储位置
                        .setCompressListener(new OnCompressListener() { //设置回调
                            @Override
                            public void onStart() {
                                Log.d("BaoZhangDengJiActivity", "相册图片开始压缩");
                            }

                            @Override
                            public void onSuccess(File file) {
                               // Log.d("BaoZhangDengJiActivity", "file.length():" + file.length()+"  "+file.getAbsolutePath());
                                stringList.add(0,file.getAbsolutePath());
                                zhaoPianAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onError(Throwable e) {

                                showMSG("压缩图片出现错误",4);
                            }
                        }).launch();    //启动压缩

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            showMSG("没有得到图片",4);
        }
    }

    public static final int TIMEOUT = 1000 * 180;
    private void link_P1(List<String> stringList, final BaoZhangDengJiBean dengJiBean) {
        showDialog();
        String nonce=Utils.getNonce();
        String timestamp=Utils.getTimestamp();

        //final MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        //http://192.168.2.4:8080/sign?cmd=getUnSignList&subjectId=jfgsdf
        OkHttpClient okHttpClient= new OkHttpClient.Builder()
                .writeTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .readTimeout(TIMEOUT, TimeUnit.MILLISECONDS)
                .cookieJar(new CookiesManager())
                .retryOnConnectionFailure(true)
                .build();
        MultipartBody mBody;
        MultipartBody.Builder builder=new MultipartBody.Builder().setType(MultipartBody.FORM);
        int ss=stringList.size()-1;
        for (int i=0;i<ss;i++){
            File file1 = new File(stringList.get(i));
            RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream") , file1);
            builder. addFormDataPart("file" , stringList.get(i).substring(stringList.get(i).lastIndexOf("/")+1,stringList.get(i).length()), fileBody1);
          //  Log.d("BaoZhangDengJiActivity", stringList.get(i).substring(stringList.get(i).lastIndexOf("/")+1,stringList.get(i).length())+">>>>");
        }
        JSONObject tijiao = null; 
       // JSONArray jsonArray=null;
        try {
             tijiao = new JSONObject();
            tijiao.put("status",0);
            tijiao.put("id",0);
            tijiao.put("accountId",dengLuBean.getUserId());
            tijiao.put("address",dizhi.getText().toString().trim());
            tijiao.put("companyId",dengLuBean.getCompanyId());
            tijiao.put("deviceId",shebeiID);
            tijiao.put("planId",itemsBeanList.get(p1).getId());
            tijiao.put("deviceNumber",bianhao);
            tijiao.put("faultTime",System.currentTimeMillis());
            tijiao.put("remark",guzhangEt.getText().toString().trim());
            tijiao.put("contactTel",dianhua.getText().toString());
            tijiao.put("faultImage",dengJiBean.getFaultImage());

           // jsonArray=new JSONArray();
           // jsonArray.put(tijiao);

           // jsonObject.put("cmd","100");
           // jsonObject.put("faults",jsonArray);
          //  Log.d("BaoZhangDengJiActivity", jsonObject.toString());
            //   jsonObject.put("password",jiami);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        builder.addFormDataPart("cmd","100");
        builder.addFormDataPart("fault",tijiao.toString());

        mBody=builder.build();
     //   Log.d("BaoZhangDengJiActivity", tijiao.toString());

//         /* 第一个要上传的file */
//       File file1 = new File(filename1);
//        RequestBody fileBody1 = RequestBody.create(MediaType.parse("application/octet-stream") , file1);
//        final String file1Name = System.currentTimeMillis()+"testFile1.jpg";
//
//
//        MultipartBody mBody = new MultipartBody.Builder().setType(MultipartBody.FORM)
//            /* 底下是上传了两个文件 */
//                .addFormDataPart("file" , file1Name , fileBody1)
//                  /* 上传一个普通的String参数 */
//                //  .addFormDataPart("subject_id" , subject_id+"")
//                //  .addFormDataPart("image_2" , file2Name , fileBody2)
//                .build();
        Request.Builder requestBuilder = new Request.Builder()
                // .header("Content-Type", "application/json")
                .header("nonce", nonce)
                .header("timestamp", timestamp)
                .header("userId", dengLuBean.getUserId()+"")
                .header("sign", Utils.encode("100"+nonce+timestamp
                        +dengLuBean.getUserId()+Utils.signaturePassword))
                .post(mBody)
                .url(dengLuBean.getZhuji() + "uploadFault.app");

        // step 3：创建 Call 对象
        Call call = okHttpClient.newCall(requestBuilder.build());

        //step 4: 开始异步请求
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
               dismissDialog();
                showMSG("上传图片出错",4);
                Log.d("AllConnects", "请求识别失败"+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                dismissDialog();
                Log.d("AllConnects", "请求识别成功"+call.request().toString());
                //获得返回体
                try {

                    ResponseBody body = response.body();
                    String ss=body.string();

                  //  link_save(dengJiBean);
                       Log.d("AllConnects", "aa   "+ss);

                    JsonObject jsonObject= GsonUtil.parse(ss).getAsJsonObject();
                    Gson gson=new Gson();
                    FanHuiBean zhaoPianBean=gson.fromJson(jsonObject,FanHuiBean.class);
                    if (zhaoPianBean.getDtoResult()==0){
                        dengJiBean.setIsTijiao(true);
                        baoZhangDengJiBeanDao.update(dengJiBean);

                        showMSG("提交成功",4);
                        finish();
                    }else if (zhaoPianBean.getDtoResult()==-33){
                        showMSG("登录过期,请重新登录",4);
                    }

                }catch (Exception e){
                    dismissDialog();
                    showMSG("上传图片出错",4);
                    Log.d("WebsocketPushMsg", e.getMessage());
                }
            }
        });

    }
}
