package com.dd.medication.about.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.login.ui.LoginActivity;
import com.dd.medication.login.ui.RegisterActivity;
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
	}
	
	//by andy
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		/*
		case R.id.about_register:
			// 注册按钮  跳转到注册界面
            Intent intent =new Intent(AboutActivity.this, RegisterActivity.class);
            startActivity(intent);
			break;*/
			//未做登录页，暂时跳转到注册。
		case R.id.about_login:
			// 跳过按钮  直接进入用药主界面
			Intent intent1 =new Intent(AboutActivity.this, RegisterActivity.class);
	        startActivity(intent1);
			break;
		case R.id.login_qq:
			// 三方登录qq
			break;
		case R.id.login_weeixin:
			// 三方登录 微信
			break;
		case R.id.login_weeibo:
			// 三方登录 微博
			break;

		default:
			break;
		}

	}
	
}
