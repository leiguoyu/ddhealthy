package com.dd.medication.medicine.adapter;

import com.dd.medication.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class RemindTimesListAdapter extends BaseAdapter {
	private String[] str;
	private Context context;
	private LayoutInflater inflater = null;

	public RemindTimesListAdapter(Context context, String[] str) {
		this.str=str;
		this.context=context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return str.length;
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
		ViewHolder viewHolder = null;
		if (convertView != null) {
			viewHolder = (ViewHolder) convertView.getTag();
		} else {
			viewHolder = new ViewHolder();
			LayoutInflater _LayoutInflater = LayoutInflater.from(context);
			convertView = _LayoutInflater.inflate(R.layout.simple_spinner_item,
					null);

			// convertView = inflater.inflate(R.layout.simple_spinner_item,
			// null);

			viewHolder.itemText = (TextView) convertView
					.findViewById(R.id.simple_spinner_dropdown_item);

		}

		viewHolder.itemText.setText(str[position]);

		convertView.setTag(viewHolder);
		return convertView;
	}

	
	private class ViewHolder {
		public TextView itemText;
	}

}
