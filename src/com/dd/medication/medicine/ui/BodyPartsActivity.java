package com.dd.medication.medicine.ui;

import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.adapter.ChaildPartsAdapter;
import com.dd.medication.medicine.dao.SymptomPartDao;

/**
 * 展示身体部位列表
 * */
public class BodyPartsActivity extends BaseActivity{
	private  String  baseParts;
	private ListView listView;
	private ArrayList<String> chaild=new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.body_part);
		
		Intent intent = getIntent();
		baseParts = intent.getStringExtra("body_part");
		
		intent.putExtra("body_part", "头部");
		intent.putExtra("body_part", "胸部");
		intent.putExtra("body_part", "手臂");
		intent.putExtra("body_part", "腹部");
		intent.putExtra("body_part", "腿部");
		intent.putExtra("body_part", "臀部");
		intent.putExtra("body_part", "外伤");
		
		listView=(ListView) this.findViewById(R.id.body_part_list);
		SymptomPartDao symptomPartDao=new SymptomPartDao();
//		final String[] mItems = getResources().getStringArray(
//				R.array.body_part_tb);
		if(!"".equals(baseParts)){
			System.out.println("=====baseParts====="+baseParts);
			if(baseParts.equals("头部")){
				//加载身体部位的列表    下一级为症状
				chaild=symptomPartDao.getPartsByBaseParts(baseParts);
			}else if(baseParts.equals("臀部")){
				//加载身体部位的列表   下一级为症状
				chaild=symptomPartDao.getPartsByBaseParts(baseParts, "1");
			}else{
				//加载部位对应的 症状列表
				
			}
			if(chaild!=null && chaild.size()>0){
				System.out.println("=====chaild.size()====="+chaild.size());
				ChaildPartsAdapter chaildPartsAdapter=new ChaildPartsAdapter(context,chaild);
				listView.setAdapter(chaildPartsAdapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// 将数据获取到传入症状显示界面进行展示
						Intent intent=new Intent();
						intent.setClass(context, SymptomActivity.class);
						intent.putExtra("body_part", chaild.get(position));
						startActivity(intent);
					}
				});
			}
		}


		TextView back =(TextView)this.findViewById(R.id.body_part_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
		
		
	}
	

}
