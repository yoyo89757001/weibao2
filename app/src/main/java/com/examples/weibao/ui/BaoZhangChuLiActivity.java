package com.examples.weibao.ui;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.fargments.Fragment_BZ1;
import com.examples.weibao.fargments.Fragment_BZ2;
import com.examples.weibao.fargments.Fragment_BZ3;
import com.examples.weibao.views.ViewPagerFragmentAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.util.ArrayList;
import java.util.List;

public class BaoZhangChuLiActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPagerFragmentAdapter mFragmentAdapter;
    private LinearLayout l1,l2,l3;
    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private TextView mTabChatTv, mTabContactsTv, mTabFriendTv;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private Fragment_BZ1 mChatFg;
    private Fragment_BZ2 mFriendFg;
    private Fragment_BZ3 mContactsFg;
    /**
     * ViewPager的当前选中页
     */
    private int currentIndex;
    /**
     * 屏幕的宽度
     */
    private int screenWidth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bao_zhang_chu_li);
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

        findById();
        init();
        initTabLineWidth();
    }

    private void findById() {
        mTabContactsTv = (TextView) findViewById(R.id.id_contacts_tv);
        mTabChatTv = (TextView) findViewById(R.id.id_chat_tv);
        mTabFriendTv = (TextView) findViewById(R.id.id_friend_tv);
        mTabLineIv = (ImageView) findViewById(R.id.id_tab_line_iv);
        mPageVp = (ViewPager) findViewById(R.id.id_page_vp);
        l1= (LinearLayout) findViewById(R.id.id_tab_chat_ll);
        l1.setOnClickListener(this);
        l2= (LinearLayout) findViewById(R.id.id_tab_friend_ll);
        l2.setOnClickListener(this);
        l3= (LinearLayout) findViewById(R.id.id_tab_contacts_ll);
        l3.setOnClickListener(this);

    }

    @SuppressWarnings("deprecation")
    private void init() {
        mFriendFg = new Fragment_BZ2();
        mContactsFg = new Fragment_BZ3();
        mChatFg = new Fragment_BZ1();
        mFragmentList.add(mChatFg);
        mFragmentList.add(mFriendFg);
        mFragmentList.add(mContactsFg);

        mFragmentAdapter = new ViewPagerFragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            /**
             * state滑动中的状态 有三种状态（0，1，2） 1：正在滑动 2：滑动完毕 0：什么都没做。
             */
            @Override
            public void onPageScrollStateChanged(int state) {

            }

            /**
             * position :当前页面，及你点击滑动的页面 offset:当前页面偏移的百分比
             * offsetPixels:当前页面偏移的像素位置
             */
            @Override
            public void onPageScrolled(int position, float offset,
                                       int offsetPixels) {
                LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
                lp.leftMargin = screenWidth/3*position+offsetPixels/3;
                mTabLineIv.setLayoutParams(lp);
            }

            @Override
            public void onPageSelected(int position) {
                resetTextView();
                switch (position) {
                    case 0:
                        mTabChatTv.setTextColor(Color.BLUE);
                        break;
                    case 1:
                        mTabFriendTv.setTextColor(Color.BLUE);
                        break;
                    case 2:
                        mTabContactsTv.setTextColor(Color.BLUE);
                        break;
                }
                currentIndex = position;
            }
        });

    }

    /**
     * 设置滑动条的宽度为屏幕的1/3(根据Tab的个数而定)
     */
    private void initTabLineWidth() {
        DisplayMetrics dpMetrics = new DisplayMetrics();
        getWindow().getWindowManager().getDefaultDisplay()
                .getMetrics(dpMetrics);
        screenWidth = dpMetrics.widthPixels;
        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) mTabLineIv.getLayoutParams();
        lp.width = screenWidth / 3;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabChatTv.setTextColor(0xff8c8c8c);
        mTabFriendTv.setTextColor(0xff8c8c8c);
        mTabContactsTv.setTextColor(0xff8c8c8c);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.id_tab_chat_ll:
                resetTextView();
                mTabChatTv.setTextColor(Color.BLUE);
                mPageVp.setCurrentItem(0);
                break;
            case R.id.id_tab_friend_ll:
                resetTextView();
                mTabFriendTv.setTextColor(Color.BLUE);
                mPageVp.setCurrentItem(1);
                break;
            case R.id.id_tab_contacts_ll:
                resetTextView();
                mTabContactsTv.setTextColor(Color.BLUE);
                mPageVp.setCurrentItem(2);
                break;
        }

    }
}
