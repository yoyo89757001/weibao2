package com.examples.weibao.ui;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.NumberPicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.examples.weibao.R;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class DatePickActivity extends Activity {

	private DatePicker datePicker;
	private TimePicker timePicker;
	private String datestrold;
	private String datestr;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_date_pick);
		datePicker= (DatePicker) findViewById(R.id.date_picker);
		timePicker= (TimePicker) findViewById(R.id.time_picker);

		init();
	}

	/**
	 * 数据初始化
	 */
	private void init() {

		datePicker.setCalendarViewShown(false);
		timePicker.setIs24HourView(true);
		resizePikcer(datePicker);// 调整datepicker大小
		resizePikcer(timePicker);// 调整timepicker大小
		String str = getIntent().getStringExtra("date");
		if (TextUtils.isEmpty(str)) {
			System.out.println("isempty");
			datestrold = "";
			datestr = "";
		} else {
			datestr = str;
			datestrold = str;
		}

	}

	
	/**
	 * 实现onTouchEvent触屏函数但点击屏幕时
	 * 
	 * @param v
	 */

	public void exit(View v) {
		back(false);
	}
	
	/**
	 * 调整FrameLayout大小
	 * 
	 * @param tp
	 */
	private void resizePikcer(FrameLayout tp) {
		List<NumberPicker> npList = findNumberPicker(tp);
		for (NumberPicker np : npList) {
			resizeNumberPicker(np);
		}
	}

	/*
	 * 调整numberpicker大小
	 */
	private void resizeNumberPicker(NumberPicker np) {
		LayoutParams params = new LayoutParams(dip2px(this, 55),
				LayoutParams.WRAP_CONTENT);
		params.setMargins(dip2px(this, 5), 0, dip2px(this, 5), 0);
		np.setLayoutParams(params);

	}
	public  int dip2px(Context context, float dpValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	/**
	 * 得到viewGroup里面的numberpicker组件
	 * 
	 * @param viewGroup
	 * @return
	 */
	private List<NumberPicker> findNumberPicker(ViewGroup viewGroup) {
		List<NumberPicker> npList = new ArrayList<NumberPicker>();
		View child = null;
		if (null != viewGroup) {
			for (int i = 0; i < viewGroup.getChildCount(); i++) {
				child = viewGroup.getChildAt(i);
				if (child instanceof NumberPicker) {
					npList.add((NumberPicker) child);
				} else if (child instanceof LinearLayout) {
					List<NumberPicker> result = findNumberPicker((ViewGroup) child);
					if (result.size() > 0) {
						return result;
					}
				}
			}
		}
		return npList;
	}

	/**
	 * 点击取消
	 * 
	 * @param v
	 */

	public void cancel(View v) {
		back(true);
	}
	
	/**
	 * 点击确定
	 * 
	 * @param v
	 */

	public void ok(View v) {
		back(false);
	}

	/**
	 * 处理返回按键
	 */
	@Override
	public void onBackPressed() {
		back(true);
		super.onBackPressed();
	}

	/**
	 * 关闭调用 old为true则不变，false则改变
	 * 
	 * @param
	 */
	private void back(boolean old) {
		// 获取时间选择
		Intent intent = new Intent();
		if (old) {
			intent.putExtra("date", datestrold);
		} else {
			datestr = getData();
		//	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			try {
//				Date date = sdf.parse(datestr);
//				if (!compare(date))
//					return;
				intent.putExtra("date", datestr);
				setResult(Activity.RESULT_OK, intent);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		finish();
	}

	// 比较时间
	private boolean compare(Date dt1) {
		Date curDate = new Date(System.currentTimeMillis());
		if (dt1.getTime() >= curDate.getTime()) {
			System.out.println("时间大于现在的时间");
			return true;
		} else if (dt1.getTime() <= curDate.getTime()) {
			showToast(this, "时间必须大于当前时间");
			return false;
		} else {// 相等
			System.out.println("相等");
			return false;
		}
	}

	public static void showToast(Context mContext, String content) {
		Toast.makeText(mContext, content, Toast.LENGTH_SHORT).show();
	}

	private String getData() {
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
		StringBuilder str = new StringBuilder().append(datePicker.getYear()).append("-")
				.append((datePicker.getMonth() + 1) < 10 ? "0" + (datePicker.getMonth() + 1)
						: (datePicker.getMonth() + 1))
				.append("-")
				.append((datePicker.getDayOfMonth() < 10) ? "0" + datePicker.getDayOfMonth()
						: datePicker.getDayOfMonth())
				.append(" ")
				.append((timePicker.getHour() < 10) ? "0" + timePicker.getHour()
						: timePicker.getHour())
				.append(":").append((timePicker.getMinute() < 10) ? "0" + timePicker.getMinute()
						: timePicker.getMinute());

		return str.toString();
		}else {

		StringBuilder str = new StringBuilder().append(datePicker.getYear()).append("-")
				.append((datePicker.getMonth() + 1) < 10 ? "0" + (datePicker.getMonth() + 1)
						: (datePicker.getMonth() + 1))
				.append("-")
				.append((datePicker.getDayOfMonth() < 10) ? "0" + datePicker.getDayOfMonth()
						: datePicker.getDayOfMonth())
				.append(" ")
				.append((timePicker.getCurrentHour() < 10) ? "0" + timePicker.getCurrentHour()
						: timePicker.getCurrentHour())
				.append(":").append((timePicker.getCurrentMinute() < 10) ? "0" + timePicker.getCurrentMinute()
						: timePicker.getCurrentMinute());

		return str.toString();

		}

	}
}
