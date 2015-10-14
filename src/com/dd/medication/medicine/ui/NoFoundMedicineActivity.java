package com.dd.medication.medicine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;

public class NoFoundMedicineActivity extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.no_medicine_activity);

		intView();

	}

	private void intView() {
		// TODO Auto-generated method stub
		TextView back = (TextView) this.findViewById(R.id.no_medicine_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

		Button selectedButton = (Button) this
				.findViewById(R.id.no_medicine_found_but);
		selectedButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(context, SeachMedicineActivity.class);
				startActivity(intent);
			}
		});
	}

}
