package com.dd.medication.timeline.model;

import java.util.List;

/**
 * 一级Item实体类
 * 
 * @author zihao
 * 
 */
public class GroupStatusEntity {
	/* 状态名称 */
	private String statusName;
	/* 预计完成时间 */
	private String completeTime;
	/* 二级状态list */
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