package com.dd.medication.timeline.ui;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView.LayoutParams;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.dao.MyHealthyDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.medicine.model.MyHealthyModel;
import com.dd.medication.medicine.ui.AddMedicineToastActivity;
import com.dd.medication.medicine.ui.HealthRecActivity;
import com.dd.medication.medicine.ui.MedicationsActivity;
import com.dd.medication.timeline.model.MedicationArrangeModel;
import com.dd.medication.util.DateUtil;
import com.dd.medication.view.PinnedHeaderExpandableListView;
import com.dd.medication.view.PinnedHeaderExpandableListView.OnHeaderUpdateListener;
import com.dd.medication.view.StickyLayout.OnGiveUpTouchEventListener;

public class TimeLineActivity extends BaseActivity implements
		ExpandableListView.OnChildClickListener,
		ExpandableListView.OnGroupClickListener, OnHeaderUpdateListener,
		OnGiveUpTouchEventListener {
	private PinnedHeaderExpandableListView expandableListView;
	// private StickyLayout stickyLayout;
	private ArrayList<String> groupList;
	// private ArrayList<String> dayList=new ArrayList<String>();
	private ArrayList<List<MedicationArrangeModel>> childList;
	private MyexpandableListAdapter adapter;
	private String currentYearMonthDate;
	private int datebg[]={R.drawable.color_blue,R.drawable.color_green,R.drawable.color_yellow,R.drawable.color_red};
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_line);

		expandableListView = (PinnedHeaderExpandableListView) findViewById(R.id.expandablelist);
		// stickyLayout = (StickyLayout)findViewById(R.id.sticky_layout);
		initData();// 获取数据

		adapter = new MyexpandableListAdapter(this);
		expandableListView.setAdapter(adapter);

		// 展开所有group
		for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
			expandableListView.expandGroup(i);
		}

		expandableListView.setOnHeaderUpdateListener(this);
		expandableListView.setOnChildClickListener(this);
		expandableListView.setOnGroupClickListener(this);
		// stickyLayout.setOnGiveUpTouchEventListener(this);
		currentYearMonthDate = DateUtil.getYearMonthDate();// 获取系统当前的日期
	}

	/***
	 * InitData
	 */
	void initData() {

		// 获取当前时间的前 后各30天
		String currentYearMonth = "";
		String currentDate = "";
		groupList = new ArrayList<String>();
		childList = new ArrayList<List<MedicationArrangeModel>>();
		ArrayList<MedicationArrangeModel> childTemp = new ArrayList<MedicationArrangeModel>();
		for (int i = 0; i < 30; i++) {
			Date indexDate = DateUtil.getDateBeforeOrAfter(-30 + i);
			currentDate = DateUtil.getYearMonth(indexDate);// 年月
			String currentDay = DateUtil.getDay(indexDate);// 日
			String str[] = currentDay.split("-");
			currentDay = str[2];
			MedicationArrangeModel medicationArrange = new MedicationArrangeModel();

			String indexYearmonthDate = DateUtil
					.getIndexYearMonthDate(indexDate);// 得到年月日

			medicationArrange.setMedicineName(currentDay);
			medicationArrange.setDay(indexYearmonthDate);
			medicationArrange.setHealthRec(currentDay + "2");

			childTemp.add(medicationArrange);
			if (!currentDate.equals(currentYearMonth)) {
				groupList.add(currentDate);
				if (!"".equals(currentYearMonth)) {
					childTemp.remove(childTemp.size() - 1);
					childList.add(childTemp);
					// childTemp.clear();
					// childTemp.removeAll(childTemp);
					childTemp = new ArrayList<MedicationArrangeModel>();
					childTemp.add(medicationArrange);
				}
				currentYearMonth = currentDate;
			}
		}
		for (int i = 0; i < 30; i++) {
			Date indexDate = DateUtil.getDateBeforeOrAfter(i);
			currentDate = DateUtil.getYearMonth(indexDate);// 年月
			String currentDay = DateUtil.getDay(indexDate);// 日
			String str[] = currentDay.split("-");
			currentDay = str[2];
			MedicationArrangeModel medicationArrange = new MedicationArrangeModel();
			String indexYearmonthDate = DateUtil
					.getIndexYearMonthDate(indexDate);// 得到年月日
			medicationArrange.setMedicineName(currentDay);
			medicationArrange.setDay(indexYearmonthDate);
			medicationArrange.setHealthRec(currentDay + "2");
			childTemp.add(medicationArrange);
			if (!currentDate.equals(currentYearMonth)) {
				groupList.add(currentDate);
				currentYearMonth = currentDate;
				childTemp.remove(childTemp.size() - 1);
				childList.add(childTemp);
				// childTemp.clear();
				// childTemp.removeAll(childTemp);
				childTemp = new ArrayList<MedicationArrangeModel>();
				childTemp.add(medicationArrange);
			}
		}

		childList.add(childTemp);

		// //这里从用药记录中获取数据
		// MedicationRemindDao medicationRemindDao=new MedicationRemindDao();
		// ArrayList<MedicationRemind>
		// recList=medicationRemindDao.getAllMedicationRemind();
		// if(null!= recList && recList.size()>0){
		// MedicationRemind info=recList.get(0);
		// System.out.println("info.getMedicationName()==="+info.getMedicationName());
		// }

		// childList = new ArrayList<List<MedicationArrangeModel>>();
		//
		// for (int i = 0; i < groupList.size(); i++) {
		// ArrayList<MedicationArrangeModel> childTemp = new
		// ArrayList<MedicationArrangeModel>();
		// childTemp = new
		// MedicationArrangeDao().getMedicationArrangeByMonth(groupList.get(i));
		// String yearMonth = groupList.get(i);
		// ArrayList<MedicationRemind> recLists = new MedicationRemindDao()
		// .getYearMonthMedicationRemind(yearMonth);
		// for (int j = 0; j < recLists.size(); j++) {
		// MedicationArrangeModel people = new MedicationArrangeModel();
		// people.setMedicineName(recLists.get(j).getDate());
		// people.setDay(recLists.get(j).getAlertDay());
		// people.setHealthRec(recLists.get(j).getMedicationName());
		//
		// childTemp.add(people);
		// }
		// childList.add(childTemp);
		// }

	}

	/***
	 * 数据源
	 * 
	 * @author Administrator
	 * 
	 */
	class MyexpandableListAdapter extends BaseExpandableListAdapter {
		private Context context;
		private LayoutInflater inflater;

		public MyexpandableListAdapter(Context context) {
			this.context = context;
			inflater = LayoutInflater.from(context);
		}

		// 返回父列表个数
		@Override
		public int getGroupCount() {
			return groupList.size();
		}

		// 返回子列表个数
		@Override
		public int getChildrenCount(int groupPosition) {
			int count = childList.get(groupPosition).size();
			return count;
		}

		@Override
		public Object getGroup(int groupPosition) {
			return groupList.get(groupPosition);
		}

		@Override
		public Object getChild(int groupPosition, int childPosition) {
			return childList.get(groupPosition).get(childPosition);
		}

		@Override
		public long getGroupId(int groupPosition) {
			return groupPosition;
		}

		@Override
		public long getChildId(int groupPosition, int childPosition) {
			return childPosition;
		}

		@Override
		public boolean hasStableIds() {

			return true;
		}

		@Override
		public View getGroupView(int groupPosition, boolean isExpanded,
				View convertView, ViewGroup parent) {
			GroupHolder groupHolder = null;
			if (convertView == null) {
				groupHolder = new GroupHolder();
				convertView = inflater.inflate(R.layout.group, null);
				groupHolder.textView = (TextView) convertView
						.findViewById(R.id.group);
				groupHolder.imageView = (ImageView) convertView
						.findViewById(R.id.image);
				convertView.setTag(groupHolder);
			} else {
				groupHolder = (GroupHolder) convertView.getTag();
			}

			groupHolder.textView
					.setText((CharSequence) getGroup(groupPosition));
			if (isExpanded)// ture is Expanded or false is not isExpanded
				groupHolder.imageView.setImageResource(R.drawable.expanded);
			else
				groupHolder.imageView.setImageResource(R.drawable.collapse);
			return convertView;
		}

		@Override
		public View getChildView(int groupPosition, int childPosition,
				boolean isLastChild, View convertView, ViewGroup parent) {
			ChildHolder childHolder = null;
			if (convertView == null) {
				childHolder = new ChildHolder();
				convertView = inflater.inflate(R.layout.child_status_item, null);

				childHolder.dateWeek=(RadioButton) convertView.findViewById(R.id.date_week);
				childHolder.timeDate = (RadioButton) convertView.findViewById(R.id.time_line_date);
//				childHolder.medicineTitle = (TextView) convertView.findViewById(R.id.medicine_status_title);
//				childHolder.medicineValue = (TextView) convertView.findViewById(R.id.medicine_status_value);
//				childHolder.healthTitle = (TextView) convertView.findViewById(R.id.health_title);
//				childHolder.healthvalue = (TextView) convertView.findViewById(R.id.health_value);
				childHolder.medicineLayout=(GridLayout) convertView.findViewById(R.id.time_line_chaild_medicine_layout);
				childHolder.healthLayout=(GridLayout) convertView.findViewById(R.id.time_line_chaild_health_layout);
				childHolder.medicineBut=(ImageView) convertView.findViewById(R.id.medicine_button);
				childHolder.healthBut=(ImageView) convertView.findViewById(R.id.health_button);
				
				convertView.setTag(childHolder);
			} else {
				childHolder = (ChildHolder) convertView.getTag();
			}

			
			// 根据日期时间获取用药记录数据及健康记录数据
			
			String indexYearMonthDate = String.valueOf(((MedicationArrangeModel) getChild(groupPosition,childPosition)).getDay());
			String date = ((MedicationArrangeModel) getChild(groupPosition,childPosition)).getMedicineName();
			childHolder.timeDate.setText(date);
			//根据年月日获取星期
			childHolder.dateWeek.setText(DateUtil.getWeekByDateStr(indexYearMonthDate));
//			int index=(int) (Math.random() * 4);
//			childHolder.timeDate.setBackgroundResource(datebg[index]);//这里背景也修改哈
			
			
			MyHealthyDao healthDao = new MyHealthyDao();
			ArrayList<MyHealthyModel> myHealthList =healthDao.getLocalCreateDay(indexYearMonthDate);
			if(myHealthList.size()>0){
				ArrayList<String> healthRec = new ArrayList<String>();
				System.out.println("=有相关健康数据======myHealthList====="+myHealthList.size());
				for (int i = 0; i < myHealthList.size(); i++) {
					MyHealthyModel info=myHealthList.get(i);
					String symptoms=info.getLocalCreateTime()+"  我的"+info.getParts()+info.getSymptoms()+info.getSymptomsSeverity();
					System.out.println("=====symptoms===="+symptoms);
					healthRec.add(symptoms);
				}	
				
				childHolder.healthBut.setVisibility(View.VISIBLE);
				childHolder.healthLayout.setVisibility(View.VISIBLE);
				
				if(null!=healthRec && healthRec.size()>0){
					for (int i = 0; i < healthRec.size(); i++) {
			            TextView newButton = new TextView(context);
			            newButton.setPadding(15, 0, 15, 0);
			            newButton.setTextColor(getResources().getColor(R.color.white));
			            newButton.setText(healthRec.get(i));
			            childHolder.healthLayout.addView(newButton, Math.min(i, childHolder.healthLayout.getChildCount()));	
					}
				}
				
				
			}else{
				childHolder.healthBut.setVisibility(View.INVISIBLE);
				childHolder.healthLayout.setVisibility(View.INVISIBLE);
			}
			
			
			MedicationRemindDao recDao = new MedicationRemindDao();
			ArrayList<MedicationRemind> recList = recDao.getAlertDay(indexYearMonthDate);
			if (recList.size() > 0) {
				int sucCount = 0;
				int falseCount = 0;
				int ingCount = 0;
				ArrayList<String> medicationNames = new ArrayList<String>();
				System.out.println("有相关用药数据  ====recList.size()=="+recList.size());
				for (int i = 0; i < recList.size(); i++) {
					MedicationRemind info = recList.get(i);
					//如果有一次未服用  药品名称为红色   这里需要将当日所设置服用的药品罗列出来
					String recMedicationNames=info.getMedicationName();
					System.out.println("=====recMedicationNames===="+recMedicationNames);
					medicationNames.add(recMedicationNames);
					
					// 0 进行中 1:正常服用 2：延迟服用 3：未服用
					if (info.getStatus() == 1 || info.getStatus() == 2) {
						sucCount = sucCount + 1;
					} else if (info.getStatus() == 3) {
						falseCount = falseCount + 1;
					} else if (info.getStatus() == 0) {
						ingCount = ingCount + 1;
					}
				}

				childHolder.medicineBut.setVisibility(View.VISIBLE);
				childHolder.medicineLayout.setVisibility(View.VISIBLE);
				//这里需要去除相同的用药元素
				if(null!=medicationNames && medicationNames.size()>0){
					for (int i = 0; i < medicationNames.size(); i++) {
			            TextView newButton = new TextView(context);
			            newButton.setPadding(15, 0, 15, 0);
			            if(falseCount!=0){
			            	newButton.setTextColor(getResources().getColor(R.color.red));
			            }else{
			            	newButton.setTextColor(getResources().getColor(R.color.white));
			            }
			            newButton.setText(medicationNames.get(i));
			            childHolder.medicineLayout.addView(newButton, Math.min(i, childHolder.medicineLayout.getChildCount()));	
					}
				}
				
				
			} else {
				// 未设置用药记录
				childHolder.medicineBut.setVisibility(View.GONE);
				childHolder.medicineLayout.setVisibility(View.GONE);
			}

			

			
			
			
//			
//			// 根据当前时间将获取到的数据分为今日 之前 之后 等
//
//			int indexDate = DateUtil.compareYearMonthDate(currentYearMonthDate,
//					indexYearMonthDate);
//			if (indexDate == 0) {// 表示当天
//				childHolder.medicineTitle.setText("今日用药：");
//				childHolder.medicineValue.setText("进行中("+ingCount+") 用药失败("+falseCount+")");
//			} else if (indexDate == -1) {// 表示将来
//				childHolder.medicineTitle.setText("未来用药：");
//				//获取当前日期的用药名称   若为空则显示未设定
//				ArrayList<String> medicineNames=recDao.getMedicationNameByDate(indexYearMonthDate);
//				if(medicineNames.size()>0){
//					String medicineName="";
//					for (int i = 0; i < medicineNames.size(); i++) {
//						medicineName=medicineName+","+medicineNames.get(i);
//					}
//					childHolder.medicineValue.setText(medicineName+"\r 已设置提醒("+medicineNames.size()+")次");
//				}else{
//					childHolder.medicineValue.setText("未设定");
//				}
////				childHolder.healthTitle.setVisibility(View.GONE);
////				childHolder.healthvalue.setVisibility(View.GONE);
//				childHolder.healthTitle.setText("我的健康:");
//				childHolder.healthvalue.setText(symptoms);
//			} else if (indexDate == 1) {// 表示已经过去
//				childHolder.medicineTitle.setText("用药记录：");
//				childHolder.medicineValue.setText("已完成("+sucCount+") 用药失败("+falseCount+")");
////				if("".equals(symptoms)){
////					childHolder.healthTitle.setVisibility(View.GONE);
////					childHolder.healthvalue.setVisibility(View.GONE);
////				} else {
//					childHolder.healthTitle.setText("我的健康:");
//					childHolder.healthvalue.setText(symptoms);
////				}
//			}

			
			childHolder.medicineBut.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//如果是历史的记录则进入 用药记录界面   如果当前或以后则进入原型图
					Intent intent=new Intent();
					intent.setClass(context, MedicationsActivity.class);
					startActivity(intent);
				}
			});

			childHolder.healthBut.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					Intent intent=new Intent();
					intent.setClass(context, HealthRecActivity.class);
					startActivity(intent);
				}
			});


			return convertView;
		}

		@Override
		public boolean isChildSelectable(int groupPosition, int childPosition) {
			return true;
		}
	}

	@Override
	public boolean onGroupClick(final ExpandableListView parent, final View v,
			int groupPosition, final long id) {

		return false;
	}

	@Override
	public boolean onChildClick(ExpandableListView parent, View v,
			int groupPosition, int childPosition, long id) {
		Toast.makeText(
				context,
				childList.get(groupPosition).get(childPosition)
						.getMedicineName(), 1).show();

		return false;
	}

	class GroupHolder {
		TextView textView;
		ImageView imageView;
	}

	class ChildHolder {
		private RadioButton timeDate;
		private RadioButton dateWeek;
//		public TextView medicineTitle;
//		public TextView medicineValue;
//		public TextView healthTitle;
//		public TextView healthvalue;
		private ImageView medicineBut;
		private ImageView healthBut;
		private GridLayout medicineLayout;
		private GridLayout healthLayout;

	}

	@Override
	public View getPinnedHeader() {
		View headerView = (ViewGroup) getLayoutInflater().inflate(
				R.layout.group, null);
		headerView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.WRAP_CONTENT));

		return headerView;
	}

	@Override
	public void updatePinnedHeader(View headerView, int firstVisibleGroupPos) {
		TextView textView = (TextView) headerView.findViewById(R.id.group);
		textView.setText((CharSequence) adapter.getGroup(firstVisibleGroupPos));
	}

	@Override
	public boolean giveUpTouchEvent(MotionEvent event) {
		if (expandableListView.getFirstVisiblePosition() == 0) {
			View view = expandableListView.getChildAt(0);
			if (view != null && view.getTop() >= 0) {
				return true;
			}
		}
		return false;
	}

}
