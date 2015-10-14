package com.dd.medication.service;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
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
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.medicine.ui.AddMedicineToastActivity;

/**
 * 
 * @ClassName: AlarmReceiver
 * @Description: ����ʱ�䵽�˻��������㲥�����ʱ�������һЩ������ҵ��
 * @author HuHood
 * @date 2013-11-25 ����4:44:30
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	private Context context;

	@Override
	public void onReceive(final Context context, Intent intent) {
		this.context = context;
		addNotification();// �����������Сϵ֪ͨ
		// ����ϵͳ�����������û��ó�ҩ��
		// medicineNotifictionDialog();


	
		
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			@Override
			public void run() { // ����dialog
				// ��ȡLayoutInflaterʵ��
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(context.LAYOUT_INFLATER_SERVICE);
				// �����main��������inflate�м����Ŷ����ǰ����ֱ��this.setContentView()�İɣ��Ǻ�
				// �÷������ص���һ��View�Ķ����ǲ����еĸ�
				View layout = inflater.inflate(R.layout.medicine_notifiction, null);
				AlertDialog.Builder builder = new AlertDialog.Builder(context);
				builder.setView(layout);
				final AlertDialog dia = builder.create();
				dia.setCanceledOnTouchOutside(true);
				dia.getWindow().setType(
						WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG);
				dia.getWindow().setType(
						WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
				dia.show();
				// ��λ�ȡ����main�еĿؼ��أ�Ҳ�ܼ�
				Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// ����
				Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// �˯
				Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// ����
				Button cxsd = (Button) layout
						.findViewById(R.id.medicine_notifiction_cxsd);// �����趨

				tg.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dia.dismiss();
					}
				});

				ks.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dia.dismiss();
					}
				});

				fy.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {
						dia.dismiss();
					}
				});

				cxsd.setOnClickListener(new View.OnClickListener() {
					@Override
					public void onClick(View arg0) {

						dia.dismiss();
					}
				});
			}
		});

		
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
				menuWindow.dismiss();
			}
		});

		ks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuWindow.dismiss();
			}
		});

		fy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				menuWindow.dismiss();
			}
		});

		cxsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Intent intent = new Intent(context, AddMedicineToastActivity.class);// ����ָ���������ת���ĵط�
				// ���������������Ҫɾ��֪ͨ��֪ͨ ������ cancleNotification();

				PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
						intent, PendingIntent.FLAG_ONE_SHOT);
				menuWindow.dismiss();
			}
		});

	}

	private void cancleNotification() {
		// TODO Auto-generated method stub
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		manager.cancel(R.drawable.ic_launcher);
		Toast.makeText(context, "Notification cancled", Toast.LENGTH_SHORT)
				.show();
	}

	private void addNotification() {
		NotificationManager manager = (NotificationManager) context
				.getSystemService(Context.NOTIFICATION_SERVICE);
		Notification notification = new Notification();
		notification.icon = R.drawable.ic_launcher;
		notification.tickerText = "��������";
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;

		Intent intent = new Intent(context, AddMedicineToastActivity.class);// ����ָ���������ת���ĵط�
		// ���������������Ҫɾ��֪ͨ��֪ͨ ������ cancleNotification();

		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		notification.setLatestEventInfo(context, "��ҩ����", "�װ��ģ�����8��쵽�ˣ��ǵó�ҩŶ~",
				pendingIntent);
		manager.notify(R.drawable.ic_launcher, notification);
	}

}
