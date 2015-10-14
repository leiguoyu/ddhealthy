package com.dd.medication.login.ui;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.Util;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.ui.MedicationsMainActivity;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;
import com.dd.medication.util.Const;
/**
 * 登陆主界面入口
 * **/
public class LoginActivity extends BaseActivity implements OnClickListener {
	public static Tencent mTencent;
	private static Intent mPrizeIntent = null;
	private static boolean isServerSideLogin = false;
	 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_activity);
		// Tencent类是SDK的主要实现类，开发者可通过Tencent类访问腾讯开放的OpenAPI。
		// 其中APP_ID是分配给第三方应用的appid，类型为String。
		mTencent = Tencent.createInstance(Const.APP_ID, this.getApplicationContext());
		intView();
	}

	private void intView() {
		Button zhuce = (Button) this.findViewById(R.id.login_zhuce);
		Button tiaoguo = (Button) this.findViewById(R.id.login_tiaoguo);
		ImageView qq = (ImageView) this.findViewById(R.id.login_qq);
		ImageView weixin = (ImageView) this.findViewById(R.id.login_weeixin);
		ImageView weibo = (ImageView) this.findViewById(R.id.login_weeibo);
		zhuce.setOnClickListener(this);
		tiaoguo.setOnClickListener(this);
		qq.setOnClickListener(this);
		weixin.setOnClickListener(this);
		weibo.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.login_zhuce:
			// 注册按钮  跳转到注册界面
            Intent intent =new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
			break;
		case R.id.login_tiaoguo:
			// 跳过按钮  直接进入用药主界面
            Intent intent1 =new Intent(LoginActivity.this,  MedicationsMainActivity.class);
            startActivity(intent1);
			break;
		case R.id.login_qq:
			// 三方登录qq
			qqlogin();
			break;
		case R.id.login_weeixin:
			// 三方登录 微信

			break;
		case R.id.login_weeibo:
			// 三方登录 微博

			break;

		default:
			break;
		}

	}

	private static Boolean isExit = false;
	private static Boolean hasTask = false;
	Timer tExit = new Timer();
	TimerTask task = new TimerTask() {
		@Override
		public void run() {
			isExit = true;
			hasTask = true;
		}
	};
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (isExit == false) {
				isExit = true;
				Toast.makeText(LoginActivity.this, "再按一次退出程序", 3000).show();
				if (!hasTask) {
					tExit.schedule(task, 2000);
				}
			} else {
				finish();
				System.exit(0);
			}
		}
		return false;
	}

	public void qqlogin()
	{
	mTencent = Tencent.createInstance(Const.APP_ID, this.getApplicationContext());
	if (!mTencent.isSessionValid())
	{
	mTencent.login(this, "all", loginListener);
	}
	}
	
	 //by andy
	public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
        }
    }
	 //by andy
	IUiListener loginListener = new BaseUiListener() {
	    @Override
	    protected void doComplete(JSONObject values) {
	    	Log.d("SDKQQAgentPref", "AuthorSwitch_SDK:" + SystemClock.elapsedRealtime());
	        initOpenidAndToken(values);
	    }
	};
	
	
	 //by andy
	private class BaseUiListener implements IUiListener {
	
		@Override
		public void onComplete(Object response) {
	        if (null == response) {
	            Util.showResultDialog(LoginActivity.this, "杩斿洖涓虹┖", "鐧诲綍澶辫触");
	            return;
	        }
	        JSONObject jsonResponse = (JSONObject) response;
	        if (null != jsonResponse && jsonResponse.length() == 0) {
	            Util.showResultDialog(LoginActivity.this, "杩斿洖涓虹┖", "鐧诲綍澶辫触");
	            return;
	        }
			Util.showResultDialog(LoginActivity.this, response.toString(), "鐧诲綍鎴愬姛");
	        // 鏈夊鍒嗕韩澶勭悊
	        handlePrizeShare();
			doComplete((JSONObject)response);
		}
	
		protected void doComplete(JSONObject values) {
	
		}
	
		@Override
		public void onError(UiError e) {
			Util.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
			Util.dismissDialog();
		}
	
		@Override
		public void onCancel() {
			Util.toastMessage(LoginActivity.this, "onCancel: ");
			Util.dismissDialog();
	        if (isServerSideLogin) {
	            isServerSideLogin = false;
	        }
		}
	}

	private void handlePrizeShare() {
	
	    if (null == mPrizeIntent || null == mTencent) {
	        return;
	    }
	    boolean hasPrize = mTencent.checkPrizeByIntent(LoginActivity.this, mPrizeIntent);
	    if (hasPrize) {
	        Util.showConfirmCancelDialog(this, "鏈夊鍝侀鍙�", "璇蜂娇鐢≦Q鐧诲綍鍚庯紝棰嗗彇濂栧搧锛�", prizeShareConfirmListener);
	    }
	}
	
	 //by andy
    private DialogInterface.OnClickListener prizeShareConfirmListener = new DialogInterface.OnClickListener() {

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    boolean isLogin = mTencent.isSessionValid();
                    if (isLogin) {
                        if (null != mPrizeIntent) {
                            mTencent.queryUnexchangePrize(LoginActivity.this, mPrizeIntent.getExtras(),
                                    prizeQueryUnexchangeListener);
                        }
                    } else {
                        // 鏈櫥闄嗘彁绀虹敤鎴蜂娇鐢≦Q鍙风櫥闄�
                    	qqlogin();
                    }
                    break;
                default:
                    break;
            }
        }
    };
    //by andy
    private IUiListener prizeQueryUnexchangeListener = new IUiListener() {

        @Override
        public void onError(UiError e) {
            Util.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
            Util.toastMessage(LoginActivity.this, "onCancel: ");
            Util.dismissDialog();
        }

        @Override
        public void onComplete(Object response) {
            Util.showConfirmCancelDialog(LoginActivity.this, "鍏戞崲濂栧搧", response.toString(),
                    new PrizeClickExchangeListener(response.toString()));
            // 鍏戞崲濂栧搧鍚庯紝mPrizeIntent 缃负绌�
            mPrizeIntent = null;
        }
    };
    //by andy
    private class PrizeClickExchangeListener implements DialogInterface.OnClickListener {
        String response = "";

        PrizeClickExchangeListener(String strResponse) {
            response = strResponse;
        }

        @Override
        public void onClick(DialogInterface dialog, int which) {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    if (null != mTencent) {
                        Bundle params = new Bundle();
                        ArrayList<String> shareIdList = handlePrizeResponse(response);
                        if (null != shareIdList) {
                            ArrayList<String> list = new ArrayList<String>();
                            // 鍚庡彴娴嬭瘯鐜鐩墠鍙敮鎸佷竴涓猻hareid鐨勫厬鎹紝姝ｅ紡鐜浼氭敮鎸佸涓猻hareid鍏戞崲銆�
                            list.add(shareIdList.get(0));
                            params.putStringArrayList("shareid_list", list);
                            mTencent.exchangePrize(LoginActivity.this, params, prizeExchangeListener);
                        }
                    }
                    break;
                default:
                    break;
            }
        }
    };
    //by andy
    private ArrayList<String> handlePrizeResponse(String response) {
        ArrayList<String> shareIdList = new ArrayList<String>();
        if (TextUtils.isEmpty(response)) {
            return null;
        }
        try {
            JSONObject obj = new JSONObject(response);
            int code = obj.getInt("ret");
            int subCode = obj.getInt("subCode");
            if (code == 0 && subCode == 0) {
                JSONObject data = obj.getJSONObject("data");
                JSONArray prizeList = data.getJSONArray("prizeList");
                int size = prizeList.length();
                JSONObject prize = null;
                for (int i = 0; i < size; i++) {
                    prize = prizeList.getJSONObject(i);
                    if (null != prize) {
                        shareIdList.add(prize.getString("shareId"));
                    }
                }
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
        return shareIdList;
    }
    //by andy
    private IUiListener prizeExchangeListener = new IUiListener() {

        @Override
        public void onError(UiError e) {
            Util.toastMessage(LoginActivity.this, "onError: " + e.errorDetail);
            Util.dismissDialog();
        }

        @Override
        public void onCancel() {
            Util.toastMessage(LoginActivity.this, "onCancel: ");
            Util.dismissDialog();
        }

        @Override
        public void onComplete(Object response) {
            Util.showResultDialog(LoginActivity.this, response.toString(), "鍏戞崲淇℃伅");
        }
    };
}
