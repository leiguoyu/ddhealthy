package com.dd.medication.medicine.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.model.HealthProductModel;
import com.dd.medication.medicine.model.MedicineDetailModel;
import com.dd.medication.net.DataSyncUtil;
import com.dd.medication.net.JsonUtil;
import com.dd.medication.util.PhoneUUID;

public class HealthDetailActivity extends BaseActivity implements OnClickListener {
	private int allProductId;
	private String registerResult;
	private HealthProductModel healthProduct ;
	private TextView productname, company, domesticOrImport, scgdq, bjgn, gxcfbzxcfhl,
	zyyl, syrq, syffjsyl, cpgg, bzq, zcff,
	zysx, usingTime,bsyrq;
	
	private Handler mHandler = new Handler() {
		@Override
		// 当有消息发送出来的时候就执行Handler的这个方法
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 处理UI
			switch (msg.what) {
			case 5000:
				productname.setText(healthProduct.getProductname());
				company.setText(healthProduct.getCompany());
				domesticOrImport.setText(healthProduct.getDomesticOrImport());
				scgdq.setText(healthProduct.getScgdq());
				bjgn.setText(healthProduct.getBjgn());
				gxcfbzxcfhl.setText(healthProduct.getGxcfbzxcfhl());
				zyyl.setText(healthProduct.getZyyl());
				syrq.setText(healthProduct.getSyrq());
				bsyrq.setText(healthProduct.getBsyrq());
				syffjsyl.setText(healthProduct.getSyffjsyl());
				cpgg.setText(healthProduct.getCpgg());
				bzq.setText(healthProduct.getBzq());
				zcff.setText(healthProduct.getZcff());
				zysx.setText(healthProduct.getZysx());
				usingTime.setText(healthProduct.getUsingTime());

				break;
				
			case 5001:
				Toast.makeText(context, "获取数据异常!", Toast.LENGTH_SHORT)
						.show();
				break;
				
			case 5002:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
						.show();
				// 跳转到未搜索到相关药品界面
				Intent intent = new Intent(context,
						NoFoundMedicineActivity.class);
				startActivity(intent);
				break;
				
			case 5003:
				Toast.makeText(context, "数据异常解析失败!", Toast.LENGTH_SHORT)
						.show();
				break;
				
			default:
				break;
			}
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.health_detail);
		
		Intent intent = getIntent();
		allProductId = intent.getIntExtra("allProductId", -1);
		
		
		healthProduct = (HealthProductModel) getIntent()
		.getSerializableExtra("healthProductModel");
		
		
		if (allProductId != -1) {
			searchMedicineById();
		}

		intView();
		if (null != healthProduct) {
			productname.setText(healthProduct.getProductname());
			company.setText(healthProduct.getCompany());
			domesticOrImport.setText(healthProduct.getDomesticOrImport());
			scgdq.setText(healthProduct.getScgdq());
			bjgn.setText(healthProduct.getBjgn());
			gxcfbzxcfhl.setText(healthProduct.getGxcfbzxcfhl());
			zyyl.setText(healthProduct.getZyyl());
			syrq.setText(healthProduct.getSyrq());
			bsyrq.setText(healthProduct.getBsyrq());
			syffjsyl.setText(healthProduct.getSyffjsyl());
			cpgg.setText(healthProduct.getCpgg());
			bzq.setText(healthProduct.getBzq());
			zcff.setText(healthProduct.getZcff());
			zysx.setText(healthProduct.getZysx());
			usingTime.setText(healthProduct.getUsingTime());
			
//			productname:商品名称
//			company:企业名称
//			domesticOrImport:国产或进口
//			scgdq:生产国（地区）
//			bjgn:保健功能
//			gxcfbzxcfhl:功效成分/标志性成份含量
//			zyyl:主要原料
//			syrq:适宜人群
//			bsyrq:不适宜人群
//			syffjsyl:食用方法及食用量
//			cpgg:产品规格
//			bzq:保质期
//			zcff:贮藏方法
//			zysx:注意事项
//			usingTime:服用时机
		}
		

		
	}

	private void searchMedicineById() {
		// 这里使用线程调用接口获取详细数据 进行展示
		if (DataSyncUtil.getNetStatus(context)) {
			// 有网络
			new Thread() {
				@Override
				public void run() {
					// memberId:用户id (没有登录时传递为空字符串) deviceUid:设备唯一标识
					// allProductId:产品ID
					String memberId = "";// 如果登陆的话有id 没有登录则为空指针
					String deviceUid = PhoneUUID.getPhoneWYBS(context);
					String result = DataSyncUtil.getProductDetail(memberId,
							deviceUid, allProductId + "");
					if (!"".equals(result)) {
						boolean resultStr = JsonUtil.getResult(result);
						if (resultStr) {
							// 将data解析并插入数据库
							healthProduct = JsonUtil.getHealthProduct(result);
							if (healthProduct != null) {
								mHandler.sendEmptyMessage(5000);
							} else {
								mHandler.sendEmptyMessage(5003);
								System.out.println("allProductList==null===");
							}

						} else {
							// 将data解析并分析出失败原因
							registerResult = JsonUtil
									.registerJsonFalse(result);
							mHandler.sendEmptyMessage(5002);
						}
					} else {
						mHandler.sendEmptyMessage(5001);
					}
				}
			}.start();
		}else{
			Toast.makeText(context, "请检查网络是否连接！", Toast.LENGTH_SHORT).show();
		}
	}

	private void intView() {
		TextView back = (TextView) this
				.findViewById(R.id.health_detail_back);


		
		productname = (TextView) this.findViewById(R.id.health_detail_name);
		company = (TextView) this.findViewById(R.id.health_detail_company);
		domesticOrImport = (TextView) this
				.findViewById(R.id.health_detail_domesticOrImport);
		scgdq = (TextView) this.findViewById(R.id.health_detail_scgdq);
		bjgn = (TextView) this
				.findViewById(R.id.health_detail_bjgn);
		gxcfbzxcfhl = (TextView) this
				.findViewById(R.id.health_detail_gxcfbzxcfhl);
		zyyl = (TextView) this
				.findViewById(R.id.health_detail_zyyl);
		syrq = (TextView) this
				.findViewById(R.id.health_detail_syrq);
		bsyrq = (TextView) this
				.findViewById(R.id.health_detail_bsyrq);
		syffjsyl = (TextView) this
				.findViewById(R.id.health_detail_syffjsyl);
		cpgg = (TextView) this
				.findViewById(R.id.health_detail_cpgg);
		bzq = (TextView) this
				.findViewById(R.id.health_detail_bzq);
		zcff = (TextView) this.findViewById(R.id.health_detail_zcff);
		zysx = (TextView) this.findViewById(R.id.health_detail_zysx);
		usingTime = (TextView) this.findViewById(R.id.health_detail_usingTime);
	
		Button setting = (Button) this
				.findViewById(R.id.health_detail_setting);
		back.setOnClickListener(this);
		setting.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.health_detail_back:// 返回
			finish();
			break;
		case R.id.health_detail_setting:// 进入服药设置页面
			// 需要跳转时将药品信息传递到药品设置界面
			Intent intent = new Intent(context, AddMedicineToastActivity.class);
			intent.putExtra("medicineDetailId",
					String.valueOf(healthProduct.getHealthProductDetailId()));
			intent.putExtra("medicineName", healthProduct.getProductname());
			startActivity(intent);
			break;

		default:
			break;
		}

	}
	
	
}
