package com.dd.medication.timeline.model;

/**
 * 用药计划 需要展示的信息
 * */
public class MedicationArrangeModel {

	/**
	 * 提醒日期 2015-9-1
	 * */
	private String alertDay;
	/**
	 * 用药日期 只显示如25号
	 * */
	private String day;
	/**
	 * 已完成
	 * */
	private int doneCount;
	/**
	 * 失败
	 * */
	private int failCount;
	/**
	 * 健康记录
	 * */
	private String healthRec;
	/**
	 * 提示标签 未来用药
	 * */
	private String futurePrompt;
	/**
	 * 当日用药 全部药品名称
	 * */
	private String medicineName;
	/**
	 * 设置的提醒次数
	 * **/
	private String toastCount;

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public int getDoneCount() {
		return doneCount;
	}

	public void setDoneCount(int doneCount) {
		this.doneCount = doneCount;
	}

	public int getFailCount() {
		return failCount;
	}

	public void setFailCount(int failCount) {
		this.failCount = failCount;
	}

	public String getHealthRec() {
		return healthRec;
	}

	public void setHealthRec(String healthRec) {
		this.healthRec = healthRec;
	}

	public String getFuturePrompt() {
		return futurePrompt;
	}

	public void setFuturePrompt(String futurePrompt) {
		this.futurePrompt = futurePrompt;
	}

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		this.medicineName = medicineName;
	}

	public String getToastCount() {
		return toastCount;
	}

	public void setToastCount(String toastCount) {
		this.toastCount = toastCount;
	}

	public String getAlertDay() {
		return alertDay;
	}

	public void setAlertDay(String alertDay) {
		this.alertDay = alertDay;
	}

}
