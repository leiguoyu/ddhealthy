package com.dd.medication.about.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.login.ui.LoginActivity;
import com.dd.medication.login.ui.RegisterActivity;
import com.dd.medication.medicine.dao.AllProductDao;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.dao.MyHealthyDao;
import com.dd.medication.medicine.model.MyHealthyModel;
import com.dd.medication.medicine.ui.MedicationsMainActivity;

import android.view.View.OnClickListener;

public class AboutActivity extends BaseActivity implements OnClickListener{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about);
		//by andy
		intView();
		
	}
	
	private void intView() {
		Button loginBtn = (Button) this.findViewById(R.id.about_login);
	//	Button registerBtn = (Button) this.findViewById(R.id.about_register);
		loginBtn.setOnClickListener(this);
	//	registerBtn.setOnClickListener(this);
		ImageView yaopinjilu=(ImageView) this.findViewById(R.id.yaopinjilu);
		ImageView jiankangjilu=(ImageView) this.findViewById(R.id.jiankangjilu);
		ImageView selectedRecord=(ImageView) this.findViewById(R.id.selectedRecord);
		//ҩƷ��¼��
		TextView yaopinRecCount=(TextView) this.findViewById(R.id.yaopinjilucount);
	    //������¼��
		TextView jiankangRecCount=(TextView) this.findViewById(R.id.jiankangRecCount);
		//������¼��
		TextView selectedRecCount=(TextView) this.findViewById(R.id.selectedRecCount);
	
		//��ȡҩƷ��¼���ݽ���չʾ
		int yaopincount=new MedicationRemindDao().getCount();
		yaopinRecCount.setText(yaopincount+"");
		//��ȡ������¼���ݽ���չʾ
		int jiankangCount = new MyHealthyDao().getCount();
		jiankangRecCount.setText(jiankangCount+"");
		//��ȡ������¼���ݽ���չʾ
		int selectedCount=new AllProductDao().getCount();
		selectedRecCount.setText(selectedCount+"");
	
	}
	
	//by andy
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/*
		case R.id.about_register:
			// ע�ᰴť  ��ת��ע�����
            Intent intent =new Intent(AboutActivity.this, RegisterActivity.class);
            startActivity(intent);
			break;*/
			//δ����¼ҳ����ʱ��ת��ע�ᡣ
		case R.id.about_login:
			// ������ť  ֱ�ӽ�����ҩ������
			Intent intent1 =new Intent(AboutActivity.this, RegisterActivity.class);
	        startActivity(intent1);
			break;
		case R.id.login_qq:
			// ������¼qq
			break;
		case R.id.login_weeixin:
			// ������¼ ΢��
			break;
		case R.id.login_weeibo:
			// ������¼ ΢��
			break;

		default:
			break;
		}

	}
	
}
