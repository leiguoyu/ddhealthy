package com.dd.medication.medicine.adapter;


import java.util.ArrayList;

import com.dd.medication.R;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.medicine.ui.AddMedicineToastActivity;
import com.dd.medication.util.DateUtil;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MedicationsPlanViewPageAdapter extends PagerAdapter implements OnClickListener{
	private LayoutInflater inflater = null;
	private Context context;
	private ArrayList<MedicationRemind> recList;
	private MedicationRemindDao  recDao;
	private String currentYearMonthDate;
	
	public MedicationsPlanViewPageAdapter(Context context,ArrayList<MedicationRemind> recList) {
		this.recList=recList;
		this.context=context;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		recDao=new MedicationRemindDao();
		currentYearMonthDate = DateUtil.getYearMonthDate();// 获取系统当前的日期
	}
	
    /**
	 * 判断是否由对象生成界面
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	/**
	 * 销毁position位置的界面
	 */
	@Override
	public void destroyItem(ViewGroup container, int position,
			Object object) {
		container.removeView((View) object);
	}

	/**
	 * 初始化position位置的界面
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ViewHolder viewHolder = new ViewHolder();
		View convertView = inflater.inflate(R.layout.medications_plan_page_item, null);

		//原型图背景icon
		viewHolder.yxtbg=(RelativeLayout) convertView.findViewById(R.id.medication_plan_yxtbg);
		viewHolder.pillIcon0 = (ImageView) convertView.findViewById(R.id.pill_icon0);
		viewHolder.pillIcon1 = (ImageView) convertView.findViewById(R.id.pill_icon1);
		viewHolder.pillIcon2 = (ImageView) convertView.findViewById(R.id.pill_icon2);
		viewHolder.pillIcon3 = (ImageView) convertView.findViewById(R.id.pill_icon3);
		viewHolder.pillIcon4 = (ImageView) convertView.findViewById(R.id.pill_icon4);
		viewHolder.pillIcon5 = (ImageView) convertView.findViewById(R.id.pill_icon5);
		viewHolder.pillIcon6 = (ImageView) convertView.findViewById(R.id.pill_icon6);
		viewHolder.pillIcon7 = (ImageView) convertView.findViewById(R.id.pill_icon7);
		viewHolder.time = (TextView) convertView.findViewById(R.id.medication_plan_time);
		viewHolder.nextTime = (TextView) convertView.findViewById(R.id.medication_plan_next_time);
		
		viewHolder.pillIcon0.setOnClickListener(this);
		viewHolder.pillIcon1.setOnClickListener(this);
		viewHolder.pillIcon2.setOnClickListener(this);
		viewHolder.pillIcon3.setOnClickListener(this);
		viewHolder.pillIcon4.setOnClickListener(this);
		viewHolder.pillIcon5.setOnClickListener(this);
		viewHolder.pillIcon6.setOnClickListener(this);
		viewHolder.pillIcon7.setOnClickListener(this);
		
		  
		
		MedicationRemind infos=recList.get(position);
		String alertDay=infos.getAlertDay();
		//根据alertDay获取当天的所有数据进行展示相关的药丸 
		int indexDate = DateUtil.compareYearMonthDate(currentYearMonthDate,
				alertDay);
		if (indexDate == 0) {// 表示当天
			viewHolder.time.setVisibility(View.VISIBLE);
			viewHolder.time.setText(DateUtil.getHourMinute());  
			viewHolder.yxtbg.setBackgroundResource(R.drawable.tixing_bg);
		} else if (indexDate == -1) {// 表示将来
			viewHolder.time.setVisibility(View.INVISIBLE);
			viewHolder.yxtbg.setBackgroundResource(R.drawable.tixing_bg);
		} else if (indexDate == 1) {// 表示已经过去
			viewHolder.time.setVisibility(View.INVISIBLE);
			viewHolder.yxtbg.setBackgroundResource(R.drawable.tixing_bg);
		}

		ArrayList<MedicationRemind> recDateList=recDao.getAlertDay(alertDay);
		if(recDateList!=null && recDateList.size()>0){
			for (int i = 0; i < recDateList.size(); i++) {
				//这里获取 alterTime进行匹配比较 显示药丸
				MedicationRemind recDate=recDateList.get(i);
				String alterTime=recDate.getAlertTime();//计划吃药时间
				String closeTime=recDate.getCloseTime();//实际吃药时间
				//比较是此时间
				if(DateUtil.compareDate(alterTime, "03:00")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon0.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon0.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon0.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "06:00")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon1.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon1.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon1.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "09:00")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon2.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon2.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon2.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "12:00")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon3.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon3.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon3.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "15:00")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon4.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon4.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon4.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "18:00")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon5.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon5.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon5.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "21:00")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon6.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon6.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon6.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "23:59")){//时间00：00-03：00 凌晨
					viewHolder.pillIcon7.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//未完成服药
						viewHolder.pillIcon7.setBackgroundResource(R.drawable.pill_fase);	
					}else{//服用
						viewHolder.pillIcon7.setBackgroundResource(R.drawable.pill_suc);	
					}
				}
			}
		}
		
		
		container.addView(convertView);

		return convertView;
	}

	@Override
	public int getCount() {
		if(recList!=null && recList.size()>0){
			return recList.size();	
		}
		return 1;
	}


	private static class ViewHolder {
		public RelativeLayout yxtbg;
		public ImageView pillIcon0;
		public ImageView pillIcon1;
		public ImageView pillIcon2;
		public ImageView pillIcon3;
		public ImageView pillIcon4;
		public ImageView pillIcon5;
		public ImageView pillIcon6;
		public ImageView pillIcon7;
		public static TextView time;
		public TextView nextTime;
	}

	 public static final BroadcastReceiver receiver = new BroadcastReceiver() {
         @Override
           public void onReceive(Context context, Intent intent) {
               String action = intent.getAction();
                 if (action.equals(Intent.ACTION_TIME_TICK)) {
 
                	 ViewHolder.time.setText(DateUtil.getHourMinute());                 
                }
          }
    };
    

	@Override
	public void onClick(View v) {
		System.out.println("%%%%%%%%%%%%%%==========");
		switch (v.getId()) {
		case R.id.pill_icon0:
			showMedicationDetailPlanDialog();
			break;
		case R.id.pill_icon1:
			showMedicationDetailPlanDialog();
			break;
		case R.id.pill_icon2:
			showMedicationDetailPlanDialog();
			break;
		case R.id.pill_icon3:
			showMedicationDetailPlanDialog();
			break;
		case R.id.pill_icon4:
			showMedicationPlanDialog();
			break;
		case R.id.pill_icon5:
			showMedicationPlanDialog();
			break;
		case R.id.pill_icon6:
			showMedicationPlanDialog();
			break;
		case R.id.pill_icon7:
			showMedicationPlanDialog();
			break;

		default:
			break;
		}
		
	}
	
	
    private void showMedicationDetailPlanDialog() {
    	 // 调用dialog
		// 获取LayoutInflater实例
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		// 这里的main布局是在inflate中加入的哦，以前都是直接this.setContentView()的吧？呵呵
		// 该方法返回的是一个View的对象，是布局中的根
		View layout = inflater.inflate(R.layout.medicine_notifiction, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(layout);
		final AlertDialog dia = builder.create();
		dia.setCanceledOnTouchOutside(true);
		dia.show();
		// 如何获取我们main中的控件呢？也很简单
		Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// 跳过
		Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// 瞌睡
		Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// 服用
		Button cxsd = (Button) layout
				.findViewById(R.id.medicine_notifiction_cxsd);// 重新设定

		tg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//跳过是 取消闹钟  
				
				dia.dismiss();
			}
		});

		ks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//延迟十分钟再提醒  
				
				dia.dismiss();
			}
		});

		fy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//服用此药品
				
				dia.dismiss();
			}
		});

		cxsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//重置  进行重新设置
                Intent intent=new Intent();
                intent.setClass(context, AddMedicineToastActivity.class);
                context.startActivity(intent);
				dia.dismiss();
			}
		});
	
	}

	//显示基于Layout的AlertDialog  
    private void showMedicationPlanDialog() {  
        LayoutInflater inflater = LayoutInflater.from(context);  
        final View view = inflater.inflate(  
                R.layout.medications_plan_dialog, null);  
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);  
        builder.setView(view);  
        
        final ListView listView=(ListView)view.findViewById(R.id.medications_plan_dialog_list);  
        //以时间段查询这个时间段有几条数据
        
        //这里看数据有几条  一条以上需要再次弹出 弹出框显示详细
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				
				//弹出用药详情弹出框
				
			}
		});
        
        
        builder.show();  
    }  


}
