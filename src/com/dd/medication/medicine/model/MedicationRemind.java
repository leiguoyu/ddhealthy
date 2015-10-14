package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

/**
 * ��ҩ���Ѽ�¼
 * **/
public class MedicationRemind extends BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 * */
	private int medicationRemindId;
	/**
	 * ������ҩƷid
	 * */
	private int allProductId;
	/**
	 * ��ҩ������ �� ���� ���ְ� ������ ��Ů�� ������ ������
	 * */
	private String medicationObjectName;
	/**
	 * ��ҩҩƷ��
	 * */
	private String medicationName;

	/**
	 * ����
	 * */
	private String dosageCategory;
	/**
	 * ������
	 * */
	private String singleDose;
	/**
	 * ���õ�λ
	 * */
	private String units;
	/**
	 * �������� 2015-9-1
	 * */
	private String alertDay;
	/**
	 * ����ʱ�� 16:20
	 * */
	private String alertTime;
	/**
	 * ����״̬ ����ʱ Ϊ 0 ������ 1:�������� 2���ӳٷ��� 3��δ���á� note�� �����ֵΪ��1��2�� ��ҩʧ�ܣ�3�� �û���������á�
	 * ��ʱ������ alertTimeΪ �ӳٷ��á���ǵ�һ������Ϊ �ӳٷ��á� ����3�����ѻ���������Ϊ δ���ã�
	 * */
	private int status;
	/**
	 * �û�����ʱ�� 16:30
	 * */
	private String closeTime;
	/**
	 * ��ҩ˵�� ��ǰ ���� ���� ��
	 * */
	private String takeMedicinetTimeExplain;
	/**
	 * ��������˵��
	 * */
	private String otherExplain;
	/**
	 * ���һ���޸�ʱ��
	 * */
	private String lmodifyDate;
	/**
	 * 1 ����ͣ or 0������ ��ͣ������ . һ�����ѽ���ʱ Ĭ��Ϊ������ 0
	 * */
	private int stop;
	/**
	 * ������������ ����ֶ�
	 * */
	private String year;
	/**
	 * ������������ �·��ֶ�
	 * */
	private String month;
	/**
	 * ������������ ����·��ֶ�
	 * */
	private String yearMonth;
	/**
	 * ������������ �����ֶ�
	 * */
	private String date;

	public int getMedicationRemindId() {
		return medicationRemindId;
	}

	public void setMedicationRemindId(int medicationRemindId) {
		this.medicationRemindId = medicationRemindId;
	}

	public int getAllProductId() {
		return allProductId;
	}

	public void setAllProductId(int allProductId) {
		this.allProductId = allProductId;
	}

	public String getMedicationObjectName() {
		return medicationObjectName;
	}

	public void setMedicationObjectName(String medicationObjectName) {
		this.medicationObjectName = medicationObjectName;
	}

	public String getMedicationName() {
		return medicationName;
	}

	public void setMedicationName(String medicationName) {
		this.medicationName = medicationName;
	}

	public String getDosageCategory() {
		return dosageCategory;
	}

	public void setDosageCategory(String dosageCategory) {
		this.dosageCategory = dosageCategory;
	}

	public String getSingleDose() {
		return singleDose;
	}

	public void setSingleDose(String singleDose) {
		this.singleDose = singleDose;
	}

	public String getUnits() {
		return units;
	}

	public void setUnits(String units) {
		this.units = units;
	}

	public String getAlertDay() {
		return alertDay;
	}

	public void setAlertDay(String alertDay) {
		this.alertDay = alertDay;
	}

	public String getAlertTime() {
		return alertTime;
	}

	public void setAlertTime(String alertTime) {
		this.alertTime = alertTime;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getCloseTime() {
		return closeTime;
	}

	public void setCloseTime(String closeTime) {
		this.closeTime = closeTime;
	}

	public String getLmodifyDate() {
		return lmodifyDate;
	}

	public void setLmodifyDate(String lmodifyDate) {
		this.lmodifyDate = lmodifyDate;
	}

	public int getStop() {
		return stop;
	}

	public void setStop(int stop) {
		this.stop = stop;
	}

	public String getTakeMedicinetTimeExplain() {
		return takeMedicinetTimeExplain;
	}

	public void setTakeMedicinetTimeExplain(String takeMedicinetTimeExplain) {
		this.takeMedicinetTimeExplain = takeMedicinetTimeExplain;
	}

	public String getOtherExplain() {
		return otherExplain;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public void setOtherExplain(String otherExplain) {
		this.otherExplain = otherExplain;
	}

	public String getYearMonth() {
		return yearMonth;
	}

	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}

}
