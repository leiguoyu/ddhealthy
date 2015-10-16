package com.dd.medication.medicine.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.adapter.BodyChartAdapter;
import com.dd.medication.medicine.adapter.ViewPagerAdapter;

/**
 * 人体图
 * */
public class BodyChartActivity extends BaseActivity implements OnClickListener {
	// 定义ViewPager对象
	private ViewPager viewPager;
	// 定义ViewPager适配器
	private ViewPagerAdapter vpAdapter;
	// 定义一个ArrayList来存放View
	private ArrayList<View> views;
	// 定义各个界面View对象
	private View view1, view2;
	// 定义底部小点图片
	private ImageView pointImage0, pointImage1;
	// 当前的位置索引值
	private int currIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_chart);

		initView();
		initData();
	}

	/**
	 * 初始化组件
	 */
	private void initView() {
		// 实例化各个界面的布局对象
		LayoutInflater mLi = LayoutInflater.from(BodyChartActivity.this);
		view1 = mLi.inflate(R.layout.body_chart_man, null);
		// view2 = mLi.inflate(R.layout.body_chart_woman, null);
		view2 = mLi.inflate(R.layout.body_chart_parts, null);
		// 实例化ViewPager
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		// 实例化ArrayList对象
		views = new ArrayList<View>();
		// 将要分页显示的View装入数组中
		views.add(view1);
		views.add(view2);
		// 实例化ViewPager适配器
		vpAdapter = new ViewPagerAdapter(views);
		// 实例化底部小点图片对象
		pointImage0 = (ImageView) findViewById(R.id.page0);
		pointImage1 = (ImageView) findViewById(R.id.page1);
		FrameLayout bodyChartBg = (FrameLayout) view1
				.findViewById(R.id.body_chart);
		// 这里以登录用户的性别加载男女图片

		//
		TextView tb = (TextView) view1.findViewById(R.id.body_chart_man_tb);// 头部
		tb.setOnClickListener(this);
		TextView xb = (TextView) view1.findViewById(R.id.body_chart_man_xb);// 胸部
		xb.setOnClickListener(this);
		TextView sz = (TextView) view1.findViewById(R.id.body_chart_man_sz);// 上肢
		sz.setOnClickListener(this);
		TextView sz1 = (TextView) view1.findViewById(R.id.body_chart_man_sz1);// 上肢
		sz1.setOnClickListener(this);
		TextView fb = (TextView) view1.findViewById(R.id.body_chart_man_fb);// 腹部
		fb.setOnClickListener(this);
		TextView xz = (TextView) view1.findViewById(R.id.body_chart_man_xz);// 下肢
		xz.setOnClickListener(this);
		TextView xz1 = (TextView) view1.findViewById(R.id.body_chart_man_xz1);// 下肢
		xz1.setOnClickListener(this);
		TextView tunb = (TextView) view1.findViewById(R.id.body_chart_man_tunb);// 臀部
		tunb.setOnClickListener(this);
		TextView ws = (TextView) view1.findViewById(R.id.body_chart_man_ws);// 外伤
		ws.setOnClickListener(this);
		ListView bodyChartListView = (ListView) view2
				.findViewById(R.id.body_parts_list);
		// 这里要根据注册 登录用户的性别加载 不同数据 男女 不一样
		final String[] bodyParts = getResources().getStringArray(
				R.array.man_body_parts);
		// String[] bodyParts=getResources().getStringArray(
		// R.array.woman_body_parts);
		// 给List装载数据
		BodyChartAdapter adapter = new BodyChartAdapter(context, bodyParts);
		bodyChartListView.setAdapter(adapter);
		bodyChartListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent = new Intent();
				intent.setClass(context, SymptomActivity.class);
				intent.putExtra("body_part", bodyParts[position]);
				startActivity(intent);
			}
		});

		RadioButton more = (RadioButton) this.findViewById(R.id.body_chart_more);
		more.setOnClickListener(this);
		TextView back = (TextView) this.findViewById(R.id.body_chart_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	/**
	 * 初始化数据
	 */
	private void initData() {
		// 设置监听
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		// 设置适配器数据
		viewPager.setAdapter(vpAdapter);

	}

	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			switch (position) {
			case 0:
				pointImage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_indicator_focused));
				pointImage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_indicator_unfocused));
				break;
			case 1:
				pointImage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_indicator_focused));
				pointImage0.setImageDrawable(getResources().getDrawable(
						R.drawable.page_indicator_unfocused));
				break;
			case 2:
				pointImage1.setImageDrawable(getResources().getDrawable(
						R.drawable.page_indicator_unfocused));
				break;
			}
			currIndex = position;
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		switch (v.getId()) {
		case R.id.body_chart_more:
			// 跳转到症状记录界面
			intent.setClass(context, HealthRecActivity.class);
			break;
		case R.id.body_chart_man_tb:
			// 头部
			intent.setClass(context, BodyPartsActivity.class);
			intent.putExtra("body_part", "头部");
			break;
		case R.id.body_chart_man_xb:
			// 胸部
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "胸部");
			break;
		case R.id.body_chart_man_sz:
		case R.id.body_chart_man_sz1:
			// 上肢
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "手臂");
			break;
		case R.id.body_chart_man_fb:
			// 腹部
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "腹部");
			break;
		case R.id.body_chart_man_xz:
		case R.id.body_chart_man_xz1:
			// 下肢
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "腿部");
			break;
		case R.id.body_chart_man_tunb:
			// 臀部
			intent.setClass(context, BodyPartsActivity.class);
			intent.putExtra("body_part", "臀部");
			break;
		case R.id.body_chart_man_ws:
			// 外伤
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "外伤");
			break;

		default:
			break;
		}
		startActivity(intent);
	}

}
