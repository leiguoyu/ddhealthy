package com.dd.medication.timeline.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.timeline.adapter.StatusExpandAdapter;
import com.dd.medication.timeline.model.ChildStatusEntity;
import com.dd.medication.timeline.model.GroupStatusEntity;

public class TimeLineActivity1 extends BaseActivity{
	private static final String TAG = "MainActivity";
	private List<GroupStatusEntity> oneList;
	private ExpandableListView expandlistView;
	private StatusExpandAdapter statusAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.time_line);
		
//		expandlistView = (ExpandableListView) findViewById(R.id.expandlist);
		
		
		
		putInitData();
		
		statusAdapter = new StatusExpandAdapter(context, oneList);
		expandlistView.setAdapter(statusAdapter);
		expandlistView.setGroupIndicator(null); // 去掉默认带的箭头

		// 遍历所有group,将所有项设置成默认展开
		int groupCount = expandlistView.getCount();
		for (int i = 0; i < groupCount; i++) {
			expandlistView.expandGroup(i);
		}
		expandlistView.setOnGroupClickListener(new OnGroupClickListener() {

			@Override
			public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
				// TODO Auto-generated method stub
				return true;
			}
		});
		
		//这里从用药记录中获取数据  
		MedicationRemindDao medicationRemindDao=new MedicationRemindDao();
		ArrayList<MedicationRemind> recList=medicationRemindDao.getAllMedicationRemind();
	    if(null!= recList && recList.size()>0){
	    	MedicationRemind info=recList.get(0);
	    	System.out.println("info.getMedicationName()==="+info.getMedicationName());
	    }
	
	}

	private void putInitData() {
		String[] strArray = new String[]{"7月 ", "8月", "9月"};
//		String[] strArray = new String[]{"贷款", "更名", "交接"};
//		String[] str1 = new String[]{"经理送件", "银行送件审核", "银行评估", "买卖双方签约"};
		String[] str1 = new String[]{"27", "28", "29", "30"};
//		String[] str2 = new String[]{"更名", "划首付", "买方取产权证", "物业维修基金更名", "土地证更名"};
		String[] str2 = new String[]{"1", "2", "3", "4", "5"};
		String[] str3 = new String[]{"1", "2", "3", "4"};
		
		String[] timeStr1 = new String[]{"用药记录", "我的健康", "2013-11-02 13:16:22", "2013-11-02 13:16:22"};
		String[] timeStr2 = new String[]{"我的健康", "2013-11-02 13:16:22", "", "", ""};
		String[] timeStr3 = new String[]{"", "", "", ""};
		
		oneList = new ArrayList<GroupStatusEntity>();
		for(int i=0 ; i<strArray.length ; i++){
			GroupStatusEntity one = new GroupStatusEntity();
			one.setStatusName(strArray[i]);
			List<ChildStatusEntity> twoList = new ArrayList<ChildStatusEntity>();
			String[] order = str1;
			String[] time = timeStr1;
			switch (i) {
			case 0:
				order = str1;
				time = timeStr1;
				Log.i(TAG, "str1");
				break;
			case 1:
				order = str2;
				time = timeStr2;
				Log.i(TAG, "str2");
				break;
			case 2:
				order = str3;
				time = timeStr3;
				Log.i(TAG, "str3");
				break;
			}
			
			for(int j=0 ; j<order.length ; j++){
				ChildStatusEntity two = new ChildStatusEntity();
				two.setStatusName(order[j]);
				if(time[j].equals("")){
					two.setCompleteTime("暂无");
					two.setIsfinished(false);
				}else{
					two.setCompleteTime(time[j]+" 完成");
					two.setIsfinished(true);
				}
				
				twoList.add(two);
			}
			one.setTwoList(twoList);
			oneList.add(one);
		}
		Log.i(TAG, "二级状态："+oneList.get(0).getTwoList().get(0).getStatusName());
		
	}


}