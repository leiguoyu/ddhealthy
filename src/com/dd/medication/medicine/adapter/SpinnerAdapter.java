package com.dd.medication.medicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dd.medication.R;

public class SpinnerAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	private Context context;
	private String[] mItems;

	public SpinnerAdapter(Context context, String[] mItems) {
		// TODO Auto-generated constructor stub
		this.context = context;
		this.mItems = mItems;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mItems.length;
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

		viewHolder.itemText.setText(mItems[position]);

		convertView.setTag(viewHolder);
		return convertView;
	}

	private class ViewHolder {
		public TextView itemText;
	}

}
