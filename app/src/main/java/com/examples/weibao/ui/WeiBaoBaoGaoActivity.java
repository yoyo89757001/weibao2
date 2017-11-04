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
import com.examples.weibao.fargments.WeiBaoFragment1;
import com.examples.weibao.fargments.WeiBaoFragment2;
import com.examples.weibao.views.ViewPagerFragmentAdapter;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import java.util.ArrayList;
import java.util.List;

public class WeiBaoBaoGaoActivity extends AppCompatActivity implements View.OnClickListener {
    private List<Fragment> mFragmentList = new ArrayList<>();
    private ViewPagerFragmentAdapter mFragmentAdapter;
    private LinearLayout l1,l2;
    private ViewPager mPageVp;
    /**
     * Tab显示内容TextView
     */
    private TextView mTabChatTv, mTabFriendTv;
    /**
     * Tab的那个引导线
     */
    private ImageView mTabLineIv;
    /**
     * Fragment
     */
    private WeiBaoFragment1 mChatFg;
    private WeiBaoFragment2 mFriendFg;
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
        setContentView(R.layout.activity_wei_bao_bao_gao);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.lanse33);
        }

        findById();
        init();
        initTabLineWidth();

    }

    private void findById() {
        TextView tit= (TextView) findViewById(R.id.title);
        tit.setText("维保报告");
        ImageView f= (ImageView) findViewById(R.id.leftim);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        mTabChatTv = (TextView) findViewById(R.id.id_chat_tv);
        mTabFriendTv = (TextView) findViewById(R.id.id_friend_tv);
        mTabLineIv = (ImageView) findViewById(R.id.id_tab_line_iv);
        mPageVp = (ViewPager) findViewById(R.id.id_page_vp);
        l1= (LinearLayout) findViewById(R.id.id_tab_chat_ll);
        l1.setOnClickListener(this);
        l2= (LinearLayout) findViewById(R.id.id_tab_friend_ll);
        l2.setOnClickListener(this);


    }


    private void init() {
        mChatFg = new WeiBaoFragment1();
        mFriendFg = new WeiBaoFragment2();
        mFragmentList.add(mChatFg);
        mFragmentList.add(mFriendFg);


        mFragmentAdapter = new ViewPagerFragmentAdapter(
                this.getSupportFragmentManager(), mFragmentList);
        mPageVp.setAdapter(mFragmentAdapter);
        mPageVp.setCurrentItem(0);

        mPageVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

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
                lp.leftMargin = screenWidth/2*position+offsetPixels/2;
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
        lp.width = screenWidth / 2;
        mTabLineIv.setLayoutParams(lp);
    }

    /**
     * 重置颜色
     */
    private void resetTextView() {
        mTabChatTv.setTextColor(0xff8c8c8c);
        mTabFriendTv.setTextColor(0xff8c8c8c);
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
        }

    }
}
