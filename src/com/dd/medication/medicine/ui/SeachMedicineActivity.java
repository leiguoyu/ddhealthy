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
		// 当有消息发送出来的时候就执行mHandler的这个方法
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			// 处理UI
			switch (msg.what) {
			case 2000:
				searchResult.setText("此次搜索共有"+pageTotal+"页每页10条记录，当前处于第"+indexPage+"页");
				adapter = new MyAdapter();
				lv.setAdapter(adapter);
				break;
			case 2001:
				Toast.makeText(context, "获取数据异常!", Toast.LENGTH_SHORT)
						.show();
				break;
			case 2002:
				Toast.makeText(context, registerResult, Toast.LENGTH_SHORT)
				.show();
				break;
			case 20031:
				System.out.println("allProductList==null=23123=1111=");

						Toast.makeText(context, "根据此关键字未搜索到相关数据，请重新输入！", Toast.LENGTH_SHORT)
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

		//首先获取本地数据库之前搜索过的药品列表    当没有找到用户想要的药品时进行搜索 显示搜索列表

		lv = (MyListViewPullDownAndUp) findViewById(R.id.lv);
		adapter = new MyAdapter();
		lv.setAdapter(adapter);
		lv.setRefreshListener(new MyRefreshListener());

		intView();

		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// 將用戶选择的某条数据传递到下一个界面   进行获取详细药品信息
				AllProductModel allProductModel=allProductList.get(position-1);
				String productType=allProductModel.getProductType();
				if(!"".equals(productType)){
					if("CP_TYPE_01".equals(productType)){
						//药品
						Intent intent=new Intent(context, MedicineDetailActivity.class);
						intent.putExtra("allProductId", allProductModel.getAllProductId());
						startActivity(intent);	
					}else if("CP_TYPE_02".equals(productType)){
						//保健品
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
		//输入需要搜索的药品名称
		
		ImageButton selected=(ImageButton) this.findViewById(R.id.seach_medicine_search_but);
		selected.setOnClickListener(this);
		layout=(LinearLayout) this.findViewById(R.id.seach_medicine_toast_layout);
		Button setting =(Button)this.findViewById(R.id.seach_medicine_toast_setting);
		setting.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 进入设置页面
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
		// 处理下拉刷新
		@Override
		public void pullDownRefresh() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					SystemClock.sleep(2000);
//					data.addFirst(i++ + "new下拉更新data……………………");
//					AllProductModel allProductModel=new AllProductModel();
//					allProductModel.setProductName("test");
//					allProductModel.setCompany("test111");
//					allProductList.add(allProductModel);
					
					if (DataSyncUtil.getNetStatus(context)) {// 有网络
						String memberId = "";// 如果登陆的话有id 没有登录则为空指针
						String deviceUid = PhoneUUID.getPhoneWYBS(context);
						String keyword = seacherEdit.getText().toString();
						String pageSize = "10";
						String isFirstSearch = "1";
						String searchType = "1";// 0扫码 1手动
						indexPage=indexPage+1;
						String result = DataSyncUtil.getSerachProduct(memberId,
								deviceUid, keyword, indexPage+"", pageSize,
								isFirstSearch/* ,searchType */);
						if (!"".equals(result)) {
							boolean resultStr = JsonUtil.getResult(result);
							if (resultStr) {
								// 将data解析并插入数据库
								ArrayList<AllProductModel> allProductListNew=new ArrayList<AllProductModel>();
								allProductListNew = JsonUtil.SerachProductData(result);
								if (allProductListNew != null
										&& allProductListNew.size() > 0) {
									// 这里是插入数据库的具体操作 暂时未增加
									allProductList.addAll(allProductListNew);
								} else {
									mHandler.sendEmptyMessage(20031);
								}

							} else {
								// 将data解析并分析出失败原因
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
								Toast.makeText(context, "请检查网络是否连接！", Toast.LENGTH_SHORT).show();
							}
						});
					
					}
					
					mHandler.post(new Runnable() {
						@Override
						public void run() {
							adapter.notifyDataSetChanged();
							lv.onPulldownRefreshComplete();
							searchResult.setText("此次搜索共有"+pageTotal+"页每页10条记录，当前处于第"+indexPage+"页");
							Toast.makeText(getApplicationContext(), "数据添加完成",
									Toast.LENGTH_LONG).show();
							System.out.println(lv.getLastVisiblePosition()
									+ "=======" + adapter.getCount());
						}
					});
				}
			}).start();
		}

		// 处理上拉刷新
		@Override
		public void pullUpRefresh() {
			new Thread(new Runnable() {
				@Override
				public void run() {
					SystemClock.sleep(2000);
//					AllProductModel allProductModel=new AllProductModel();
//					allProductModel.setProductName("test");
//					allProductModel.setCompany("test111");
//					allProductList.addLast(i++ + "new上拉更新data……………………");
					
					if (DataSyncUtil.getNetStatus(context)) {// 有网络
						String memberId = "";// 如果登陆的话有id 没有登录则为空指针
						String deviceUid = PhoneUUID.getPhoneWYBS(context);
						String keyword = seacherEdit.getText().toString();
						String pageSize = "10";
						String isFirstSearch = "1";
						String searchType = "1";// 0扫码 1手动
						indexPage=indexPage+1;
						String result = DataSyncUtil.getSerachProduct(memberId,
								deviceUid, keyword, indexPage+"", pageSize,
								isFirstSearch/* ,searchType */);
						if (!"".equals(result)) {
							boolean resultStr = JsonUtil.getResult(result);
							if (resultStr) {
								// 将data解析并插入数据库
								ArrayList<AllProductModel> allProductListNew=new ArrayList<AllProductModel>();
								allProductListNew = JsonUtil.SerachProductData(result);
								if (allProductListNew != null
										&& allProductListNew.size() > 0) {
									// 这里是插入数据库的具体操作 暂时未增加
									allProductList.addAll(allProductListNew);
								} else {
									mHandler.sendEmptyMessage(20031);
								}

							} else {
								// 将data解析并分析出失败原因
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
							searchResult.setText("此次搜索共有"+pageTotal+"页每页10条记录，当前处于第"+indexPage+"页");
							Toast.makeText(getApplicationContext(), "数据添加完成",
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
			//手动搜索药品名称
			//这里进行搜索  搜索到进入药品设置界面  若相反则提示并手动添加
//			lv.setVisibility(View.GONE);
//			layout.setVisibility(View.VISIBLE);
			
			if (DataSyncUtil.getNetStatus(context)) {
				// 有网络
				new Thread() {
					@Override
					public void run() {
						// 你要执行的方法
						// 执行完毕后给mHandler发送一个空消息
						/*   memberId:用户id (没有登录时传递为空字符串)
						deviceUid:设备唯一标识
						keyword:搜索关键字
						pageNo:页码（必填）
						pageSize:每页条数（必填）
						isFirstSearch:是否是点击搜索按钮进行的搜索（用于区分翻页操作）
						  1:点击搜索按钮时调用本接口
						  0:翻页操作或从搜索记录中进入时调用本接口   */
						String memberId="";//如果登陆的话有id  没有登录则为空指针
						String deviceUid=PhoneUUID.getPhoneWYBS(context);
						String keyword=seacherEdit.getText().toString();
						String pageSize="10";
						String isFirstSearch="1";
						String searchType="1";//0扫码  1手动
						String result=DataSyncUtil.getSerachProduct(memberId,deviceUid,keyword,indexPage+"",pageSize,isFirstSearch/*,searchType*/);
						if(!"".equals(result)){
							boolean resultStr=JsonUtil.getResult(result);
							if(resultStr){
								//将data解析并插入数据库
								 allProductList=JsonUtil.SerachProductData(result);
								 pageTotal =JsonUtil.SerachProductPageTotal(result);
								if(allProductList!=null && allProductList.size()>0 ){
									//这里是插入数据库的具体操作  暂时未增加
//									AllProductDao allProductDao=new AllProductDao();
//									allProductDao.addAllProduct(allProductList);
									mHandler.sendEmptyMessage(2000);
								}else{
									System.out.println("allProductList==null=23123==");
									//跳转到未搜索到结果页面
									 mHandler.sendEmptyMessage(20031);
								}
							}else{
								//将data解析并分析出失败原因
								registerResult=JsonUtil.registerJsonFalse(result);
								 mHandler.sendEmptyMessage(2002);
							}
						}else{
							mHandler.sendEmptyMessage(2001);
						}
					}
				}.start();
			}else{
				Toast.makeText(getApplicationContext(), "请检查网络是否连接！",
						Toast.LENGTH_LONG).show();
			}
			break;

		default:
			break;
		}
	}

}
