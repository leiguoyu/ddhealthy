package com.dd.medication.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

/**
 * �������������Ĺ㲥
 * */
public class BootBroadcastReceiver extends BroadcastReceiver {
	// ��дonReceive����
	@Override
	public void onReceive(Context context, Intent intent) {
		if (intent.getAction().equals(Intent.ACTION_BOOT_COMPLETED)) {
			Intent service = new Intent(context, MedicationToastService.class);
			context.startService(service);
			Log.v("TAG", "�����Զ������Զ�����.....");
		}

		// ����Ӧ�ã�����Ϊ��Ҫ�Զ�������Ӧ�õİ���
		// Intent intent =
		// context.getPackageManager().getLaunchIntentForPackage(
		// packageName);
		// context.startActivity(intent);
	}

}