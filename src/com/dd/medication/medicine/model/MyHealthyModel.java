package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

/**
 * 我的健康记录数据
 * **/
public class MyHealthyModel extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 本地存储id
	 * */
	private int myHealthyId;
	/**
	 * 创建日期，用于区分某天的健康记录
	 * */
	private String localCreateDay;
	/**
	 * 创建时间
	 * */
	private String localCreateTime;
	/**
	 * 部位
	 * */
	private String parts;

	/**
	 * 部位的症状 （历史症状可用数据）
	 * */
	private String symptoms;
	/**
	 * 症状的严重程度（历史症状可用数据）
	 * */
	private String symptomsSeverity;
	/**
	 * 用户自定义输入内容。限制100字以内。
	 * */
	private String symptomsDetail;
	/**
	 * 操作类型
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
