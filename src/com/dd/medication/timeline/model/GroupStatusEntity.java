package com.dd.medication.timeline.model;

import java.util.List;

/**
 * һ��Itemʵ����
 * 
 * @author zihao
 * 
 */
public class GroupStatusEntity {
	/* ״̬���� */
	private String statusName;
	/* Ԥ�����ʱ�� */
	private String completeTime;
	/* ����״̬list */
	private List<ChildStatusEntity> twoList;

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

	public List<ChildStatusEntity> getTwoList() {
		return twoList;
	}

	public void setTwoList(List<ChildStatusEntity> twoList) {
		this.twoList = twoList;
	}

}