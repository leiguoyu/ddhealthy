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
import com.dd.medication.medicine.model.MedicineDetailModel;
import com.dd.medication.net.DataSyncUtil;
import com.dd.medication.net.JsonUtil;
import com.dd.medication.util.PhoneUUID;

public class MedicineDetailActivity extends BaseActivity implements
		OnClickListener {
	private MedicineDetailModel medicineDetail;
	private TextView name, company, ingredient, functions, note, specification,
			dosage, dosageCategory, indication, theusage, validity, usingTime,
			reactions, taboo, store;
	private int allProductId;
	private String registerResult;

	private Handler mHandler = new Handler() {
		@Override
		// 当有消息发送出来的时候就执行Handler的这个方法
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 处理UI
			switch (msg.what) {
			case 3000:
				name.setText(medicineDetail.getProductname());
				company.setText(medicineDetail.getCompany());
				ingredient.setText(medicineDetail.getIngredient());
				functions.setText(medicineDetail.getFunctions());
				note.setText(medicineDetail.getNote());
				specification.setText(medicineDetail.getSpecification());
				dosage.setText(medicineDetail.getDosage());
				dosageCategory.setText(medicineDetail.getDosageCategory());
				indication.setText(medicineDetail.getIndication());
				theusage.setText(medicineDetail.getTheusage());
				validity.setText(medicineDetail.getValidity());
				usingTime.setText(medicineDetail.getUsingTime());
				reactions.setText(medicineDetail.getReactions());
				taboo.setText(medicineDetail.getTaboo());
				store.setText(medicineDetail.getStore());

				break;
			case 3001:
				Toast.makeText(context, "获取数据异常!", Toast.LENGTH_SHORT)
				.show();
				break;
			case 3002:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
						.show();
				// 跳转到未搜索到相关药品界面
				Intent intent = new Intent(context,
						NoFoundMedicineActivity.class);
				startActivity(intent);
				break;
			case 3003:
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
		setContentView(R.layout.medications_detail);

		Intent intent = getIntent();
		// final String allProductId = intent.getStringExtra("allProductId");
		allProductId = intent.getIntExtra("allProductId", -1);

		medicineDetail = (MedicineDetailModel) getIntent()
				.getSerializableExtra("medicineDetail");

		if (allProductId != -1) {
			searchMedicineById();
		}

		intView();
		if (null != medicineDetail) {
			name.setText(medicineDetail.getProductname());
			company.setText(medicineDetail.getCompany());
			ingredient.setText(medicineDetail.getIngredient());
			functions.setText(medicineDetail.getFunctions());
			note.setText(medicineDetail.getNote());
			specification.setText(medicineDetail.getSpecification());
			dosage.setText(medicineDetail.getDosage());
			dosageCategory.setText(medicineDetail.getDosageCategory());
			indication.setText(medicineDetail.getIndication());
			theusage.setText(medicineDetail.getTheusage());
			validity.setText(medicineDetail.getValidity());
			usingTime.setText(medicineDetail.getUsingTime());
			reactions.setText(medicineDetail.getReactions());
			taboo.setText(medicineDetail.getTaboo());
			store.setText(medicineDetail.getStore());
//			药品详情 （按照以下顺序排序）
//			productname:商品名称  
//			company:企业名称  
//			specification:规格 
//			dosage:剂型 
//			ingredient:成份 
//			functions:功能主治 
//			theusage:用法用量 
//			reactions:不良反应 
//			taboo:禁忌 
//			note:注意事项 
//			store:贮藏 
//			validity:有效期 
//			indication:适应症 
//			dosageCategory:剂型类别（归类后的剂型） 
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
							medicineDetail = JsonUtil.getMedicineDetail(result);
							if (medicineDetail != null) {
								// 这里是插入数据库的具体操作 暂时未增加
								// AllProductDao allProductDao=new
								// AllProductDao();
								// allProductDao.addAllProduct(allProductList);
								mHandler.sendEmptyMessage(3000);
							} else {
								System.out.println("allProductList==null===");
								mHandler.sendEmptyMessage(3003);
							}

						} else {
							// 将data解析并分析出失败原因
							registerResult = JsonUtil
									.registerJsonFalse(result);
							mHandler.sendEmptyMessage(3002);
						}
					} else {
						mHandler.sendEmptyMessage(3001);
					}
				}
			}.start();
		}else{
			Toast.makeText(context, "请检查网络是否连接！", Toast.LENGTH_SHORT).show();
		}
	}

	private void intView() {
		TextView back = (TextView) this
				.findViewById(R.id.medications_detail_back);
		name = (TextView) this.findViewById(R.id.medications_detail_name);
		company = (TextView) this.findViewById(R.id.medications_detail_company);
		specification = (TextView) this
				.findViewById(R.id.medications_detail_specification);
		dosage = (TextView) this.findViewById(R.id.medications_detail_dosage);
		dosageCategory = (TextView) this
				.findViewById(R.id.medications_detail_dosageCategory);
		ingredient = (TextView) this
				.findViewById(R.id.medications_detail_ingredient);
		functions = (TextView) this
				.findViewById(R.id.medications_detail_functions);
		indication = (TextView) this
				.findViewById(R.id.medications_detail_indication);
		theusage = (TextView) this
				.findViewById(R.id.medications_detail_theusage);
		validity = (TextView) this
				.findViewById(R.id.medications_detail_validity);
		usingTime = (TextView) this
				.findViewById(R.id.medications_detail_usingTime);
		reactions = (TextView) this
				.findViewById(R.id.medications_detail_reactions);
		taboo = (TextView) this.findViewById(R.id.medications_detail_taboo);
		note = (TextView) this.findViewById(R.id.medications_detail_note);
		store = (TextView) this.findViewById(R.id.medications_detail_store);
		Button setting = (Button) this
				.findViewById(R.id.medications_detail_setting);
		back.setOnClickListener(this);
		setting.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.medications_detail_back:// 返回
			finish();
			break;
		case R.id.medications_detail_setting:// 进入服药设置页面
			// 需要跳转时将药品信息传递到药品设置界面
			Intent intent = new Intent(context, AddMedicineToastActivity.class);
			intent.putExtra("medicineDetailId",
					String.valueOf(medicineDetail.getMedicineDetailId()));
			intent.putExtra("medicineName", medicineDetail.getProductname());
			startActivity(intent);
			break;

		default:
			break;
		}

	}

}
