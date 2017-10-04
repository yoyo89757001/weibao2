package com.examples.weibao.dialogs;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.EditText;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.views.RadioButton;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class ZhuangTaiXuanZe2Dialog extends Dialog {
    private RadioButton rb3,rb4;
    private TextView que,qu;
    private int isTrue;


    public ZhuangTaiXuanZe2Dialog(Context context, int isTrue) {
        super(context, R.style.dialog_style2);
        this.isTrue=isTrue;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.queren_ll6, null);

        rb3= (RadioButton) mView.findViewById(R.id.rb3);
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTrue=3;
                rb4.ChongZhi();
                rb3.XuanZhong();
            }
        });
        rb4= (RadioButton) mView.findViewById(R.id.rb4);
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTrue=4;
                rb3.ChongZhi();
                rb4.XuanZhong();

            }
        });

        rb3.setText("设备松动,已紧固处理");
        rb4.setText("设备有污垢,已清洁");
        que= (TextView) mView.findViewById(R.id.queding);
        qu= (TextView) mView.findViewById(R.id.quxiao);


        switch (isTrue){

            case 1:
                rb3.XuanZhong();
                break;
            case 2:

                rb4.XuanZhong();
                break;

        }
        super.setContentView(mView);


    }
    public int getIsTrue(){
        return isTrue;
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
