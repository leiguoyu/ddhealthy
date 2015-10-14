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
		// ������Ϣ���ͳ�����ʱ���ִ��Handler���������
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ����UI
			switch (msg.what) {
			case 4000:
				Intent intent1 = new Intent(context,
						MedicineDetailActivity.class);
				// �������������ݴ��ݹ�ȥ
				Bundle mBundle = new Bundle();
				mBundle.putSerializable("medicineDetail",
						(Serializable) medicineDetail);
				intent1.putExtras(mBundle);
				startActivity(intent1);
				break;
			case 4001:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
						.show();
				// ��ת��δ���������ҩƷ����
				Intent intent = new Intent(context,
						NoFoundMedicineActivity.class);
				startActivity(intent);
				break;
			case 4002:
				// ��ת��δ���������ҩƷ����
				Intent intent2 = new Intent(context,
						HealthDetailActivity.class);
				// �������������ݴ��ݹ�ȥ
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
		// ��ʼ�� CameraManager
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

		// //��ȡ�����ݺ� �������뷢��ҩƷ�������
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
		// dialog.setTitle("ɨ����");
		// dialog.setMessage(obj.getText());
		// dialog.setNegativeButton("ȷ��", new DialogInterface.OnClickListener()
		// {
		// @Override
		// public void onClick(DialogInterface dialog, int which) {
		// // ��Ĭ���������ɨ��õ��ĵ�ַ
		// Intent intent = new Intent();
		// intent.setAction("android.intent.action.VIEW");
		// Uri content_url = Uri.parse(obj.getText());
		// intent.setData(content_url);
		// startActivity(intent);
		// finish();
		// }
		// });
		// dialog.setPositiveButton("ȡ��", new DialogInterface.OnClickListener()
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
			// ������
			new Thread() {
				@Override
				public void run() {
					// ��Ҫִ�еķ���
					// ִ����Ϻ��handler����һ������Ϣ
					// memberId:�û�id (û�е�¼ʱ����Ϊ���ַ���)
					// deviceUid:�豸Ψһ��ʶ
					// barcode:������
					String memberId = "";// �����½�Ļ���id û�е�¼��Ϊ��ָ��
					String deviceUid = PhoneUUID.getPhoneWYBS(context);
					String result = DataSyncUtil.getScanOfBarcode(memberId,
							deviceUid, barcode);
					if (null!=result && !"".equals(result)) {// ����Ӧ��ֻ��һ������
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
							// ��ȡ���� �ж���ҩƷ���Ǳ���Ʒ //CP_TYPE_01 ҩƷ //����ƷCP_TYPE_02
							String productType = JsonUtil
									.getProductType(result);
							if (productType.equals("CP_TYPE_01")) {
								// ��ȡ����ΪҩƷ ��data�������������ݿ�
								medicineDetail = JsonUtil
										.getMedicineDetail(result);
								if (medicineDetail != null) {
									// �����ǲ������ݿ�ľ������ ��ʱδ����
									MedicineDetailDao medicineDetailDao = new MedicineDetailDao();
									medicineDetailDao
											.addMedicineDetail(medicineDetail);
								}
								mHandler.sendEmptyMessage(4000);
							} else if (productType.equals("CP_TYPE_02")) {
								// ��ȡ����Ϊ����Ʒ
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
							// ��ת��δ���������ҳ��
							Intent intent = new Intent();
							intent.setClass(context,
									NoFoundMedicineActivity.class);
							startActivity(intent);
						}
					} else {
						// ��data������������ʧ��ԭ��
						registerResult = JsonUtil.registerJsonFalse(result);
						mHandler.sendEmptyMessage(4001);
					}
				}
			}.start();
		}else{
			Toast.makeText(getApplicationContext(), "���������Ƿ����ӣ�",
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