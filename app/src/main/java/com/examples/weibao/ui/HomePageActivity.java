package com.examples.weibao.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.examples.weibao.R;
import com.examples.weibao.fargments.Fragment1;
import com.examples.weibao.fargments.Fragment2;
import com.examples.weibao.fargments.Fragment3;
import com.examples.weibao.fargments.Fragment4;
import com.examples.weibao.views.ViewPagerFragmentAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;

public class HomePageActivity extends AppCompatActivity implements View.OnClickListener{
    private RelativeLayout r1,r2,r3,r4;
    private LinearLayout zhong_ll;
    private ViewPager mViewPager;
    private ViewPagerFragmentAdapter mViewPagerFragmentAdapter;
    private FragmentManager mFragmentManager;
    private String[] titleName = new String[] {"首页","维保","消息","资讯"};
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ImageView tabIm,tabIm2,tabIm3,tabIm4;
    private TextView tabText,tabText2,tabText3,tabText4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_home_page);
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



        initFragmetList();

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);

        initView();
        initViewPager();

    }

    private void initViewPager() {

        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
        updateBottomLinearLayoutSelect(0);
    }

    private void initFragmetList() {
        Fragment fragment1 = new Fragment1();
        Fragment fragment2 = new Fragment2();
        Fragment fragment3 = new Fragment3();
        Fragment fragment4 = new Fragment4();
        mFragmentList.add(fragment1);
        mFragmentList.add(fragment2);
        mFragmentList.add(fragment3);
        mFragmentList.add(fragment4);
    }

    private void initView() {
        mViewPager= (ViewPager) findViewById(R.id.viewpage);
        r1= (RelativeLayout) findViewById(R.id.homeLayout);
        r1.setOnClickListener(this);
        r2= (RelativeLayout) findViewById(R.id.chosenLayout);
        r2.setOnClickListener(this);
        r3= (RelativeLayout) findViewById(R.id.localLayout);
        r3.setOnClickListener(this);
        r4= (RelativeLayout) findViewById(R.id.settingLayout);
        r4.setOnClickListener(this);
        zhong_ll= (LinearLayout) findViewById(R.id.searchLayout);
        tabIm= (ImageView) findViewById(R.id.tabImg);
        tabIm2= (ImageView) findViewById(R.id.tabImg2);
        tabIm3= (ImageView) findViewById(R.id.tabImg3);
        tabIm4= (ImageView) findViewById(R.id.tabImg4);
        tabText= (TextView) findViewById(R.id.tabText);
        tabText2= (TextView) findViewById(R.id.tabText2);
        tabText3= (TextView) findViewById(R.id.tabText3);
        tabText4= (TextView) findViewById(R.id.tabText4);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLayout:
                mViewPager.setCurrentItem(0);
                updateBottomLinearLayoutSelect(0);
                break;
            case R.id.chosenLayout:
                mViewPager.setCurrentItem(1);
                updateBottomLinearLayoutSelect(1);
                break;
            case R.id.localLayout:
                mViewPager.setCurrentItem(2);
                updateBottomLinearLayoutSelect(2);
                break;
            case R.id.settingLayout:
                mViewPager.setCurrentItem(3);
                updateBottomLinearLayoutSelect(3);
                break;

            case R.id.searchLayout:
                //扫描

                break;
            default:
                break;
        }
    }
    private void  updateBottomLinearLayoutSelect(int position) {
        tabText.setTextColor(Color.parseColor("#8c050505"));
        tabText2.setTextColor(Color.parseColor("#8c050505"));
        tabText3.setTextColor(Color.parseColor("#8c050505"));
        tabText4.setTextColor(Color.parseColor("#8c050505"));

        switch (position){
            case 0:
                tabIm.setImageResource(R.drawable.nav_mt_hover);
                tabIm2.setImageResource(R.drawable.nav_msg);
                tabIm3.setImageResource(R.drawable.nav_news);
                tabIm4.setImageResource(R.drawable.nav_me);
                tabText.setTextColor(Color.parseColor("#FF1c97fe"));
                break;
            case 1:
                tabText2.setTextColor(Color.parseColor("#FF1c97fe"));
                tabIm.setImageResource(R.drawable.nav_mt);
                tabIm2.setImageResource(R.drawable.nav_msg_hover);
                tabIm3.setImageResource(R.drawable.nav_news);
                tabIm4.setImageResource(R.drawable.nav_me);

                break;
            case 2:
                tabText3.setTextColor(Color.parseColor("#FF1c97fe"));
                tabIm.setImageResource(R.drawable.nav_mt);
                tabIm2.setImageResource(R.drawable.nav_msg);
                tabIm3.setImageResource(R.drawable.nav_news_hover);
                tabIm4.setImageResource(R.drawable.nav_me);
                break;
            case 3:
                tabText4.setTextColor(Color.parseColor("#FF1c97fe"));
                tabIm.setImageResource(R.drawable.nav_mt);
                tabIm2.setImageResource(R.drawable.nav_msg);
                tabIm3.setImageResource(R.drawable.nav_news);
                tabIm4.setImageResource(R.drawable.nav_me_hover);
                break;
        }

    }
   private class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.d(TAG,"onPageScrooled");
        }
        @Override
        public void onPageSelected(int position) {

            updateBottomLinearLayoutSelect(position);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("home","onPageScrollStateChanged");
        }
    }
}
