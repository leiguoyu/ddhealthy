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
		initData();// ��ȡ����

		adapter = new MyexpandableListAdapter(this);
		expandableListView.setAdapter(adapter);

		// չ������group
		for (int i = 0, count = expandableListView.getCount(); i < count; i++) {
			expandableListView.expandGroup(i);
		}

		expandableListView.setOnHeaderUpdateListener(this);
		expandableListView.setOnChildClickListener(this);
		expandableListView.setOnGroupClickListener(this);
		// stickyLayout.setOnGiveUpTouchEventListener(this);
		currentYearMonthDate = DateUtil.getYearMonthDate();// ��ȡϵͳ��ǰ������
	}

	/***
	 * InitData
	 */
	void initData() {

		// ��ȡ��ǰʱ���ǰ ���30��
		String currentYearMonth = "";
		String currentDate = "";
		groupList = new ArrayList<String>();
		childList = new ArrayList<List<MedicationArrangeModel>>();
		ArrayList<MedicationArrangeModel> childTemp = new ArrayList<MedicationArrangeModel>();
		for (int i = 0; i < 30; i++) {
			Date indexDate = DateUtil.getDateBeforeOrAfter(-30 + i);
			currentDate = DateUtil.getYearMonth(indexDate);// ����
			String currentDay = DateUtil.getDay(indexDate);// ��
			String str[] = currentDay.split("-");
			currentDay = str[2];
			MedicationArrangeModel medicationArrange = new MedicationArrangeModel();

			String indexYearmonthDate = DateUtil
					.getIndexYearMonthDate(indexDate);// �õ�������

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
			currentDate = DateUtil.getYearMonth(indexDate);// ����
			String currentDay = DateUtil.getDay(indexDate);// ��
			String str[] = currentDay.split("-");
			currentDay = str[2];
			MedicationArrangeModel medicationArrange = new MedicationArrangeModel();
			String indexYearmonthDate = DateUtil
					.getIndexYearMonthDate(indexDate);// �õ�������
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

		// //�������ҩ��¼�л�ȡ����
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
	 * ����Դ
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

		// ���ظ��б����
		@Override
		public int getGroupCount() {
			return groupList.size();
		}

		// �������б����
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

			
			// ��������ʱ���ȡ��ҩ��¼���ݼ�������¼����
			
			String indexYearMonthDate = String.valueOf(((MedicationArrangeModel) getChild(groupPosition,childPosition)).getDay());
			String date = ((MedicationArrangeModel) getChild(groupPosition,childPosition)).getMedicineName();
			childHolder.timeDate.setText(date);
			//���������ջ�ȡ����
			childHolder.dateWeek.setText(DateUtil.getWeekByDateStr(indexYearMonthDate));
//			int index=(int) (Math.random() * 4);
//			childHolder.timeDate.setBackgroundResource(datebg[index]);//���ﱳ��Ҳ�޸Ĺ�
			
			
			MyHealthyDao healthDao = new MyHealthyDao();
			ArrayList<MyHealthyModel> myHealthList =healthDao.getLocalCreateDay(indexYearMonthDate);
			if(myHealthList.size()>0){
				ArrayList<String> healthRec = new ArrayList<String>();
				System.out.println("=����ؽ�������======myHealthList====="+myHealthList.size());
				for (int i = 0; i < myHealthList.size(); i++) {
					MyHealthyModel info=myHealthList.get(i);
					String symptoms=info.getLocalCreateTime()+"  �ҵ�"+info.getParts()+info.getSymptoms()+info.getSymptomsSeverity();
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
				System.out.println("�������ҩ����  ====recList.size()=="+recList.size());
				for (int i = 0; i < recList.size(); i++) {
					MedicationRemind info = recList.get(i);
					//�����һ��δ����  ҩƷ����Ϊ��ɫ   ������Ҫ�����������÷��õ�ҩƷ���г���
					String recMedicationNames=info.getMedicationName();
					System.out.println("=====recMedicationNames===="+recMedicationNames);
					medicationNames.add(recMedicationNames);
					
					// 0 ������ 1:�������� 2���ӳٷ��� 3��δ����
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
				//������Ҫȥ����ͬ����ҩԪ��
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
				// δ������ҩ��¼
				childHolder.medicineBut.setVisibility(View.GONE);
				childHolder.medicineLayout.setVisibility(View.GONE);
			}

			

			
			
			
//			
//			// ���ݵ�ǰʱ�佫��ȡ�������ݷ�Ϊ���� ֮ǰ ֮�� ��
//
//			int indexDate = DateUtil.compareYearMonthDate(currentYearMonthDate,
//					indexYearMonthDate);
//			if (indexDate == 0) {// ��ʾ����
//				childHolder.medicineTitle.setText("������ҩ��");
//				childHolder.medicineValue.setText("������("+ingCount+") ��ҩʧ��("+falseCount+")");
//			} else if (indexDate == -1) {// ��ʾ����
//				childHolder.medicineTitle.setText("δ����ҩ��");
//				//��ȡ��ǰ���ڵ���ҩ����   ��Ϊ������ʾδ�趨
//				ArrayList<String> medicineNames=recDao.getMedicationNameByDate(indexYearMonthDate);
//				if(medicineNames.size()>0){
//					String medicineName="";
//					for (int i = 0; i < medicineNames.size(); i++) {
//						medicineName=medicineName+","+medicineNames.get(i);
//					}
//					childHolder.medicineValue.setText(medicineName+"\r ����������("+medicineNames.size()+")��");
//				}else{
//					childHolder.medicineValue.setText("δ�趨");
//				}
////				childHolder.healthTitle.setVisibility(View.GONE);
////				childHolder.healthvalue.setVisibility(View.GONE);
//				childHolder.healthTitle.setText("�ҵĽ���:");
//				childHolder.healthvalue.setText(symptoms);
//			} else if (indexDate == 1) {// ��ʾ�Ѿ���ȥ
//				childHolder.medicineTitle.setText("��ҩ��¼��");
//				childHolder.medicineValue.setText("�����("+sucCount+") ��ҩʧ��("+falseCount+")");
////				if("".equals(symptoms)){
////					childHolder.healthTitle.setVisibility(View.GONE);
////					childHolder.healthvalue.setVisibility(View.GONE);
////				} else {
//					childHolder.healthTitle.setText("�ҵĽ���:");
//					childHolder.healthvalue.setText(symptoms);
////				}
//			}

			
			childHolder.medicineBut.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					//�������ʷ�ļ�¼����� ��ҩ��¼����   �����ǰ���Ժ������ԭ��ͼ
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
