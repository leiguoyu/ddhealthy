package com.dd.medication.login.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.ui.MedicationsMainActivity;
import com.dd.medication.util.DBFileImporter;
import com.dd.medication.util.SharedPreferencesUtil;

/**
 * 引导初叶
 * **/
public class GuideActivity extends BaseActivity {
	private final int SPLASH_DISPLAY_LENGHT = 3000; // 延迟3秒
	private SharedPreferences preferences;
	private Editor editor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.guide_activity);

		//导入数据库
		new DBFileImporter().importDB(context);
		//检测
		preferences = getSharedPreferences("phone", Context.MODE_PRIVATE);
		final boolean firststart = preferences.getBoolean("firststart", true);
		final boolean isFirstLogin = SharedPreferencesUtil
				.getFirstLoginSharedPreferences(context);

		System.out.println("isFirstLogin====" + isFirstLogin);
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				if (firststart && !isFirstLogin) {
					editor = preferences.edit();
					// 将登录标志位设置为false，下次登录时不在显示首次登录界面
					editor.putBoolean("firststart", false);
					editor.commit();
					Intent intent = new Intent();
					intent.setClass(GuideActivity.this,
							GuideViewPagerActivity.class);
					GuideActivity.this.startActivity(intent);
					GuideActivity.this.finish();
				} else if (!firststart && isFirstLogin) {
					// 这里需要判断 是否登陆过 如果登陆过有记录则直接进入主界面 如果没有则到登陆界面

					Intent intent = new Intent();
					intent.setClass(GuideActivity.this,
							MedicationsMainActivity.class);
					GuideActivity.this.startActivity(intent);
					GuideActivity.this.finish();
				} else {
					Intent intent = new Intent();
					intent.setClass(GuideActivity.this, LoginActivity.class);
					GuideActivity.this.startActivity(intent);
					GuideActivity.this.finish();
				}

			}
		}, SPLASH_DISPLAY_LENGHT);
	}

}
