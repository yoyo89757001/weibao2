package com.examples.weibao.dialogs;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
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
public class ZhuangTaiXuanZeDialog extends Dialog {
    private RadioButton rb1,rb2,rb3,rb4;
    private TextView que,qu;
    private int isTrue;
    private View v1,v2,v3,v4;
    private EditText et;
    private TextView top;



    public ZhuangTaiXuanZeDialog(Context context,int isTrue) {
        super(context, R.style.dialog_style2);
        this.isTrue=isTrue;
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.queren_ll5, null);
        rb1= (RadioButton) mView.findViewById(R.id.rb1);
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTrue=1;
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
                isTrue=2;
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
                isTrue=3;
                rb1.ChongZhi();
                rb2.ChongZhi();
                rb4.ChongZhi();
                rb3.XuanZhong();
            }
        });
        rb4= (RadioButton) mView.findViewById(R.id.rb4);
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isTrue=4;
                rb1.setVisibility(View.GONE);
                rb2.setVisibility(View.GONE);
                rb3.setVisibility(View.GONE);
                rb4.setVisibility(View.GONE);
                top.setVisibility(View.VISIBLE);
                et.setVisibility(View.VISIBLE);
                v1.setVisibility(View.VISIBLE);
                v2.setVisibility(View.GONE);
                v3.setVisibility(View.GONE);
                v4.setVisibility(View.GONE);
            }
        });
        rb1.setText("正常");
        rb2.setText("修复");
        rb3.setText("异常");
        rb4.setText("其它");
        que= (TextView) mView.findViewById(R.id.queding);
        qu= (TextView) mView.findViewById(R.id.quxiao);
        top= (TextView) mView.findViewById(R.id.top_tv);
        et= (EditText) mView.findViewById(R.id.et);
        v1=mView.findViewById(R.id.v1);
        v2=mView.findViewById(R.id.v2);
        v3=mView.findViewById(R.id.v3);
        v4=mView.findViewById(R.id.v4);


        switch (isTrue){
            case 0:

                rb1.XuanZhong();

                break;
            case 1:
                rb2.XuanZhong();
                break;
            case 2:
                rb3.XuanZhong();
                break;
            case 3:

                rb4.XuanZhong();
                break;

        }
        super.setContentView(mView);


    }
    public int getIsTrue(){
        return isTrue;
    }

    public String getET(){

        return et.getText().toString().trim();
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
