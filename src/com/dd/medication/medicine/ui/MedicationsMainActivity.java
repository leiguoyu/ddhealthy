package com.dd.medication.medicine.ui;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.about.ui.AboutActivity;
import com.dd.medication.setting.ui.SettingActivity;
import com.dd.medication.timeline.ui.TimeLineActivity;

public class MedicationsMainActivity extends TabActivity {
	/** Called when the activity is first created. */
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.medications_main_activity);

		Resources res = getResources(); // Resource object to get Drawables
		TabHost tabHost = getTabHost(); // The activity TabHost
		TabHost.TabSpec spec; // Resusable TabSpec for each tab
		Intent intent; // Reusable Intent for each tab

		// Create an Intent to launch an Activity for the tab (to be reused)
		intent = new Intent().setClass(this, TimeLineActivity.class);

		// Initialize a TabSpec for each tab and add it to the TabHost
		spec = tabHost.newTabSpec("timeline")
				.setIndicator(getMenuItem(R.drawable.my_plain, "足迹"))
				.setContent(intent);
		tabHost.addTab(spec);

		// Do the same for the other tabs
		intent = new Intent().setClass(this, MedicationsActivity.class);
		spec = tabHost.newTabSpec("medications")
				.setIndicator(getMenuItem(R.drawable.setting_menu, "健康助理"))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, AboutActivity.class);
		spec = tabHost.newTabSpec("about")
				.setIndicator(getMenuItem(R.drawable.about_me, "我"))
				.setContent(intent);
		tabHost.addTab(spec);

		intent = new Intent().setClass(this, SettingActivity.class);
		spec = tabHost
				.newTabSpec("seeting")
				.setIndicator(getMenuItem(R.drawable.system_setting_menu, "设置"))
				.setContent(intent);
		tabHost.addTab(spec);
		
		tabHost.setCurrentTab(0);
	}

	/**
	 * 需要一个selector文件
	 * 
	 * @param resId
	 * @param textID
	 * @return
	 */
	public View getMenuItem(int resid, String textID) {
		LinearLayout ll = (LinearLayout) LayoutInflater.from(
				MedicationsMainActivity.this).inflate(
				R.layout.tab_bottom_menu_item, null);
		ImageView imgView = (ImageView) ll.findViewById(R.id.icon);
		imgView.setBackgroundResource(resid);
		TextView textView = (TextView) ll.findViewById(R.id.name);
		textView.setText(textID);
		return ll;
	}

	private long firstTime = 0;

	@Override
	public boolean onKeyUp(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		switch (keyCode) {
		case KeyEvent.KEYCODE_BACK:
			long secondTime = System.currentTimeMillis();
			if (secondTime - firstTime > 2000) { // 如果两次按键时间间隔大于2秒，则不退出
				Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
				firstTime = secondTime;// 更新firstTime
				return true;
			} else { // 两次按键小于2秒时，退出应用
				System.exit(0);
			}
			break;
		}
		return super.onKeyUp(keyCode, event);
	}

}