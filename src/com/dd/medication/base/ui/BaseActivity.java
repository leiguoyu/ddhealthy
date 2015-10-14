package com.dd.medication.base.ui;

import java.io.Serializable;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnKeyListener;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.widget.TextView;

import com.dd.medication.R;

public class BaseActivity extends Activity{

	/**
	 * ������
	 */
	protected Context context;
	protected List<? extends Serializable> datas;
	/**
	 * ������������
	 */
	protected ProgressDialog progressDialog = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		
	}

	
	public void showProgressDialog(String text) {
		if (progressDialog == null) {
			progressDialog = ProgressDialog.show(context, "���Ե�...", "���ڵ�¼...",
					true);
			progressDialog.setContentView(R.layout.custom_progress);
			// progressDialog.setCancelable(true);
			progressDialog.setOnKeyListener(new OnKeyListener() {

				@Override
				public boolean onKey(DialogInterface arg0, int arg1,
						KeyEvent arg2) {
					if (arg1 == KeyEvent.KEYCODE_BACK
							&& arg2.getRepeatCount() == 0
							&& arg2.getAction() == KeyEvent.ACTION_UP) {
						new AlertDialog.Builder(BaseActivity.this)
								.setTitle("����")
								.setMessage("������δ��ɣ��Ƿ�ֹͣ")
								.setPositiveButton("ȷ��",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												hideProgressDialog();
											}
										})
								.setNegativeButton("ȡ��",
										new DialogInterface.OnClickListener() {
											public void onClick(
													DialogInterface dialog,
													int whichButton) {
												return;
											}
										}).show();
					}
					return true;
				}

			});
			View v = progressDialog.getWindow().getDecorView();
			if (text == null) {
				text = "���Ե�...";
			}
			setProgressText(v, text);
		}

	}

	private void setProgressText(View v, String text) {

		if (v instanceof ViewGroup) {
			ViewGroup parent = (ViewGroup) v;
			int count = parent.getChildCount();
			for (int i = 0; i < count; i++) {
				View child = parent.getChildAt(i);
				setProgressText(child, text);
			}
		} else if (v instanceof TextView) {
			LayoutParams params = v.getLayoutParams();
			params.width = 300;
			v.setLayoutParams(params);
			((TextView) v).setWidth(300);
			((TextView) v).setTextSize(18);
			((TextView) v).setText(text);
			((TextView) v).setTextColor(Color.WHITE);
		}
	}

	protected void hideProgressDialog() {
		if (progressDialog != null) {
			progressDialog.dismiss();
			progressDialog = null;
		}
	}
	
	
}
