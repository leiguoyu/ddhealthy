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
		// ������Ϣ���ͳ�����ʱ���ִ��Handler���������
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ����UI
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
				Toast.makeText(context, "��ȡ�����쳣!", Toast.LENGTH_SHORT)
				.show();
				break;
			case 3002:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
						.show();
				// ��ת��δ���������ҩƷ����
				Intent intent = new Intent(context,
						NoFoundMedicineActivity.class);
				startActivity(intent);
				break;
			case 3003:
				Toast.makeText(context, "�����쳣����ʧ��!", Toast.LENGTH_SHORT)
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
//			ҩƷ���� ����������˳������
//			productname:��Ʒ����  
//			company:��ҵ����  
//			specification:��� 
//			dosage:���� 
//			ingredient:�ɷ� 
//			functions:�������� 
//			theusage:�÷����� 
//			reactions:������Ӧ 
//			taboo:���� 
//			note:ע������ 
//			store:���� 
//			validity:��Ч�� 
//			indication:��Ӧ֢ 
//			dosageCategory:������𣨹����ļ��ͣ� 
//			usingTime:����ʱ��
		}
	}

	private void searchMedicineById() {
		// ����ʹ���̵߳��ýӿڻ�ȡ��ϸ���� ����չʾ
		if (DataSyncUtil.getNetStatus(context)) {
			// ������
			new Thread() {
				@Override
				public void run() {
					// memberId:�û�id (û�е�¼ʱ����Ϊ���ַ���) deviceUid:�豸Ψһ��ʶ
					// allProductId:��ƷID
					String memberId = "";// �����½�Ļ���id û�е�¼��Ϊ��ָ��
					String deviceUid = PhoneUUID.getPhoneWYBS(context);
					String result = DataSyncUtil.getProductDetail(memberId,
							deviceUid, allProductId + "");
					if (!"".equals(result)) {
						boolean resultStr = JsonUtil.getResult(result);
						if (resultStr) {
							// ��data�������������ݿ�
							medicineDetail = JsonUtil.getMedicineDetail(result);
							if (medicineDetail != null) {
								// �����ǲ������ݿ�ľ������ ��ʱδ����
								// AllProductDao allProductDao=new
								// AllProductDao();
								// allProductDao.addAllProduct(allProductList);
								mHandler.sendEmptyMessage(3000);
							} else {
								System.out.println("allProductList==null===");
								mHandler.sendEmptyMessage(3003);
							}

						} else {
							// ��data������������ʧ��ԭ��
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
			Toast.makeText(context, "���������Ƿ����ӣ�", Toast.LENGTH_SHORT).show();
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
		case R.id.medications_detail_back:// ����
			finish();
			break;
		case R.id.medications_detail_setting:// �����ҩ����ҳ��
			// ��Ҫ��תʱ��ҩƷ��Ϣ���ݵ�ҩƷ���ý���
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
