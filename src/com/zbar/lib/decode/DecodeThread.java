package com.zbar.lib.decode;

import java.util.concurrent.CountDownLatch;

import android.os.Handler;
import android.os.Looper;

import com.dd.medication.medicine.ui.CaptureActivity;
import com.dd.medication.medicine.ui.CaptureActivity1;

/**
 *
 * √Ë ˆ: Ω‚¬Îœﬂ≥Ã
 */
final class DecodeThread extends Thread {

	CaptureActivity1 activity;
	private Handler handler;
	private final CountDownLatch handlerInitLatch;

	DecodeThread(CaptureActivity1 activity) {
		this.activity = activity;
		handlerInitLatch = new CountDownLatch(1);
	}

	Handler getHandler() {
		try {
			handlerInitLatch.await();
		} catch (InterruptedException ie) {
			// continue?
		}
		return handler;
	}

	@Override
	public void run() {
		Looper.prepare();
		handler = new DecodeHandler(activity);
		handlerInitLatch.countDown();
		Looper.loop();
	}

}
