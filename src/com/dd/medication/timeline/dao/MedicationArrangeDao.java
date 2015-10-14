package com.dd.medication.timeline.dao;

import java.util.ArrayList;

import com.dd.medication.base.dao.BaseDao;
import com.dd.medication.medicine.dao.MedicationRemindDao;
import com.dd.medication.medicine.model.MedicationRemind;
import com.dd.medication.timeline.model.MedicationArrangeModel;
import com.dd.medication.timeline.model.YearMonthModel;

public class MedicationArrangeDao extends BaseDao{
	
	public ArrayList<MedicationArrangeModel> getMedicationArrangeByMonth(
			YearMonthModel yearMonthModel) {
		//YearMonthModel只存储了 年月获取当月的所有
		ArrayList<MedicationArrangeModel> list=new ArrayList<MedicationArrangeModel>();
		//以年月获取相应  当月的用药数据
		String yearMonth=yearMonthModel.getYearMonth();
		ArrayList<MedicationRemind> recList=new MedicationRemindDao().getYearMonthMedicationRemind(yearMonth);
		for (int i = 0; i < recList.size(); i++) {
			MedicationRemind medicationRemind=recList.get(i);
			
			MedicationArrangeModel medicationArrange=new MedicationArrangeModel();
			medicationArrange.setDay(medicationRemind.getDate());//展示需要的日期
			medicationArrange.setAlertDay(medicationRemind.getAlertDay());//比较时间需要的当前时间点
//			medicationArrange.setDoneCount(doneCount);//当天已完成服药数
//			medicationArrange.setFailCount(failCount);//当天失败服药数
//			medicationArrange.setHealthRec(healthRec);//当天的全部健康记录
//			
//			medicationArrange.setFuturePrompt("未来用药:");//提示 
//			medicationArrange.setMedicineName(medicineName);//当日用药 全部药品名称
//			medicationArrange.setToastCount(toastCount);//当天设置的提醒次数   
			
		}
		
		return list;
	}

}
