package com.dd.medication.setting.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;

import com.dd.medication.R;
import com.dd.medication.about.ui.AboutDetailActivity;
import com.dd.medication.base.ui.BaseActivity;

public class SettingActivity extends BaseActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.setting);
		
		RadioButton yytxsd=(RadioButton) this.findViewById(R.id.setting_yytxsd);
		RadioButton help=(RadioButton) this.findViewById(R.id.setting_healp);
		RadioButton sysn=(RadioButton) this.findViewById(R.id.setting_sysn);
		RadioButton about=(RadioButton) this.findViewById(R.id.setting_about);

		yytxsd.setOnClickListener(this);
		help.setOnClickListener(this);
		sysn.setOnClickListener(this);
		about.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.setting_yytxsd:
			//������ҩ�����趨����
			
			break;
		case R.id.setting_healp:
			//�������Ƿ����ν���
			
			break;
		case R.id.setting_sysn:
			//���������   ������Ҫһ���ӿڼ���Ƿ����°汾   ������ʾ������  ������ɰ�װ
			
			break;
		case R.id.setting_about:
			//�������� 
			Intent intent=new Intent();
			intent.setClass(context, AboutDetailActivity.class);
			context.startActivity(intent);
			break;

		default:
			break;
		}
		
	}

}
