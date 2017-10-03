package com.examples.weibao.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examples.weibao.R;

/**
 * Created by Administrator on 2017/10/3.
 */

public class RadioButton extends LinearLayout {

    private Context context;

    private ImageView imageView;

    private TextView textView;



    private int index = 0;

    private int id = 0;// 判断是否选中



    private RadioButton tempRadioButton;// 模版用于保存上次点击的对象



    private int state[] = { R.drawable.ic_select,

            R.drawable.ic_selected };





    /***

     * 改变图片

     */

    public void ChageImage() {



        index++;

        id = index % 2;// 获取图片id

        imageView.setImageResource(state[id]);

    }

    public void ChongZhi() {


        imageView.setImageResource(R.drawable.ic_select);

    }
    public void XuanZhong() {

        imageView.setImageResource(R.drawable.ic_selected);

    }



    /***

     * 设置文本

     *

     * @param text

     */

    public void setText(String text) {

        textView.setText(text);

    }


    public String getText() {

        return id == 0 ? "" : textView.getText().toString();

    }


    public RadioButton(Context context) {

        this(context, null);

    }


    public RadioButton(Context context, AttributeSet attrs) {

        super(context, attrs);

        this.context = context;

        LayoutInflater.from(context).inflate(R.layout.radio_item, this, true);

        imageView = (ImageView) findViewById(R.id.iv_item);

        textView = (TextView) findViewById(R.id.tv_item);

    }

}
