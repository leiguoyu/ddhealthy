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
		// ������Ϣ���ͳ�����ʱ���ִ��Handler���������
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ����UI
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
				Toast.makeText(context, "��ȡ�����쳣!", Toast.LENGTH_SHORT)
						.show();
				break;
				
			case 5002:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
						.show();
				// ��ת��δ���������ҩƷ����
				Intent intent = new Intent(context,
						NoFoundMedicineActivity.class);
				startActivity(intent);
				break;
				
			case 5003:
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
			
//			productname:��Ʒ����
//			company:��ҵ����
//			domesticOrImport:���������
//			scgdq:��������������
//			bjgn:��������
//			gxcfbzxcfhl:��Ч�ɷ�/��־�Գɷݺ���
//			zyyl:��Ҫԭ��
//			syrq:������Ⱥ
//			bsyrq:��������Ⱥ
//			syffjsyl:ʳ�÷�����ʳ����
//			cpgg:��Ʒ���
//			bzq:������
//			zcff:���ط���
//			zysx:ע������
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
							healthProduct = JsonUtil.getHealthProduct(result);
							if (healthProduct != null) {
								mHandler.sendEmptyMessage(5000);
							} else {
								mHandler.sendEmptyMessage(5003);
								System.out.println("allProductList==null===");
							}

						} else {
							// ��data������������ʧ��ԭ��
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
			Toast.makeText(context, "���������Ƿ����ӣ�", Toast.LENGTH_SHORT).show();
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
		case R.id.health_detail_back:// ����
			finish();
			break;
		case R.id.health_detail_setting:// �����ҩ����ҳ��
			// ��Ҫ��תʱ��ҩƷ��Ϣ���ݵ�ҩƷ���ý���
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
