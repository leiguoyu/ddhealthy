package com.dd.medication.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.net.DataSyncUtil;
import com.dd.medication.net.JsonUtil;
import com.dd.medication.util.SharedPreferencesUtil;

/**
 * 注册界面
 * **/
public class RegisterActivity extends BaseActivity implements OnClickListener {
	private EditText phoneNum,passwordEdit,yzmEdit;
	private String  registerResult;
	// 定义Handler对象
	private Handler handler = new Handler() {
		@Override
		// 当有消息发送出来的时候就执行Handler的这个方法
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 处理UI
			switch (msg.what) {
			case 1000:
				Toast.makeText(context, "恭喜你，注册成功！", Toast.LENGTH_SHORT).show();
				SharedPreferencesUtil.setFirstLoginSharedPreferences(context, "isFirstLogin", true);
				Intent intent = new Intent(RegisterActivity.this,
						SexualSelectionActivity.class);
				startActivity(intent);
				break;
			case 1001:
				Toast.makeText(context, "注册失败，请再次尝试！", Toast.LENGTH_SHORT).show();
				break;
			case 1002:
				Toast.makeText(context, "提示验证码已发送，请注意查收！", Toast.LENGTH_SHORT).show();
				break;
			case 1003:
				Toast.makeText(context, "验证码发送失败，请检查输入参数并再次尝试！", Toast.LENGTH_SHORT).show();
				break;
			case 1004:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT).show();
				break;
			default:
				break;
			}
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_activity);

		intView();

	}

	private void intView() {
		phoneNum = (EditText) this.findViewById(R.id.phone_num_edit);
		passwordEdit = (EditText) this.findViewById(R.id.password_edit);
		yzmEdit = (EditText) this.findViewById(R.id.yzm_edit);
		
		Button getYzm = (Button) this.findViewById(R.id.get_yzm_but);// 获取验证码
		getYzm.setOnClickListener(this);
		Button login = (Button) this.findViewById(R.id.register_login);
		login.setOnClickListener(this);
		Button cancel = (Button) this.findViewById(R.id.register_cancel);
		cancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.get_yzm_but:
			// 获取验证码
			final String phone = phoneNum.getText().toString();
			if (!"".equals(phone)) {
				// 这里获取验证码进行注册验证
				if (DataSyncUtil.getNetStatus(context)) {
					// 有网络
					new Thread() {
						@Override
						public void run() {
							// 你要执行的方法
							// 执行完毕后给handler发送一个空消息
							boolean result=DataSyncUtil.getVeriCode(phone);
							if(result){
								//提示验证码已发送，请注意查收
								handler.sendEmptyMessage(1002);
							}else{
								//提示获取验证码失败   非法参数异常
								handler.sendEmptyMessage(1003);
							}
						}
					}.start();
				} else {
					// 提示用户检查网路 链接
					Toast.makeText(context, "请检查网络是否连接", Toast.LENGTH_SHORT)
							.show();
				}
			} else {
				Toast.makeText(context, "请输入电话号码", Toast.LENGTH_SHORT).show();
			}
			break;
			
		case R.id.register_login:
			// 跳转到性别选择界面
			final String phone1= phoneNum.getText().toString();
			final String password=passwordEdit.getText().toString();
			final String yzm=yzmEdit.getText().toString();
			if ("".equals(phone1)) {
				Toast.makeText(context, "电话号码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(password)) {
				Toast.makeText(context, "密码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(yzm)) {
				Toast.makeText(context, "验证码不能为空", Toast.LENGTH_SHORT).show();
				return;
			}
			
			if (DataSyncUtil.getNetStatus(context)) {
				// 有网络
				new Thread() {
					@Override
					public void run() {
						// 你要执行的方法
						// 执行完毕后给handler发送一个空消息
						String result=DataSyncUtil.getregister(phone1,password,yzm);
						if(!"".equals(result)){
							boolean resultStr=JsonUtil.getResult(result);
							if(resultStr){
								//将data解析并插入数据库
								String[] registerResult=JsonUtil.registerData(result);
								
								handler.sendEmptyMessage(1000);
							}else{
								//将data解析并分析出失败原因
								 registerResult=JsonUtil.registerJsonFalse(result);
								 handler.sendEmptyMessage(1004);
							}
						}else{
							handler.sendEmptyMessage(1001);
						}
					}
				}.start();
			} else {
				// 提示用户检查网路 链接
				Toast.makeText(context, "请检查网络是否连接", Toast.LENGTH_SHORT)
						.show();
			}

			break;
			
		default:
			break;
		}
	}

}
