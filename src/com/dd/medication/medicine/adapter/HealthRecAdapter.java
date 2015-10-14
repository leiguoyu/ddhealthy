package com.dd.medication.medicine.adapter;

import java.util.ArrayList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import com.dd.medication.R;
import com.dd.medication.medicine.dao.MyHealthyDao;
import com.dd.medication.medicine.model.MyHealthyModel;
import com.dd.medication.medicine.ui.SymptomSettingActivity;

public class HealthRecAdapter extends BaseAdapter {
	
	private Context context;
	private LayoutInflater inflater = null;
	private ArrayList<MyHealthyModel> listData =new ArrayList<MyHealthyModel>();
	
	public HealthRecAdapter(Context context,ArrayList<MyHealthyModel> listData) {
		this.context=context;
		this.listData=listData;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return listData.size();
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder = null;
		if (convertView != null) {
			viewHolder = (ViewHolder) convertView.getTag();
		} else {
			viewHolder = new ViewHolder();
			convertView = inflater.inflate(R.layout.health_rec_item,null);

			viewHolder.symptom = (TextView) convertView
					.findViewById(R.id.health_rec_item_symptom);
			viewHolder.dateTime = (TextView) convertView
					.findViewById(R.id.health_rec_item_datetime);
			viewHolder.delete=  (TextView) convertView
					.findViewById(R.id.health_rec_item_delete);
			viewHolder.edit=  (TextView) convertView
					.findViewById(R.id.health_rec_item_edit);		
			
		}
		
		MyHealthyModel myHealthyModel=listData.get(position);
		String parts=myHealthyModel.getParts();//身体病变部位
		String symptoms= myHealthyModel.getSymptoms();//症状
		String symptomsSeverity= myHealthyModel.getSymptomsSeverity();//严重程度
		String symptomsDetail= myHealthyModel.getSymptomsDetail();//病症的详细情况
		String date=myHealthyModel.getLocalCreateDay();
		String time=myHealthyModel.getLocalCreateTime();
		viewHolder.symptom.setText("我的"+parts+symptoms+symptomsSeverity+symptomsDetail);
		viewHolder.dateTime.setText(date+" "+time);
		viewHolder.delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 删除此条数据  这里需要提示
				deleteHealthRecDialog(position);
			}
		});
		
		viewHolder.edit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 编辑词条数据  跳转到编辑界面
				Intent intent =new Intent();
				intent.setClass(context, SymptomSettingActivity.class);
		        Bundle mBundle = new Bundle();  
		        MyHealthyModel myHealthyModel=listData.get(position);
		        mBundle.putSerializable("myHealthyModel", myHealthyModel);  
		        intent.putExtras(mBundle);  
		        context.startActivity(intent);
			}
		});
		
		convertView.setTag(viewHolder);
		
		return convertView;
	}
	
	private class ViewHolder {
		public TextView symptom;
		public TextView dateTime;
		public TextView delete;
		public TextView edit;
	}
	
	
	//显示基于Layout的AlertDialog  
    private void deleteHealthRecDialog(final int position) {  
        LayoutInflater inflater = LayoutInflater.from(context);  
        final View view = inflater.inflate(  
                R.layout.delete_health_rec_dialog, null);  
        AlertDialog.Builder builder = new AlertDialog.Builder(context);  
        builder.setView(view);  
		final AlertDialog dia = builder.create();
		dia.setCanceledOnTouchOutside(true);
		dia.show();
        
        Button delete=(Button)view.findViewById(R.id.delete_health_rec_dialog_delete);  
        Button cancel=(Button)view.findViewById(R.id.delete_health_rec_dialog_cancel);  
        delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// 移除此条数据 刷新列表  删除数据库
				listData.remove(position);
				notifyDataSetChanged();
				//这里需要修改数据库   id设为自动增长
				MyHealthyDao dao=new MyHealthyDao();
				String whereClause="myHealthyId=?";
				String[] whereArgs={String.valueOf(listData.get(position).getMyHealthyId())};
				dao.deleteHealthRec(whereClause, whereArgs);
				dia.dismiss();
				dia.cancel();
			}
		});
        cancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dia.dismiss();
				dia.cancel();
			}
		});

        
    }  
    

}
