package com.dd.medication.medicine.ui;

import java.util.ArrayList;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.adapter.MedicationsPlanViewPageAdapter;
import com.dd.medication.medicine.adapter.ViewPagerAdapter;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.medicine.ui.BodyChartActivity.MyOnPageChangeListener;
import com.dd.medication.util.DateUtil;

public class MedicationsActivity extends BaseActivity {
	// ��ǰ��λ������ֵ
	private int currIndex = 0;
	private ViewPager viewPager;
	private TextView dateText;
	// ʵ����ArrayList����
	private ArrayList<MedicationRemind> recList = new ArrayList<MedicationRemind>();
	// ��ȡ���� ����������
	private MedicationRemindDao recDao = new MedicationRemindDao();
	private MedicationsPlanViewPageAdapter vpAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medications);

		// ԭ��ͼ��ȡ��ǰ����ǰ��15������ ֻ��ȡ�����ݵ�չʾ Ҫ�����Ű˸���ͼ�� ������Ե��������ʽչʾ

		intView();

		// ʵ����ViewPager
		viewPager = (ViewPager) this.findViewById(R.id.medications_viewpager);
		recList = recDao.getRecByGroupDate();
		if (recList.size() > 0) {
			// ʵ����ViewrecListPager������
			vpAdapter = new MedicationsPlanViewPageAdapter(
					context, recList);
			// ���ü���
			viewPager.setOnPageChangeListener(new MyOnPageChangeListener());
			// ��������������
			viewPager.setAdapter(vpAdapter);
			// ѡ�е�ǰҳ
			String currentTime = DateUtil.getYearMonthDate();
			for (int i = 0; i < recList.size(); i++) {
				if (currentTime.equals(recList.get(i).getAlertDay())) {
					viewPager.setCurrentItem(i);
					break;
				}
			}
		}

	}

	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		IntentFilter filter=new IntentFilter();
		filter.addAction(Intent.ACTION_TIME_TICK);
		registerReceiver(vpAdapter.receiver,filter);
		
	}
	
	public class MyOnPageChangeListener implements OnPageChangeListener {
		@Override
		public void onPageSelected(int position) {
			currIndex = position;
			String month = recList.get(position).getMonth();
			String date = recList.get(position).getDate();
			// ������Ҫ����ʱ��
			dateText.setText(month + "��" + date + "��");
		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}
	}

	private void intView() {
		dateText = (TextView) this.findViewById(R.id.medications_date);
		dateText.setText(DateUtil.getMonthDate() + "�����죩");
		TextView back = (TextView) this.findViewById(R.id.medications_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				System.exit(0);
				finish();
			}
		});
		RadioButton yongyao = (RadioButton) this
				.findViewById(R.id.medications_yongyao);
		yongyao.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ������ҩ���ý���
				Intent intent = new Intent(MedicationsActivity.this,
						DrugUseActivity.class);
				startActivity(intent);
			}
		});
		RadioButton myHealth = (RadioButton) this
				.findViewById(R.id.medications_my_health);
		myHealth.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// ���뽡����¼����
				Intent intent = new Intent(MedicationsActivity.this,
						BodyChartActivity.class);
				startActivity(intent);
			}
		});

	}

}
