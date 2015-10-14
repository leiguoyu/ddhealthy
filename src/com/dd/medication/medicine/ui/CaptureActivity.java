package com.dd.medication.medicine.ui;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Vector;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.Zxing.camera.CameraManager;
import com.dd.medication.Zxing.decoding.CaptureActivityHandler;
import com.dd.medication.Zxing.decoding.InactivityTimer;
import com.dd.medication.Zxing.view.ViewfinderView;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.dao.AllProductDao;
import com.dd.medication.medicine.dao.HealthProductDao;
import com.dd.medication.medicine.dao.MedicineDetailDao;
import com.dd.medication.medicine.model.AllProductModel;
import com.dd.medication.medicine.model.HealthProductModel;
import com.dd.medication.medicine.model.MedicineDetailModel;
import com.dd.medication.net.DataSyncUtil;
import com.dd.medication.net.JsonUtil;
import com.dd.medication.util.PhoneUUID;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;

public class CaptureActivity extends BaseActivity implements Callback {

	private CaptureActivityHandler handler;
	private ViewfinderView viewfinderView;
	private boolean hasSurface;
	private Vector<BarcodeFormat> decodeFormats;
	private String characterSet;
	private InactivityTimer inactivityTimer;
	private MediaPlayer mediaPlayer;
	private boolean playBeep;
	private static final float BEEP_VOLUME = 0.10f;
	private boolean vibrate;
	private String registerResult;
	private MedicineDetailModel medicineDetail;
	private HealthProductModel healthProductModel;

	private Handler mHandler = new Handler() {
		@Override
		// 当有消息发送出来的时候就执行Handler的这个方法
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 处理UI
			switch (msg.what) {
			case 4000:
				Intent intent1 = new Intent(context,
						MedicineDetailActivity.class);
				// 将搜索到的数据传递过去
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("medicineDetail",
						(Serializable) medicineDetail);
				intent1.putExtras(mBundle);
				startActivity(intent1);
				break;
			case 4001:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
						.show();
				// 跳转到未搜索到相关药品界面
				Intent intent = new Intent(context,
						NoFoundMedicineActivity.class);
				startActivity(intent);
				break;
			case 4002:
				// 跳转到未搜索到相关药品界面
				Intent intent2 = new Intent(context,
						HealthDetailActivity.class);
				// 将搜索到的数据传递过去
				Bundle mBundle1 = new Bundle();
				mBundle1.putSerializable("healthProductModel",
						(Serializable) healthProductModel);
				intent2.putExtras(mBundle1);
				startActivity(intent2);
				break;
			default:
				break;
			}
		}
	};

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		// 初始化 CameraManager
		CameraManager.init(getApplication());
		viewfinderView = (ViewfinderView) findViewById(R.id.viewfinder_view);
		hasSurface = false;
		inactivityTimer = new InactivityTimer(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		SurfaceView surfaceView = (SurfaceView) findViewById(R.id.preview_view);
		SurfaceHolder surfaceHolder = surfaceView.getHolder();
		if (hasSurface) {
			initCamera(surfaceHolder);
		} else {
			surfaceHolder.addCallback(this);
			surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
		}
		decodeFormats = null;
		characterSet = null;

		playBeep = true;
		AudioManager audioService = (AudioManager) getSystemService(AUDIO_SERVICE);
		if (audioService.getRingerMode() != AudioManager.RINGER_MODE_NORMAL) {
			playBeep = false;
		}
		initBeepSound();
		vibrate = true;
	}

	@Override
	protected void onPause() {
		super.onPause();
		if (handler != null) {
			handler.quitSynchronously();
			handler = null;
		}
		CameraManager.get().closeDriver();
	}

	@Override
	protected void onDestroy() {
		inactivityTimer.shutdown();
		super.onDestroy();
	}

	private void initCamera(SurfaceHolder surfaceHolder) {
		try {
			CameraManager.get().openDriver(surfaceHolder);
		} catch (IOException ioe) {
			return;
		} catch (RuntimeException e) {
			return;
		}
		if (handler == null) {
			handler = new CaptureActivityHandler(this, decodeFormats,
					characterSet);
		}
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {

	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		if (!hasSurface) {
			hasSurface = true;
			initCamera(holder);
		}

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		hasSurface = false;

	}

	public ViewfinderView getViewfinderView() {
		return viewfinderView;
	}

	public Handler getHandler() {
		return handler;
	}

	public void drawViewfinder() {
		viewfinderView.drawViewfinder();

	}

	public void handleDecode(final Result obj, Bitmap barcode) {
		inactivityTimer.onActivity();
		playBeepSoundAndVibrate();

		if (null != barcode && !"".equals(barcode)) {
			searchMedicineByBarcode(String.valueOf(obj));
		}

		// //或取到数据后 将条形码发到药品详情界面
		// Intent intent=new Intent(context, MedicineDetailActivity.class);
		// intent.putExtra("barcode", String.valueOf(obj));
		// startActivity(intent);

		// AlertDialog.Builder dialog = new AlertDialog.Builder(this);
		// if (barcode == null) {
		// dialog.setIcon(null);
		// } else {
		//
		// Drawable drawable = new BitmapDrawable(barcode);
		// dialog.setIcon(drawable);
		// }
		// dialog.setTitle("扫描结果");
		// dialog.setMessage(obj.getText());
		// dialog.setNegativeButton("确定", new DialogInterface.OnClickListener()
		// {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // 用默认浏览器打开扫描得到的地址
		// Intent intent = new Intent();
		// intent.setAction("android.intent.action.VIEW");
		// Uri content_url = Uri.parse(obj.getText());
		// intent.setData(content_url);
		// startActivity(intent);
		// finish();
		// }
		// });
		// dialog.setPositiveButton("取消", new DialogInterface.OnClickListener()
		// {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// finish();
		// }
		// });
		// dialog.create().show();
	}

	private void searchMedicineByBarcode(final String barcode) {
		if (DataSyncUtil.getNetStatus(context)) {
			// 有网络
			new Thread() {
				@Override
				public void run() {
					// 你要执行的方法
					// 执行完毕后给handler发送一个空消息
					// memberId:用户id (没有登录时传递为空字符串)
					// deviceUid:设备唯一标识
					// barcode:条形码
					String memberId = "";// 如果登陆的话有id 没有登录则为空指针
					String deviceUid = PhoneUUID.getPhoneWYBS(context);
					String result = DataSyncUtil.getScanOfBarcode(memberId,
							deviceUid, barcode);
					if (null!=result && !"".equals(result)) {// 这里应该只有一条数据
						boolean resultStr = JsonUtil.getResult(result);
						if (resultStr) {
							ArrayList<AllProductModel> allProductInfo = JsonUtil
									.SerachProductData(result);
							if (allProductInfo != null
									&& allProductInfo.size() > 0) {
								AllProductDao allProductDao = new AllProductDao();
								allProductDao.addAllProduct(allProductInfo
										.get(0));
							}
							// 获取类型 判断是药品还是保健品 //CP_TYPE_01 药品 //保健品CP_TYPE_02
							String productType = JsonUtil
									.getProductType(result);
							if (productType.equals("CP_TYPE_01")) {
								// 获取到的为药品 将data解析并插入数据库
								medicineDetail = JsonUtil
										.getMedicineDetail(result);
								if (medicineDetail != null) {
									// 这里是插入数据库的具体操作 暂时未增加
									MedicineDetailDao medicineDetailDao = new MedicineDetailDao();
									medicineDetailDao
											.addMedicineDetail(medicineDetail);
								}
								mHandler.sendEmptyMessage(4000);
							} else if (productType.equals("CP_TYPE_02")) {
								// 获取到的为保健品
								healthProductModel = JsonUtil
										.getHealthProduct(result);
								if (healthProductModel != null) {
									HealthProductDao healthProductDao = new HealthProductDao();
									healthProductDao
											.addHealthProduct(healthProductModel);
								}
								mHandler.sendEmptyMessage(4002);
							}
							
						} else {
							// 跳转到未搜索到结果页面
							Intent intent = new Intent();
							intent.setClass(context,
									NoFoundMedicineActivity.class);
							startActivity(intent);
						}
					} else {
						// 将data解析并分析出失败原因
						registerResult = JsonUtil.registerJsonFalse(result);
						mHandler.sendEmptyMessage(4001);
					}
				}
			}.start();
		}else{
			Toast.makeText(getApplicationContext(), "请检查网络是否连接！",
					Toast.LENGTH_LONG).show();
		}
	}

	private void initBeepSound() {
		if (playBeep && mediaPlayer == null) {
			// The volume on STREAM_SYSTEM is not adjustable, and users found it
			// too loud,
			// so we now play on the music stream.
			setVolumeControlStream(AudioManager.STREAM_MUSIC);
			mediaPlayer = new MediaPlayer();
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(beepListener);

			AssetFileDescriptor file = getResources().openRawResourceFd(
					R.raw.beep);
			try {
				mediaPlayer.setDataSource(file.getFileDescriptor(),
						file.getStartOffset(), file.getLength());
				file.close();
				mediaPlayer.setVolume(BEEP_VOLUME, BEEP_VOLUME);
				mediaPlayer.prepare();
			} catch (IOException e) {
				mediaPlayer = null;
			}
		}
	}

	private static final long VIBRATE_DURATION = 200L;

	private void playBeepSoundAndVibrate() {
		if (playBeep && mediaPlayer != null) {
			mediaPlayer.start();
		}
		if (vibrate) {
			Vibrator vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
			vibrator.vibrate(VIBRATE_DURATION);
		}
	}

	/**
	 * When the beep has finished playing, rewind to queue up another one.
	 */
	private final OnCompletionListener beepListener = new OnCompletionListener() {
		public void onCompletion(MediaPlayer mediaPlayer) {
			mediaPlayer.seekTo(0);
		}
	};

}