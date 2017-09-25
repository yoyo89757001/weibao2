package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
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
    private ImageView tabIm;
    private TextView tabText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentManager = getSupportFragmentManager();
        setContentView(R.layout.activity_home_page);
        initFragmetList();

        mViewPagerFragmentAdapter = new ViewPagerFragmentAdapter(mFragmentManager,mFragmentList);

        initView();
        initViewPager();

    }

    private void initViewPager() {

        mViewPager.addOnPageChangeListener(new ViewPagetOnPagerChangedLisenter());
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setCurrentItem(0);
        tabText.setText(titleName[0]);
        updateBottomLinearLayoutSelect(true,false,false,false);
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
        r2= (RelativeLayout) findViewById(R.id.chosenLayout);
        r3= (RelativeLayout) findViewById(R.id.localLayout);
        r4= (RelativeLayout) findViewById(R.id.settingLayout);
        zhong_ll= (LinearLayout) findViewById(R.id.searchLayout);
        tabIm= (ImageView) findViewById(R.id.tabImg);
        tabText= (TextView) findViewById(R.id.tabText);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeLayout:
                mViewPager.setCurrentItem(0);
                updateBottomLinearLayoutSelect(true,false,false,false);
                break;
            case R.id.chosenLayout:
                mViewPager.setCurrentItem(1);
                updateBottomLinearLayoutSelect(false,true,false,false);
                break;
            case R.id.localLayout:
                mViewPager.setCurrentItem(2);
                updateBottomLinearLayoutSelect(false,false,true,false);
                break;
            case R.id.settingLayout:
                mViewPager.setCurrentItem(3);
                updateBottomLinearLayoutSelect(false,false,false,true);
                break;

            case R.id.searchLayout:

                break;
            default:
                break;
        }
    }
    private void updateBottomLinearLayoutSelect(boolean f, boolean s, boolean t,boolean g) {
        r1.setSelected(f);
        r2.setSelected(s);
        r3.setSelected(t);
        r4.setSelected(g);

    }
    class ViewPagetOnPagerChangedLisenter implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//            Log.d(TAG,"onPageScrooled");
        }
        @Override
        public void onPageSelected(int position) {
            Log.d("home","onPageSelected");
            boolean[] state = new boolean[titleName.length];
            state[position] = true;
            tabText.setText(titleName[position]);
            updateBottomLinearLayoutSelect(state[0],state[1],state[2],state[3]);
        }
        @Override
        public void onPageScrollStateChanged(int state) {
            Log.d("home","onPageScrollStateChanged");
        }
    }
}
