package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

public class SymptomPartModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5494942991882516094L;
	/**
	 * ���岿λID
	 * */
	private String symptomPartId;
	/**
	 * ���岿λ ������
	 * */
	private String baseParts;
	/**
	 * ���岿λС����
	 * */
	private String parts;
	/**
	 * ���岿λ����Ӧ�Ĳ�֢
	 * */
	private String symptom;
	/**
	 * ���岿λID
	 * */
	private String clinicalSeverity;
	/**
	 * ֢״��Ӧ�����Ա� 1Ů 0 �� ����Ůͨ��
	 * */
	private String sex;

	public String getSymptomPartId() {
		return symptomPartId;
	}

	public void setSymptomPartId(String symptomPartId) {
		this.symptomPartId = symptomPartId;
	}

	public String getBaseParts() {
		return baseParts;
	}

	public void setBaseParts(String baseParts) {
		this.baseParts = baseParts;
	}

	public String getParts() {
		return parts;
	}

	public void setParts(String parts) {
		this.parts = parts;
	}

	public String getSymptom() {
		return symptom;
	}

	public void setSymptom(String symptom) {
		this.symptom = symptom;
	}

	public String getClinicalSeverity() {
		return clinicalSeverity;
	}

	public void setClinicalSeverity(String clinicalSeverity) {
		this.clinicalSeverity = clinicalSeverity;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
