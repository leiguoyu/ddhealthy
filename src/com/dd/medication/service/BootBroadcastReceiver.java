package com.dd.medication.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * 监听开机启动的广播
 * */
public class BootBroadcastReceiver extends BroadcastReceiver {
	// 重写onReceive方法
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent service = new Intent(context, MedicationToastService.class);
			context.startService(service);
			Log.v("TAG", "开机自动服务自动启动.....");
		}

		// 启动应用，参数为需要自动启动的应用的包名
		// Intent intent =
		// context.getPackageManager().getLaunchIntentForPackage(
		// packageName);
		// context.startActivity(intent);
	}

}