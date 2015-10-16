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
 * @Description: ����ʱ�䵽�˻��������㲥�����ʱ�������һЩ������ҵ��
 * @date 2013-11-25 ����4:44:30
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	private Context context;
	private MedicationRemind medicationRemind;
	private AlertDialog builder;

	@Override
	public void onReceive(final Context context, Intent intent) {
		this.context = context;
		
		addNotification();// �����������Сϵ֪ͨ
		// ����ϵͳ�����������û��ó�ҩ��
		// medicineNotifictionDialog();
		if(builder==null){
			Handler handler = new Handler(Looper.getMainLooper());
			handler.post(new Runnable() {
				@Override
				public void run() { // ����dialog
					//��ȡ��ʼ������
					String hourMinute = DateUtil.getHourMinute();
					ArrayList<MedicationRemind> dataList=new MedicationRemindDao().getAlertDay1(DateUtil.getYearMonthDate());
					if(dataList!=null && dataList.size()>0){
						for (int i = 0; i < dataList.size(); i++) {
							//�����е�͵��д��  һ��ʱ�������ڵ�����
							String alertDay=dataList.get(i).getAlertTime();
							System.out.println("onReceive==alertDay===="+alertDay);
							System.out.println("onReceive==hourMinute===="+hourMinute);
							String str[]=alertDay.split(":");
							if(hourMinute.contains(str[0]) 
									|| hourMinute.contains(String.valueOf(Integer.valueOf(str[0])+1))
									|| hourMinute.contains(String.valueOf(Integer.valueOf(str[0])-1))){
								medicationRemind=dataList.get(i);
								System.out.println("ƥ�䵽��������====hourMinute==="+hourMinute);
								break;
							}
						}
					}
					
					// ��ȡLayoutInflaterʵ��
					LayoutInflater inflater = (LayoutInflater) context
							.getSystemService(context.LAYOUT_INFLATER_SERVICE);
					// �����main��������inflate�м����Ŷ����ǰ����ֱ��this.setContentView()�İɣ��Ǻ�
					// �÷������ص���һ��View�Ķ����ǲ����еĸ�
					View layout = inflater.inflate(R.layout.medicine_notifiction, null);
					builder = new AlertDialog.Builder(context).create();
					builder.setView(layout);
					builder.setCanceledOnTouchOutside(true);
					builder.getWindow().setType(
							WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
					builder.getWindow().setType(
							WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
					builder.show();
					// ��λ�ȡ����main�еĿؼ��أ�Ҳ�ܼ�
					TextView medicationName=(TextView) layout.findViewById(R.id.medicine_notifiction_medication_name);
					TextView alertTime=(TextView) layout.findViewById(R.id.medicine_notifiction_alert_time);
					TextView fysl=(TextView) layout.findViewById(R.id.medicine_notifiction_fysl);
					if(medicationRemind!=null){
						medicationName.setText(medicationRemind.getMedicationName());
						alertTime.setText(hourMinute/*medicationRemind.getAlertTime()*/);
						fysl.setText("����"+medicationRemind.getSingleDose()+medicationRemind.getUnits());	
					}
					
					Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// ����
					Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// �˯
					Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// ����
					Button cxsd = (Button) layout
							.findViewById(R.id.medicine_notifiction_cxsd);// �����趨

					tg.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							cancelTost();
							
							Intent intent = new Intent(context, AlarmReceiver.class);
							PendingIntent pi = PendingIntent.getBroadcast(
									context, DateUtil.getyyyyMMddHHmm(), intent, 0);
							AlarmManager am = (AlarmManager) context.getSystemService("alarm");
							// ȡ������
							am.cancel(pi);
							
							//�������ݿ�  status 3��δ����		stop  1�ر�  
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
							
							//�������ݿ�  status 2���ӳٷ��� 
							if(medicationRemind!=null){
								medicationRemind.setStatus(2);
								new MedicationRemindDao().updateMedicationRemind(medicationRemind, 
										"medicationRemindId=?",
										new String[]{String.valueOf(medicationRemind.getMedicationRemindId())});
							}
							
							//�ӳ���Ҫ��������    ����������ݿ�
							Calendar cal = Calendar.getInstance();
							AlarmManager am = (AlarmManager) context.getSystemService("alarm");
							Intent intent = new Intent(context, AlarmReceiver.class);
							PendingIntent sender = PendingIntent.getBroadcast(context, DateUtil.getyyyyMMddHHmm(),
									intent, 0);
							//ʮ���Ӻ��ٴ�����
							am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis()+1000*60*10, sender);
						}
					});

					fy.setOnClickListener(new View.OnClickListener() {
						@Override
						public void onClick(View arg0) {
							cancelTost();
							//����Ҫ���ķ���ʱ������ ��ʾ�Ѿ�����   
//							stop    1�ر�      
//							closeTime  ʵ�ʷ���ʱ��  
//							status  0 ������ 1:�������� 2���ӳٷ��� 3��δ����   //��2.3��Ϊ1
							
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
							// ȡ������
							am.cancel(pi);
							//�������ݿ�  	status 3��δ����		stop  1�ر�     
							
							if(medicationRemind!=null){
								medicationRemind.setStatus(3);
								medicationRemind.setStop(1);
								new MedicationRemindDao().updateMedicationRemind(medicationRemind, 
										"medicationRemindId=?",
										new String[]{String.valueOf(medicationRemind.getMedicationRemindId())});
							}
							
							Intent intent1 = new Intent(context, AddMedicineToastActivity.class);// ����ָ���������ת���ĵط�
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
	 * ��ҩ����ϵͳ������
	 * */
	private void medicineNotifictionDialog() {
		// TODO Auto-generated method stub

		// ��ȡLayoutInflaterʵ��
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		// �����main��������inflate�м����Ŷ����ǰ����ֱ��this.setContentView()�İɣ��Ǻ�
		// �÷������ص���һ��View�Ķ����ǲ����еĸ�
		View layout = inflater.inflate(R.layout.medicine_notifiction, null);
		// ��������Ҫ�����ˣ����������ҵ�layout���뵽PopupWindow���أ������ܼ�
		final PopupWindow menuWindow = new PopupWindow(layout,
				LayoutParams.FILL_PARENT, LayoutParams.WRAP_CONTENT); // ������������width��height
		// menuWindow.showAsDropDown(layout); //���õ���Ч��
		// menuWindow.showAsDropDown(null, 0, layout.getHeight());
		// ��������������Ϣ���������������ʹ�����أ�Ҫ��show֮ǰ����
		menuWindow.setFocusable(true);
		menuWindow.setOutsideTouchable(true);
		menuWindow.update();
		menuWindow.setBackgroundDrawable(new BitmapDrawable());
		// ��λ�ȡ����main�еĿؼ��أ�Ҳ�ܼ�
		Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// ����
		Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// �˯
		Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// ����
		Button cxsd = (Button) layout
				.findViewById(R.id.medicine_notifiction_cxsd);// �����趨

		tg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				cancleNotification();
				
				Intent intent = new Intent(context, AlarmReceiver.class);
				PendingIntent pi = PendingIntent.getBroadcast(
						context, DateUtil.getyyyyMMddHHmm(), intent, 0);
				AlarmManager am = (AlarmManager) context.getSystemService("alarm");
				// ȡ������
				am.cancel(pi);
				
				//�������ݿ�  status 3��δ����		stop  1�ر�     
				
				menuWindow.dismiss();
			}
		});

		ks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				
				cancleNotification();
				
				//�������ݿ�  status 2���ӳٷ��� 
				
				
				menuWindow.dismiss();
				//�ӳ���Ҫ��������    ����������ݿ�
				Calendar cal = Calendar.getInstance();
				AlarmManager am = (AlarmManager) context.getSystemService("alarm");
				Intent intent = new Intent(context, AlarmReceiver.class);
				PendingIntent sender = PendingIntent.getBroadcast(context, DateUtil.getyyyyMMddHHmm(),
						intent, 0);
				//ʮ���Ӻ��ٴ�����
				am.set(AlarmManager.RTC_WAKEUP,cal.getTimeInMillis()+1000*60*10, sender);
			}
		});

		fy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				cancleNotification() ;
				menuWindow.dismiss();
				//����Ҫ���ķ���ʱ������ ��ʾ�Ѿ�����   
//				stop    1�ر�      
//				closeTime  ʵ�ʷ���ʱ��  
//				status  0 ������ 1:�������� 2���ӳٷ��� 3��δ����   //��2.3��Ϊ1

				
			}
		});

		cxsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, AlarmReceiver.class);
				PendingIntent pi = PendingIntent.getBroadcast(
						context, DateUtil.getyyyyMMddHHmm(), intent, 0);
				AlarmManager am = (AlarmManager) context.getSystemService("alarm");
				// ȡ������
				am.cancel(pi);
				
				cancleNotification();

				menuWindow.dismiss();
				
				//�������ݿ�  	status 3��δ����		stop  1�ر�     
				
				  
				
				Intent intent1 = new Intent(context, AddMedicineToastActivity.class);// ����ָ���������ת���ĵط�
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
		notification.tickerText = "��ν���";
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;

		Intent intent = new Intent(context, AddMedicineToastActivity.class);// ����ָ���������ת���ĵط�
		// ���������������Ҫɾ��֪ͨ��֪ͨ ������ cancleNotification();

		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		notification.setLatestEventInfo(context, "��ҩ����", "�װ���,�ǵó�ҩŶ~",
				pendingIntent);
		manager.notify(R.drawable.icon, notification);
	}

}
