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
	private String[] remind_times = null;// ����ʱ��
	private CheckBox checkDate1, checkDate2, checkDate3, checkDate4,
			checkDate5, checkDate6, checkDate7;
	private Button yyjl_jian, yyjl_jia;// ��ҩ���� �Ӽ�
	private RadioButton cysm_qian, cysm_zhong, cysm_hou, cysm_no;
	private String medicineName, medicineDetailId;
	private String yyjl_dnwei;// ��ҩ��λ
	private String yydxStr;// ��ҩ����
	private int singleDose;// ��ҩ����
	private String takeMedicinetTimeExplain;// ��ҩʱ��˵�� ��ǰ * * * *
	private int indexPosition;
	private RemindTimesListAdapter listAdapter ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_medicine_toast_activity);

		// ��ȡ���ݹ����� ҩƷ���Ƽ�Id
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
		// ҩƷ���������
		medicineNameEdit = (EditText) this
				.findViewById(R.id.medicine_name_edit);
		if (!"".equals(medicineName)) {
			medicineNameEdit.setText(medicineName);
		}
		// ��ʼʱ��
		startToastTime = (TextView) this
				.findViewById(R.id.medicine_toast_start_time);
		startToastTime.setOnClickListener(this);
		startToastTime.setText(DateUtil.getYearMonthDate());
		// ��ҩ���� ����ʱ��
		cxsjEdit = (EditText) this.findViewById(R.id.medicine_cxsj_edit);
		// ��ҩ���ӵ� ѡ��
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
		// ��ҩ�����Ӽ�
		yyjl_jian = (Button) this.findViewById(R.id.medicine_yyjl_jian);
		yyjl_jia = (Button) this.findViewById(R.id.medicine_yyjl_jia);
		yyjl_jian.setOnClickListener(this);
		yyjl_jia.setOnClickListener(this);
		// ��ҩ����ֵ
		yyjlEdit = (EditText) this.findViewById(R.id.medicine_jl_edit);
		
		// ��ҩ˵��
		cysm_explan = (EditText) this.findViewById(R.id.medicine_cysm_explan);
		// ѡ���ҩʱ��� ��ǰ �� �� ��
		cysm_qian = (RadioButton) this.findViewById(R.id.medicine_cysm_qian);
		cysm_zhong = (RadioButton) this.findViewById(R.id.medicine_cysm_zhong);
		cysm_hou = (RadioButton) this.findViewById(R.id.medicine_cysm_hou);
		cysm_no = (RadioButton) this.findViewById(R.id.medicine_cysm_no);
		cysm_qian.setOnCheckedChangeListener(this);
		cysm_zhong.setOnCheckedChangeListener(this);
		cysm_hou.setOnCheckedChangeListener(this);
		cysm_no.setOnCheckedChangeListener(this);

		Spinner yydx = (Spinner) this
				.findViewById(R.id.medicine_toast_yydx_spinner1);// ��ҩ�����ѡ��
		final String[] mItems = getResources().getStringArray(
				R.array.selected_yydx);
		SpinnerAdapter adapter = new SpinnerAdapter(context, mItems);
		yydx.setAdapter(adapter);

		yydx.setOnItemSelectedListener(new OnItemSelectedListener() {
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				yydxStr = mItems[position];// ��ҩ����
//				showToast("mItems[position]====" + yydxStr);
			}

			public void onNothingSelected(AdapterView<?> parent) {
			}
		});

		Spinner selected_jx = (Spinner) this
				.findViewById(R.id.medicine_toast_jx_spinner1);// ��ҩ����ĵ�λ
		final String[] jx_mItems = getResources().getStringArray(
				R.array.selected_jx);
		SpinnerAdapter adapter1 = new SpinnerAdapter(context, jx_mItems);
		selected_jx.setAdapter(adapter1);
		selected_jx.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				yyjl_dnwei = jx_mItems[position];// ѡ�����ҩ��λ
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {

			}
		});

		txsjList = (ListView) this
				.findViewById(R.id.medicine_toast_txcs_listView);
		txsjList.setOnItemClickListener(this);
		Spinner selected_txcs = (Spinner) this
				.findViewById(R.id.medicine_toast_txcs_spinner1);// ��ҩ�����ѡ��
		final String[] txcs_mItems = getResources().getStringArray(
				R.array.selected_txcs);
		SpinnerAdapter adapter2 = new SpinnerAdapter(context, txcs_mItems);
		selected_txcs.setAdapter(adapter2);

		selected_txcs.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// �Դ�����ȡʱ��
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
			// ��ҩ������
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
					Toast.makeText(context, "��������Ϊ0��С��0��ֵ��", Toast.LENGTH_SHORT)
							.show();
				}	
			}
			break;
		case R.id.medicine_yyjl_jia:
			// ��ҩ������
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
			// ���������ҩ���ѿ�ʼ����ʱ��
			// new DatePickDialog(AddMedicineToastActivity.this, "����ѡ��", "ȷ��",
			// "ȡ��").show();
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
			// �����û����õ����� ���������� ��ʱ���� ��������ҳ��

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
				Toast.makeText(context, "ҩƷ����Ϊ�գ�", Toast.LENGTH_SHORT)
				.show();
		        return;
			}
			if ("".equals(cxsjValue) ) {
				Toast.makeText(context, "��ҩ��������Ϊ��!", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(yyjlValue11) ) {
				Toast.makeText(context, "ҩƷ��������Ϊ��!", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(yyjl_dnwei)) {
				Toast.makeText(context, "ҩƷ��λ����Ϊ��!", Toast.LENGTH_SHORT).show();
				return;
			}
			if ("".equals(takeMedicinetTimeExplain)) {
				Toast.makeText(context, "��ҩ��ǰ����ʱ����Ҫѡ��!", Toast.LENGTH_SHORT)
						.show();
				return;
			}

			singleDose = Integer.valueOf(yyjlValue11);
			int cxts = Integer.valueOf(cxsjValue);// ������ҩ����
			// �������� �� ÿ�����ö��ٴ� ��Ҫ����ʱ�� ���������������
			ArrayList<MedicationRemind> medicationRemindList = new ArrayList<MedicationRemind>();
			for (int i = 0; i < cxts; i++) {
				// ����һ���������ҩ���Ѽ�¼
				MedicationRemind medicationRemind = new MedicationRemind();
				if(!"".equals(medicineDetailId)){
					medicationRemind.setAllProductId(Integer
							.valueOf(medicineDetailId));// ҩƷID
				}
				medicationRemind.setMedicationName(medicineName);// ҩƷ����
				medicationRemind.setSingleDose(singleDose + "");// ҩƷ����
				medicationRemind.setUnits(yyjl_dnwei);// ҩƷ��λ
				// ��ҩ˵��     ��ǰ     ��
				medicationRemind.setTakeMedicinetTimeExplain(takeMedicinetTimeExplain);
				medicationRemind.setOtherExplain(otherExplain);// ����˵��
				medicationRemind.setMedicationObjectName(yydxStr);// ��ҩ����
				medicationRemind.setStatus(0);// ����״̬
				medicationRemind.setCloseTime("");// ʵ�ʷ���ʱ��
				Date currentDate;
				try {
					String str11=startToastTime.getText().toString();
					currentDate = DateUtil.ConverToDate(str11);
					Date indexDate = DateUtil.getDateBeforeOrAfter(currentDate,i);
					// ��ҩ���� ѡ�еĵ�ǰ����   �ͳ���������ʱ��
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
						// medicationRemind.setAlertTime(DateUtil.getHourMinute());//һ������ҩʱ��
						// ���ܻ��ж��ʱ��    һ������ҩʱ��    ���ܻ��ж��ʱ��
						medicationRemind.setAlertTime(remind_times[j]);
																	
						medicationRemindList.add(medicationRemind);
						String str=remind_times[j];
						String tiem[]=str.split(":");
						
						
						
			            long firstTime = SystemClock.elapsedRealtime();	// ����֮�����ڵ�����ʱ��(����˯��ʱ��)
			            long systemTime = System.currentTimeMillis();

			            Calendar calendar = Calendar.getInstance();
					 	calendar.setTimeInMillis(System.currentTimeMillis());
					 	calendar.setTimeZone(TimeZone.getTimeZone("GMT+8")); // ����ʱ����Ҫ����һ�£���Ȼ����8��Сʱ��ʱ���
					 	calendar.set(Calendar.MINUTE, Integer.valueOf(tiem[1]));
					 	calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(tiem[0]));
					 	calendar.set(Calendar.SECOND, 0);
					 	calendar.set(Calendar.MILLISECOND, 0);

					 	// ѡ���ÿ�춨ʱʱ��
					 	long selectTime = calendar.getTimeInMillis();	

					 	// �����ǰʱ��������õ�ʱ�䣬��ô�ʹӵڶ�����趨ʱ�俪ʼ
					 	if(systemTime > selectTime) {
					 		Toast.makeText(context, "���õ�ʱ��С�ڵ�ǰʱ��", Toast.LENGTH_SHORT).show();
					 		calendar.add(Calendar.DAY_OF_MONTH, 1);
					 		selectTime = calendar.getTimeInMillis();
					 	}

					 	// ��������ʱ�䵽�趨ʱ���ʱ���
					 	long time = selectTime - systemTime;
				 		firstTime += time;

//						// ��������ע��
//						// ��1��set(int type��long startTime��PendingIntent pi)��
//						// �÷�����������һ�������ӣ���һ��������ʾ�������ͣ��ڶ���������ʾ����ִ��ʱ�䣬������������ʾ������Ӧ������
//						// ��2��setRepeating(int type��long startTime��longintervalTime��PendingIntent pi)��
//						// �÷������������ظ����ӣ���һ��������ʾ�������ͣ��ڶ���������ʾ�����״�ִ��ʱ��
//						// ��������������ʾ��������ִ�еļ��ʱ�䣬������������ʾ������Ӧ������
//						// ��3��setInexactRepeating��int type��long startTime��long       ��PendingIntent pi����
//						// �÷���Ҳ���������ظ����ӣ���ڶ����������ƣ���������������ִ�еļ��ʱ�䲻�ǹ̶��Ķ��ѡ�
			            AlarmManager manager = (AlarmManager)getSystemService(ALARM_SERVICE);
//			            manager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,firstTime, 10*1000, sender);
//						manager.set(AlarmManager.RTC_WAKEUP, firstTime, sender);
			            manager.setRepeating(AlarmManager.RTC_WAKEUP, firstTime, 10*1000, sender);
						
						System.out.println("=======firstTime========"+firstTime);
				
					}
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("�������쳣�ˣ�������������");
				}
			}
			new MedicationRemindDao().addMedicationRemind(medicationRemindList);

			Toast.makeText(context, "��ҩ�������óɹ�! ", Toast.LENGTH_LONG).show();

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
	            // ÿ�α������õ�����  
	        	 Calendar  calendar = Calendar.getInstance(); 
	        	 calendar.set(Calendar.YEAR, year);  
	            calendar.set(Calendar.MONTH, monthOfYear);  
	            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);  
	  
	            String str = year + "��" + (monthOfYear + 1) + "��" + dayOfMonth + "��";
	        	startToastTime.setText(str);
//				Toast.makeText(context, "������������ǣ�" +str,Toast.LENGTH_LONG).show();
	        }
	    };
	    
	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position,
			long id) {
		// ���������Ѵ��������� �����Ҫ�����޸�ʱ��Ĺ���
		indexPosition=position;
		showDialog();
	}

	public void showDialog() {
		DateTimePickerDialog dialog = new DateTimePickerDialog(this,
				System.currentTimeMillis());
		dialog.setOnDateTimeSetListener(new OnDateTimeSetListener() {
			public void OnDateTimeSet(AlertDialog dialog, long date) {
				//ˢ������  
				String hhMM=DateUtil.getCurrentHourMinute(date);
				remind_times[indexPosition]=hhMM;
				listAdapter.notifyDataSetChanged();
//				Toast.makeText(context, "������������ǣ�" + DateUtil.getStringDate(date),
//						Toast.LENGTH_LONG).show();
			}
		});
		dialog.show();
	}

	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		switch (buttonView.getId()) {
		// ��ҩ���� ������
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
		// ��ҩ˵�� ��ǰ �� �� ��
		case R.id.medicine_cysm_qian:
			if (isChecked) {
				takeMedicinetTimeExplain = "��ǰ";
			}
			break;
		case R.id.medicine_cysm_zhong:
			if (isChecked) {
				takeMedicinetTimeExplain = "����";
			}
			break;
		case R.id.medicine_cysm_hou:
			if (isChecked) {
				takeMedicinetTimeExplain = "����";
			}
			break;
		case R.id.medicine_cysm_no:
			if (isChecked) {
				takeMedicinetTimeExplain = "û�в���˵��";
			}
			break;

		default:
			
			break;
		}
		
	}


}
