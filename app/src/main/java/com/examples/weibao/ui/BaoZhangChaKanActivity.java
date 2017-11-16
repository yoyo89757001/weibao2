package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
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
import com.readystatesoftware.systembartint.SystemBarTintManager;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        shebeiID=getIntent().getIntExtra("shebeiID",0);
        baozhangID=getIntent().getLongExtra("baozhangID",0);
        faultsBeanDao= MyAppLaction.myAppLaction.getDaoSession().getFaultsBeanDao();
        dengLuBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDengLuBeanDao();
        dengLuBean=dengLuBeanDao.load(123456L);
        devicesBeanDao=MyAppLaction.myAppLaction.getDaoSession().getDevicesBeanDao();
        devicesBean=devicesBeanDao.load((long) shebeiID);
        faultsBean=faultsBeanDao.load(baozhangID);


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



    }

    private void initView() {
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
            paichashijian.setText(DateUtils.time(faultsBean.getPlanCheckTime()+""));
            huifuren.setText(faultsBean.getReplyUsername());
           chuliren.setText(faultsBean.getProcessUsername());

        }

        if (devicesBean != null) {
            shebei.setText(devicesBean.getName());

        }
        }




}
