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
			//进入用药提醒设定界面
			
			break;
		case R.id.setting_healp:
			//帮助我们分享点滴健康
			
			break;
		case R.id.setting_sysn:
			//检查更新软件   这里需要一个接口检查是否有新版本   有则提示下载在  下载完成安装
			
			break;
		case R.id.setting_about:
			//关于我们 
			Intent intent=new Intent();
			intent.setClass(context, AboutDetailActivity.class);
			context.startActivity(intent);
			break;

		default:
			break;
		}
		
	}

}
