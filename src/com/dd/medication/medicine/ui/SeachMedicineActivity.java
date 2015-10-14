package com.dd.medication.medicine.ui;

import java.util.ArrayList;
import java.util.LinkedList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.login.ui.RegisterActivity;
import com.dd.medication.login.ui.SexualSelectionActivity;
import com.dd.medication.medicine.dao.AllProductDao;
import com.dd.medication.medicine.model.AllProductModel;
import com.dd.medication.net.DataSyncUtil;
import com.dd.medication.net.JsonUtil;
import com.dd.medication.util.PhoneUUID;
import com.dd.medication.util.SharedPreferencesUtil;
import com.dd.medication.view.MyListViewPullDownAndUp;
import com.dd.medication.view.MyListViewPullDownAndUp.RefreshListener;

public class SeachMedicineActivity extends BaseActivity implements OnClickListener {
	private MyListViewPullDownAndUp lv;
	private MyAdapter adapter;
	int i = 1;
	private EditText seacherEdit;
	private LinearLayout layout;
	private ArrayList<AllProductModel> allProductList=new ArrayList<AllProductModel>();
	private String pageTotal;
	private int indexPage=1;
	private TextView searchResult;
	private String registerResult;
	
	private Handler  mHandler= new Handler() {
		@Override
		// ������Ϣ���ͳ�����ʱ���ִ��mHandler���������
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// ����UI
			switch (msg.what) {
			case 2000:
				searchResult.setText("�˴���������"+pageTotal+"ҳÿҳ10����¼����ǰ���ڵ�"+indexPage+"ҳ");
				adapter = new MyAdapter();
				lv.setAdapter(adapter);
				break;
			case 2001:
				Toast.makeText(context, "��ȡ�����쳣!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 2002:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
				.show();
				break;
			case 20031:
				System.out.println("allProductList==null=23123=1111=");

						Toast.makeText(context, "���ݴ˹ؼ���δ������������ݣ����������룡", Toast.LENGTH_SHORT)
						.show();

				break;
			default:
				break;
			}
		}
	};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seach_medicine_activity);

		//���Ȼ�ȡ�������ݿ�֮ǰ��������ҩƷ�б�    ��û���ҵ��û���Ҫ��ҩƷʱ�������� ��ʾ�����б�

		lv = (MyListViewPullDownAndUp) findViewById(R.id.lv);
		adapter = new MyAdapter();
		lv.setAdapter(adapter);
		lv.setRefreshListener(new MyRefreshListener());

		intView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// ���Ñ�ѡ���ĳ�����ݴ��ݵ���һ������   ���л�ȡ��ϸҩƷ��Ϣ
				AllProductModel allProductModel=allProductList.get(position-1);
				String productType=allProductModel.getProductType();
				if(!"".equals(productType)){
					if("CP_TYPE_01".equals(productType)){
						//ҩƷ
						Intent intent=new Intent(context, MedicineDetailActivity.class);
						intent.putExtra("allProductId", allProductModel.getAllProductId());
						startActivity(intent);	
					}else if("CP_TYPE_02".equals(productType)){
						//����Ʒ
						Intent intent=new Intent(context, HealthDetailActivity.class);
						intent.putExtra("allProductId", allProductModel.getAllProductId());
						startActivity(intent);	
					}
				}
				

			}
		});
		
		
	}

	private void intView() {
		seacherEdit=(EditText) this.findViewById(R.id.seach_medicine_ypmc);
		searchResult=(TextView)this.findViewById(R.id.seach_medicine_result);
		//������Ҫ������ҩƷ����
		
		ImageButton selected=(ImageButton) this.findViewById(R.id.seach_medicine_search_but);
		selected.setOnClickListener(this);
		layout=(LinearLayout) this.findViewById(R.id.seach_medicine_toast_layout);
		Button setting =(Button)this.findViewById(R.id.seach_medicine_toast_setting);
		setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// ��������ҳ��
				Intent intent = new Intent(SeachMedicineActivity.this,
						AddMedicineToastActivity.class);
				startActivity(intent);
			}
		});
		
		TextView back=(TextView) this.findViewById(R.id.drug_use_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
	}

	class MyRefreshListener implements RefreshListener {
		// ��������ˢ��
		@Override
		public void pullDownRefresh() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					SystemClock.sleep(2000);
//					data.addFirst(i++ + "new��������data����������������");
//					AllProductModel allProductModel=new AllProductModel();
//					allProductModel.setProductName("test");
//					allProductModel.setCompany("test111");
//					allProductList.add(allProductModel);
					
					if (DataSyncUtil.getNetStatus(context)) {// ������
						String memberId = "";// �����½�Ļ���id û�е�¼��Ϊ��ָ��
						String deviceUid = PhoneUUID.getPhoneWYBS(context);
						String keyword = seacherEdit.getText().toString();
						String pageSize = "10";
						String isFirstSearch = "1";
						String searchType = "1";// 0ɨ�� 1�ֶ�
						indexPage=indexPage+1;
						String result = DataSyncUtil.getSerachProduct(memberId,
								deviceUid, keyword, indexPage+"", pageSize,
								isFirstSearch/* ,searchType */);
						if (!"".equals(result)) {
							boolean resultStr = JsonUtil.getResult(result);
							if (resultStr) {
								// ��data�������������ݿ�
								ArrayList<AllProductModel> allProductListNew=new ArrayList<AllProductModel>();
								allProductListNew = JsonUtil.SerachProductData(result);
								if (allProductListNew != null
										&& allProductListNew.size() > 0) {
									// �����ǲ������ݿ�ľ������ ��ʱδ����
									allProductList.addAll(allProductListNew);
								} else {
									mHandler.sendEmptyMessage(20031);
								}

							} else {
								// ��data������������ʧ��ԭ��
								registerResult = JsonUtil
										.registerJsonFalse(result);
								mHandler.sendEmptyMessage(2002);
							}
						} else {
							mHandler.sendEmptyMessage(2001);
						}
					}else{
						mHandler.post(new Runnable() {
							@Override
							public void run() {
								Toast.makeText(context, "���������Ƿ����ӣ�", Toast.LENGTH_SHORT).show();
							}
						});
					
					}
					
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							adapter.notifyDataSetChanged();
							lv.onPulldownRefreshComplete();
							searchResult.setText("�˴���������"+pageTotal+"ҳÿҳ10����¼����ǰ���ڵ�"+indexPage+"ҳ");
							Toast.makeText(getApplicationContext(), "����������",
									Toast.LENGTH_LONG).show();
							System.out.println(lv.getLastVisiblePosition()
									+ "=======" + adapter.getCount());
						}
					});
				}
			}).start();
		}

		// ��������ˢ��
		@Override
		public void pullUpRefresh() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					SystemClock.sleep(2000);
//					AllProductModel allProductModel=new AllProductModel();
//					allProductModel.setProductName("test");
//					allProductModel.setCompany("test111");
//					allProductList.addLast(i++ + "new��������data����������������");
					
					if (DataSyncUtil.getNetStatus(context)) {// ������
						String memberId = "";// �����½�Ļ���id û�е�¼��Ϊ��ָ��
						String deviceUid = PhoneUUID.getPhoneWYBS(context);
						String keyword = seacherEdit.getText().toString();
						String pageSize = "10";
						String isFirstSearch = "1";
						String searchType = "1";// 0ɨ�� 1�ֶ�
						indexPage=indexPage+1;
						String result = DataSyncUtil.getSerachProduct(memberId,
								deviceUid, keyword, indexPage+"", pageSize,
								isFirstSearch/* ,searchType */);
						if (!"".equals(result)) {
							boolean resultStr = JsonUtil.getResult(result);
							if (resultStr) {
								// ��data�������������ݿ�
								ArrayList<AllProductModel> allProductListNew=new ArrayList<AllProductModel>();
								allProductListNew = JsonUtil.SerachProductData(result);
								if (allProductListNew != null
										&& allProductListNew.size() > 0) {
									// �����ǲ������ݿ�ľ������ ��ʱδ����
									allProductList.addAll(allProductListNew);
								} else {
									mHandler.sendEmptyMessage(20031);
								}

							} else {
								// ��data������������ʧ��ԭ��
								registerResult = JsonUtil
										.registerJsonFalse(result);
								mHandler.sendEmptyMessage(2002);
							}
						} else {
							mHandler.sendEmptyMessage(2001);
						}
					}
					
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							adapter.notifyDataSetChanged();
							lv.onPullupRefreshComplete();
							searchResult.setText("�˴���������"+pageTotal+"ҳÿҳ10����¼����ǰ���ڵ�"+indexPage+"ҳ");
							Toast.makeText(getApplicationContext(), "����������",
									Toast.LENGTH_LONG).show();
							System.out.println(lv.getLastVisiblePosition()
									+ "=======" + adapter.getCount());
						}
					});
				}
			}).start();
		}
	}

	class MyAdapter extends BaseAdapter {
		private LayoutInflater inflater = null;

		public MyAdapter() {
			// TODO Auto-generated constructor stub
			inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return allProductList.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {

			ViewHolder holder = new ViewHolder();

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.seach_medicine_item,
						null);
			}
			holder.medicineName = (TextView) convertView
					.findViewById(R.id.seach_medicine_name);
			holder.complanyName = (TextView) convertView
					.findViewById(R.id.seach_complany_name);
			AllProductModel allProduct=allProductList.get(position);
			holder.medicineName.setText(allProduct.getProductName());
			holder.complanyName.setText(allProduct.getCompany());

			return convertView;

		}

	}

	private class ViewHolder {
		public TextView medicineName;
		public TextView complanyName;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.seach_medicine_search_but:
			//�ֶ�����ҩƷ����
			//�����������  ����������ҩƷ���ý���  ���෴����ʾ���ֶ����
//			lv.setVisibility(View.GONE);
//			layout.setVisibility(View.VISIBLE);
			
			if (DataSyncUtil.getNetStatus(context)) {
				// ������
				new Thread() {
					@Override
					public void run() {
						// ��Ҫִ�еķ���
						// ִ����Ϻ��mHandler����һ������Ϣ
						/*   memberId:�û�id (û�е�¼ʱ����Ϊ���ַ���)
						deviceUid:�豸Ψһ��ʶ
						keyword:�����ؼ���
						pageNo:ҳ�루���
						pageSize:ÿҳ���������
						isFirstSearch:�Ƿ��ǵ��������ť���е��������������ַ�ҳ������
						  1:���������ťʱ���ñ��ӿ�
						  0:��ҳ�������������¼�н���ʱ���ñ��ӿ�   */
						String memberId="";//�����½�Ļ���id  û�е�¼��Ϊ��ָ��
						String deviceUid=PhoneUUID.getPhoneWYBS(context);
						String keyword=seacherEdit.getText().toString();
						String pageSize="10";
						String isFirstSearch="1";
						String searchType="1";//0ɨ��  1�ֶ�
						String result=DataSyncUtil.getSerachProduct(memberId,deviceUid,keyword,indexPage+"",pageSize,isFirstSearch/*,searchType*/);
						if(!"".equals(result)){
							boolean resultStr=JsonUtil.getResult(result);
							if(resultStr){
								//��data�������������ݿ�
								 allProductList=JsonUtil.SerachProductData(result);
								 pageTotal =JsonUtil.SerachProductPageTotal(result);
								if(allProductList!=null && allProductList.size()>0 ){
									//�����ǲ������ݿ�ľ������  ��ʱδ����
//									AllProductDao allProductDao=new AllProductDao();
//									allProductDao.addAllProduct(allProductList);
									mHandler.sendEmptyMessage(2000);
								}else{
									System.out.println("allProductList==null=23123==");
									//��ת��δ���������ҳ��
									 mHandler.sendEmptyMessage(20031);
								}
							}else{
								//��data������������ʧ��ԭ��
								registerResult=JsonUtil.registerJsonFalse(result);
								 mHandler.sendEmptyMessage(2002);
							}
						}else{
							mHandler.sendEmptyMessage(2001);
						}
					}
				}.start();
			}else{
				Toast.makeText(getApplicationContext(), "���������Ƿ����ӣ�",
						Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
	}

}
