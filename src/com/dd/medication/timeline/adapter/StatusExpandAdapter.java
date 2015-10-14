package com.dd.medication.timeline.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.RadioButton;
import android.widget.TextView;

import com.dd.medication.R;
import com.dd.medication.timeline.model.ChildStatusEntity;
import com.dd.medication.timeline.model.GroupStatusEntity;

public class StatusExpandAdapter extends BaseExpandableListAdapter {
	//private static final String TAG = "StatusExpandAdapter";
	private LayoutInflater inflater = null;
	private List<GroupStatusEntity> oneList;
	private Context context;
	
	
	public StatusExpandAdapter(Context context, List<GroupStatusEntity> oneList) {
		this.oneList = oneList;
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.context = context;
	}

	@Override
	public int getGroupCount() {
		// TODO Auto-generated method stub
		return oneList.size();
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		if(oneList.get(groupPosition).getTwoList() == null){
			return 0;
		}else{
			return oneList.get(groupPosition).getTwoList().size();
		}
	}

	@Override
	public GroupStatusEntity getGroup(int groupPosition) {
		// TODO Auto-generated method stub
		return oneList.get(groupPosition);
	}

	@Override
	public ChildStatusEntity getChild(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return oneList.get(groupPosition).getTwoList().get(childPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		// TODO Auto-generated method stub
		return groupPosition;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return childPosition;
	}

	@Override
	public boolean hasStableIds() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		
		GroupViewHolder holder = new GroupViewHolder();
		
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.group_status_item, null);
		}
		holder.groupName = (TextView) convertView.findViewById(R.id.one_status_name);
		holder.group_tiao = (TextView) convertView.findViewById(R.id.group_tiao);
		
		holder.groupName.setText(oneList.get(groupPosition).getStatusName());
		if(oneList.get(groupPosition).getTwoList().get(0).isIsfinished()){
			holder.group_tiao.setBackgroundColor(context.getResources().getColor(R.color.yellow));
		}else{
			holder.group_tiao.setBackgroundColor(context.getResources().getColor(R.color.grey));
		}
		
		return convertView;
	}

	@Override
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {
		ChildViewHolder viewHolder = null;
		ChildStatusEntity entity = getChild(groupPosition, childPosition);
		if (convertView != null) {
			viewHolder = (ChildViewHolder) convertView.getTag();
		} else {
			viewHolder = new ChildViewHolder();
			convertView = inflater.inflate(R.layout.child_status_item, null);
			
			viewHolder.timeDate=(RadioButton) convertView.findViewById(R.id.time_line_date);
//			viewHolder.medicineTitle = (TextView) convertView.findViewById(R.id.medicine_status_title);
//			viewHolder.medicineValue = (TextView) convertView.findViewById(R.id.medicine_status_value);
//			viewHolder.healthTitle = (TextView) convertView.findViewById(R.id.health_title);
//			viewHolder.healthvalue = (TextView) convertView.findViewById(R.id.health_value);
			
		}
		viewHolder.timeDate.setText(entity.getStatusName());
//		viewHolder.medicineTitle.setText(entity.getCompleteTime());
//		viewHolder.healthTitle.setText(entity.getCompleteTime());
//		
//		if(entity.isIsfinished()){
//			viewHolder.tiao.setBackgroundColor(context.getResources().getColor(R.color.yellow));
//		}else{
//			viewHolder.tiao.setBackgroundColor(context.getResources().getColor(R.color.grey));
//		}
		
		convertView.setTag(viewHolder);
		return convertView;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		// TODO Auto-generated method stub
		return false;
	}
	
	private class GroupViewHolder {
		TextView groupName;
		public TextView group_tiao;
	}
	
	private class ChildViewHolder {
		private RadioButton timeDate;
//		public TextView medicineTitle;
//		public TextView medicineValue;
//		public TextView healthTitle;
//		public TextView healthvalue;
	}

}
