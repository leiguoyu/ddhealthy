package com.dd.medication.service;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.view.WindowManager;

public class MedicationToastService extends Service{

	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		System.out.println("开机启动了服务");
		
		
		return null;
	}

	public void onCreate() {
		System.out.println("---> Service onCreate()");
	}
	
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
		System.out.println("---> Service onStart()");
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		System.out.println("---> Service onStartCommand()");
		return super.onStartCommand(intent, flags, startId);
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		System.out.println("---> Service onDestroy()");
	}
	
	
}
