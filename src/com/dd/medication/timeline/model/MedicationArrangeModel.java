package com.dd.medication.timeline.model;

/**
 * ��ҩ�ƻ� ��Ҫչʾ����Ϣ
 * */
public class MedicationArrangeModel {

	/**
	 * �������� 2015-9-1
	 * */
	private String alertDay;
	/**
	 * ��ҩ���� ֻ��ʾ��25��
	 * */
	private String day;
	/**
	 * �����
	 * */
	private int doneCount;
	/**
	 * ʧ��
	 * */
	private int failCount;
	/**
	 * ������¼
	 * */
	private String healthRec;
	/**
	 * ��ʾ��ǩ δ����ҩ
	 * */
	private String futurePrompt;
	/**
	 * ������ҩ ȫ��ҩƷ����
	 * */
	private String medicineName;
	/**
	 * ���õ����Ѵ���
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
