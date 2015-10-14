package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

public class SymptomPartModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5494942991882516094L;
	/**
	 * 身体部位ID
	 * */
	private String symptomPartId;
	/**
	 * 身体部位 大区域
	 * */
	private String baseParts;
	/**
	 * 身体部位小区域
	 * */
	private String parts;
	/**
	 * 身体部位所对应的病症
	 * */
	private String symptom;
	/**
	 * 身体部位ID
	 * */
	private String clinicalSeverity;
	/**
	 * 症状对应分类性别 1女 0 男 空男女通用
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
