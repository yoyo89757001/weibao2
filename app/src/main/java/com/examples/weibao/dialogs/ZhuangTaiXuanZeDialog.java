package com.examples.weibao.dialogs;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;
import com.examples.weibao.R;
import com.examples.weibao.adapters.ZhuangTaiXuanZeAdapter;


/**
 * @Function: 自定义对话框
 * @Date: 2013-10-28
 * @Time: 下午12:37:43
 * @author Tom.Cai
 */
public class ZhuangTaiXuanZeDialog extends Dialog {
    private TextView que,qu;
    private ListView listView;
    private ZhuangTaiXuanZeAdapter adapter;

    public ZhuangTaiXuanZeDialog(Context context,int isTrue) {
        super(context, R.style.dialog_style2);
        setCustomDialog();
    }

    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.queren_ll5, null);
        que= (TextView) mView.findViewById(R.id.queding);
        qu= (TextView) mView.findViewById(R.id.quxiao);
        listView= (ListView) mView.findViewById(R.id.listview);
      //  adapter=new ZhuangTaiXuanZeAdapter(getContext(),null);

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
