package com.examples.weibao.fargments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.examples.weibao.R;
import com.examples.weibao.ui.WeiBaoYuCeShiActivity;
import com.examples.weibao.utils.Utils;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment1 extends Fragment implements View.OnClickListener {
    private LinearLayout l1,l2,l3,l4,l5,l6,l7,l8;
    private int dw,dh;
    private LinearLayout top_ll;
    private ImageView touxiang;
    private TextView name,zhiwu1,zhiwu2;



    public Fragment1() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        dw = Utils.getDisplaySize(getActivity()).x;
        dh = Utils.getDisplaySize(getActivity()).y;

        View view=inflater.inflate(R.layout.fragment_fragment1, container, false);

        initView(view);



        return view;
    }

    private void initView(View view) {

        top_ll= (LinearLayout) view.findViewById(R.id.top_ll);
        l1= (LinearLayout) view.findViewById(R.id.l1);
        l1.setOnClickListener(this);
        l2= (LinearLayout) view.findViewById(R.id.l2);
        l2.setOnClickListener(this);
        l3= (LinearLayout) view.findViewById(R.id.l3);
        l3.setOnClickListener(this);
        l4= (LinearLayout) view.findViewById(R.id.l4);
        l4.setOnClickListener(this);
        l5= (LinearLayout) view.findViewById(R.id.l5);
        l5.setOnClickListener(this);
        l6= (LinearLayout) view.findViewById(R.id.l6);
        l6.setOnClickListener(this);
        l7= (LinearLayout) view.findViewById(R.id.l7);
        l7.setOnClickListener(this);
        l8= (LinearLayout) view.findViewById(R.id.l8);
        l8.setOnClickListener(this);
        name= (TextView) view.findViewById(R.id.name);
        zhiwu1= (TextView) view.findViewById(R.id.zhiwu1);
        zhiwu2= (TextView) view.findViewById(R.id.zhiwu2);
        touxiang= (ImageView) view.findViewById(R.id.touxiang);

        LinearLayout.LayoutParams lp_tx = (LinearLayout.LayoutParams) touxiang.getLayoutParams();
        lp_tx.width=dw/6;
        lp_tx.height=dw/6;
        touxiang.setLayoutParams(lp_tx);
        touxiang.invalidate();
        ViewGroup.LayoutParams lp =  top_ll.getLayoutParams();
        //弹窗的高宽
        lp.height=dh/5;
        top_ll.setLayoutParams(lp);
        top_ll.invalidate();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.l1:
            startActivity(new Intent(getContext(), WeiBaoYuCeShiActivity.class));

                break;
            case R.id.l2:

                break;
            case R.id.l3:

                break;
            case R.id.l4:

                break;
            case R.id.l5:

                break;
            case R.id.l6:

                break;
            case R.id.l7:

                break;
            case R.id.l8:

                break;

        }

    }
}
