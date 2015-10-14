package com.dd.medication.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.net.DataSyncUtil;
import com.dd.medication.net.JsonUtil;
import com.dd.medication.util.SharedPreferencesUtil;

/**
 * ע�����
 * **/
public class RegisterActivity extends BaseActivity implements OnClickListener {
	private EditText phoneNum,passwordEdit,yzmEdit;
	private String  registerResult;
	// ����Handler����
	private Handler handler = new Handler() {
		@Override
		// ������Ϣ���ͳ�����ʱ���ִ��Handler���������
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ����UI
			switch (msg.what) {
			case 1000:
				Toast.makeText(context, "��ϲ�㣬ע��ɹ���", Toast.LENGTH_SHORT).show();
				SharedPreferencesUtil.setFirstLoginSharedPreferences(context, "isFirstLogin", true);
				Intent intent = new Intent(RegisterActivity.this,
						SexualSelectionActivity.class);
				startActivity(intent);
				break;
			case 1001:
				Toast.makeText(context, "ע��ʧ�ܣ����ٴγ��ԣ�", Toast.LENGTH_SHORT).show();
				break;
			case 1002:
				Toast.makeText(context, "��ʾ��֤���ѷ��ͣ���ע����գ�", Toast.LENGTH_SHORT).show();
				break;
			case 1003:
				Toast.makeText(context, "��֤�뷢��ʧ�ܣ���������������ٴγ��ԣ�", Toast.LENGTH_SHORT).show();
				break;
			case 1004:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);

		intView();

	}

	private void intView() {
		phoneNum = (EditText) this.findViewById(R.id.phone_num_edit);
		passwordEdit = (EditText) this.findViewById(R.id.password_edit);
		yzmEdit = (EditText) this.findViewById(R.id.yzm_edit);
		
		Button getYzm = (Button) this.findViewById(R.id.get_yzm_but);// ��ȡ��֤��
		getYzm.setOnClickListener(this);
		Button login = (Button) this.findViewById(R.id.register_login);
		login.setOnClickListener(this);
		Button cancel = (Button) this.findViewById(R.id.register_cancel);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get_yzm_but:
			// ��ȡ��֤��
			final String phone = phoneNum.getText().toString();
			if (!"".equals(phone)) {
				// �����ȡ��֤�����ע����֤
				if (DataSyncUtil.getNetStatus(context)) {
					// ������
					new Thread() {
						@Override
						public void run() {
							// ��Ҫִ�еķ���
							// ִ����Ϻ��handler����һ������Ϣ
							boolean result=DataSyncUtil.getVeriCode(phone);
							if(result){
								//��ʾ��֤���ѷ��ͣ���ע�����
								handler.sendEmptyMessage(1002);
							}else{
								//��ʾ��ȡ��֤��ʧ��   �Ƿ������쳣
								handler.sendEmptyMessage(1003);
							}
						}
					}.start();
				} else {
					// ��ʾ�û������· ����
					Toast.makeText(context, "���������Ƿ�����", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(context, "������绰����", Toast.LENGTH_SHORT).show();
			}
			break;
			
		case R.id.register_login:
			// ��ת���Ա�ѡ�����
			final String phone1= phoneNum.getText().toString();
			final String password=passwordEdit.getText().toString();
			final String yzm=yzmEdit.getText().toString();
			if ("".equals(phone1)) {
				Toast.makeText(context, "�绰���벻��Ϊ��", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(password)) {
				Toast.makeText(context, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(yzm)) {
				Toast.makeText(context, "��֤�벻��Ϊ��", Toast.LENGTH_SHORT).show();
				return;
			}
			
			if (DataSyncUtil.getNetStatus(context)) {
				// ������
				new Thread() {
					@Override
					public void run() {
						// ��Ҫִ�еķ���
						// ִ����Ϻ��handler����һ������Ϣ
						String result=DataSyncUtil.getregister(phone1,password,yzm);
						if(!"".equals(result)){
							boolean resultStr=JsonUtil.getResult(result);
							if(resultStr){
								//��data�������������ݿ�
								String[] registerResult=JsonUtil.registerData(result);
								
								handler.sendEmptyMessage(1000);
							}else{
								//��data������������ʧ��ԭ��
								 registerResult=JsonUtil.registerJsonFalse(result);
								 handler.sendEmptyMessage(1004);
							}
						}else{
							handler.sendEmptyMessage(1001);
						}
					}
				}.start();
			} else {
				// ��ʾ�û������· ����
				Toast.makeText(context, "���������Ƿ�����", Toast.LENGTH_SHORT)
						.show();
			}

			break;
			
		default:
			break;
		}
	}

}
