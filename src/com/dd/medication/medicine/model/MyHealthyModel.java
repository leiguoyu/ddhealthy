package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

/**
 * �ҵĽ�����¼����
 * **/
public class MyHealthyModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ���ش洢id
	 * */
	private int myHealthyId;
	/**
	 * �������ڣ���������ĳ��Ľ�����¼
	 * */
	private String localCreateDay;
	/**
	 * ����ʱ��
	 * */
	private String localCreateTime;
	/**
	 * ��λ
	 * */
	private String parts;

	/**
	 * ��λ��֢״ ����ʷ֢״�������ݣ�
	 * */
	private String symptoms;
	/**
	 * ֢״�����س̶ȣ���ʷ֢״�������ݣ�
	 * */
	private String symptomsSeverity;
	/**
	 * �û��Զ����������ݡ�����100�����ڡ�
	 * */
	private String symptomsDetail;
	/**
	 * ��������
	 * */
	private String operateType;
	

	public int getMyHealthyId() {
		return myHealthyId;
	}

	public void setMyHealthyId(int myHealthyId) {
		this.myHealthyId = myHealthyId;
	}

	public String getLocalCreateDay() {
		return localCreateDay;
	}

	public void setLocalCreateDay(String localCreateDay) {
		this.localCreateDay = localCreateDay;
	}
	
	public String getLocalCreateTime() {
		return localCreateTime;
	}

	public void setLocalCreateTime(String localCreateTime) {
		this.localCreateTime = localCreateTime;
	}

	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public String getSymptoms() {
		return symptoms;
	}

	public void setSymptoms(String symptoms) {
		this.symptoms = symptoms;
	}

	public String getSymptomsSeverity() {
		return symptomsSeverity;
	}

	public void setSymptomsSeverity(String symptomsSeverity) {
		this.symptomsSeverity = symptomsSeverity;
	}

	public String getSymptomsDetail() {
		return symptomsDetail;
	}

	public void setSymptomsDetail(String symptomsDetail) {
		this.symptomsDetail = symptomsDetail;
	}

	public String getOperateType() {
		return operateType;
	}

	public void setOperateType(String operateType) {
		this.operateType = operateType;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
