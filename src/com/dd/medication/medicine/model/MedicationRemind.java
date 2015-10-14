package com.dd.medication.medicine.model;

import com.dd.medication.base.model.BaseModel;

/**
 * 用药提醒记录
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
	 * 所设置药品id
	 * */
	private int allProductId;
	/**
	 * 用药对象名 ； 本人 ，爸爸 ，妈妈 ，女儿 ，儿子 ，其他
	 * */
	private String medicationObjectName;
	/**
	 * 用药药品名
	 * */
	private String medicationName;

	/**
	 * 剂型
	 * */
	private String dosageCategory;
	/**
	 * 服用量
	 * */
	private String singleDose;
	/**
	 * 服用单位
	 * */
	private String units;
	/**
	 * 提醒日期 2015-9-1
	 * */
	private String alertDay;
	/**
	 * 提醒时间 16:20
	 * */
	private String alertTime;
	/**
	 * 服用状态 创建时 为 0 进行中 1:正常服用 2：延迟服用 3：未服用。 note： 已完成值为（1，2） 用药失败（3） 用户点击“服用”
	 * 的时间晚于 alertTime为 延迟服用。或非第一次提醒为 延迟服用。 超过3次提醒或“跳过”则为 未服用；
	 * */
	private int status;
	/**
	 * 用户服用时间 16:30
	 * */
	private String closeTime;
	/**
	 * 吃药说明 饭前 饭中 饭后 无
	 * */
	private String takeMedicinetTimeExplain;
	/**
	 * 其它文字说明
	 * */
	private String otherExplain;
	/**
	 * 最后一次修改时间
	 * */
	private String lmodifyDate;
	/**
	 * 1 ：暂停 or 0：正常 暂停该提醒 . 一条提醒建立时 默认为正常： 0
	 * */
	private int stop;
	/**
	 * 便于搜索增加 年份字段
	 * */
	private String year;
	/**
	 * 便于搜索增加 月份字段
	 * */
	private String month;
	/**
	 * 便于搜索增加 年和月份字段
	 * */
	private String yearMonth;
	/**
	 * 便于搜索增加 日期字段
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
