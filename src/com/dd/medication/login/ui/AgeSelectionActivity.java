package com.dd.medication.login.ui;

import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;

import com.dd.medication.R;
import com.dd.medication.base.ui.BaseActivity;
import com.dd.medication.medicine.ui.MedicationsMainActivity;

public class AgeSelectionActivity extends BaseActivity {
	private List<Integer> years=new ArrayList<Integer>();
	private int year=0;
	private TextView age;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.age_selection_activity);


		intView();

	}

	private void intView() {
		age = (TextView) this.findViewById(R.id.age_selection_age);// 显示选择的出生年份
		ImageView sexIcon=(ImageView)this.findViewById(R.id.age_activity_sexicon);
		Intent intent = this.getIntent(); 
		final String sexIndex = intent.getStringExtra("GGMK_XB");
		if(!"".equals(sexIndex)){
			if(sexIndex.equals("GGMK_XB_01")){//男
				sexIcon.setBackgroundResource(R.drawable.boy_icon);	
			}else{//女
				sexIcon.setBackgroundResource(R.drawable.girl_icon);
			}
		}
		
		Button next = (Button) this.findViewById(R.id.age_selection_next);
		next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 将获取到的数据 更新数据库中的数据 
				if(!"".equals(sexIndex)){
					//sexIndex  GGMK_XB_01  GGMK_XB_02  GGMK_XB_03
					//year //年份
				}
				
				
				
				
				// 点击下一步进入主界面
				Intent intent = new Intent(AgeSelectionActivity.this,
						MedicationsMainActivity.class);
				startActivity(intent);
			}
		});
		Gallery gallery = (Gallery) this
				.findViewById(R.id.age_selection_gallery);
		GalleryAdpter adapter=new GalleryAdpter();
		gallery.setAdapter(adapter);
		gallery.setSelection(20);
		
		gallery.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				year=years.get(position);
				age.setText(year+"");
			}
		});
		gallery.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				year=years.get(position);
				age.setText(year+"");
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub

			}
		});

	}

	class GalleryAdpter extends BaseAdapter {
		private LayoutInflater inflater;
		
	    public GalleryAdpter() {
	    	years=getDate();
		}

		@Override
		public int getCount() {
			return years.size();
		}

		@Override
		public Object getItem(int position) {
			return position;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			ViewHolder viewHolder = null;
			if (convertView == null) {
				inflater = LayoutInflater.from(parent.getContext());
				convertView = inflater.inflate(R.layout.gallery_item, null);
				viewHolder = new ViewHolder();

				viewHolder.year = (TextView) convertView
						.findViewById(R.id.gallery_item_year);
				convertView.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) convertView.getTag();
			}

			viewHolder.year.setText(years.get(position)+"");

			return convertView;
		}
	}

	static class ViewHolder {
		public TextView year;
	}

	public List<Integer> getDate() {
		List<Integer> years=new ArrayList<Integer>();
		for (int i = 1910; i < 3000; i++) {
			years.add(i);
		}
		return years;
	}

}
