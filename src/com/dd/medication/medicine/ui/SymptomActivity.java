package com.dd.medication.medicine.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.adapter.ChaildPartsAdapter;
import com.dd.medication.medicine.dao.SymptomPartDao;

public class SymptomActivity extends BaseActivity{
	private String baseParts;
	private ListView listView;
	private ArrayList<String> chaild=new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.symptom);
		
		Intent intent = getIntent();
		baseParts = intent.getStringExtra("body_part");
		
		listView=(ListView) this.findViewById(R.id.symptom_list);
		if(!"".equals(baseParts)){
			//根据获取的身体部位  获取相应的症状
			SymptomPartDao symptomPartDao=new SymptomPartDao();
			chaild=symptomPartDao.getSymptomByParts(baseParts);
			if(chaild!=null && chaild.size()>0){
				System.out.println("=====chaild.size()====="+chaild.size());
				ChaildPartsAdapter chaildPartsAdapter=new ChaildPartsAdapter(context,chaild);
				listView.setAdapter(chaildPartsAdapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// 将获取到的症状传入  症状设置界面
						Intent intent=new Intent();
						intent.setClass(context, SymptomSettingActivity.class);
						intent.putExtra("body_part", baseParts);
						intent.putExtra("symptom", chaild.get(position));
						startActivity(intent);
					}
				});
			}
		}
		
		
		TextView back =(TextView)this.findViewById(R.id.symptom_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	}
	
	
}
