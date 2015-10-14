package com.dd.medication.medicine.ui;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.adapter.RemindTimesListAdapter;
import com.dd.medication.medicine.adapter.SpinnerAdapter;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.service.AlarmReceiver;
import com.dd.medication.service.MedicationToastService;
import com.dd.medication.util.DateTimePickerDialog;
import com.dd.medication.util.DateTimePickerDialog.OnDateTimeSetListener;
import com.dd.medication.util.DateUtil;

public class AddMedicineToastActivity extends BaseActivity implements
		OnClickListener, OnItemClickListener, OnCheckedChangeListener {
	private RadioButton nameIvisble;
	private EditText medicineNameEdit,cxsjEdit, yyjlEdit, cysm_explan;
	private ListView txsjList;
	private TextView startToastTime;
	private Handler handler = new Handler();
	private String[] remind_times = null;// 提醒时间
	private CheckBox checkDate1, checkDate2, checkDate3, checkDate4,
			checkDate5, checkDate6, checkDate7;
	private Button yyjl_jian, yyjl_jia;// 用药剂量 加减
	private RadioButton cysm_qian, cysm_zhong, cysm_hou, cysm_no;
	private String medicineName, medicineDetailId;
	private String yyjl_dnwei;// 用药单位
	private String yydxStr;// 用药对象
	private int singleDose;// 用药剂量
	private String takeMedicinetTimeExplain;// 服药时刻说明 饭前 * * * *
	private int indexPosition;
	private RemindTimesListAdapter listAdapter ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_medicine_toast_activity);

		// 获取传递过来的 药品名称及Id
		Intent intent = getIntent();
		medicineDetailId = intent.getStringExtra("medicineDetailId");
		medicineName = intent.getStringExtra("medicineName");

		intView();
	}

	private void intView() {
		Button saveSetting = (Button) this
				.findViewById(R.id.medicine_setting_save_but);
		saveSetting.setOnClickListener(this);
		nameIvisble = (RadioButton) this
				.findViewById(R.id.medicine_name_setting_but_visble);
		nameIvisble.setOnClickListener(this);
		TextView back = (TextView) this
				.findViewById(R.id.add_medicine_toast_back);
		back.setOnClickListener(this);
		// 药品名称输入框
		medicineNameEdit = (EditText) this
				.findViewById(R.id.medicine_name_edit);
		if (!"".equals(medicineName)) {
			medicineNameEdit.setText(medicineName);
		}
		// 开始时间
		startToastTime = (TextView) this
				.findViewById(R.id.medicine_toast_start_time);
		startToastTime.setOnClickListener(this);
		startToastTime.setText(DateUtil.getYearMonthDate());
		// 用药设置 持续时间
		cxsjEdit = (EditText) this.findViewById(R.id.medicine_cxsj_edit);
		// 服药日子的 选择
		checkDate1 = (CheckBox) this.findViewById(R.id.check_date1);
		checkDate2 = (CheckBox) this.findViewById(R.id.check_date2);
		checkDate3 = (CheckBox) this.findViewById(R.id.check_date3);
		checkDate4 = (CheckBox) this.findViewById(R.id.check_date4);
		checkDate5 = (CheckBox) this.findViewById(R.id.check_date5);
		checkDate6 = (CheckBox) this.findViewById(R.id.check_date6);
		checkDate7 = (CheckBox) this.findViewById(R.id.check_date7);
		checkDate1.setOnCheckedChangeListener(this);
		checkDate1.setOnCheckedChangeListener(this);
		checkDate1.setOnCheckedChangeListener(this);
		checkDate1.setOnCheckedChangeListener(this);
		checkDate1.setOnCheckedChangeListener(this);
		checkDate1.setOnCheckedChangeListener(this);
		checkDate1.setOnCheckedChangeListener(this);
		// 用药剂量加减
		yyjl_jian = (Button) this.findViewById(R.id.medicine_yyjl_jian);
		yyjl_jia = (Button) this.findViewById(R.id.medicine_yyjl_jia);
		yyjl_jian.setOnClickListener(this);
		yyjl_jia.setOnClickListener(this);
		// 用药剂量值
		yyjlEdit = (EditText) this.findViewById(R.id.medicine_jl_edit);
		
		// 吃药说明
		cysm_explan = (EditText) this.findViewById(R.id.medicine_cysm_explan);
		// 选择吃药时间点 饭前 中 后 无
		cysm_qian = (RadioButton) this.findViewById(R.id.medicine_cysm_qian);
		cysm_zhong = (RadioButton) this.findViewById(R.id.medicine_cysm_zhong);
		cysm_hou = (RadioButton) this.findViewById(R.id.medicine_cysm_hou);
		cysm_no = (RadioButton) this.findViewById(R.id.medicine_cysm_no);
		cysm_qian.setOnCheckedChangeListener(this);
		cysm_zhong.setOnCheckedChangeListener(this);
		cysm_hou.setOnCheckedChangeListener(this);
		cysm_no.setOnCheckedChangeListener(this);

		Spinner yydx = (Spinner) this
				.findViewById(R.id.medicine_toast_yydx_spinner1);// 用药对象的选择
		final String[] mItems = getResources().getStringArray(
				R.array.selected_yydx);
		SpinnerAdapter adapter = new SpinnerAdapter(context, mItems);
		yydx.setAdapter(adapter);

		yydx.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				yydxStr = mItems[position];// 用药对象
//				showToast("mItems[position]====" + yydxStr);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		Spinner selected_jx = (Spinner) this
				.findViewById(R.id.medicine_toast_jx_spinner1);// 用药对象的单位
		final String[] jx_mItems = getResources().getStringArray(
				R.array.selected_jx);
		SpinnerAdapter adapter1 = new SpinnerAdapter(context, jx_mItems);
		selected_jx.setAdapter(adapter1);
		selected_jx.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				yyjl_dnwei = jx_mItems[position];// 选择的用药单位
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		txsjList = (ListView) this
				.findViewById(R.id.medicine_toast_txcs_listView);
		txsjList.setOnItemClickListener(this);
		Spinner selected_txcs = (Spinner) this
				.findViewById(R.id.medicine_toast_txcs_spinner1);// 用药对象的选择
		final String[] txcs_mItems = getResources().getStringArray(
				R.array.selected_txcs);
		SpinnerAdapter adapter2 = new SpinnerAdapter(context, txcs_mItems);
		selected_txcs.setAdapter(adapter2);

		selected_txcs.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// 以此来获取时间
				switch (position) {
				case 0:
					remind_times = getResources().getStringArray(
							R.array.remind_time0);
					break;
				case 1:
					remind_times = getResources().getStringArray(
							R.array.remind_time1);
					break;
				case 2:
					remind_times = getResources().getStringArray(
							R.array.remind_time2);
					break;
				case 3:
					remind_times = getResources().getStringArray(
							R.array.remind_time3);
					break;
				case 4:
					remind_times = getResources().getStringArray(
							R.array.remind_time4);
					break;
				case 5:
					remind_times = getResources().getStringArray(
							R.array.remind_time5);
					break;
				case 6:
					remind_times = getResources().getStringArray(
							R.array.remind_time6);
					break;
				case 7:
					remind_times = getResources().getStringArray(
							R.array.remind_time7);
					break;
				case 8:
					remind_times = getResources().getStringArray(
							R.array.remind_time8);
					break;
				case 9:
					remind_times = getResources().getStringArray(
							R.array.remind_time9);
					break;
				case 10:
					remind_times = getResources().getStringArray(
							R.array.remind_time10);
					break;
				case 11:
					remind_times = getResources().getStringArray(
							R.array.remind_time11);
					break;
				default:
					break;
				}
				handler.postDelayed(new Runnable() {
					public void run() {
						listAdapter = new RemindTimesListAdapter(context,remind_times);

						int totalHeight = 0;
						int count = listAdapter.getCount();
						for (int i = 0; i < count; i++) {
							View listItem = listAdapter.getView(i, null,
									txsjList);
							listItem.measure(0, 0);
							totalHeight += listItem.getMeasuredHeight();
						}

						ViewGroup.LayoutParams params = txsjList
								.getLayoutParams();
						params.height = totalHeight
								+ (txsjList.getDividerHeight() * (count - 1));
						txsjList.setLayoutParams(params);

						txsjList.setAdapter(listAdapter);

					}
				}, 20);

			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

	}

	void showToast(CharSequence msg) {
		Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.medicine_yyjl_jian:
			// 用药剂量减
			String yyjlValue=yyjlEdit.getText().toString();
			if(!"".equals(yyjlValue)){
				singleDose = Integer.valueOf(yyjlValue);
				if (singleDose > 1) {
					singleDose = singleDose - 1;
					handler.post(new Runnable() {

						@Override
						public void run() {
							yyjlEdit.setText(singleDose + "");
						}
					});
				} else {
					Toast.makeText(context, "用量不能为0或小于0的值！", Toast.LENGTH_SHORT)
							.show();
				}	
			}
			break;
		case R.id.medicine_yyjl_jia:
			// 用药剂量加
			String yyjlValue1=yyjlEdit.getText().toString();
			if(!"".equals(yyjlValue1)){
			singleDose = Integer.valueOf(yyjlValue1);
			singleDose = singleDose + 1;
			handler.post(new Runnable() {

				@Override
				public void run() {
					yyjlEdit.setText(singleDose + "");
				}
			});
			}
			break;
		case R.id.medicine_toast_start_time:
			// 点击设置用药提醒开始日期时间
			// new DatePickDialog(AddMedicineToastActivity.this, "日期选择", "确定",
			// "取消").show();
			// String data=DatePickDialog.date;
			showDateDialog();

			break;
		case R.id.medicine_name_setting_but_visble:
			if (medicineNameEdit.getVisibility() == View.GONE) {
				medicineNameEdit.setVisibility(View.VISIBLE);
			} else {
				medicineNameEdit.setVisibility(View.GONE);
			}
			break;

		case R.id.medicine_setting_save_but:
			// 保存用户设置的数据 并设置闹钟 到时提醒 启动提醒页面

			Intent intent2 = new Intent(context, MedicationToastService.class);
			startService(intent2);

			Intent intent = new Intent(context, AlarmReceiver.class);
			PendingIntent sender = PendingIntent.getBroadcast(context, 0,
					intent, 0);

			String otherExplain = cysm_explan.getText().toString();
			String yyjlValue11=yyjlEdit.getText().toString();
			String cxsjValue=cxsjEdit.getText().toString();
			medicineName=medicineNameEdit.getText().toString();
			if("".equals(medicineName)){
				Toast.makeText(context, "药品不能为空！", Toast.LENGTH_SHORT)
				.show();
		        return;
			}
			if ("".equals(cxsjValue) ) {
				Toast.makeText(context, "用药天数不能为空!", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(yyjlValue11) ) {
				Toast.makeText(context, "药品用量不能为空!", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(yyjl_dnwei)) {
				Toast.makeText(context, "药品单位不能为空!", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(takeMedicinetTimeExplain)) {
				Toast.makeText(context, "服药饭前饭后时刻需要选择!", Toast.LENGTH_SHORT)
						.show();
				return;
			}

			singleDose = Integer.valueOf(yyjlValue11);
			int cxts = Integer.valueOf(cxsjValue);// 持续用药天数
			// 持续天数 和 每天设置多少次 需要计算时间 看插入多少条数据
			ArrayList<MedicationRemind> medicationRemindList = new ArrayList<MedicationRemind>();
			for (int i = 0; i < cxts; i++) {
				// 保存一条或多条用药提醒记录
				MedicationRemind medicationRemind = new MedicationRemind();
				if(!"".equals(medicineDetailId)){
					medicationRemind.setAllProductId(Integer
							.valueOf(medicineDetailId));// 药品ID
				}
				medicationRemind.setMedicationName(medicineName);// 药品名称
				medicationRemind.setSingleDose(singleDose + "");// 药品用量
				medicationRemind.setUnits(yyjl_dnwei);// 药品单位
				// 吃药说明     饭前     后
				medicationRemind.setTakeMedicinetTimeExplain(takeMedicinetTimeExplain);
				medicationRemind.setOtherExplain(otherExplain);// 其他说明
				medicationRemind.setMedicationObjectName(yydxStr);// 用药对象
				medicationRemind.setStatus(0);// 服用状态
				medicationRemind.setCloseTime("");// 实际服用时间
				Date currentDate;
				try {
					String str11=startToastTime.getText().toString();
					currentDate = DateUtil.ConverToDate(str11);
					Date indexDate = DateUtil.getDateBeforeOrAfter(currentDate,i);
					// 用药日期 选中的当前日期   和持续的日期时间
					medicationRemind.setAlertDay(DateUtil.getIndexYearMonthDate(indexDate));
					for (int j = 0; j < remind_times.length; j++) {
						Calendar c = Calendar.getInstance();
						String year = String.valueOf(c.get(Calendar.YEAR));
						String month = String.valueOf(c.get(Calendar.MONTH));
						String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
						medicationRemind.setYear(year);
						medicationRemind.setMonth(month);
						medicationRemind.setDate(day);
						medicationRemind.setYearMonth(DateUtil.getYearMonth());
						// medicationRemind.setAlertTime(DateUtil.getHourMinute());//一日内用药时间
						// 可能会有多个时间    一日内用药时间    可能会有多个时间
						medicationRemind.setAlertTime(remind_times[j]);
																	
						medicationRemindList.add(medicationRemind);
						String str=remind_times[j];
						String tiem[]=str.split(":");
						
						
						
			            long firstTime = SystemClock.elapsedRealtime();	// 开机之后到现在的运行时间(包括睡眠时间)
			            long systemTime = System.currentTimeMillis();

			            Calendar calendar = Calendar.getInstance();
					 	calendar.setTimeInMillis(System.currentTimeMillis());
					 	calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); // 这里时区需要设置一下，不然会有8个小时的时间差
					 	calendar.set(Calendar.MINUTE, Integer.valueOf(tiem[1]));
					 	calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(tiem[0]));
					 	calendar.set(Calendar.SECOND, 0);
					 	calendar.set(Calendar.MILLISECOND, 0);

					 	// 选择的每天定时时间
					 	long selectTime = calendar.getTimeInMillis();	

					 	// 如果当前时间大于设置的时间，那么就从第二天的设定时间开始
					 	if(systemTime > selectTime) {
					 		Toast.makeText(context, "设置的时间小于当前时间", Toast.LENGTH_SHORT).show();
					 		calendar.add(Calendar.DAY_OF_MONTH, 1);
					 		selectTime = calendar.getTimeInMillis();
					 	}

					 	// 计算现在时间到设定时间的时间差
					 	long time = selectTime - systemTime;
				 		firstTime += time;

//						// 进行闹铃注册
//						// （1）set(int type，long startTime，PendingIntent pi)；
//						// 该方法用于设置一次性闹钟，第一个参数表示闹钟类型，第二个参数表示闹钟执行时间，第三个参数表示闹钟响应动作。
//						// （2）setRepeating(int type，long startTime，longintervalTime，PendingIntent pi)；
//						// 该方法用于设置重复闹钟，第一个参数表示闹钟类型，第二个参数表示闹钟首次执行时间
//						// ，第三个参数表示闹钟两次执行的间隔时间，第三个参数表示闹钟响应动作。
//						// （3）setInexactRepeating（int type，long startTime，long       ，PendingIntent pi）；
//						// 该方法也用于设置重复闹钟，与第二个方法相似，不过其两个闹钟执行的间隔时间不是固定的而已。
			            AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
//			            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,firstTime, 10*1000, sender);
//						manager.set(AlarmManager.RTC_WAKEUP, firstTime, sender);
			            manager.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, 10*1000, sender);
						
						System.out.println("=======firstTime========"+firstTime);
				
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("这里抛异常了！！！！！！！");
				}
			}
			new MedicationRemindDao().addMedicationRemind(medicationRemindList);

			Toast.makeText(context, "用药提醒设置成功! ", Toast.LENGTH_LONG).show();

			break;

		case R.id.add_medicine_toast_back:
			finish();
			break;

		default:
			break;
		}
	}

	private void showDateDialog() {
		 final Calendar objTime = Calendar.getInstance();
	        int iYear = objTime.get(Calendar.YEAR);
	        int iMonth = objTime.get(Calendar.MONTH);
	        int iDay = objTime.get(Calendar.DAY_OF_MONTH);
	         
	        new DatePickerDialog(AddMedicineToastActivity.this, DatePickerListener, iYear, iMonth, iDay).show();
	}

	  private DatePickerDialog.OnDateSetListener DatePickerListener = new DatePickerDialog.OnDateSetListener(){
		  
	        @Override
	        public void onDateSet(DatePicker view, int year, int monthOfYear,
	                int dayOfMonth) {
	            // 每次保存设置的日期  
	        	 Calendar  calendar = Calendar.getInstance(); 
	        	 calendar.set(Calendar.YEAR, year);  
	            calendar.set(Calendar.MONTH, monthOfYear);  
	            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);  
	  
	            String str = year + "年" + (monthOfYear + 1) + "月" + dayOfMonth + "日";
	        	startToastTime.setText(str);
//				Toast.makeText(context, "您输入的日期是：" +str,Toast.LENGTH_LONG).show();
	        }
	    };
	    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// 这里是提醒次数的设置 点击需要弹出修改时间的滚轮
		indexPosition=position;
		showDialog();
	}

	public void showDialog() {
		DateTimePickerDialog dialog = new DateTimePickerDialog(this,
				System.currentTimeMillis());
		dialog.setOnDateTimeSetListener(new OnDateTimeSetListener() {
			public void OnDateTimeSet(AlertDialog dialog, long date) {
				//刷新数据  
				String hhMM=DateUtil.getCurrentHourMinute(date);
				remind_times[indexPosition]=hhMM;
				listAdapter.notifyDataSetChanged();
//				Toast.makeText(context, "您输入的日期是：" + DateUtil.getStringDate(date),
//						Toast.LENGTH_LONG).show();
			}
		});
		dialog.show();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		// 服药安排 挑日子
		case R.id.check_date1:
			if (isChecked) {

			}
			break;
		case R.id.check_date2:
			if (isChecked) {

			}
			break;
		case R.id.check_date3:
			if (isChecked) {

			}
			break;
		case R.id.check_date4:
			if (isChecked) {

			}
			break;
		case R.id.check_date5:
			if (isChecked) {

			}
			break;
		case R.id.check_date6:
			if (isChecked) {

			}
			break;
		case R.id.check_date7:
			if (isChecked) {

			}
			break;
		// 吃药说明 饭前 中 后 无
		case R.id.medicine_cysm_qian:
			if (isChecked) {
				takeMedicinetTimeExplain = "饭前";
			}
			break;
		case R.id.medicine_cysm_zhong:
			if (isChecked) {
				takeMedicinetTimeExplain = "饭中";
			}
			break;
		case R.id.medicine_cysm_hou:
			if (isChecked) {
				takeMedicinetTimeExplain = "饭后";
			}
			break;
		case R.id.medicine_cysm_no:
			if (isChecked) {
				takeMedicinetTimeExplain = "没有餐饮说明";
			}
			break;

		default:
			
			break;
		}
		
	}


}
