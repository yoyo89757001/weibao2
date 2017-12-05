package com.examples.weibao.ui;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.examples.weibao.MyAppLaction;
import com.examples.weibao.R;
import com.examples.weibao.allbeans.DengLuBean;
import com.examples.weibao.allbeans.DengLuBeanDao;
import com.examples.weibao.allbeans.DevicesBean;
import com.examples.weibao.allbeans.DevicesBeanDao;
import com.examples.weibao.allbeans.FaultsBean;
import com.examples.weibao.allbeans.FaultsBeanDao;
import com.examples.weibao.utils.DateUtils;
import com.examples.weibao.utils.FileUtil;
import com.jude.rollviewpager.HintView;
import com.jude.rollviewpager.RollPagerView;
import com.jude.rollviewpager.adapter.LoopPagerAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BaoZhangChaKanActivity extends Activity {
    private int shebeiID=0;
    private long baozhangID=0;
    private DengLuBeanDao dengLuBeanDao=null;
    private DengLuBean dengLuBean=null;
    private FaultsBeanDao faultsBeanDao=null;
    private FaultsBean faultsBean=null;
    private DevicesBeanDao devicesBeanDao=null;
    private DevicesBean devicesBean=null;
    private TextView shebei,baozhangtoubu_tv,bianhao,gongsi,weizhi,guzhangshijian,baozhangdianhua,
            lianxidianhua,shengyushijian,paichashijian,huifuren,chuliren;
    private EditText huifuneirong,chulineirong;
    private ImageView tupian;
    private RelativeLayout paicha_rl;
    private View view;
    private Button bt1,bt2;
    private RollPagerView rollPagerView=null;
    private  List<String> photoPathList=new ArrayList<>();
    private int status=-2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        status=getIntent().getIntExtra("status",-2);
        shebeiID=getIntent().getIntExtra("shebeiID",0);
        baozhangID=getIntent().getLongExtra("baozhangID",0);
        faultsBeanDao= MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        devicesBean=devicesBeanDao.load((long) shebeiID);
        faultsBean=faultsBeanDao.load(baozhangID);
        Log.d("BaoZhangChaKanActivity", "shebeiID:" + shebeiID);

        setContentView(R.layout.activity_bao_zhang_cha_kan);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }
        ImageView l= (ImageView) findViewById(R.id.leftim);
        l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        initView();
        initTuPian();


        if (dengLuBean!=null){
            switch (dengLuBean.getStatus()){
                case 0:
                    bt1.setVisibility(View.GONE);
                    view.setVisibility(View.GONE);
                    break;
                case 1:
                    bt1.setText("审核通过");
                    bt2.setText("审核不通过");
                    break;
                case 2:
                    bt1.setText("确认审核通过");
                    bt2.setText("确认审核不通过");
                    break;

            }
        }



    }

    private void initTuPian() {
        if (faultsBean!=null && faultsBean.getFaultImage()!=null){
            String s[] =faultsBean.getFaultImage().split(";");
            if (photoPathList.size()>0){
                photoPathList.clear();
            }
            for (String value : s) {
                photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator + value);
            }
//            photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator +"fdgg.jpg");
//            photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator +"fdgg.jpg");
//            photoPathList.add(FileUtil.SDPATH + File.separator + FileUtil.PATH + File.separator +"fdgg.jpg");
        }

        rollPagerView.setAdapter(new TestLoopAdapter(rollPagerView));


    }

    private class TestLoopAdapter extends LoopPagerAdapter {

        public TestLoopAdapter(RollPagerView viewPager) {
            super(viewPager);
        }

        @Override
        public View getView(ViewGroup container, int position) {
            ImageView view = new ImageView(container.getContext());
            try {

                view.setImageDrawable(getImageDrawable(photoPathList.get(position)));
            } catch (IOException e) {
                Log.d("test", e.getMessage());
            }

            view.setScaleType(ImageView.ScaleType.FIT_XY);
            view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            return view;
        }

        @Override
        public int getRealCount() {
            return photoPathList.size();
        }
    }
    /**
     * 将文件生成位图
     * @param path
     * @return
     * @throws IOException
     */
    public BitmapDrawable getImageDrawable(String path)
            throws IOException
    {
        //打开文件
        File file = new File(path);
        if(!file.exists())
        {
            return null;
        }

        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        byte[] bt = new byte[1024];

        //得到文件的输入流
        InputStream in = new FileInputStream(file);

        //将文件读出到输出流中
        int readLength = in.read(bt);
        while (readLength != -1) {
            outStream.write(bt, 0, readLength);
            readLength = in.read(bt);
        }

        //转换成byte 后 再格式化成位图
        byte[] data = outStream.toByteArray();
        Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);// 生成位图
        BitmapDrawable bd = new BitmapDrawable(getResources(),bitmap);

        return bd;
    }

    private void initView() {
        rollPagerView= (RollPagerView) findViewById(R.id.lunbo);
        view=findViewById(R.id.bt_view);
        bt1= (Button) findViewById(R.id.bt1);
        bt2= (Button) findViewById(R.id.bt2);
        shebei= (TextView) findViewById(R.id.shebei_tv);
        baozhangtoubu_tv= (TextView) findViewById(R.id.baozhang_tv);
        bianhao= (TextView) findViewById(R.id.bianhao);
        gongsi= (TextView) findViewById(R.id.gongsi);
        weizhi= (TextView) findViewById(R.id.weizhi);
        guzhangshijian= (TextView) findViewById(R.id.shijian_tv);
        baozhangdianhua= (TextView) findViewById(R.id.dianhua1);
        lianxidianhua= (TextView) findViewById(R.id.dianhua2);
        shengyushijian= (TextView) findViewById(R.id.shenyushijian);
        paichashijian= (TextView) findViewById(R.id.paichashijian);
        huifuren= (TextView) findViewById(R.id.renyuan);
        chuliren= (TextView) findViewById(R.id.chuliren);
        paicha_rl= (RelativeLayout) findViewById(R.id.paicha_rl);
        paicha_rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        huifuneirong= (EditText) findViewById(R.id.neirong_et);
        chulineirong= (EditText) findViewById(R.id.chulineirong);

        if (faultsBean!=null){
            bianhao.setText("编号:"+faultsBean.getDeviceNumber());
            weizhi.setText("位置:"+faultsBean.getAddress());
            guzhangshijian.setText("故障发现时间: "+ DateUtils.time(faultsBean.getFaultTime()+""));
            lianxidianhua.setText("联系电话:"+faultsBean.getContactTel());
            baozhangtoubu_tv.setText(faultsBean.getRemark());
            if (faultsBean.getPlanCheckTime()==0){
                paichashijian.setText("暂无");
            }else {
                paichashijian.setText(DateUtils.time(faultsBean.getPlanCheckTime()+""));
            }

            huifuren.setText(faultsBean.getReplyUsername());
           chuliren.setText(faultsBean.getProcessUsername());

        }

        if (devicesBean != null) {
            shebei.setText(devicesBean.getName());
        }
        switch (status){
            case 1:


                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 5:

                break;
            case 6:

                break;
            case 7:

                break;

        }

        }




}
