package com.examples.weibao.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.examples.weibao.R;
import com.examples.weibao.adapters.SheBeiAdapter;
import com.examples.weibao.dialogs.ZhuangTaiXuanZe2Dialog;
import com.examples.weibao.dialogs.ZhuangTaiXuanZeDialog;
import com.examples.weibao.intface.ClickIntface;
import com.examples.weibao.utils.Utils;
import com.github.jdsjlzx.ItemDecoration.DividerDecoration;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class SheBeiWeiBaoYuCeShiActivity extends Activity implements ClickIntface {
    private int dw,dh;
    private LRecyclerView lRecyclerView;
    private TextView t1,t2,t3;
    private List<String> dataList;
    private SheBeiAdapter sheBeiAdapter;
    private LRecyclerViewAdapter lRecyclerViewAdapter;
    private LinearLayoutManager linearLayoutManager;
    private int isTrue;
    private String miaoshu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_she_bei_wei_bao_yu_ce_shi);
        dw = Utils.getDisplaySize(this).x;
        dh = Utils.getDisplaySize(this).y;

        dataList=new ArrayList<>();
        dataList.add("ddddddd");
        dataList.add("dfff");

        TextView t= (TextView) findViewById(R.id.title);
        t.setText("设备维保与测试");
        ImageView imageView= (ImageView) findViewById(R.id.leftim);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        t1= (TextView) findViewById(R.id.t1);
        t1= (TextView) findViewById(R.id.t1);
        t1= (TextView) findViewById(R.id.t1);


        LinearLayout l= (LinearLayout) findViewById(R.id.tl);
        LinearLayout.LayoutParams layoutParams= (LinearLayout.LayoutParams) l.getLayoutParams();
        layoutParams.height=(dh*20)/100;
        l.setLayoutParams(layoutParams);
        l.invalidate();

        lRecyclerView= (LRecyclerView) findViewById(R.id.lrecyclerview);
        sheBeiAdapter=new SheBeiAdapter(dataList);
        sheBeiAdapter.setClickIntface(this);
        lRecyclerViewAdapter = new LRecyclerViewAdapter(sheBeiAdapter);

        linearLayoutManager=new LinearLayoutManager(SheBeiWeiBaoYuCeShiActivity.this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        lRecyclerView.setLayoutManager(linearLayoutManager);

        lRecyclerView.setAdapter(lRecyclerViewAdapter);
        lRecyclerView.setPullRefreshEnabled(false);
        lRecyclerView.setLoadMoreEnabled(false);

        DividerDecoration divider = new DividerDecoration.Builder(SheBeiWeiBaoYuCeShiActivity.this)
                .setHeight(R.dimen.default_divider_height)
                .setPadding(R.dimen.default_divider_padding)
                .setColorResource(R.color.view_bg)
                .build();
        lRecyclerView.addItemDecoration(divider);
    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public int BackId(int id) {

        switch (id){
          case   R.id.rl_1:
              final ZhuangTaiXuanZeDialog dialog=new ZhuangTaiXuanZeDialog(this,0);
              dialog.setOnPositiveListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      isTrue=dialog.getIsTrue();
                      miaoshu=dialog.getET();
                      dialog.dismiss();

                  }
              });
              dialog.setOnQuXiaoListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      dialog.dismiss();
                  }
              });
              dialog.setOnR2Listener(new View.OnClickListener() {
                  @Override
                  public void onClick(View v) {
                      final ZhuangTaiXuanZe2Dialog ze2Dialog=new ZhuangTaiXuanZe2Dialog(SheBeiWeiBaoYuCeShiActivity.this,1);
                      ze2Dialog.setOnPositiveListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {

                              dialog.dismiss();
                              ze2Dialog.dismiss();
                          }
                      });
                      ze2Dialog.setOnQuXiaoListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {

                              dialog.dismiss();
                              ze2Dialog.dismiss();
                          }
                      });
                      ze2Dialog.show();
                  }
              });
              dialog.show();

            break;
            case   R.id.rl_2:
                final ZhuangTaiXuanZeDialog dialog2=new ZhuangTaiXuanZeDialog(this,1);
                dialog2.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isTrue=dialog2.getIsTrue();
                        miaoshu=dialog2.getET();
                        dialog2.dismiss();

                    }
                });
                dialog2.setOnQuXiaoListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog2.dismiss();
                    }
                });
                dialog2.setOnR2Listener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ZhuangTaiXuanZe2Dialog ze2Dialog=new ZhuangTaiXuanZe2Dialog(SheBeiWeiBaoYuCeShiActivity.this,1);
                        ze2Dialog.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog2.dismiss();
                                ze2Dialog.dismiss();
                            }
                        });
                        ze2Dialog.setOnQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog2.dismiss();
                                ze2Dialog.dismiss();
                            }
                        });
                        ze2Dialog.show();
                    }
                });
                dialog2.show();
                break;
            case   R.id.rl_3:
                final ZhuangTaiXuanZeDialog dialog3=new ZhuangTaiXuanZeDialog(this,2);
                dialog3.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isTrue=dialog3.getIsTrue();
                        miaoshu=dialog3.getET();
                        dialog3.dismiss();

                    }
                });
                dialog3.setOnQuXiaoListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog3.dismiss();
                    }
                });
                dialog3.setOnR2Listener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ZhuangTaiXuanZe2Dialog ze2Dialog=new ZhuangTaiXuanZe2Dialog(SheBeiWeiBaoYuCeShiActivity.this,1);
                        ze2Dialog.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog3.dismiss();
                                ze2Dialog.dismiss();
                            }
                        });
                        ze2Dialog.setOnQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog3.dismiss();
                                ze2Dialog.dismiss();
                            }
                        });
                        ze2Dialog.show();
                    }
                });
                dialog3.show();
                break;
            case   R.id.rl_4:
                final ZhuangTaiXuanZeDialog dialog4=new ZhuangTaiXuanZeDialog(this,2);
                dialog4.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isTrue=dialog4.getIsTrue();
                        miaoshu=dialog4.getET();
                        dialog4.dismiss();

                    }
                });
                dialog4.setOnQuXiaoListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog4.dismiss();
                    }
                });
                dialog4.setOnR2Listener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ZhuangTaiXuanZe2Dialog ze2Dialog=new ZhuangTaiXuanZe2Dialog(SheBeiWeiBaoYuCeShiActivity.this,1);
                        ze2Dialog.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog4.dismiss();
                                ze2Dialog.dismiss();
                            }
                        });
                        ze2Dialog.setOnQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog4.dismiss();
                                ze2Dialog.dismiss();
                            }
                        });
                        ze2Dialog.show();
                    }
                });
                dialog4.show();
                break;
            case   R.id.rl_5:
                final ZhuangTaiXuanZeDialog dialog5=new ZhuangTaiXuanZeDialog(this,2);
                dialog5.setOnPositiveListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        isTrue=dialog5.getIsTrue();
                        miaoshu=dialog5.getET();
                        dialog5.dismiss();

                    }
                });
                dialog5.setOnQuXiaoListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        dialog5.dismiss();
                    }
                });
                dialog5.setOnR2Listener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final ZhuangTaiXuanZe2Dialog ze2Dialog=new ZhuangTaiXuanZe2Dialog(SheBeiWeiBaoYuCeShiActivity.this,1);
                        ze2Dialog.setOnPositiveListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                dialog5.dismiss();
                                ze2Dialog.dismiss();
                            }
                        });
                        ze2Dialog.setOnQuXiaoListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog5.dismiss();
                                ze2Dialog.dismiss();

                            }
                        });
                        ze2Dialog.show();
                    }
                });
                dialog5.show();
                break;
        }

        return 0;
    }
}
