package com.dd.medication.timeline.model;

/**
 * ����Itemʵ����
 * 
 * @author zihao
 * 
 */
public class ChildStatusEntity {
	/* ״̬���� */
	private String statusName;
	/* Ԥ�����ʱ�� */
	private String completeTime;
	/* �Ƿ������ */
	private boolean isfinished; //0Ϊδ��ɣ�1Ϊ�����
	
	public String getStatusName() {
		return statusName;
	}
	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}
	public String getCompleteTime() {
		return completeTime;
	}
	public void setCompleteTime(String completeTime) {
		this.completeTime = completeTime;
	}
	public boolean isIsfinished() {
		return isfinished;
	}
	public void setIsfinished(boolean isfinished) {
		this.isfinished = isfinished;
	}
	

}
