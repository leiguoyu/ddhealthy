package com.dd.medication.medicine.ui;

import java.io.Serializable;
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
import com.dd.medication.medicine.adapter.HealthRecAdapter;
import com.dd.medication.medicine.dao.MyHealthyDao;
import com.dd.medication.medicine.model.MyHealthyModel;

/**
 * ½¡¿µ¼ÇÂ¼ÁÐ±í
 * **/
public class HealthRecActivity extends BaseActivity{
	private ArrayList<MyHealthyModel> listData ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_rec);
		
		ListView healthList=(ListView) this.findViewById(R.id.health_rec_list);
		listData =new ArrayList<MyHealthyModel>();
		MyHealthyDao myHealthyDao=new MyHealthyDao();
		listData=myHealthyDao.getAllMyHealthy();
		if(listData!=null && listData.size()>0){
			HealthRecAdapter adapter=new HealthRecAdapter(context,listData);
			healthList.setAdapter(adapter);	
		}

		healthList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent =new Intent();
				intent.setClass(context, SymptomSettingActivity.class);
		        Bundle mBundle = new Bundle();  
		        MyHealthyModel myHealthyModel=listData.get(position);
		        mBundle.putSerializable("myHealthyModel", myHealthyModel);  
		        intent.putExtras(mBundle);  
				startActivity(intent);
				
			}
		});
		
		TextView back =(TextView)this.findViewById(R.id.health_rec_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		
	
	}

	
}
