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
		currentYearMonthDate = DateUtil.getYearMonthDate();// ��ȡϵͳ��ǰ������
	}
	
    /**
	 * �ж��Ƿ��ɶ������ɽ���
	 */
	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		return arg0 == arg1;
	}

	/**
	 * ����positionλ�õĽ���
	 */
	@Override
	public void destroyItem(ViewGroup container, int position,
			Object object) {
		container.removeView((View) object);
	}

	/**
	 * ��ʼ��positionλ�õĽ���
	 */
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		ViewHolder viewHolder = new ViewHolder();
		View convertView = inflater.inflate(R.layout.medications_plan_page_item, null);

		//ԭ��ͼ����icon
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
		//����alertDay��ȡ������������ݽ���չʾ��ص�ҩ�� 
		int indexDate = DateUtil.compareYearMonthDate(currentYearMonthDate,
				alertDay);
		if (indexDate == 0) {// ��ʾ����
			viewHolder.time.setVisibility(View.VISIBLE);
			viewHolder.time.setText(DateUtil.getHourMinute());  
			viewHolder.yxtbg.setBackgroundResource(R.drawable.tixing_bg);
		} else if (indexDate == -1) {// ��ʾ����
			viewHolder.time.setVisibility(View.INVISIBLE);
			viewHolder.yxtbg.setBackgroundResource(R.drawable.tixing_bg);
		} else if (indexDate == 1) {// ��ʾ�Ѿ���ȥ
			viewHolder.time.setVisibility(View.INVISIBLE);
			viewHolder.yxtbg.setBackgroundResource(R.drawable.tixing_bg);
		}

		ArrayList<MedicationRemind> recDateList=recDao.getAlertDay(alertDay);
		if(recDateList!=null && recDateList.size()>0){
			for (int i = 0; i < recDateList.size(); i++) {
				//�����ȡ alterTime����ƥ��Ƚ� ��ʾҩ��
				MedicationRemind recDate=recDateList.get(i);
				String alterTime=recDate.getAlertTime();//�ƻ���ҩʱ��
				String closeTime=recDate.getCloseTime();//ʵ�ʳ�ҩʱ��
				//�Ƚ��Ǵ�ʱ��
				if(DateUtil.compareDate(alterTime, "03:00")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon0.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon0.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
						viewHolder.pillIcon0.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "06:00")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon1.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon1.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
						viewHolder.pillIcon1.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "09:00")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon2.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon2.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
						viewHolder.pillIcon2.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "12:00")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon3.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon3.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
						viewHolder.pillIcon3.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "15:00")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon4.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon4.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
						viewHolder.pillIcon4.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "18:00")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon5.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon5.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
						viewHolder.pillIcon5.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "21:00")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon6.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon6.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
						viewHolder.pillIcon6.setBackgroundResource(R.drawable.pill_suc);	
					}
				}else if(DateUtil.compareDate(alterTime, "23:59")){//ʱ��00��00-03��00 �賿
					viewHolder.pillIcon7.setVisibility(View.VISIBLE);
					if(!"".equals(closeTime)){//δ��ɷ�ҩ
						viewHolder.pillIcon7.setBackgroundResource(R.drawable.pill_fase);	
					}else{//����
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
    	 // ����dialog
		// ��ȡLayoutInflaterʵ��
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(context.LAYOUT_INFLATER_SERVICE);
		// �����main��������inflate�м����Ŷ����ǰ����ֱ��this.setContentView()�İɣ��Ǻ�
		// �÷������ص���һ��View�Ķ����ǲ����еĸ�
		View layout = inflater.inflate(R.layout.medicine_notifiction, null);
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setView(layout);
		final AlertDialog dia = builder.create();
		dia.setCanceledOnTouchOutside(true);
		dia.show();
		// ��λ�ȡ����main�еĿؼ��أ�Ҳ�ܼ�
		Button tg = (Button) layout.findViewById(R.id.medicine_notifiction_tg);// ����
		Button ks = (Button) layout.findViewById(R.id.medicine_notifiction_ks);// �˯
		Button fy = (Button) layout.findViewById(R.id.medicine_notifiction_fy);// ����
		Button cxsd = (Button) layout
				.findViewById(R.id.medicine_notifiction_cxsd);// �����趨

		tg.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//������ ȡ������  
				
				dia.dismiss();
			}
		});

		ks.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//�ӳ�ʮ����������  
				
				dia.dismiss();
			}
		});

		fy.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//���ô�ҩƷ
				
				dia.dismiss();
			}
		});

		cxsd.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				//����  ������������
                Intent intent=new Intent();
                intent.setClass(context, AddMedicineToastActivity.class);
                context.startActivity(intent);
				dia.dismiss();
			}
		});
	
	}

	//��ʾ����Layout��AlertDialog  
    private void showMedicationPlanDialog() {  
        LayoutInflater inflater = LayoutInflater.from(context);  
        final View view = inflater.inflate(  
                R.layout.medications_plan_dialog, null);  
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);  
        builder.setView(view);  
        
        final ListView listView=(ListView)view.findViewById(R.id.medications_plan_dialog_list);  
        //��ʱ��β�ѯ���ʱ����м�������
        
        //���￴�����м���  һ��������Ҫ�ٴε��� ��������ʾ��ϸ
        listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				
				
				//������ҩ���鵯����
				
			}
		});
        
        
        builder.show();  
    }  


}
