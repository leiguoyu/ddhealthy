package com.dd.medication.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;

public class SexualSelectionActivity extends BaseActivity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sexual_selection_activity);
		
		
		intView();

	}

	private void intView() {
		ImageView bou=(ImageView) this.findViewById(R.id.sexual_selection_boy);
		bou.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ��ת������ѡ�����  �������ѡ������
	            Intent intent =new Intent(SexualSelectionActivity.this, AgeSelectionActivity.class);
	            intent.putExtra("GGMK_XB", "GGMK_XB_01");//��
	            startActivity(intent);
			}
		});
		ImageView girl=(ImageView) this.findViewById(R.id.sexual_selection_girl);
		girl.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ��ת������ѡ�����
	            Intent intent =new Intent(SexualSelectionActivity.this, AgeSelectionActivity.class);
	            intent.putExtra("GGMK_XB", "GGMK_XB_02");//Ů
	            //intent.putExtra("GGMK_XB", "GGMK_XB_02");//����
	            startActivity(intent);
			}
		});
		
	}
	
	
}
