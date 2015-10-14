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
 * @Description: 闹铃时间到了会进入这个广播，这个时候可以做一些该做的业务。
 * @author HuHood
 * @date 2013-11-25 下午4:44:30
 *
 */
public class AlarmReceiver extends BroadcastReceiver {
	private Context context;

	@Override
	public void onReceive(final Context context, Intent intent) {
		this.context = context;
		addNotification();// 这里是添加了小系通知
		// 增加系统弹出框提醒用户该吃药了
		// medicineNotifictionDialog();


	
		
		Handler handler = new Handler(Looper.getMainLooper());
		handler.post(new Runnable() {
			@Override
			public void run() { // 调用dialog
				// 获取LayoutInflater实例
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(context.LAYOUT_INFLATER_SERVICE);
				// 这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵呵
				// 该方法返回的是一个View的对象，是布局中的根
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
				// 如何获取我们main中的控件呢？也很简单
				Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// 跳过
				Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// 瞌睡
				Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// 服用
				Button cxsd = (Button) layout
						.findViewById(R.id.medicine_notifiction_cxsd);// 重新设定

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
				Intent intent = new Intent(context, AddMedicineToastActivity.class);// 这是指定点击后跳转到的地方
				// 当点击进入界面后需要删除通知栏通知 即调用 cancleNotification();

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
		notification.tickerText = "我在这里";
		notification.defaults = Notification.DEFAULT_SOUND;
		notification.audioStreamType = android.media.AudioManager.ADJUST_LOWER;

		Intent intent = new Intent(context, AddMedicineToastActivity.class);// 这是指定点击后跳转到的地方
		// 当点击进入界面后需要删除通知栏通知 即调用 cancleNotification();

		PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,
				intent, PendingIntent.FLAG_ONE_SHOT);
		notification.setLatestEventInfo(context, "用药提醒", "亲爱的，晚上8点快到了，记得吃药哦~",
				pendingIntent);
		manager.notify(R.drawable.ic_launcher, notification);
	}

}
