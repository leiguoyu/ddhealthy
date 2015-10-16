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
 * ����ͼ
 * */
public class BodyChartActivity extends BaseActivity implements OnClickListener {
	// ����ViewPager����
	private ViewPager viewPager;
	// ����ViewPager������
	private ViewPagerAdapter vpAdapter;
	// ����һ��ArrayList�����View
	private ArrayList<View> views;
	// �����������View����
	private View view1, view2;
	// ����ײ�С��ͼƬ
	private ImageView pointImage0, pointImage1;
	// ��ǰ��λ������ֵ
	private int currIndex = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_chart);

		initView();
		initData();
	}

	/**
	 * ��ʼ�����
	 */
	private void initView() {
		// ʵ������������Ĳ��ֶ���
		LayoutInflater mLi = LayoutInflater.from(BodyChartActivity.this);
		view1 = mLi.inflate(R.layout.body_chart_man, null);
		// view2 = mLi.inflate(R.layout.body_chart_woman, null);
		view2 = mLi.inflate(R.layout.body_chart_parts, null);
		// ʵ����ViewPager
		viewPager = (ViewPager) findViewById(R.id.viewpager);
		// ʵ����ArrayList����
		views = new ArrayList<View>();
		// ��Ҫ��ҳ��ʾ��Viewװ��������
		views.add(view1);
		views.add(view2);
		// ʵ����ViewPager������
		vpAdapter = new ViewPagerAdapter(views);
		// ʵ�����ײ�С��ͼƬ����
		pointImage0 = (ImageView) findViewById(R.id.page0);
		pointImage1 = (ImageView) findViewById(R.id.page1);
		FrameLayout bodyChartBg = (FrameLayout) view1
				.findViewById(R.id.body_chart);
		// �����Ե�¼�û����Ա������ŮͼƬ

		//
		TextView tb = (TextView) view1.findViewById(R.id.body_chart_man_tb);// ͷ��
		tb.setOnClickListener(this);
		TextView xb = (TextView) view1.findViewById(R.id.body_chart_man_xb);// �ز�
		xb.setOnClickListener(this);
		TextView sz = (TextView) view1.findViewById(R.id.body_chart_man_sz);// ��֫
		sz.setOnClickListener(this);
		TextView sz1 = (TextView) view1.findViewById(R.id.body_chart_man_sz1);// ��֫
		sz1.setOnClickListener(this);
		TextView fb = (TextView) view1.findViewById(R.id.body_chart_man_fb);// ����
		fb.setOnClickListener(this);
		TextView xz = (TextView) view1.findViewById(R.id.body_chart_man_xz);// ��֫
		xz.setOnClickListener(this);
		TextView xz1 = (TextView) view1.findViewById(R.id.body_chart_man_xz1);// ��֫
		xz1.setOnClickListener(this);
		TextView tunb = (TextView) view1.findViewById(R.id.body_chart_man_tunb);// �β�
		tunb.setOnClickListener(this);
		TextView ws = (TextView) view1.findViewById(R.id.body_chart_man_ws);// ����
		ws.setOnClickListener(this);
		ListView bodyChartListView = (ListView) view2
				.findViewById(R.id.body_parts_list);
		// ����Ҫ����ע�� ��¼�û����Ա���� ��ͬ���� ��Ů ��һ��
		final String[] bodyParts = getResources().getStringArray(
				R.array.man_body_parts);
		// String[] bodyParts=getResources().getStringArray(
		// R.array.woman_body_parts);
		// ��Listװ������
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
	 * ��ʼ������
	 */
	private void initData() {
		// ���ü���
		viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
		// ��������������
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
			// ��ת��֢״��¼����
			intent.setClass(context, HealthRecActivity.class);
			break;
		case R.id.body_chart_man_tb:
			// ͷ��
			intent.setClass(context, BodyPartsActivity.class);
			intent.putExtra("body_part", "ͷ��");
			break;
		case R.id.body_chart_man_xb:
			// �ز�
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "�ز�");
			break;
		case R.id.body_chart_man_sz:
		case R.id.body_chart_man_sz1:
			// ��֫
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "�ֱ�");
			break;
		case R.id.body_chart_man_fb:
			// ����
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "����");
			break;
		case R.id.body_chart_man_xz:
		case R.id.body_chart_man_xz1:
			// ��֫
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "�Ȳ�");
			break;
		case R.id.body_chart_man_tunb:
			// �β�
			intent.setClass(context, BodyPartsActivity.class);
			intent.putExtra("body_part", "�β�");
			break;
		case R.id.body_chart_man_ws:
			// ����
			intent.setClass(context, SymptomActivity.class);
			intent.putExtra("body_part", "����");
			break;

		default:
			break;
		}
		startActivity(intent);
	}

}
