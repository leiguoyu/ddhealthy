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
 * չʾ���岿λ�б�
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
		
		intent.putExtra("body_part", "ͷ��");
		intent.putExtra("body_part", "�ز�");
		intent.putExtra("body_part", "�ֱ�");
		intent.putExtra("body_part", "����");
		intent.putExtra("body_part", "�Ȳ�");
		intent.putExtra("body_part", "�β�");
		intent.putExtra("body_part", "����");
		
		listView=(ListView) this.findViewById(R.id.body_part_list);
		SymptomPartDao symptomPartDao=new SymptomPartDao();
//		final String[] mItems = getResources().getStringArray(
//				R.array.body_part_tb);
		if(!"".equals(baseParts)){
			System.out.println("=====baseParts====="+baseParts);
			if(baseParts.equals("ͷ��")){
				//�������岿λ���б�    ��һ��Ϊ֢״
				chaild=symptomPartDao.getPartsByBaseParts(baseParts);
			}else if(baseParts.equals("�β�")){
				//�������岿λ���б�   ��һ��Ϊ֢״
				chaild=symptomPartDao.getPartsByBaseParts(baseParts, "1");
			}else{
				//���ز�λ��Ӧ�� ֢״�б�
				
			}
			if(chaild!=null && chaild.size()>0){
				System.out.println("=====chaild.size()====="+chaild.size());
				ChaildPartsAdapter chaildPartsAdapter=new ChaildPartsAdapter(context,chaild);
				listView.setAdapter(chaildPartsAdapter);
				listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// �����ݻ�ȡ������֢״��ʾ�������չʾ
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
