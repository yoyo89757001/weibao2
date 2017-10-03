package com.examples.weibao.dialogs;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.views.RadioButton;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class ZhuangTaiXuanZeDialog extends Dialog {
    private RadioButton rb1,rb2,rb3,rb4;
    private TextView que,qu;
    private RadioButton temp=null;

    public ZhuangTaiXuanZeDialog(Context context) {
        super(context, R.style.dialog_style2);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.queren_ll5, null);
        rb1= (RadioButton) mView.findViewById(R.id.rb1);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb2.ChongZhi();
                rb3.ChongZhi();
                rb4.ChongZhi();
                rb1.XuanZhong();
            }
        });

        rb2= (RadioButton) mView.findViewById(R.id.rb2);
        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb1.ChongZhi();
                rb3.ChongZhi();
                rb4.ChongZhi();
                rb2.XuanZhong();
            }
        });
        rb3= (RadioButton) mView.findViewById(R.id.rb3);
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rb1.ChongZhi();
                rb2.ChongZhi();
                rb4.ChongZhi();
                rb3.XuanZhong();
            }
        });
        rb4= (RadioButton) mView.findViewById(R.id.rb4);
        rb1.setText("正常");
        rb2.setText("修复");
        rb3.setText("异常");
        rb4.setText("其它");
        que= (TextView) mView.findViewById(R.id.queding);
        qu= (TextView) mView.findViewById(R.id.quxiao);

        super.setContentView(mView);


    }



    @Override
    public void setContentView(int layoutResID) {
    }

    @Override
    public void setContentView(View view, LayoutParams params) {
    }

    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        que.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnQuXiaoListener(View.OnClickListener listener){
        qu.setOnClickListener(listener);
    }
}
