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
		//YearMonthModelֻ�洢�� ���»�ȡ���µ�����
		ArrayList<MedicationArrangeModel> list=new ArrayList<MedicationArrangeModel>();
		//�����»�ȡ��Ӧ  ���µ���ҩ����
		String yearMonth=yearMonthModel.getYearMonth();
		ArrayList<MedicationRemind> recList=new MedicationRemindDao().getYearMonthMedicationRemind(yearMonth);
		for (int i = 0; i < recList.size(); i++) {
			MedicationRemind medicationRemind=recList.get(i);
			
			MedicationArrangeModel medicationArrange=new MedicationArrangeModel();
			medicationArrange.setDay(medicationRemind.getDate());//չʾ��Ҫ������
			medicationArrange.setAlertDay(medicationRemind.getAlertDay());//�Ƚ�ʱ����Ҫ�ĵ�ǰʱ���
//			medicationArrange.setDoneCount(doneCount);//��������ɷ�ҩ��
//			medicationArrange.setFailCount(failCount);//����ʧ�ܷ�ҩ��
//			medicationArrange.setHealthRec(healthRec);//�����ȫ��������¼
//			
//			medicationArrange.setFuturePrompt("δ����ҩ:");//��ʾ 
//			medicationArrange.setMedicineName(medicineName);//������ҩ ȫ��ҩƷ����
//			medicationArrange.setToastCount(toastCount);//�������õ����Ѵ���   
			
		}
		
		return list;
	}

}
