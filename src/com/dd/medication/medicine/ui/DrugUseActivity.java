package com.dd.medication.medicine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;

public class DrugUseActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.drug_use_activity);

		intView();
	}

	private void intView() {
		TextView back = (TextView) this.findViewById(R.id.drug_use_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		LinearLayout addMedicineToast = (LinearLayout) this
				.findViewById(R.id.drug_use_add_medicine_toast);
		ImageButton addMedicineToast1 = (ImageButton) this
				.findViewById(R.id.sweep_code_for_medicine_img);
		addMedicineToast1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到增加提醒界面
				Intent intent = new Intent(DrugUseActivity.this,
						CaptureActivity.class);
				startActivity(intent);
			}
		});
		addMedicineToast.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到增加提醒界面
				Intent intent = new Intent(DrugUseActivity.this,
						AddMedicineToastActivity.class);
				startActivity(intent);
			}
		});

		LinearLayout selectedButton = (LinearLayout) this
				.findViewById(R.id.drug_use_selected_button);
		selectedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				//跳转到用户搜索药品界面
				Intent intent = new Intent(DrugUseActivity.this,
						SeachMedicineActivity.class);
				startActivity(intent);
			}
		});
		ImageButton selectedButton1 = (ImageButton) this
				.findViewById(R.id.drug_use_selected_but);
		selectedButton1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到用户搜索药品界面
				Intent intent = new Intent(DrugUseActivity.this,
						SeachMedicineActivity.class);
				startActivity(intent);
			}
		});
		
		LinearLayout saoMa = (LinearLayout) this
				.findViewById(R.id.sweep_code_for_medicine);
		saoMa.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到界面进行扫码
				Intent intent = new Intent(DrugUseActivity.this,
						CaptureActivity.class);
				startActivity(intent);
			}
		});

		ImageButton saoMa1 = (ImageButton) this
				.findViewById(R.id.drug_use_add_medicine_toast_img);
		saoMa1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 跳转到界面进行扫码
				Intent intent = new Intent(DrugUseActivity.this,
						AddMedicineToastActivity.class);
				startActivity(intent);
			}
		});

	}

}
