package com.dd.medication.medicine.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.medication.R;

public class BodyChartAdapter extends BaseAdapter {
	private LayoutInflater inflater = null;
	private Context context;
	private String[] mItems;        
	
	private int bodyPartsImg[]={R.drawable.toubu,R.drawable.yanjing,R.drawable.bizi,R.drawable.zhui,R.drawable.lianbu,R.drawable.jingbu,
			R.drawable.xiongbu,R.drawable.shoubu,R.drawable.fubu,R.drawable.tunbu,R.drawable.qita/*ล่นว*/,R.drawable.fugugou,R.drawable.shengzhiqi,
			R.drawable.tuibu,R.drawable.beibu};

	public BodyChartAdapter(Context context, String[] mItems) {
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
			convertView = _LayoutInflater.inflate(R.layout.body_part_item,
					null);

			// convertView = inflater.inflate(R.layout.simple_spinner_item,
			// null);

			viewHolder.itemText = (TextView) convertView
					.findViewById(R.id.body_part_item_text);
			viewHolder.icon=(ImageView)convertView.findViewById(R.id.body_part_item_icon);

		}

		viewHolder.itemText.setText(mItems[position]);
		viewHolder.icon.setBackgroundResource(bodyPartsImg[position]);

		convertView.setTag(viewHolder);
		return convertView;
	}

	private class ViewHolder {
		public TextView itemText;
		public ImageView icon;
	}

}
