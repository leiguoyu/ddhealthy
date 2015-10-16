package com.dd.medication.service;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.medicine.ui.AddMedicineToastActivity;
import com.dd.medication.util.DateUtil;

/**
 * 
 * @ClassName: AlarmReceiver
 * @Description: 闹铃时间到了会进入这个广播，这个时候可以做一些该做的业务。
 * @date 2013-11-25 下午4:44:30
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	private Context context;
	private MedicationRemind medicationRemind;
	private AlertDialog builder;

	@Override
	public void onReceive(final Context context, Intent intent) {
		this.context = context;
		
		addNotification();// 这里是添加了小系通知
		// 增加系统弹出框提醒用户该吃药了
		// medicineNotifictionDialog();
		if(builder==null){
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() { // 调用dialog
					//获取初始化数据
					String hourMinute = DateUtil.getHourMinute();
					ArrayList<MedicationRemind> dataList=new MedicationRemindDao().getAlertDay1(DateUtil.getYearMonthDate());
					if(dataList!=null && dataList.size()>0){
						for (int i = 0; i < dataList.size(); i++) {
							//这里有点偷懒写成  一定时间区域内的数据
							String alertDay=dataList.get(i).getAlertTime();
							System.out.println("onReceive==alertDay===="+alertDay);
							System.out.println("onReceive==hourMinute===="+hourMinute);
							String str[]=alertDay.split(":");
							if(hourMinute.contains(str[0]) 
									|| hourMinute.contains(String.valueOf(Integer.valueOf(str[0])+1))
									|| hourMinute.contains(String.valueOf(Integer.valueOf(str[0])-1))){
								medicationRemind=dataList.get(i);
								System.out.println("匹配到！！！！====hourMinute==="+hourMinute);
								break;
							}
						}
					}
					
					// 获取LayoutInflater实例
					LayoutInflater inflater = (LayoutInflater) context
							.getSystemService(context.LAYOUT_INFLATER_SERVICE);
					// 这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵呵
					// 该方法返回的是一个View的对象，是布局中的根
					View layout = inflater.inflate(R.layout.medicine_notifiction, null);
					builder = new AlertDialog.Builder(context).create();
					builder.setView(layout);
					builder.setCanceledOnTouchOutside(true);
					builder.getWindow().setType(
							WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
					builder.getWindow().setType(
							WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
					builder.show();
					// 如何获取我们main中的控件呢？也很简单
					TextView medicationName=(TextView) layout.findViewById(R.id.medicine_notifiction_medication_name);
					TextView alertTime=(TextView) layout.findViewById(R.id.medicine_notifiction_alert_time);
					TextView fysl=(TextView) layout.findViewById(R.id.medicine_notifiction_fysl);
					if(medicationRemind!=null){
						medicationName.setText(medicationRemind.getMedicationName());
						alertTime.setText(hourMinute/*medicationRemind.getAlertTime()*/);
						fysl.setText("服用"+medicationRemind.getSingleDose()+medicationRemind.getUnits());	
					}
					
					Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// 跳过
					Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// 瞌睡
					Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// 服用
					Button cxsd = (Button) layout
							.findViewById(R.id.medicine_notifiction_cxsd);// 重新设定

					tg.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							cancelTost();
							
							Intent intent = new Intent(context, AlarmReceiver.class);
							PendingIntent pi = PendingIntent.getBroadcast(
									context, DateUtil.getyyyyMMddHHmm(), intent, 0);
							AlarmManager am = (AlarmManager) context.getSystemService("alarm");
							// 取消警报
							am.cancel(pi);
							
							//更新数据库  status 3：未服用		stop  1关闭  
							if(medicationRemind!=null){
								medicationRemind.setStatus(3);
								medicationRemind.setStop(1);
								new MedicationRemindDao().updateMedicationRemind(medicationRemind, 
										"medicationRemindId=?",
										new String[]{String.valueOf(medicationRemind.getMedicationRemindId())});
							}
						}
					});

					ks.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							
							cancelTost();
							
							//更新数据库  status 2：延迟服用 
							if(medicationRemind!=null){
								medicationRemind.setStatus(2);
								new MedicationRemindDao().updateMedicationRemind(medicationRemind, 
										"medicationRemindId=?",
										new String[]{String.valueOf(medicationRemind.getMedicationRemindId())});
							}
							
							//延迟需要重新设置    更新相关数据库
							Calendar cal = Calendar.getInstance();
							AlarmManager am = (AlarmManager) context.getSystemService("alarm");
							Intent intent = new Intent(context, AlarmReceiver.class);
							PendingIntent sender = PendingIntent.getBroadcast(context, DateUtil.getyyyyMMddHHmm(),
									intent, 0);
							//十分钟后再次提醒
							am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis()+1000*60*10, sender);
						}
					});

					fy.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							cancelTost();
							//这里要更改服用时间数据 表示已经服用   
//							stop    1关闭      
//							closeTime  实际服用时间  
//							status  0 进行中 1:正常服用 2：延迟服用 3：未服用   //非2.3改为1
							
							if(medicationRemind!=null){
								if(medicationRemind.getStatus()!=2 || medicationRemind.getStatus()!=3){
									medicationRemind.setStatus(1);
								}
								String closeTime = DateUtil.getHourMinute();
								medicationRemind.setCloseTime(closeTime);
								medicationRemind.setStop(1);
								new MedicationRemindDao().updateMedicationRemind(medicationRemind, 
										"medicationRemindId=?",
										new String[]{String.valueOf(medicationRemind.getMedicationRemindId())});
							}
						}
					});

					cxsd.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							cancelTost();
							Intent intent = new Intent(context, AlarmReceiver.class);
							PendingIntent pi = PendingIntent.getBroadcast(
									context, DateUtil.getyyyyMMddHHmm(), intent, 0);
							AlarmManager am = (AlarmManager) context.getSystemService("alarm");
							// 取消警报
							am.cancel(pi);
							//更改数据库  	status 3：未服用		stop  1关闭     
							
							if(medicationRemind!=null){
								medicationRemind.setStatus(3);
								medicationRemind.setStop(1);
								new MedicationRemindDao().updateMedicationRemind(medicationRemind, 
										"medicationRemindId=?",
										new String[]{String.valueOf(medicationRemind.getMedicationRemindId())});
							}
							
							Intent intent1 = new Intent(context, AddMedicineToastActivity.class);// 这是指定点击后跳转到的地方
							intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							context.startActivity(intent1);
						}
					});
				}
			});	
		}


		
	}

	
	private void cancelTost() {
		cancleNotification();
		if(builder!=null){
			((DialogInterface) builder).dismiss();
			builder=null;	
		}
	}
	
	/**
	 * 用药提醒系统弹出框
	 * */
	private void medicineNotifictionDialog() {
		// TODO Auto-generated method stub

		// 获取LayoutInflater实例
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		// 这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵呵
		// 该方法返回的是一个View的对象，是布局中的根
		View layout = inflater.inflate(R.layout.medicine_notifiction, null);
		// 下面我们要考虑了，我怎样将我的layout加入到PopupWindow中呢？？？很简单
		final PopupWindow menuWindow = new PopupWindow(layout,
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); // 后两个参数是width和height
		// menuWindow.showAsDropDown(layout); //设置弹出效果
		// menuWindow.showAsDropDown(null, 0, layout.getHeight());
		// 设置如下四条信息，当点击其他区域使其隐藏，要在show之前配置
		menuWindow.setFocusable(true);
		menuWindow.setOutsideTouchable(true);
		menuWindow.update();
		menuWindow.setBackgroundDrawable(new BitmapDrawable());
		// 如何获取我们main中的控件呢？也很简单
		Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// 跳过
		Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// 瞌睡
		Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// 服用
		Button cxsd = (Button) layout
				.findViewById(R.id.medicine_notifiction_cxsd);// 重新设定

		tg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				cancleNotification();
				
				Intent intent = new Intent(context, AlarmReceiver.class);
				PendingIntent pi = PendingIntent.getBroadcast(
						context, DateUtil.getyyyyMMddHHmm(), intent, 0);
				AlarmManager am = (AlarmManager) context.getSystemService("alarm");
				// 取消警报
				am.cancel(pi);
				
				//更新数据库  status 3：未服用		stop  1关闭     
				
				menuWindow.dismiss();
			}
		});

		ks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				cancleNotification();
				
				//更新数据库  status 2：延迟服用 
				
				
				menuWindow.dismiss();
				//延迟需要重新设置    更新相关数据库
				Calendar cal = Calendar.getInstance();
				AlarmManager am = (AlarmManager) context.getSystemService("alarm");
				Intent intent = new Intent(context, AlarmReceiver.class);
				PendingIntent sender = PendingIntent.getBroadcast(context, DateUtil.getyyyyMMddHHmm(),
						intent, 0);
				//十分钟后再次提醒
				am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis()+1000*60*10, sender);
			}
		});

		fy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cancleNotification() ;
				menuWindow.dismiss();
				//这里要更改服用时间数据 表示已经服用   
//				stop    1关闭      
//				closeTime  实际服用时间  
//				status  0 进行中 1:正常服用 2：延迟服用 3：未服用   //非2.3改为1

				
			}
		});

		cxsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, AlarmReceiver.class);
				PendingIntent pi = PendingIntent.getBroadcast(
						context, DateUtil.getyyyyMMddHHmm(), intent, 0);
				AlarmManager am = (AlarmManager) context.getSystemService("alarm");
				// 取消警报
				am.cancel(pi);
				
				cancleNotification();

				menuWindow.dismiss();
				
				//更改数据库  	status 3：未服用		stop  1关闭     
				
				  
				
				Intent intent1 = new Intent(context, AddMedicineToastActivity.class);// 这是指定点击后跳转到的地方
				context.startActivity(intent1);
				
			}
		});

	}

	private void cancleNotification() {
		// TODO Auto-generated method stub
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(R.drawable.icon);
	}

	private void addNotification() {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification();
		notification.icon = R.drawable.icon;
		notification.tickerText = "点滴健康";
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;

		Intent intent = new Intent(context, AddMedicineToastActivity.class);// 这是指定点击后跳转到的地方
		// 当点击进入界面后需要删除通知栏通知 即调用 cancleNotification();

		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		notification.setLatestEventInfo(context, "用药提醒", "亲爱的,记得吃药哦~",
				pendingIntent);
		manager.notify(R.drawable.icon, notification);
	}

}
